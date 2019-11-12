/*
    ���ڽӿ�:
        -��һ�ֳ�������
        -�﷨:
            [���η�] interface �ӿ��� [extends �����ӿ���]
            {
                //��������
                //���󷽷�
            }
            
        -�ӿ���ֻ�ܳ��ֳ���, ���󷽷�
        -�ӿ���ʵ��һ������ĳ�����, �����ڽӿ��Ǵ������
        
        -�ӿ���û�й��췽��, �ӿ�Ҳ�޷���ʵ����
        
        -�ӿںͽӿ�֮����Զ�̳�
        
        -һ�������ʵ�ֶ���ӿ�(�����ʵ�ֵ�ͬ�����̳�)
        
        -һ���ǳ������ʵ�ֽӿ�, ��Ҫ���ӿ������з���"ʵ��/��д/����"
        
        -����ʵ�ֽӿ�ʱ, ʹ��implements�ؼ���
        -implements��extends������ͬ
        
        ����:
            -����ʹ��Ŀ�ֲ�, ���в�����ӿڿ���, ����Ч�����
            -�ӿ�ʹ�������϶Ƚ���
             ���������л�
    
    ���ڽӿںͳ��������:
        -������ͽӿڶ����ܱ�ֱ��ʵ����, ��Ҫʵ������ʹ�ö�̬
            -��������ʵ����, �����ඨ��ı�������ָ��һ���������, ������̳��˳����ಢʵ�������г��󷽷�
            -���ӿ�ʵ����, �ýӿڶ���ı���Ҫָ��һ���������, ���������ʵ���˸ýӿ����еķ���
        -������Ҫ������̳�, �ӿ�Ҫ������ʵ��
        
        -�ӿ���ֻ�ܶԷ�����������, ������ȿ��ԶԷ�������Ҳ���ԶԷ�������ʵ��
        
        -�������еĳ��󷽷�����ȫ��������ʵ��, ������ȫ��ʵ��, ��ô����Ҳ�����ǳ�����
        -�ӿ��еķ���Ҳ����ȫ��������ʵ��, �����಻��ʵ������������ǳ�����
        
        -�ӿ��еķ���ֻ������, �����о����ʵ��
            -˵���ӿ�����ƵĽ��, ���������ع��Ľ��
            
        -�������п���û�г��󷽷�
        
        -��һ�������г��󷽷�, �����һ���ǳ�����
        
        -�������еķ�����Ҫ��ʵ��, ���Գ��󷽷������Ǿ�̬(static), ˽��(private)
        
        -�ӿڿ��Լ̳ж���ӿ�, ��ֻ�ܼ̳�һ����
        
        -���󼶱�: �ӿ�>������>ʵ����
        
        -��������Ҫ�����������, �ӿ���Ҫ�������󷽷�����
        -����ע���ﱾ��ʱ, ʹ�ó�����; ����ע����ʱ, ʹ�ýӿ�
        
        -�����������۽ϸ�: Java��һ����ֻ�ܼ̳�һ������
         �����������ʱ�����Ҫ������������������������еĹ�ͬ���Ժͷ���
        -�ӿڿɼ̳ж���ӿ�, ÿ���ӿ�ֻ��Ҫ���ض��Ķ���������������ӿڼ���
        
        -�ӿڵ���ƾ��и���Ŀ���չ�ԣ������������Ʊ���ʮ�ֽ���
*/

public class InterfaceTest
{
    public static void main(String[] args)
    {
        CustomerService cs = new CustomerServiceImpl(); //��̬ �ӿ�--->�ӿ�ʵ����
        cs.logout();
        
        Engine e1 = new HONDA(); //��̬ �ӿ�--->�ӿ�ʵ���� (��������--->ʵ��������)
        
        Car c = new Car(e1); //ʵ�� ����
        c.testEngine(); //��������Է���
        
        c.e = new YAMAHA(); //�滻�ӿ�ʵ����(����)
        c.testEngine();
        
        
    }
}

/*
public interface InterfaceTest
{
    
    //���� ������public static final����
    public static final String SUCCESS = "Success";
    public static final double PI = 3.14;
    
    //public static final �ǿ���ʡ�Ե�
    byte MAX_VALUE = 127; //����, ���ǳ�Ա����
    
    //���󷽷�:(�ӿ������еĳ��󷽷�����public abstract)
    public abstract void m1();
    
    //public abstract����ʡ��
    void m2();
    
}
*/

/*
interface A
{
    void m1();
}

interface B
{
    void m2();
}

interface C
{
    void m3();
}

interface D extends A,B,C
{
    void m4();
}

class MyClass implements A,B
{
    public void m1(){}
    public void m2(){}
}

class MyInterface implements D
{
    public void m1(){}
    public void m2(){}
    public void m3(){}
    public void m4(){}
}
*/

//���ڽӿ�����
//����һ���ӿ�
/*public*/
interface CustomerService
{
    //�����˳�ϵͳ����
    void logout();
}

//����ӿ�ʵ����
//����ӿ�ʵ��
/*public*/
class CustomerServiceImpl implements CustomerService
{
    //�Խӿ��еĳ��󷽷�ʵ��
    public void logout()
    {
        System.out.println("logout");
    }
}

//����һ���ӿ�: ��������
interface Engine
{
    void start();
}

//����ʵ�ָýӿڵ���
class HONDA implements Engine
{
    public void start()
    {
        System.out.println("HONDA");
    }
}

//����ʵ�ֽӿڵ��滻��
class YAMAHA implements Engine
{
    public void start()
    {
        System.out.println("YAMAHA");
    }
}

//����ʵ����
class Car
{
    //Field
    //����ӿڱ��
    Engine e;
    
    //Constructor
    Car(){}
    
    Car(Engine e)
    {
        this.e = e;
    }
    
    //��������
    public void testEngine()
    {
        e.start(); //����ӿڵ���
    }
}