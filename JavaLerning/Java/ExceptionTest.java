/*
    �����쳣:
        -�ڳ������й����г��ֵĴ��󱻳�Ϊ�쳣
        
        -java�в�����ķ�ʽģ���쳣
        -����Դ�������, ����:
            NullPointerException e = 0x1234;
            e����������, e�б�����ڴ��ַָ����еĶ���
            ���������NullPointerException����
            �ö����ʾ��ʵ���ڵ��쳣�¼�
            NullPointerException��һ���쳣
            
        -JavaΪ�����ṩ��һ�����Ƶ��쳣�������
        -�������쳣�¼�֮��, Ϊ���������ϸ����Ϣ
        -ͨ�������Ϣ���ԶԳ�����и��ƴ���, ʹ�����������
        
        Object����:
            -Throwable����̳�Object, ���ʾ���е��쳣�����׳�; 
            -��������̳�:
                -Error
                    -Java�������й�������������˴���, �����ǲ��ܴ����, ֻ���˳�JVM, ����: StackOverflowError
                    
                -Exception
                    -Exception�ɴ���, ���û�д����쳣, �����ֱ���˳�JVM
                    
                    -��Ϊ��������:
                        -����ʱ�쳣:
                            -����Exception��ֱ�����඼�Ǳ���ʱ�쳣
                            -�������ʽϸ�
                            -���еı���ʱ�쳣, Ҫ�����Ա�ڱ�д�׶α���������д���, ��������������޷�ͨ��
                            -�����쳣�����ַ�ʽ:
                                -��׽: try...catch, �÷�����:
                                    try
                                    {
                                        ���ܳ����쳣�Ĵ���;
                                    }
                                    catch(�쳣����1 ����)
                                    {
                                        �����쳣�Ĵ���;
                                    }
                                    catch(�쳣����2 ����)
                                    {
                                        �����쳣�Ĵ���;
                                    }
                                    ...
                                    ע: catch����д���, ���Ǳ�����ϵ���, ��С����׽
                                    ���ִ��һ��catch����, ִ�н���������try...catch������
                                -�����׳�: �ڷ���������λ����ʹ��throws�ؼ����׳��쳣
                                
                        -����ʱ�쳣(RuntimeException)
                            -���е�RuntimeException�����඼������ʱ�쳣
                            -�������ʽϵ�
                            -����ʱ�쳣, ����Ա�ڱ�д�׶β���Ҫ�������д���
                            
        -����getMessage()��printStackTrace()����:
            -printStackTrace():
                -��ӡ�쳣��ջ��Ϣ
                -һ��������ø÷�ʽ���Գ���
                
            -getMessage():
                -��ӡ�쳣������Ϣ
                -��õ���Ϣ����
                
        -����finally����:
            -���Ժ�try�������� try...finally...   try...catch...finally...
            -��finally�����еĴ�����һ����ִ�е�
            -����finally����ִ��֮ǰ�˳���JVM, ��finally����ִ��
            
        -�����Զ����쳣:
            -����ʱ�쳣, ֱ�Ӽ̳�Exception
            -����ʱ�쳣, �̳�RuntimeException
*/

import java.io.*;
import java.util.Scanner;

public class ExceptionTest
{
    /*
    //public static void main(String[] args) throws IOException //����ͨ��
    //public static void main(String[] args) throws FileNotFoundException //����ͨ��
    public static void main(String[] args) throws Exception
    {
        
        //�쳣����
        int a = 10;
        int b = 0;
        
        System.out.println(a / b); //java.lang.ArithmeticException �����쳣
        
        System.out.println("Test"); //�����δִ��
        
        //���ϳ������ͨ��, ��������ʱ���������쳣, ��ʾ����ĳ���쳣�¼�
        //����: ����ִ�й����з����������쳣, JVM������һ��ArithmeticException���͵Ķ���
        //���Ҹö����������ϸ���쳣��Ϣ, ��JVM����������е���Ϣ���������̨
        //�������쳣ʱ, ���쳣δ����, �쳣֮�����䲻��ִ��
        
        
        
        //���������׳�
        //�ڷ�������λ��ʹ��throws�ؼ���
        FileInputStream fis = new FileInputStream("D:/a.txt"); //����: δ������쳣����FileNotFoundException; ���������в���������Ա��׳�
        
        //���ڸ�����Java�����ڼ�⵽�п��ܻ����(FileNotFoundException), JavaҪ�������ڱ���ʱ������д���
        //�����Ҫ�ڷ����������׳�FileNotFoundException�쳣(���߸��쳣�ĸ���)
        
    }
    */
    
