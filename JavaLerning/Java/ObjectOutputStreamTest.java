/*
    java.io.ObjectOutputStream; ���л�Java������Ӳ�� (Serial)
    java.io.ObjectInputStream; ��Ӳ���е����ݷ����л���JVM�ڴ� (Deserial)
*/

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;


public class ObjectOutputStreamTest {

    public static void main(String[] args) {

        //��������
        Users u1 = new Users("Alpha");

        //�������л��� ��JVM�е�Java���󱣴���Ӳ����
        ObjectOutputStream oos = null;

        try {

            oos = new ObjectOutputStream(new FileOutputStream("D:/SoftWareTechnologie/Java/TEST3.txt"));

            //д��
            oos.writeObject(u1);

            //ˢ��
            oos.flush();

            //�ر�
            oos.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}

//java.io.NotSerializableException: Users
//Users��֧�����л�

/*
    ��ʶ�ӿ� --> �𵽱�ʶ����
    JVM��������ö���ʵ����ĳ����ʶ�ӿ�, ������������: ��������������
    static final long serialVersionUID = 6251463328413987154;
*/

/*
class Users implements Serializable { //�ýӿ��ǿ����л���
                                      //�ýӿ�û���κη���, ��һ����ʶ�ӿ�
                                      //���ƽӿ�: java.lang.Cloneable

    String name;
    int age; //��Users��class�ļ�ɾ��, ���int age�����±���
    //��û�����int ageʱ, Userʵ����Serializable�ӿ�, JVM�����һ�����л��汾��
    //���޸���ʱ, ��Ҫ���±���, ������һ���µ����л��汾��

    public Users(String name) {

        this.name = name;
    }

    public String toString() {

        return "[name = " + name + "]";
    }
}
*/

/*
//�������, �Զ���һ�����л��汾��
class Users implements Serializable {

    static final long serialVersionUID = 1234567L;

    //���������µĴ���
    String name;
}
*/

//����transient�ؼ���
class Users implements Serializable {

    static final long serialVersionUID = 1234L;

    //transient�ؼ��ֿ��������Բ��μ����л�
    transient String name;

    Users(String name) {

        this.name = name;
    }

    public String toString() {

        return "name --> " + name;
    }
    
}