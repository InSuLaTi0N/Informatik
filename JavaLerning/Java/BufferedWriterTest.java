/*
    BufferedWriter;
*/

import java.io.BufferedWriter;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.FileReader;

public class BufferedWriterTest {
    
    /*
    public static void main(String[] args) {
        
        //创建带有缓冲区的字符输出流
        BufferedWriter bw = null;
        
        try {
            
            bw = new BufferedWriter(new FileWriter("TEST.txt"));
            
            //写入
            bw.write("SoftwareTechnologie");
            
            //加入行分隔符
            bw.newLine();
            
            bw.write("java");
            
            //刷新
            bw.flush();
            
            //关闭流
            bw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    
    //使用BufferedReader和BufferedWriter完成复制
    public static void main(String[] args) {
        
        //创建带有缓冲区的字符输入流和输出流
        BufferedReader br = null;
        BufferedWriter bw = null;
        
        try {
            
            br = new BufferedReader(new FileReader("BufferedWriterTest.java"));
            bw = new BufferedWriter(new FileWriter("C:/Users/Bai Song/Desktop/BufferedWriterTest.java"));
            
            //复制
            String temp = null;
            
            while((temp = br.readLine()) != null) {
                
                bw.write(temp); //写入一行
                
                bw.newLine(); //换行
                
            }
            
            //刷新
            bw.flush();
            
            //关闭流
            br.close();
            bw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}