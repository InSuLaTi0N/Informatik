/*
    关于File
    java.io.File;
        -和流无关, 无法进行文件读写
        -是文件和目录路径名的抽象表示形式

        -File代表硬盘的Directory和file
*/

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {

    /*
    public static void main(String[] args) {

        File f = null;

        try {
            
            f = new File("FileTest.java"); //相对路径
            System.out.println(f.exists() ? "存在该文件" : "不存在该文件"); //返回true

            f = new File("D:/SoftWareTechnologie/Java/FileTest.java"); //绝对路径
            System.out.println(f.exists() ? "存在该文件" : "不存在该文件"); //返回true

            f = new File("D:/SoftWareTechnologie/Java"); //目录
            System.out.println(f.exists() ? "存在该目录" : "不存在该目录"); //返回true

            f = new File("D:/Software"); //目录
            System.out.println(f.exists() ? "存在该目录" : "不存在该目录");

            //若不存在则以目录形式创建
            f = new File("D:/SoftWareTechnologie/Java/FileExample");
            if(!f.exists()) f.mkdir(); //创建目录

            //创建文件
            f = new File("D:/SoftWareTechnologie/Java/FileExample.txt");
            if(!f.exists()) f.createNewFile();

            //创建多个目录
            f = new File("D:/SoftWareTechnologie/Java/FileExample/java/io/File");
            if(!f.exists()) f.mkdirs(); //创建多重目录
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    /*
    public static void main(String[] args) {

        //创建对象
        File f = null;

        try {
            
            f = new File("FileTest.java");
            
            //获取绝对路径
            System.out.println(f.getAbsolutePath()); //D:\SoftWareTechnologie\Java\FileTest.java

            //获取父路径
            System.out.println(f.getParent()); //null

            //判断File是文件或目录
            System.out.println(f.isDirectory()); //false
            System.out.println(f.isFile()); //true


            f = new File("D:/SoftWareTechnologie/Java");

            //获取父路径
            System.out.println(f.getParent()); //D:\SoftWareTechnologie

            //判断File是文件或目录
            System.out.println(f.isDirectory()); //true
            System.out.println(f.isFile()); //false

            //获取文件最后一次修改时间
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(f.lastModified()))); //2019-11-12 11:38:18 277

            //获取文件长度
            System.out.println(f.length()); //12288(字节)

            //列出子文件或子目录
            for(File file : f.listFiles()) {

                System.out.println(file.getAbsolutePath());
            }

            System.out.println("-------------------------------------------------");

            //列出以.class结尾的文件
            for(File file : f.listFiles()) {

                if(file.getAbsolutePath().endsWith(".class")) System.out.println(file.getAbsolutePath());
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    //使用递归找出某目录下的所有子目录及子文件
    public static void main(String[] args) {

        //创建对象
        File f = null;

        try {
            
            f = new File("D:/SoftWareTechnologie"); //找出SWT下所有子目录与子文件

            searchAll(f);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public static void searchAll(File file) {

        if(file.isFile()) return;

        for(File direct : file.listFiles()) {

            System.out.println(direct.getAbsolutePath());

            searchAll(direct);
        }
    }
}