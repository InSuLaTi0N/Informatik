/*
    java.io.Reader;
        java.io.InputStreamReader; ת���� (�ֽ������� --> �ַ�������)
            java.io.FileReader; �ļ��ַ�������
*/

import java.io.FileReader;

public class FileReaderTest {
    
    public static void main(String[] args) {
        
        //�����ļ��ַ�������
        FileReader fr = null;
        try {
            
            fr = new FileReader("FileReaderTest.java");
            
            //��ȡ
            char[] c = new char[512];
            
            int temp = 0;
            
            while((temp = fr.read(c)) != -1) {
                
                //��char������Ч����ת��ΪString
                System.out.print(new String(c, 0, temp));
                
            }
            
            fr.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
    }
    
}