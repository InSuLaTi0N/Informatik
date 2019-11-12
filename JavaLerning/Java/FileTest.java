/*
    ����File
    java.io.File;
        -�����޹�, �޷������ļ���д
        -���ļ���Ŀ¼·�����ĳ����ʾ��ʽ

        -File����Ӳ�̵�Directory��file
*/

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {

    /*
    public static void main(String[] args) {

        File f = null;

        try {
            
            f = new File("FileTest.java"); //���·��
            System.out.println(f.exists() ? "���ڸ��ļ�" : "�����ڸ��ļ�"); //����true

            f = new File("D:/SoftWareTechnologie/Java/FileTest.java"); //����·��
            System.out.println(f.exists() ? "���ڸ��ļ�" : "�����ڸ��ļ�"); //����true

            f = new File("D:/SoftWareTechnologie/Java"); //Ŀ¼
            System.out.println(f.exists() ? "���ڸ�Ŀ¼" : "�����ڸ�Ŀ¼"); //����true

            f = new File("D:/Software"); //Ŀ¼
            System.out.println(f.exists() ? "���ڸ�Ŀ¼" : "�����ڸ�Ŀ¼");

            //������������Ŀ¼��ʽ����
            f = new File("D:/SoftWareTechnologie/Java/FileExample");
            if(!f.exists()) f.mkdir(); //����Ŀ¼

            //�����ļ�
            f = new File("D:/SoftWareTechnologie/Java/FileExample.txt");
            if(!f.exists()) f.createNewFile();

            //�������Ŀ¼
            f = new File("D:/SoftWareTechnologie/Java/FileExample/java/io/File");
            if(!f.exists()) f.mkdirs(); //��������Ŀ¼
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    /*
    public static void main(String[] args) {

        //��������
        File f = null;

        try {
            
            f = new File("FileTest.java");
            
            //��ȡ����·��
            System.out.println(f.getAbsolutePath()); //D:\SoftWareTechnologie\Java\FileTest.java

            //��ȡ��·��
            System.out.println(f.getParent()); //null

            //�ж�File���ļ���Ŀ¼
            System.out.println(f.isDirectory()); //false
            System.out.println(f.isFile()); //true


            f = new File("D:/SoftWareTechnologie/Java");

            //��ȡ��·��
            System.out.println(f.getParent()); //D:\SoftWareTechnologie

            //�ж�File���ļ���Ŀ¼
            System.out.println(f.isDirectory()); //true
            System.out.println(f.isFile()); //false

            //��ȡ�ļ����һ���޸�ʱ��
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(f.lastModified()))); //2019-11-12 11:38:18 277

            //��ȡ�ļ�����
            System.out.println(f.length()); //12288(�ֽ�)

            //�г����ļ�����Ŀ¼
            for(File file : f.listFiles()) {

                System.out.println(file.getAbsolutePath());
            }

            System.out.println("-------------------------------------------------");

            //�г���.class��β���ļ�
            for(File file : f.listFiles()) {

                if(file.getAbsolutePath().endsWith(".class")) System.out.println(file.getAbsolutePath());
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    //ʹ�õݹ��ҳ�ĳĿ¼�µ�������Ŀ¼�����ļ�
    public static void main(String[] args) {

        //��������
        File f = null;

        try {
            
            f = new File("D:/SoftWareTechnologie"); //�ҳ�SWT��������Ŀ¼�����ļ�

            searchAll(f);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public static void searchAll(File file) {

        if(file.isFile()) return;

        for(File direct : file.listFiles()) {

            System.out.println(direct.getAbsolutePath());

            searchAll(direct);
        }
    }
}