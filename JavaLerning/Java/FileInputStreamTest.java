/*
    -FileInputStream
        -java.io.FileInputStream; �ļ��ֽ�������
        -�����ֽڷ�ʽ��ȡ�ļ�
        
        -��Ҫ����:
            void    close() �ͷ��ļ�
            int    read()   ��ȡ�ļ�
            int    read(byte[] b)
*/

import java.io.FileInputStream;

public class FileInputStreamTest {
    
    /*
    //���ڶ�ȡ�ļ�
    public static void main(String[] args) {
        
        //��ȡ�ļ�ǰ�ȴ���������
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("TEST.txt");

            //��ȡ�ļ�
            
            //for(int i = 0; i < 6; i++)
            //{
            //    System.out.println(fis.read()); //���ֽڷ�ʽ��ȡ
            //    // 97 98 99 100 101 -1
            //    //����Ѿ���ȡ���ļ���ĩβ, �ͻ᷵��-1
            //}
            
            
            //while��ȡ�ļ�
            int temp = 0;
            
            while((temp = fis.read()) != -1) {
                System.out.print(temp + " ");
            }
            
            
            //���ϳ���Ƶ�����ʴ���, �˺�����, Ч�ʵ���

            //�ͷ��ļ�
            fis.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {
            
            e.printStackTrace();

        }
    }
    */
    /*
    //����int read(byte[] b);
    //��ȡ֮ǰ���ڴ���׼��һ��byte����, ÿ�ζ�ȡ����ֽڴ洢��byte������
    //read()�ǵ��ֽڶ�ȡ
    
    public static void main(String[] args) {
        
        //����������
        FileInputStream fis = null;
        
        try {
            
            //��ȡ�ļ�
            fis = new FileInputStream("TEST.txt");
            
            byte[] b = new byte[3]; //ÿ������ȡ3�ֽ�
            
            //�÷������ص���ÿ�ζ�ȡ���ֽ���
            //�����ļ�ĩβ�򷵻�-1
            
            for(int i = 0; i < 3; i++) {
                
                System.out.println(fis.read(b)); //3 2 -1
                //�ڶ��ζ�ȡʱ���θ���
                
                //��byte����ת��Ϊ�ַ���
                System.out.println(new String(b)); //abc dec dec
                
            }
            //�ͷ��ļ�
            fis.close();
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
    }
    */
    /*
    //����ѭ����ȡ
    public static void main(String[] args) {
        
        FileInputStream fis = null;
        try {
            
            fis = new FileInputStream("FileInputStreamTest.java");
            
            byte[] b = new byte[1024]; //ÿ�ζ�ȡ1kb
            
            int temp = 0;
            
            while((temp = fis.read(b)) != -1) {
                
                //��byte��������Ч����ת��Ϊ�ַ���
                System.out.print(new String(b, 0, temp));
            }
            
            fis.close();
        }catch(Exception e) {
            
            e.printStackTrace();
        }
        
    }
    */
    /*
    //����available����
    //int available(); ��������ʣ��Ĺ����ֽ���
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream("D:/SoftWareTechnologie/Java/TEST.txt");

            System.out.println(fis.available()); //5

            System.out.println(fis.read()); //97

            System.out.println(fis.available()); //4

            fis.close();
        } catch (Exception e) {

            e.printStackTrace();
        
        }

    }
    */

    //����skip����
    //long skip(long n); ����n���ֽ�
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {

            fis = new FileInputStream("D:/SoftWareTechnologie/Java/TEST.txt");

            System.out.println(fis.read()); //97

            //��Ծ1���ֽ�
            fis.skip(1);

            System.out.println(fis.read()); //99

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}