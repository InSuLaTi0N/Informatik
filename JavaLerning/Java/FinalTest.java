/*
    ����final�ؼ���:
        -final���ε����޷����̳�
        -final���εķ����޷�������
        -final���εľֲ�����һ������ֵ, �����ٸı�
        -final���εĳ�Ա����������ʾ�س�ʼ��
        -final���εĳ�Ա����һ���static����
*/
public class FinalTest
{
    public static void main(String[] args)
    {
        
    }
}

/*
final class A{}

class B extends A{} //����  �޷�������A�̳�
*/

/*
class A
{
    public final void m1(){}
}

class B extends A
{
    public void m1(){} //����, B�е�m1�޷�����A�е�m1 �����Ƿ���Ϊfinal
}
*/

/*
class A
{
    public void m1()
    {
        //����
        final int i;
        
        //��ֵ
        i = 100;
        
        //���¸�ֵ
        //i = 200; //���� �ѷ������i
    }
}
*/

class A
{
    //final���εĳ�Ա���������ֶ���ʼ��
    //final int i; //���� ����iδ��Ĭ�Ϲ������г�ʼ��
    final int i = 100;
    
    final int k;
    
    A()
    {
        k = 100; //��ʼ��
    }
}

class MyMath
{
    //����(static��finalһ�����ε���)
    //java�淶Ҫ��, ���г�����д
    public static final double PI = 3.14;
}