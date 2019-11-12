/*
    关于异常:
        -在程序运行过程中出现的错误被称为异常
        
        -java中采用类的方式模拟异常
        -类可以创建对象, 例如:
            NullPointerException e = 0x1234;
            e是引用类型, e中保存的内存地址指向堆中的对象
            这个对象是NullPointerException类型
            该对象表示真实存在的异常事件
            NullPointerException是一类异常
            
        -Java为人们提供了一种完善的异常处理机制
        -程序发生异常事件之后, 为我们输出详细的信息
        -通过这个信息可以对程序进行改善处理, 使程序更加完善
        
        Object父类:
            -Throwable子类继承Object, 其表示所有的异常均可抛出; 
            -被以下类继承:
                -Error
                    -Java程序运行过程中如果出现了错误, 错误是不能处理的, 只能退出JVM, 例如: StackOverflowError
                    
                -Exception
                    -Exception可处理, 如果没有处理异常, 则程序直接退出JVM
                    
                    -分为以下两类:
                        -编译时异常:
                            -所有Exception的直接子类都是编译时异常
                            -发生几率较高
                            -所有的编译时异常, 要求程序员在编写阶段必须对它进行处理, 若不处理则编译无法通过
                            -处理异常有两种方式:
                                -捕捉: try...catch, 用法如下:
                                    try
                                    {
                                        可能出现异常的代码;
                                    }
                                    catch(异常类型1 变量)
                                    {
                                        处理异常的代码;
                                    }
                                    catch(异常类型2 变量)
                                    {
                                        处理异常的代码;
                                    }
                                    ...
                                    注: catch可以写多个, 但是必须从上到下, 从小到大捕捉
                                    最多执行一个catch语句块, 执行结束后整个try...catch语句结束
                                -声明抛出: 在方法声明的位置上使用throws关键字抛出异常
                                
                        -运行时异常(RuntimeException)
                            -所有的RuntimeException的子类都是运行时异常
                            -发生几率较低
                            -运行时异常, 程序员在编写阶段不需要对它进行处理
                            
        -关于getMessage()和printStackTrace()方法:
            -printStackTrace():
                -打印异常堆栈信息
                -一般情况下用该方式调试程序
                
            -getMessage():
                -打印异常描述信息
                -获得的信息较少
                
        -关于finally语句块:
            -可以和try语句块联用 try...finally...   try...catch...finally...
            -在finally语句块中的代码是一定会执行的
            -若在finally语句块执行之前退出了JVM, 则finally不会执行
            
        -关于自定义异常:
            -编译时异常, 直接继承Exception
            -运行时异常, 继承RuntimeException
*/

import java.io.*;
import java.util.Scanner;

public class ExceptionTest
{
    /*
    //public static void main(String[] args) throws IOException //编译通过
    //public static void main(String[] args) throws FileNotFoundException //编译通过
    public static void main(String[] args) throws Exception
    {
        
        //异常举例
        int a = 10;
        int b = 0;
        
        System.out.println(a / b); //java.lang.ArithmeticException 算术异常
        
        System.out.println("Test"); //该语句未执行
        
        //以上程序编译通过, 但是运行时发生上述异常, 表示发生某个异常事件
        //本质: 程序执行过程中发生了算术异常, JVM创建了一个ArithmeticException类型的对象
        //并且该对象包含了详细的异常信息, 且JVM将这个对象中的信息输出到控制台
        //当发生异常时, 若异常未处理, 异常之后的语句不会执行
        
        
        
        //关于声明抛出
        //在方法声明位置使用throws关键字
        FileInputStream fis = new FileInputStream("D:/a.txt"); //错误: 未报告的异常错误FileNotFoundException; 必须对其进行捕获或声明以便抛出
        
        //由于该类在Java编译期检测到有可能会出错(FileNotFoundException), Java要求我们在编译时对其进行处理
        //因此需要在方法声明处抛出FileNotFoundException异常(或者该异常的父类)
        
    }
    */
    
    /*
    //关于throws用法
    
    public static void main(String[] args) throws FileNotFoundException
    {
        m1(); //错误: 未报告的异常错误FileNotFoundException; 必须对其进行捕获或声明以便抛出
        
        System.out.println("Test"); //未输出
    }
    
    public static void m1() throws FileNotFoundException //m1抛出异常 主方法调用m1报错
    {
        m2(); //错误: 未报告的异常错误FileNotFoundException; 必须对其进行捕获或声明以便抛出
    }
    
    public static void m2() throws FileNotFoundException //m2抛出异常 但m1没有, 因此在调用m2方法时报错
    {
        m3(); //错误: 未报告的异常错误FileNotFoundException; 必须对其进行捕获或声明以便抛出
    }
    
    public static void m3() throws FileNotFoundException //此处抛出异常 在m2调用m3方法时报错
    {
        new FileInputStream("D:/A.txt"); //错误: 未报告的异常错误FileNotFoundException; 必须对其进行捕获或声明以便抛出
    }
    */
    
