/*
    ���ڳ�����:
        -��������෽��:
            abstract class ..{}
        -�������޷���ʵ����
        
        -��Ȼ�������޷�ʵ����, ���ǳ�����Ҳ�й��췽��, �����Ǹ����ഴ������
        
        -�������п��Զ�����󷽷�
        -���󷽷����﷨:
            -�ڷ��������η��б������abstract�ؼ���, ���ҳ��󷽷���;����, ���ܴ���{}
             ��: public abstract void m1();
        
        -�������в�һ���г��󷽷�, �����󷽷���������ڳ�������
        -һ���ǳ������̳г�����, ���뽫�������еĳ��󷽷�����, ʵ��, ��д
*/
public abstract class AbstractTest
{
    public static void main(String[] args)
    {
        
    }
    
    //public abstract void m1(){} //����: ���󷽷�����������
    public abstract void m1();
}
/*
abstract class A
{
    //Constractor
    A()
    {
        System.out.println("A");
    }
    public void m1()
    {
        //A a = new A(); //����: A�ǳ���� �޷�ʵ���� ���޷���������
        
        //��̬
        A a = new B();
    }
    
    public abstract void m2(); //����: B���ǳ����, ����δ����A�еĳ��󷽷�m2
}

class B extends A
{
    B()
    {
        //�˴�ִ����super();
        //����Ĺ��췽����Ȼ������, ���ǲ�û�д����������
        System.out.println("B");
    }
}
*/