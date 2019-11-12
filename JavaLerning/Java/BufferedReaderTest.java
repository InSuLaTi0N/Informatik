/*
    BufferedInputStream; 带有缓冲区的字节输入流
    BufferedOutputStream; 带有缓冲区的字节输出流
    BufferedReader; 带有缓冲区的字符输入流
    BufferedWriter; 带有缓冲区的字符输出流
    
    InputStreamReader; 转换流
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.util.Scanner;

public class BufferedReaderTest {
    
    /*
    public static void main(String[] args) {
        
        //创建带有缓冲区的字符输入流
        BufferedReader br = null;
        
        //由于BufferedReader需要读取Reader类型数据, 则需要准备一个Reader对象
        FileReader fr = null;
        
        try {
            
            fr = new FileReader("BufferedReaderTest.java"); //创建一个文件字符输入流
            
            br = new BufferedReader(fr); //将文件字符输入流包装成带有缓冲区的字符输入流
            
            //流可以根据出现的位置分为 包装流(处理流) 和 节点流
            //FileReader fr 是一个节点流
            //BufferedReader br 是一个处理流
            
            //使用readLine读取文件
            System.out.println(br.readLine());
            
            //全部读取
            String temp = null;
            
            while((temp = br.readLine()) != null) {
                
                System.out.println(temp);
                
            }
            
            //关闭流
            br.close();
            fr.close(); //关闭时只需关闭最外层的处理流
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    /*
    //关于转换流使用
    public static void main(String[] args) {
        
        //创建带有缓冲区的字符输入流
        BufferedReader br = null;
        
        //创建文件字节输入流
        FileInputStream fis = null;
        
        try {
            
            fis = new FileInputStream("BufferedReaderTest.java"); //这是文件字节输入流
            
            //需要转换成字符输入流, 才可用于BufferedReader
            
            //需要转换流InputStreamReader
            //InputStreamReader isr = new InputStreamReader(fis);
            
            br = new BufferedReader(new InputStreamReader(fis));
            
            //读取
            String temp = null;
            
            while((temp = br.readLine()) != null) {
                
                System.out.println(temp);
                
            }
            
            //关闭最外层流
            br.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    
    //关于接收键盘输入
    public static void main(String[] args) {
        
        //System.in是一个标准的输入流, 默认接收键盘键入
        
        //使用BufferedReader接收用户输入
        BufferedReader br = null;
        
        try {
            
            br = new BufferedReader(new InputStreamReader(System.in));
            
            //接收输入
            String userInput = br.readLine();
            
            System.out.println(userInput);
            
            //关闭
            br.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}