    /*
    //����throws�÷�
    
    public static void main(String[] args) throws FileNotFoundException
    {
        m1(); //����: δ������쳣����FileNotFoundException; ���������в���������Ա��׳�
        
        System.out.println("Test"); //δ���
    }
    
    public static void m1() throws FileNotFoundException //m1�׳��쳣 ����������m1����
    {
        m2(); //����: δ������쳣����FileNotFoundException; ���������в���������Ա��׳�
    }
    
    public static void m2() throws FileNotFoundException //m2�׳��쳣 ��m1û��, ����ڵ���m2����ʱ����
    {
        m3(); //����: δ������쳣����FileNotFoundException; ���������в���������Ա��׳�
    }
    
    public static void m3() throws FileNotFoundException //�˴��׳��쳣 ��m2����m3����ʱ����
    {
        new FileInputStream("D:/A.txt"); //����: δ������쳣����FileNotFoundException; ���������в���������Ա��׳�
    }
    */
    
    /*
        ʹ��throws���������쳣�����������Ĵ����쳣
        ͨ�׵�˵������ж����
        �쳣��û�д���
        �������ʱ�����´���:
            java.io.FileNotFoundException: D:\A.txt (ϵͳ�Ҳ���ָ�����ļ���)
            at java.base/java.io.FileInputStream.open0(Native Method)
            at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
            at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
            at java.base/java.io.FileInputStream.<init>(FileInputStream.java:112)
            at ExceptionTest.m3(ExceptionTest.java:95)
            at ExceptionTest.m2(ExceptionTest.java:90)
            at ExceptionTest.m1(ExceptionTest.java:85)
            at ExceptionTest.main(ExceptionTest.java:80)
        �ó������й����з�����FileNotFoundException�쳣
        JVM������һ��FileNotFoundException���͵Ķ���
        �ö������������Ϣ
        JVM���𽫸ö������Ϣ��ӡ�ڿ���̨
        ��JVM�˳����� Testδ��ӡ�ڿ���̨
    */
    
    /*
    //���ڲ�׽�쳣
    //ʹ��try...catch����
    
    public static void main(String[] args)
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/A.txt"); //����: δ������쳣����FileNotFoundException; ���������в���������Ա��׳�
        }
        catch(ArithmeticException e) //�˴�������������쳣(ArithmeticException), ��δ�Կ��ܳ��ֵ�FileNotFoundException�쳣���д���
        {
            System.out.println("Test");
        }
        catch(FileNotFoundException e) //�˴��������FileNotFoundException, �Ѷ��쳣���д���, ����ͨ��, ����Test Test
        {
            System.out.println("Test Test");
        }
        
        m1();
        m2();
        m3();
        m4();
    }
    
    public static void m1()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/a.txt");
            fis.read(); //����: δ������쳣����IOException; ���������в���������Ա��׳�
            //���ϱ����õķ����׳�����IOException, ��FileNotFoundException�ĸ���
            //���б���쳣δ������
        }
        catch(FileNotFoundException e)
        {
            System.out.println("m1 Test");
        }
        catch(IOException e) //����IO�쳣
        {
            System.out.println("Not Success");
        }
    }
    
    public static void m2()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/a.txt");
            fis.read();
        }
        catch(IOException e) //����IO�쳣 �����FileNotFoundException���д���
        {
            System.out.println("Not Success"); //����ͨ��
        }
        
        //����IOException��FileNotFoundException�ĸ���, ��˲���IO�쳣����������IO�����쳣
    }
    
    public static void m3()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/a.txt");
            fis.read();
        }
        catch(IOException e)
        {
            System.out.println("m3 Test");
        }
        //catch(FileNotFoundException e) //����: �Ѳ����쳣����FileNotFoundException
        //{
            //System.out.println("Not Success");
        //}
        
        //����IOException����FileNotFoundException, ���FileNotFoundException��Զ����ִ��
    }
    
    public static void m4()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/a.txt");
            fis.read();
            
            System.out.println("Test"); //�ó���δִ��
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Not Success"); //����ͨ��
        }
        catch(IOException e)
        {
            System.out.println("failed"); //δִ��
        }
        
        System.out.println("finished");
        
        //����try�����е�һ�з����쳣, ���try����֮��Ĵ��벻��ִ��
        //����catch����ִ��, ���ڵ�һ��catch�Ѿ��������쳣, ���ִ�е�һ��catch����
        //try...catch����ִ�н���, ���������һ��catch����
    }
    */
    
