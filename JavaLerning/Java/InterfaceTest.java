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
*/

public class InterfaceTest
{
    public static void main(String[] args)
    {
        CustomerService cs = new CustomerServiceImpl(); //��̬ �ӿ�--->�ӿ�ʵ����
        cs.logout();
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