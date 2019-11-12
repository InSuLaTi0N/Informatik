/*
    java.io.PrintStream; 标准的输出流, 默认打印至控制台, 以字节方式
*/

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.Date;

public class PrintStreamTest {

    /*
    public static void main(String[] args) {

        try {
            //标准输出
            //默认输出至控制台
            System.out.println("Hello");

            PrintStream ps = System.out;

            //输出
            ps.println("World");

            //可以改变输出方向
            System.setOut(new PrintStream(new FileOutputStream("D:/SoftWareTechnologie/Java/log.txt"))); //通常使用这种方式记录日志

            //再次输出
            System.out.println("Hello World!");

            //关闭
            ps.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    //关于记录m1方法执行的日志
    public static void main(String[] args) {

        //创建日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

        try {
            
            System.setOut(new PrintStream(new FileOutputStream("D:/SoftWareTechnologie/Java/log.txt"))); //将输出记录至log文件

            //记录m1执行
            System.out.println("m1 started at " + sdf.format(new Date()));

            m1();

            System.out.println("m1 ended at " + sdf.format(new Date()));

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }


    public static void m1() {

        System.out.println("m1 method excute");
    }
}