/*
    �����л������л��汾��
*/

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectInputStreamTest {

    /*
    public static void main(String[] args) {

        //���������л���
        ObjectInputStream ois = null;

        try {
            
            ois = new ObjectInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST3.txt"));

            //�����л�
            System.out.println(ois.readObject());
            //����
            //java.io.InvalidClassException: Users;  ��Ч��
            //local class incompatible: class������
            //stream classdesc serialVersionUID = -2282987656811176025,  Ӳ���ļ��İ汾��
            //local class serialVersionUID = 6251463328413987154 class�ļ��İ汾��, ���л��汾�Ų�ͬ

            //�ر���
            ois.close();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    /*
    //���±�дUsers, �������л�
    public static void main(String[] args) {

        //���������л���
        ObjectInputStream ois = null;

        try {
            
            //�����л�
            ois = new ObjectInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST3.txt")); //����µĴ����ɹ�����

            //��ȡ
            System.out.println(ois.readObject()); //Users@2be94b0f

            //�ر�
            ois.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    */

    //����transient�ؼ���
    public static void main(String[] args) {

        //������
        ObjectInputStream ois = null;

        try {
            
            //�����л�
            ois = new ObjectInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST3.txt"));

            //��ȡ
            //δ��transient�ؼ���
            //System.out.println(ois.readObject()); //name --> Alpha

            //���transient�ؼ���
            System.out.println(ois.readObject()); //name --> null

            //�ر�
            ois.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}