    /*
    //����getMessage��printStackTrace
    
    public static void main(String[] args)
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/A.txt");
        }
        catch(FileNotFoundException e)
        {
            //����printStackTrace����
            e.printStackTrace();
            
            //������³���:
            //java.io.FileNotFoundException: C:\A.txt (ϵͳ�Ҳ���ָ�����ļ���)
                //at java.base/java.io.FileInputStream.open0(Native Method)
                //at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
                //at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
                //at java.base/java.io.FileInputStream.<init>(FileInputStream.java:112)
                //at ExceptionTest.main(ExceptionTest.java:253)
        }
        
        m1();
        
        System.out.println("main finished"); //�����ִ��
    }
    
    public static void m1()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/A.txt");
        }
        catch(FileNotFoundException e)
        {
            //ʹ��getMessage()����
            String msg = e.getMessage();
            
            System.out.println(msg); //C:\A.txt (ϵͳ�Ҳ���ָ�����ļ���)
            //�÷��������printStackTrace����ϸ
        }
        
        System.out.println("m1 finished"); //�����ִ��
    }
    */
    
    /*
    //����finally
    
    public static void main(String[] args)
    {   
        try
        {
            System.out.println("try"); //�����ִ��
            
            //return;
        }
        finally
        {
            System.out.println("finally Test"); //�����ִ��
        }
        
        //m1();
        
        //int i = m2();
        //System.out.println(i); //10
        
        m3();
    }
    
    public static void m1()
    {
        try
        {
            //�˳�JVM
            System.exit(0);
        }
        finally
        {
            System.out.println("finally test"); //��ִ��
        }
    }
    
    public static int m2()
    {
        int i = 10;
        
        try
        {
            return i; //��try�����и���ʱ����, �˴�return������ʱ�������ظ��˵��õ�ֵ
        }
        finally
        {
            i++;
            System.out.println("finally--->i is " + i); //11
        }
    }
    
    public static void m3()
    {
        FileInputStream fis = null;
        
        try
        {
            fis = new FileInputStream("ExceptionTest.java");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
            finally
        {
            //�ͷ���Դ
            if(fis != null)
            {
               try
               {
                   fis.close();
               }
               catch(IOException e)
               {
                   e.printStackTrace();
               }
            }
        }
    }
    */
    
    //�����Զ����쳣����
    
    public static void main(String[] args)
    {
        //�û��ṩ���û�������
        System.out.print("�������û���: ");
        Scanner s = new Scanner(System.in);
        String username = s.next();
        
        //ע��
        CustomerService cs = new CustomerService();
        
        //�����쳣
        try
        {
            cs.register(username);
        }
        catch(IllegalNameException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

//�����Զ����쳣
//�����û��������쳣
/* public */ class IllegalNameException extends Exception //����ʱ�쳣
// public class IllegalNameException extends RuntimeException //����ʱ�쳣
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // �����쳣
    //һ���ṩ�������췽��
    public IllegalNameException(){}
    
    public IllegalNameException(String msg)
    {
        super(msg);
    }
}

class CustomerService
{
    //����˿����
    //�ṩע�᷽��
    public void register(String name) throws IllegalNameException
    {
        if(name.length() < 6)
        {
            //�����쳣
            //�����쳣����
            //IllegalNameException e = new IllegalNameException("�û�����������6λ");
            
            //�ֶ��׳��쳣
            //throw e;
            
            throw new IllegalNameException("�û�����������6λ");
        }
        
        //���뵽�˴�һ���ǺϷ��û���
        System.out.println("ע��ɹ�");
    }
}