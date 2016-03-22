/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hybridhomomorphicencryption;

/**
 *
 * @author kani
 */
import java.lang.*;
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import static java.lang.Math.*;

class Encryption {
    FileInputStream fin;
    FileOutputStream fout = null;
    FileWriter f1,f2,f3;
    String s1,s2;
    private int sk;
       double counter=0;
       
 
       
		
    
    public void Encrypt(BigInteger p,BigInteger g, BigInteger b,BigInteger a,String filepath,String file)
    {
        sk=a.intValue();
         int alert=0;
        try{
            // create file output stream
         //  f3=new FileWriter("D:\\output\\second_encryption.txt");
            f2=new FileWriter("D:\\cloud\\"+file+".txt");
           // f1=new FileWriter("D:\\output\\first_encrytpion.txt");
         //fout = new FileOutputStream("D:\\output of project\\encrypted.txt");
           
         
        Scanner stdin = new Scanner(System.in);
        Random r = new Random();
        
        BigInteger k = new BigInteger(p.bitCount()-1, r);
        fin = new FileInputStream(filepath);
              
        int i=0;
        while((i=fin.read())!=-1){
          // System.out.println("ascii of char is "+ i);
           BigInteger c1 = g.modPow(k, p);
         BigInteger c2 = b.modPow(k, p);
          BigInteger m = BigInteger.valueOf(i);
           c2 = c2.multiply(m);
           c2 = c2.mod(p);
          char ch=(char)i;
           int intc1=c1.intValue();
           int intc2=c2.intValue();
           //f1.write("character =" +ch+"\tits ASCII IS= "+i+"\tc1 value is =\t"+intc1+"\tc2 value is =\t"+intc2+"\n" );
           //f1.flush();
           gentryencrytion(intc1, intc2);
           
           if( intc1 >=100 ||intc2>=100)
           { alert=1;
        //   System.out.println("value goes greather than 100");
           }
           String s1=Integer.toString(intc1);
           String s2=Integer.toString(intc2);
           byte b1[]=s1.getBytes();
           byte b2[]=s2.getBytes();
        //  fout.write(b1);
        //    fout.write(b2);
         // fout.
      // System.out.println("c1 is "+c1+"  c2 is "+c2);
           BigInteger temp = c1.modPow(a,p);
            temp = temp.modInverse(p);
            BigInteger recover = temp.multiply(c2);
            recover = recover.mod(p);
      //System.out.println("orginal value is "+recover);
     
                      
    }
        //System.out.println("count value is  " +counter+ "alert is "+alert);
        
        }
        catch(Exception e){
        System.out.println(e);}
    
}
    public void gentryencrytion(int a, int b)
    {
        s1=Integer.toBinaryString(a);
        s2=Integer.toBinaryString(b);
        switch(s1.length())
        {
            case 1:
                s1="0000000"+s1;
                break;
            case 2:
                s1="000000"+s1;
                break; 
            case 3:
                s1="00000"+s1;
                break;
            case 4:
                s1="0000"+s1;
                break; 
            case 5:
                s1="000"+s1;
                break;
            case 6:
                s1="00"+s1;
                break;
            case 7:
                s1="0"+s1;
                break; 
        }
        switch(s2.length())
        {
            case 1:
                s2="0000000"+s2;
                break;
            case 2:
                s2="000000"+s2;
                break; 
            case 3:
                s2="00000"+s2;
                break;
            case 4:
                s2="0000"+s2;
                break; 
            case 5:
                s2="000"+s2;
                break;
            case 6:
                s2="00"+s2;
                break;
            case 7:
                s2="0"+s2;
                break; 
        }
      //  f1=new FileWriter("D:\\output of project\\first_level_encrytpion.txt");
       
        int gentrypubkey[]=new int[10];int r1=5,r2=3,r3=7,q1=9,q2=11,q3=13,q4=15;
        gentrypubkey[0]=(sk*q1)+2*r1;
        gentrypubkey[1]=(sk*q1)+2*r2;
        gentrypubkey[2]=(sk*q2)+2*r1;
        gentrypubkey[3]=(sk*q2)+2*r2;
        gentrypubkey[4]=(sk*q3)+2*r2;
        gentrypubkey[5]=(sk*q3)+2*r1;
        gentrypubkey[6]=(sk*q4)+2*r1;
        gentrypubkey[7]=(sk*q4)+2*r2;
        gentrypubkey[8]=(sk*q3)+2*r3;
        gentrypubkey[9]=(sk*q4)+2*r3;
        
        
        
        //for(int z=0;z<10;z++)
          //  System.out.println(gentrypubkey[z]);
        String s3=s1+s2;
        char arr[]=s3.toCharArray();
        String s4=""; int genc1;
        
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]=='1')
            {
                double ran=  random();
        int ran2=(int) ((ran*100)%10);
       genc1= gentrypubkey[ran2]+1+(2*r1);
       s4=s4+Integer.toHexString(genc1);
            }
            else
            {
                double ran=  random();
        int ran2=(int) ((ran*100)%10);
       genc1= gentrypubkey[ran2]+0+(2*r1);
       s4=s4+Integer.toHexString(genc1);
            }
            
        }
         try{ 
            // f3.write("c1="+a+"\tc2="+b+"\tbinary c1 "+s1+"\tbinary c2  "+s2+"\n");
           //f3.flush();
           
         } catch(Exception e){ System.out.println(e);}
         try{ f2.write(s4+"\n");
           f2.flush();} catch(Exception e){ System.out.println(e);}
        
        
       
    }
}


