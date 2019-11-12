/*
    java.io.OutputStream;
        java.io.FileOutputStream; �ļ��ֽ������

        -��������ڴ��е�����д��Ӳ���ļ���
*/

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutputStreamTest {
    
    /*
    //�����ļ����(д��)
    public static void main(String[] args) {

        //�����ļ��ֽ������
        FileOutputStream fos = null;
        FileOutputStream fos1 = null;

        try {
            fos = new FileOutputStream("D:/SoftWareTechnologie/Java/TEST.txt"); //���ļ����������Զ�����

            //д��
            String msg = "fgh jkl";

            //��Stringת��Ϊbyte����
            byte[] b = msg.getBytes();

            //��byte������ȫ������д��
            fos.write(b); //�Ḳ��֮ǰ������, �����ʹ��

            //��byte����һ����д��
            fos.write(b, 0, 3); //fgh jklfgh

            //��׷�ӷ�ʽд��
            fos1 = new FileOutputStream("D:/SoftWareTechnologie/Java/TEST1.txt", true);

            fos1.write(b); //���Կ����ļ�TEST1����׷�ӷ�ʽд��, ԭ�����Ա���

            //Ϊ�˱�֤������ȫд��Ӳ��, ��Ҫˢ��
            fos.flush(); //ǿ��д��

            fos.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    */

    //���ڸ����ļ�
    public static void main(String[] args) {

        //�������������
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {

            //���ж�д����
            fis = new FileInputStream("D:/SoftWareTechnologie/Java/HelloWorld.java");
            fos = new FileOutputStream("C:/Users/Bai Song/Desktop/HelloWorld.java");

            byte[] b = new byte[1024]; //ÿ�ζ�ȡ1kb

            int temp = 0;

            while((temp = fis.read(b)) != -1) {

                fos.write(b, 0, temp);
            }

            //ˢ��
            fos.flush();

            //�ر�
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}