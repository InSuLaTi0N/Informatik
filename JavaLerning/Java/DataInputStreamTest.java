/*
    java.io.DataInputStream;
*/

import java.io.DataInputStream;
import java.io.FileInputStream;

public class DataInputStreamTest {

    public static void main(String[] args) {

        //创建数据字节输入流
        DataInputStream dis = null;
        
        try {
            
            dis = new DataInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST2.txt"));

            //使用该流读取数据, 必须提前知道数据的存储格式以及顺序
            //读取顺序必须和写入顺序一致
            System.out.println(dis.readByte());
            System.out.println(dis.readShort());
            System.out.println(dis.readInt());
            System.out.println(dis.readLong());

            System.out.println(dis.readFloat());
            System.out.println(dis.readDouble());

            System.out.println(dis.readBoolean());
            System.out.println(dis.readChar());

            //关闭
            dis.close();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}