class keygeneration {
     private BigInteger p;
     private BigInteger g;
     private BigInteger SECRETKEY;
     private BigInteger b;
     private int pval[]={151,157,163,167,179};
      //private int pval[]={307,311, 313, 317, 331, 337,};
 //private int xval[]={79,81,83,91,53,57};     
private int xval[]={45,47,49,51,53,57};
    public void generatekeys(String file_dir,String file)
    {
         Scanner stdin = new Scanner(System.in);
    
		Random r = new Random();
                double ran=  random();
        int ran2=(int) ((ran*100)%5);
       // ran2=1;
        
        //System.out.println(ran+"      "+ran2);
       //p=pval[ran2];
//		System.out.println("Enter the approximate value of the prime number for your El Gamal key.");
		p = BigInteger.valueOf(pval[ran2]);
		
				g = getGenerator(p, r);
		
				if (g != null) {
		
                   // int sk= stdin.nextInt();
                       SECRETKEY = BigInteger.valueOf(xval[ran2]);
			
			// Calculate the corresponding public b.
			b = g.modPow(SECRETKEY, p);
		
			// Print out our public keys.
                        try{
                   FileWriter f1=new FileWriter(file_dir+file+"_publickey.txt");
			f1.write("p = "+p+" g = "+g+" b = "+b );
                        f1.flush();
                        f1=new FileWriter(file_dir+file+"_secretkey.txt");
                        f1.write(SECRETKEY.toString());
                        f1.flush();
                        } 
                        catch(Exception e){}
                        
                }
                
             else
			System.out.println("Sorry, a generator for your prime couldn't be found.");
    }
    
    public static BigInteger getNextPrime(String ans) {
		
		BigInteger one = new BigInteger("1");
		BigInteger test = new BigInteger(ans);
		while (!test.isProbablePrime(99))
			test = test.add(one);
		return test;		
	}
	
	
	
	public static BigInteger getGenerator(BigInteger p, Random r) {

		int numtries = 0;
		while (numtries < 1000){
                    BigInteger rand = new BigInteger(p.bitCount()-1,r);
    		BigInteger exp = BigInteger.ONE;
    		BigInteger next = rand.mod(p);
                while (!next.equals(BigInteger.ONE)) {
      			next = (next.multiply(rand)).mod(p);
      			exp = exp.add(BigInteger.ONE);
    		}

    		if (exp.equals(p.subtract(BigInteger.ONE)))
      			return rand;
      	}
        return null;
      	
        }
        public BigInteger getp()
        { return p;}
        public BigInteger getg()
        { return g;}
        public BigInteger getb()
        { return b;}
        public BigInteger getSecretkey()
        { return SECRETKEY;}
        
}



class decryption {
    String s1="",s2="";
    FileWriter f1,f2,f3;
    File f;
    String[] paths;
            // create file output stream
            
