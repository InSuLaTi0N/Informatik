/*
    ���ڷ�������:
        -�����෽���޷�����ҵ������, ��Ҫ������д(����, Override)
        
        -������д���෽����, ������������д��ķ���
        -���������Ȼ���ø��෽��
        
        -�����������ǵ�����:
            -�����ھ��м̳й�ϵ��������֮��
            -���������ͬ�ķ�����, ��ͬ�ķ���ֵ����, ��ͬ�Ĳ����б�
            -��д�ķ������ܱȱ���д�ķ���ӵ�и��͵ķ���Ȩ��
            -��д�ķ������ܱȱ���д�ķ����׳��������쳣(�쳣����)
            -˽�еķ������ܱ�����(��̬)
            -���췽���޷�������
            -��̬���������ڸ���(��̬)
            -����ָ���ǳ�Ա����, �ͳ�Ա�����޹�
            
        -�̳е�����:
            -��������
            -����������д
*/

public class OverrideTest
{
    public static void main(String[] args)
    {
        /*
        //�����������
        Cat c = new Cat();
        
        //���÷���
        c.move();
        
        //��̬
        OverrideTest a = new B();
        a.m1(); //���method A m1
        */
        
        /*
        //��̬
        OverrideTest b = new C();
        b.m1(); //���method A m1
        */
        //��̬���������ڸ���, ��Ϊ���о�̬������ִ�кͶ����޹�
    }
    /*
    //˽�з����޷�������
    private void m1()
    {
        System.out.println("A'm1 invoke");
    }
    */
    
    //��̬�����޷�������
    public static void m1()
    {
        System.out.println("Method A m1");
    }
}

/*
//����
class Animal
{
    //��Ա����
    public void move()
    {
        System.out.println("�������ƶ�");
    }
}
*/

/*
//����
class Cat extends Animal
{
    //Cat��move����Ӧ����� "è���ƶ�"
    //Animal�е�move�޷�����
    //������Cat���ж�Animal��ķ������¶���
    //Override
    public void move()
    {
        System.out.println("è���ƶ�");
    }
    
    //Ȩ�޸���  ����
    void move()
    {
        System.out.println("è���ƶ�");
    }
    
}
*/


/*
class B extends OverrideTest
{
    //������д����˽�з���
    public void m1()
    {
        System.out.println("B'm1 invoke");
    }
}
*/

class C extends OverrideTest
{
    //������д���ྲ̬����
    public static void m1()
    {
        System.out.println("Method B m1");
    }
}