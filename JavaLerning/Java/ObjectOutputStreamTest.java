/*
    java.io.ObjectOutputStream; 序列化Java对象至硬盘 (Serial)
    java.io.ObjectInputStream; 将硬盘中的数据反序列化至JVM内存 (Deserial)
*/

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;


public class ObjectOutputStreamTest {

    public static void main(String[] args) {

        //创建对象
        Users u1 = new Users("Alpha");

        //创建序列化流 将JVM中的Java对象保存至硬盘中
        ObjectOutputStream oos = null;

        try {

            oos = new ObjectOutputStream(new FileOutputStream("D:/SoftWareTechnologie/Java/TEST3.txt"));

            //写入
            oos.writeObject(u1);

            //刷新
            oos.flush();

            //关闭
            oos.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}

//java.io.NotSerializableException: Users
//Users不支持序列化

/*
    标识接口 --> 起到标识作用
    JVM如果看到该对象实现了某个标识接口, 会对它特殊待遇: 会给该类添加属性
    static final long serialVersionUID = 6251463328413987154;
*/

/*
class Users implements Serializable { //该接口是可序列化的
                                      //该接口没有任何方法, 是一个标识接口
                                      //类似接口: java.lang.Cloneable

    String name;
    int age; //将Users的class文件删除, 添加int age后重新编译
    //当没有添加int age时, User实现了Serializable接口, JVM会分配一个序列化版本号
    //当修改类时, 需要重新编译, 则会分配一个新的序列化版本号

    public Users(String name) {

        this.name = name;
    }

    public String toString() {

        return "[name = " + name + "]";
    }
}
*/

/*
//解决方案, 自定义一个序列化版本号
class Users implements Serializable {

    static final long serialVersionUID = 1234567L;

    //编译后添加新的代码
    String name;
}
*/

//关于transient关键字
class Users implements Serializable {

    static final long serialVersionUID = 1234L;

    //transient关键字可以让属性不参加序列化
    transient String name;

    Users(String name) {

        this.name = name;
    }

    public String toString() {

        return "name --> " + name;
    }
    
}