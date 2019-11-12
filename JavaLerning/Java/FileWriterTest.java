/*
    java.io.Writer;
        java.io.OutputStreamWriter; ת���� (�ֽ������ --> �ַ������)
            java.io.FileWriter; �ļ��ַ������
*/

import java.io.FileWriter;
import java.io.FileReader;

public class FileWriterTest {
    
    /*
    public static void main(String[] args) {
        
        //�����ļ��ַ������
        FileWriter fw = null;
        
        try {
            
            fw = new FileWriter("TEST.txt");
            
            //д��
            //fw.write("FileWriterTest"); //����
            fw.write("FileWriterTest", true);
            
            //��char����һ����д��
            char[] c = {'A', 'B', 'C', 'D', 'E'};
            
            fw.write(c, 0, 3);
            
            //ˢ��
            fw.flush();
            
            //�ر�
            fw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    */
    
    //���ڴ��ı��ļ�����
    public static void main(String[] args) {
        
        //�����ļ��ַ��������������
        FileReader fr = null;
        FileWriter fw = null;
        
        try {
            
            fr = new FileReader("FileWriterTest.java");
            fw = new FileWriter("C:/Users/Bai Song/Desktop/FileWriterTest.java");
            
            //���ж�д����
            char[] c = new char[512];
            
            int temp = 0;
            
            while((temp = fr.read(c)) != -1) {
                
                fw.write(c, 0, temp); //��Чд��
                
            }
            
            //ˢ��
            fw.flush();
            
            //�ر���
            fr.close();
            fw.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}