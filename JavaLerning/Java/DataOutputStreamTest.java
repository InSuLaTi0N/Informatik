/*
    java.io.DataOutputStream; 数据字节输出流
        -可以将内存中的 "int i = 10;" 写入到硬盘文件中, 写入的不是字符串, 而是二进制数据, 带类型
*/

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataOutputStreamTest {

    public static void main(String[] args) {

        //创建数据字节输出流
        DataOutputStream dos = null;

        //准备数据
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

            //写入数据
            dos.writeByte(b);
            dos.writeShort(s);
            dos.writeInt(i);
            dos.writeLong(l);

            dos.writeFloat(f);
            dos.writeDouble(d);

            dos.writeBoolean(flag);
            dos.writeChar(c);


            //刷新
            dos.flush();

            //关闭
            dos.close();
            
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}