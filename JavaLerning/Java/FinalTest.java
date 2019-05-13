/*
    ����final�ؼ���:
        -final���ε����޷����̳�
        -final���εķ����޷�������
        -final���εľֲ�����һ������ֵ, �����ٸı�
        -final���εĳ�Ա����������ʾ�س�ʼ��
        -final���εĳ�Ա����һ���static����
        
        -final���ε���������, �����ò�����ָ��������Java����
         ����final���ε�����, ������ָ��Ķ���������ǿ����޸ĵ�
*/
public class FinalTest
{
    public static void main(String[] args)
    {
        final Customer c = new Customer("Alpha", 20);
        //c = new Customer("Bravo", 25); //����: c��final����, �޷����¸�ֵ
        
        c.name = "Bravo";
        c.age = 25;
        
        System.out.println(c.name + " " + c.age);
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

class Customer
{
    String name;
    int age;
    
    Customer(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
}