/*
    -FileInputStream
        -java.io.FileInputStream; 文件字节输入流
        -按照字节方式读取文件
        
        -主要方法:
            void    close() 释放文件
            int    read()   读取文件
            int    read(byte[] b)
*/

import java.io.FileInputStream;

public class FileInputStreamTest {
    
    /*
    //关于读取文件
    public static void main(String[] args) {
        
        //读取文件前先创建输入流
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("TEST.txt");

            //读取文件
            
            //for(int i = 0; i < 6; i++)
            //{
            //    System.out.println(fis.read()); //以字节方式读取
            //    // 97 98 99 100 101 -1
            //    //如果已经读取到文件的末尾, 就会返回-1
            //}
            
            
            //while读取文件
            int temp = 0;
            
            while((temp = fis.read()) != -1) {
                System.out.print(temp + " ");
            }
            
            
            //以上程序频繁访问磁盘, 伤害磁盘, 效率低下

            //释放文件
            fis.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {
            
            e.printStackTrace();

        }
    }
    */
    /*
    //关于int read(byte[] b);
    //读取之前在内存中准备一个byte数组, 每次读取多个字节存储到byte数组中
    //read()是单字节读取
    
    public static void main(String[] args) {
        
        //创建输入流
        FileInputStream fis = null;
        
        try {
            
            //读取文件
            fis = new FileInputStream("TEST.txt");
            
            byte[] b = new byte[3]; //每次最多读取3字节
            
            //该方法返回的是每次读取的字节数
            //到达文件末尾则返回-1
            
            for(int i = 0; i < 3; i++) {
                
                System.out.println(fis.read(b)); //3 2 -1
                //第二次读取时依次覆盖
                
                //将byte数组转换为字符串
                System.out.println(new String(b)); //abc dec dec
                
            }
            //释放文件
            fis.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
    }
    */
    /*
    //关于循环读取
    public static void main(String[] args) {
        
        FileInputStream fis = null;
        try {
            
            fis = new FileInputStream("FileInputStreamTest.java");
            
            byte[] b = new byte[1024]; //每次读取1kb
            
            int temp = 0;
            
            while((temp = fis.read(b)) != -1) {
                
                //将byte数组中有效数据转换为字符串
                System.out.print(new String(b, 0, temp));
            }
            
            fis.close();
        }catch(Exception e) {
            
            e.printStackTrace();
        }
        
    }
    */
    /*
    //关于available方法
    //int available(); 返回流中剩余的估计字节数
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream("D:/SoftWareTechnologie/Java/TEST.txt");

            System.out.println(fis.available()); //5

            System.out.println(fis.read()); //97

            System.out.println(fis.available()); //4

            fis.close();
        } catch (Exception e) {

            e.printStackTrace();
        
        }

    }
    */

    //关于skip方法
    //long skip(long n); 跳过n个字节
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {

            fis = new FileInputStream("D:/SoftWareTechnologie/Java/TEST.txt");

            System.out.println(fis.read()); //97

            //跳跃1个字节
            fis.skip(1);

            System.out.println(fis.read()); //99

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}