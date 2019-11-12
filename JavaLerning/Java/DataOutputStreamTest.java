/*
    java.io.DataOutputStream; �����ֽ������
        -���Խ��ڴ��е� "int i = 10;" д�뵽Ӳ���ļ���, д��Ĳ����ַ���, ���Ƕ���������, ������
*/

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataOutputStreamTest {

    public static void main(String[] args) {

        //���������ֽ������
        DataOutputStream dos = null;

        //׼������
        byte b = 10;
        short s = 11;
        int i = 12;
        long l = 1000L;

        float f = 3.2f;
        double d  = 4.3;

        boolean flag = false;
        char c = 'a';
        
        try {

            dos = new DataOutputStream(new FileOutputStream("D:/SoftWareTechnologie/Java/TEST2.txt"));

            //д������
            dos.writeByte(b);
            dos.writeShort(s);
            dos.writeInt(i);
            dos.writeLong(l);

            dos.writeFloat(f);
            dos.writeDouble(d);

            dos.writeBoolean(flag);
            dos.writeChar(c);


            //ˢ��
            dos.flush();

            //�ر�
            dos.close();
            
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}