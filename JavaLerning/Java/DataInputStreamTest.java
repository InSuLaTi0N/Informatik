/*
    java.io.DataInputStream;
*/

import java.io.DataInputStream;
import java.io.FileInputStream;

public class DataInputStreamTest {

    public static void main(String[] args) {

        //���������ֽ�������
        DataInputStream dis = null;
        
        try {
            
            dis = new DataInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST2.txt"));

            //ʹ�ø�����ȡ����, ������ǰ֪�����ݵĴ洢��ʽ�Լ�˳��
            //��ȡ˳������д��˳��һ��
            System.out.println(dis.readByte());
            System.out.println(dis.readShort());
            System.out.println(dis.readInt());
            System.out.println(dis.readLong());

            System.out.println(dis.readFloat());
            System.out.println(dis.readDouble());

            System.out.println(dis.readBoolean());
            System.out.println(dis.readChar());

            //�ر�
            dis.close();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}