/*
    java.io.Writer;
        java.io.OutputStreamWriter; 转换流 (字节输出流 --> 字符输出流)
            java.io.FileWriter; 文件字符输出流
*/

import java.io.FileWriter;
import java.io.FileReader;

public class FileWriterTest {
    
    /*
    public static void main(String[] args) {
        
        //创建文件字符输出流
        FileWriter fw = null;
        
        try {
            
            fw = new FileWriter("TEST.txt");
            
            //写入
            //fw.write("FileWriterTest"); //覆盖
            fw.write("FileWriterTest", true);
            
            //将char数组一部分写入
            char[] c = {'A', 'B', 'C', 'D', 'E'};
            
            fw.write(c, 0, 3);
            
            //刷新
            fw.flush();
            
            //关闭
            fw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    
    //关于纯文本文件复制
    public static void main(String[] args) {
        
        //创建文件字符输入流和输出流
        FileReader fr = null;
        FileWriter fw = null;
        
        try {
            
            fr = new FileReader("FileWriterTest.java");
            fw = new FileWriter("C:/Users/Bai Song/Desktop/FileWriterTest.java");
            
            //进行读写工作
            char[] c = new char[512];
            
            int temp = 0;
            
            while((temp = fr.read(c)) != -1) {
                
                fw.write(c, 0, temp); //有效写入
                
            }
            
            //刷新
            fw.flush();
            
            //关闭流
            fr.close();
            fw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}