public class ConstructorTest
{
    public static void main(String[] args)
    {
        //����User���͵Ķ���
        User u1 = new User();
        
        User u2 = new User("Alpha"); //�����в�����User���캯��
        
        User u3 = new User(20);
        
        User u4 = new User("Bravo", 25);
        
        System.out.println(u1.name); //��ʼֵnull
        System.out.println(u1.age); //��ʼֵ0
        
        System.out.println(u2.name); //�Ѹ�ֵAlpha
        System.out.println(u2.age); //��ʼֵ0
        
        System.out.println(u3.name); //��ʼֵnull
        System.out.println(u3.age); //�Ѹ�ֵ20
        
        System.out.println(u4.name); //Bravo
        System.out.println(u4.age); //25
    }
}