    public void decrypt(String file)
    {
        Scanner in=new Scanner(System.in);
        
        try{
            int i,file_num;
             f=new File("D:\\cloud");
            paths = f.list();
             i=1;
            System.out.println("FILES IN THE CLOUD");
         for(String path:paths)
         {
            // prints filename and directory name
            System.out.println(i+"  "+path);
            i++;
         }
         System.out.println("Enter the file number to be download");
         file_num=in.nextInt();
            
            System.out.println("Enter the secret key to decrypt the file::");
       int int_s=in.nextInt();
       System.out.println("Enter the p value::");
       int int_p=in.nextInt();
       System.out.println("Enter the download path");
      String dpath=in.next();
       BigInteger secretkey = BigInteger.valueOf(int_s);
       BigInteger p = BigInteger.valueOf(int_p);
       
           // f2=new FileWriter("D:\\output of project\\second_decryption.txt");
            f3=new FileWriter(dpath);
           // f1=new FileWriter("D:\\output of project\\first_decrytpion.txt");
        String substring="";
        int j=0,sk=secretkey.intValue();
        try {
            
            
         // for each name in the path array
           
            BufferedReader br = new BufferedReader(new FileReader("D:\\cloud\\"+paths[file_num-1]));
    String line;
    while ((line = br.readLine()) != null) {
      // process the line.
       i=0;j=0;
        for(i=0;i<48;i=i+3)
        {
            if(j<8){
             substring=line.substring(i,i+3);
            int a=Integer.parseInt(substring,16);
            
            int bit=(a%sk)%2;
            s1=s1+Integer.toString(bit);
            //System.out.print(bit);
            j++;
            }
            
            else
            {
                substring=line.substring(i,i+3);
            int a=Integer.parseInt(substring,16);
            
            int bit=(a%sk)%2;
            //System.out.print(bit);
           s2=s2+Integer.toString(bit);
            j++;}
            //System.out.print(substring);
            if(j==8);
        //        System.out.print("\t\t");
        }
        //System.out.print("\n");
       // System.out.println(s1+"    "+s2);
        BigInteger c1,c2;
        //f1.write("input is ="+line+" binary ="+s1+"   "+s2+"\n");
        //f1.flush();
        int a1=binaryToInteger(s1);
        int a2=binaryToInteger(s2);
        
       // System.out.println("c1 is "+a1+"  c2 is "+a2);
        c1=BigInteger.valueOf(binaryToInteger(s1));
        c2=BigInteger.valueOf(binaryToInteger(s2));
        //f2.write("binary   "+s1+"  "+s2+"\tc1="+c1+"\tc2="+c2+"\n");
        //f2.flush();
         BigInteger temp = c1.modPow(secretkey,p);
            temp = temp.modInverse(p);
            BigInteger recover = temp.multiply(c2);
            recover = recover.mod(p);
            char ch=(char)recover.intValue();
           // System.out.print(ch);
            f3.write(ch);
            f3.flush();
           // String c=Integer.toString(recover.intValue());
            
            
       
        
        
        
        s1="";
        s2="";
        //System.out.println("c1 is "+"  c2 is "+c2);
       
    }
}
        catch(Exception e){}
   
       
        }
        catch(Exception e){System.out.println("exception at decrytion file");}
        }
        
        
    public int binaryToInteger(String binary) {
    char[] numbers = binary.toCharArray();
    int result = 0;
    for(int i=numbers.length - 1; i>=0; i--)
        if(numbers[i]=='1')
            result += Math.pow(2, (numbers.length-i - 1));
    return result;
}
}



public class HybridHomomorphicEncryption {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
            Scanner s=new Scanner(System.in);
            String ch;String file="";
            do{
                System.out.println("HYBRID HOMOMORPHIC ENCRYTPION\n1\tUPLOAD\n2\tDOWNLOAD");
                System.out.println("Enter your option::");
            int option=s.nextInt();
            switch(option)
            {
                case 1: 
                    System.out.print("Enter the file path::");
                    String file_path=s.next();
                    String file_dir=file_path.substring(0,file_path.lastIndexOf("\\")+1);
                    file=file_path.substring(file_path.lastIndexOf("\\")+1, file_path.length()-4);
        keygeneration k=new keygeneration();
        
        k.generatekeys(file_dir,file);
       Encryption E=new Encryption();
        E.Encrypt(k.getp(),k.getg(),k.getb(),k.getSecretkey(),file_path,file);
        
                   break;
                case 2: 
                    //file=s.next();
       decryption d=new decryption();
       
        d.decrypt(null);
        
                    break;                  
            }
            
	System.out.println("Press Y to continue, N to exit::");
      ch= s.next();
     
	
            }while(ch.equals("Y"));	
        }
    
    
}