    /*
        使用throws方法处理异常并不是真正的处理异常
        通俗地说属于推卸责任
        异常并没有处理
        因此运行时报如下错误:
            java.io.FileNotFoundException: D:\A.txt (系统找不到指定的文件。)
            at java.base/java.io.FileInputStream.open0(Native Method)
            at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
            at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
            at java.base/java.io.FileInputStream.<init>(FileInputStream.java:112)
            at ExceptionTest.m3(ExceptionTest.java:95)
            at ExceptionTest.m2(ExceptionTest.java:90)
            at ExceptionTest.m1(ExceptionTest.java:85)
            at ExceptionTest.main(ExceptionTest.java:80)
        该程序运行过程中发生了FileNotFoundException异常
        JVM创建了一个FileNotFoundException类型的对象
        该对象包含以上信息
        JVM负责将该对象的信息打印在控制台
        且JVM退出工作 Test未打印在控制台
    */
    
    /*
    //关于捕捉异常
    //使用try...catch方法
    
    public static void main(String[] args)
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/A.txt"); //错误: 未报告的异常错误FileNotFoundException; 必须对其进行捕获或声明以便抛出
        }
        catch(ArithmeticException e) //此处捕获的是算术异常(ArithmeticException), 并未对可能出现的FileNotFoundException异常进行处理
        {
            System.out.println("Test");
        }
        catch(FileNotFoundException e) //此处捕获的是FileNotFoundException, 已对异常进行处理, 编译通过, 运行Test Test
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
            fis.read(); //错误: 未报告的异常错误IOException; 必须对其进行捕获或声明以便抛出
            //以上被调用的方法抛出的是IOException, 是FileNotFoundException的父类
            //还有别的异常未被处理
        }
        catch(FileNotFoundException e)
        {
            System.out.println("m1 Test");
        }
        catch(IOException e) //处理IO异常
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
        catch(IOException e) //处理IO异常 不针对FileNotFoundException进行处理
        {
            System.out.println("Not Success"); //编译通过
        }
        
        //由于IOException是FileNotFoundException的父类, 因此捕获IO异常即捕获所有IO子类异常
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
        //catch(FileNotFoundException e) //错误: 已捕获到异常错误FileNotFoundException
        //{
            //System.out.println("Not Success");
        //}
        
        //由于IOException包含FileNotFoundException, 因此FileNotFoundException永远不会执行
    }
    
    public static void m4()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/a.txt");
            fis.read();
            
            System.out.println("Test"); //该程序未执行
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Not Success"); //编译通过
        }
        catch(IOException e)
        {
            System.out.println("failed"); //未执行
        }
        
        System.out.println("finished");
        
        //由于try语句块中第一行发生异常, 因此try语句块之后的代码不会执行
        //进入catch语句块执行, 由于第一个catch已经捕获到了异常, 因此执行第一个catch语句块
        //try...catch语句块执行结束, 不会进入下一个catch语句块
    }
    */
    
    /*
    //关于getMessage和printStackTrace
    
    public static void main(String[] args)
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/A.txt");
        }
        catch(FileNotFoundException e)
        {
            //调用printStackTrace方法
            e.printStackTrace();
            
            //输出以下程序:
            //java.io.FileNotFoundException: C:\A.txt (系统找不到指定的文件。)
                //at java.base/java.io.FileInputStream.open0(Native Method)
                //at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
                //at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
                //at java.base/java.io.FileInputStream.<init>(FileInputStream.java:112)
                //at ExceptionTest.main(ExceptionTest.java:253)
        }
        
        m1();
        
        System.out.println("main finished"); //该语句执行
    }
    
    public static void m1()
    {
        try
        {
            FileInputStream fis = new FileInputStream("C:/A.txt");
        }
        catch(FileNotFoundException e)
        {
            //使用getMessage()方法
            String msg = e.getMessage();
            
            System.out.println(msg); //C:\A.txt (系统找不到指定的文件。)
            //该方法相比于printStackTrace不详细
        }
        
        System.out.println("m1 finished"); //该语句执行
    }
    */
    
    /*
    //关于finally
    
    public static void main(String[] args)
    {   
        try
        {
            System.out.println("try"); //该语句执行
            
            //return;
        }
        finally
        {
            System.out.println("finally Test"); //该语句执行
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
            //退出JVM
            System.exit(0);
        }
        finally
        {
            System.out.println("finally test"); //不执行
        }
    }
    
    public static int m2()
    {
        int i = 10;
        
        try
        {
            return i; //在try语句块有个临时变量, 此处return的是临时变量返回给了调用的值
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
            //释放资源
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
    
    //关于自定义异常测试
    
    public static void main(String[] args)
    {
        //用户提供的用户名如下
        System.out.print("请输入用户名: ");
        Scanner s = new Scanner(System.in);
        String username = s.next();
        
        //注册
        CustomerService cs = new CustomerService();
        
        //处理异常
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

//关于自定义异常
//例如用户名输入异常
/* public */ class IllegalNameException extends Exception //编译时异常
// public class IllegalNameException extends RuntimeException //运行时异常
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 定义异常
    //一般提供两个构造方法
    public IllegalNameException(){}
    
    public IllegalNameException(String msg)
    {
        super(msg);
    }
}

class CustomerService
{
    //定义顾客相关
    //提供注册方法
    public void register(String name) throws IllegalNameException
    {
        if(name.length() < 6)
        {
            //出现异常
            //创建异常对象
            //IllegalNameException e = new IllegalNameException("用户名不能少于6位");
            
            //手动抛出异常
            //throw e;
            
            throw new IllegalNameException("用户名不能少于6位");
        }
        
        //代码到此处一定是合法用户名
        System.out.println("注册成功");
    }
}