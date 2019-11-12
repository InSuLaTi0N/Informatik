/*
    java.io.OutputStream;
        java.io.FileOutputStream; 文件字节输出流

        -将计算机内存中的数据写入硬盘文件中
*/

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutputStreamTest {
    
    /*
    //关于文件输出(写入)
    public static void main(String[] args) {

        //创建文件字节输出流
        FileOutputStream fos = null;
        FileOutputStream fos1 = null;

        try {
            fos = new FileOutputStream("D:/SoftWareTechnologie/Java/TEST.txt"); //该文件不存在则自动创建

            //写入
            String msg = "fgh jkl";

            //将String转换为byte数组
            byte[] b = msg.getBytes();

            //将byte数组中全部数据写入
            fos.write(b); //会覆盖之前的数据, 需谨慎使用

            //将byte数组一部分写入
            fos.write(b, 0, 3); //fgh jklfgh

            //以追加方式写入
            fos1 = new FileOutputStream("D:/SoftWareTechnologie/Java/TEST1.txt", true);

            fos1.write(b); //可以看到文件TEST1中以追加方式写入, 原数据仍保留

            //为了保证数据完全写入硬盘, 需要刷新
            fos.flush(); //强制写入

            fos.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    */

    //关于复制文件
    public static void main(String[] args) {

        //创建输入输出流
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {

            //进行读写工作
            fis = new FileInputStream("D:/SoftWareTechnologie/Java/HelloWorld.java");
            fos = new FileOutputStream("C:/Users/Bai Song/Desktop/HelloWorld.java");

            byte[] b = new byte[1024]; //每次读取1kb

            int temp = 0;

            while((temp = fis.read(b)) != -1) {

                fos.write(b, 0, temp);
            }

            //刷新
            fos.flush();

            //关闭
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}