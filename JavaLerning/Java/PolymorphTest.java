/*
    ����Java�е�����ת��(upcasting):
        - �� ---> ��
    ����Java�е�����ת��(downcasting):
        - �� ---> ��
    ע:
        ��������ת�ͻ�������ת��, ������֮������м̳й�ϵ
*/
public class PolymorphTest
{
    public static void main(String[] args)
    {
        /*
        Animal a1 = new Animal();
        a1.eat();
        
        Cat c1 = new Cat();
        c1.eat();
        
        Dog d1 = new Dog();
        d1.eat();
        
        //����ת��, ���Զ�����ת��
        //����������ָ�������Ͷ���
        Animal a2 = new Cat();
        
        //�����ڱ���׶�a2������������Animal����
        //���Գ����ڱ���׶�a2���ð󶨵���Animal���eat����(��̬��)
        //����������ʱ, ���еĶ���ʵ����Cat����, ��Cat��д��eat����
        //���Գ��������н׶ζ���İ󶨷�����Cat�е�eat����(��̬��)
        a2.eat();
        
        //����ת��, ��ǿ������ת��
        //���ǿ������ת����
        Cat c2 = (Cat)a2;
        
        //ִ��move����
        c2.move();
        
        /*
        //�жϳ���
        Animal a3 = new Dog();
        
        Cat c3 = (Cat)a3; //java.lang.ClassCastException ����ת���쳣
        */
        //��ǿ������ת��, ������ڷ���
        //Ϊ�˱����쳣, Java������ instanceof
        /*
            �÷�:
                -instanceof��������Ϊboolean����
                -(���� instanceof ����) --> true/false
                
                exp: (a instanceof Cat) ��Ϊtrue, a����ָ����е�Java������Cat����
        */
        
        /*
        //�޸ĳ���
        Animal a3 = new Dog();
        
        if(a3 instanceof Cat)
        {
            Cat c3 = (Cat)a3;
        }
        */
    }
}

class Person
{
    public void feed(Animal a)
    {
        a.eat();
    }
}

class Dog extends Animal
{
    //��д
    public void eat()
    {
        System.out.println("Dog is eating");
    }
}

class Cat extends Animal
{
    //��д
    public void eat()
    {
        System.out.println("Cat is eating");
    }
    
    //Cat���еķ���
    public void move()
    {
        System.out.println("Cat is running");
    }
}

class Animal
{
    //��Ա����
    public void eat()
    {
        System.out.println("eating");
    }
}

//������Ҫ���������, �������ͱ��, ���������

/*
    ���ڶ�̬:
        -ʹ�ö�̬��ʹ������϶Ƚ���
        -��Ŀ����չ������ǿ
        -
*/