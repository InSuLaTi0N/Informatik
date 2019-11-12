/*
    BufferedInputStream; ���л��������ֽ�������
    BufferedOutputStream; ���л��������ֽ������
    BufferedReader; ���л��������ַ�������
    BufferedWriter; ���л��������ַ������
    
    InputStreamReader; ת����
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.util.Scanner;

public class BufferedReaderTest {
    
    /*
    public static void main(String[] args) {
        
        //�������л��������ַ�������
        BufferedReader br = null;
        
        //����BufferedReader��Ҫ��ȡReader��������, ����Ҫ׼��һ��Reader����
        FileReader fr = null;
        
        try {
            
            fr = new FileReader("BufferedReaderTest.java"); //����һ���ļ��ַ�������
            
            br = new BufferedReader(fr); //���ļ��ַ���������װ�ɴ��л��������ַ�������
            
            //�����Ը��ݳ��ֵ�λ�÷�Ϊ ��װ��(������) �� �ڵ���
            //FileReader fr ��һ���ڵ���
            //BufferedReader br ��һ��������
            
            //ʹ��readLine��ȡ�ļ�
            System.out.println(br.readLine());
            
            //ȫ����ȡ
            String temp = null;
            
            while((temp = br.readLine()) != null) {
                
                System.out.println(temp);
                
            }
            
            //�ر���
            br.close();
            fr.close(); //�ر�ʱֻ��ر������Ĵ�����
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    /*
    //����ת����ʹ��
    public static void main(String[] args) {
        
        //�������л��������ַ�������
        BufferedReader br = null;
        
        //�����ļ��ֽ�������
        FileInputStream fis = null;
        
        try {
            
            fis = new FileInputStream("BufferedReaderTest.java"); //�����ļ��ֽ�������
            
            //��Ҫת�����ַ�������, �ſ�����BufferedReader
            
            //��Ҫת����InputStreamReader
            //InputStreamReader isr = new InputStreamReader(fis);
            
            br = new BufferedReader(new InputStreamReader(fis));
            
            //��ȡ
            String temp = null;
            
            while((temp = br.readLine()) != null) {
                
                System.out.println(temp);
                
            }
            
            //�ر��������
            br.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    
    //���ڽ��ռ�������
    public static void main(String[] args) {
        
        //System.in��һ����׼��������, Ĭ�Ͻ��ռ��̼���
        
        //ʹ��BufferedReader�����û�����
        BufferedReader br = null;
        
        try {
            
            br = new BufferedReader(new InputStreamReader(System.in));
            
            //��������
            String userInput = br.readLine();
            
            System.out.println(userInput);
            
            //�ر�
            br.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}