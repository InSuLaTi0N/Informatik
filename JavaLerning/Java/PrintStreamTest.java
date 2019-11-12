/*
    java.io.PrintStream; ��׼�������, Ĭ�ϴ�ӡ������̨, ���ֽڷ�ʽ
*/

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.Date;

public class PrintStreamTest {

    /*
    public static void main(String[] args) {

        try {
            //��׼���
            //Ĭ�����������̨
            System.out.println("Hello");

            PrintStream ps = System.out;

            //���
            ps.println("World");

            //���Ըı��������
            System.setOut(new PrintStream(new FileOutputStream("D:/SoftWareTechnologie/Java/log.txt"))); //ͨ��ʹ�����ַ�ʽ��¼��־

            //�ٴ����
            System.out.println("Hello World!");

            //�ر�
            ps.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    //���ڼ�¼m1����ִ�е���־
    public static void main(String[] args) {

        //�������ڸ�ʽ
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

        try {
            
            System.setOut(new PrintStream(new FileOutputStream("D:/SoftWareTechnologie/Java/log.txt"))); //�������¼��log�ļ�

            //��¼m1ִ��
            System.out.println("m1 started at " + sdf.format(new Date()));

            m1();

            System.out.println("m1 ended at " + sdf.format(new Date()));

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }


    public static void m1() {

        System.out.println("m1 method excute");
    }
}