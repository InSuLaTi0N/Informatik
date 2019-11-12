/*
    反序列化及序列化版本号
*/

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectInputStreamTest {

    /*
    public static void main(String[] args) {

        //创建反序列化流
        ObjectInputStream ois = null;

        try {
            
            ois = new ObjectInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST3.txt"));

            //反序列化
            System.out.println(ois.readObject());
            //报错
            //java.io.InvalidClassException: Users;  无效类
            //local class incompatible: class不相容
            //stream classdesc serialVersionUID = -2282987656811176025,  硬盘文件的版本号
            //local class serialVersionUID = 6251463328413987154 class文件的版本号, 序列化版本号不同

            //关闭流
            ois.close();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    */

    /*
    //重新编写Users, 及反序列化
    public static void main(String[] args) {

        //创建反序列化流
        ObjectInputStream ois = null;

        try {
            
            //反序列化
            ois = new ObjectInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST3.txt")); //添加新的代码后成功运行

            //读取
            System.out.println(ois.readObject()); //Users@2be94b0f

            //关闭
            ois.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    */

    //关于transient关键字
    public static void main(String[] args) {

        //创建流
        ObjectInputStream ois = null;

        try {
            
            //反序列化
            ois = new ObjectInputStream(new FileInputStream("D:/SoftWareTechnologie/Java/TEST3.txt"));

            //读取
            //未加transient关键字
            //System.out.println(ois.readObject()); //name --> Alpha

            //添加transient关键字
            System.out.println(ois.readObject()); //name --> null

            //关闭
            ois.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}