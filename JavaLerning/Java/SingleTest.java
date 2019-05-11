/*
    ���ڵ���ģʽ:
        -�������ģʽ
        -���ģʽ�ǿ����ظ����õĽ������
        
        -��23�����ģʽ����򵥵�һ��
        -Ϊ�˱�֤java�����(jvm)��ĳһ�����͵�java������Զֻ��һ��
        
        -Ϊ�˽�ʡ�ڴ�Ŀ���
        
        -��Ҫ��Ϊ���ֵ���ģʽ:
            -����ʽ����
                -����ؽ׶ξʹ����˶���
            -����ʽ����
                -�õ�����Żᴴ��
        
    ʵ�ֵ���ģʽ:
        -���췽��˽�л�
        -�����ṩһ������(public)��̬(static)�Ļ�ȡ��ǰ����ķ���
        -�ṩһ����ǰ���͵ľ�̬����
*/

public class SingleTest
{
    public static void main(String[] args)
    {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
        
        Singleton1 c1 = Singleton1.getInstance();
        Singleton1 c2 = Singleton1.getInstance();
        System.out.println(c1 == c2);
    }
}

//ʵ�ֵ���ģʽ
//����ʽ����
class Singleton
{
    //�����췽��˽�л�
    private Singleton(){}
    
    //ֻ��ʼ��һ��s, ʹ�þ�̬����
    private static Singleton s;
    
    //�����ṩһ��������ȡSingleton����ķ���
    public  static Singleton getInstance()
    {
        //�ж�s
        if(s == null)
        {
            s = new Singleton();
        }
        
        return s;
    }
}

//����ʽ����
class Singleton1
{
    //��������
    //�����ִֻ��һ��
    private static Singleton1 c = new Singleton1();
    
    //��������˽�л�
    private Singleton1(){}
    
    //�ṩ�����ķ���
    public static Singleton1 getInstance()
    {
        return c;
    }
}