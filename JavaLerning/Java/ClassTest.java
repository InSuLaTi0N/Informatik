/*
    ���ڶ���Ĵ���
        -java������new������ͳһ�洢��"��(heap)"��, �����޷��Զ�����ֱ�Ӳ���, ֻ��ͨ���ڴ��ַ��Ӳ���
        -��Ա�����ڶ��еĶ����д洢
        -��Ա������û�и�ֵ, ϵͳ���Զ���ֵ ��ΪĬ��ֵ
        
    ����ϵͳĬ��ֵ:
        ������������:
            byte, short, int, lang --> 0
            float, double --> 0
            boolean --> false
            char --> \u0000
        ������������: null
    �����������ķ�װ��:
        -����˽�л�
        -�����ṩ�����ķ���
        
        ע: set������get�����ķ�����Ӧ����ѭ�淶
*/

public class ClassTest
{
    public static void main(String[] args)
    {
        /*
        //��������
        //stu1 �Ǿֲ�����, stu1 ��Student����, ������������
        //stu1ʵ�ʱ���Ϊ����, �����б����˶����ڶ��е��ڴ��ַ
        //ͨ��"����"��ӷ��ʶ��еĶ���
        Student stu1 = new Student();
        
        //һ����ɴ����������
        //Student stu2 = new Student();
        
        //ʹ�ö���
        //���ʳ�Ա���� ����ʹ��"����."
        
        //name�ǳ�Ա����, �������, �������ж�����ܷ���, ����ʹ��"����."
        //System.out.println(Student.name); //Error
        
        //��ȡ����
        System.out.println("id = " + stu1.id); //0
        System.out.println("name = " + stu1.name); //null
        System.out.println("age = " + stu1.age); //0
        System.out.println("sex = " + stu1.sex); //false
        System.out.println("address = " + stu1.address); //null
        
        //��ֵ����
        stu1.id = 1234;
        stu1.name = "Alice";
        //stu1.age = -20; ��Ȼ�����������ͨ��, ��������ҵ��Ҫ��  ����ȱ��
        stu1.age = 20;
        stu1.sex = true;
        stu1.address = "Dresden";
        
        //���¶�ȡ
        System.out.println("id = " + stu1.id); //1234
        System.out.println("name = " + stu1.name); //Alice
        System.out.println("age = " + stu1.age); //20
        System.out.println("sex = " + (stu1.sex ? "Ů" : "��")); //true
        System.out.println("address = " + stu1.address); //Dresden
        */
        
        //����Customer����
        Customer c = new Customer();
        
        //���ڼ���private�ؼ���, ����age�޷����ⲿ����
        /*
        //��ȡ(get)
        System.out.println(c.age);
        
        //��ֵ(set)
        c.age = 20; //��Ϊage����û���κ�����, �����ⲿ��������������age, ����age����ȫ
        //c.age = -20; //���Ϸ�������
        
        //��ȡ
        System.out.println(c.age);
        */
        
        //���¸�ֵ
        c.setAge(20);
        
        //c.setAge(-20); //��Ȼ������Ҫ��, ���ǿ�����class�и��ķ���
        
        //���¶�ȡ
        /*
        int age = c.getAge;
        System.out.println(age);
        */
        
        System.out.println(c.getAge());
    }
}