/*
    java.io.Reader;
        java.io.InputStreamReader; 转换流 (字节输入流 --> 字符输入流)
            java.io.FileReader; 文件字符输入流
*/

import java.io.FileReader;

public class FileReaderTest {
    
    public static void main(String[] args) {
        
        //创建文件字符输入流
        FileReader fr = null;
        try {
            
            fr = new FileReader("FileReaderTest.java");
            
            //读取
            char[] c = new char[512];
            
            int temp = 0;
            
            while((temp = fr.read(c)) != -1) {
                
                //将char数组有效部分转换为String
                System.out.print(new String(c, 0, temp));
                
            }
            
            fr.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
    }
    
}