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
        
        //�������л��������ַ������
        BufferedWriter bw = null;
        
        try {
            
            bw = new BufferedWriter(new FileWriter("TEST.txt"));
            
            //д��
            bw.write("SoftwareTechnologie");
            
            //�����зָ���
            bw.newLine();
            
            bw.write("java");
            
            //ˢ��
            bw.flush();
            
            //�ر���
            bw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    
    //ʹ��BufferedReader��BufferedWriter��ɸ���
    public static void main(String[] args) {
        
        //�������л��������ַ��������������
        BufferedReader br = null;
        BufferedWriter bw = null;
        
        try {
            
            br = new BufferedReader(new FileReader("BufferedWriterTest.java"));
            bw = new BufferedWriter(new FileWriter("C:/Users/Bai Song/Desktop/BufferedWriterTest.java"));
            
            //����
            String temp = null;
            
            while((temp = br.readLine()) != null) {
                
                bw.write(temp); //д��һ��
                
                bw.newLine(); //����
                
            }
            
            //ˢ��
            bw.flush();
            
            //�ر���
            br.close();
            bw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}