/*
    ����This�ؼ���:
        -����������, �������ڴ��ַ, ���ַָ������
        -�ڶ��е�ÿһ��java�����������Լ���this�ؼ���
        
        -this�������ڳ�Ա������
        -this�������ڹ��췽����
        -��this���ڹ��췽��ʱ, ��ʹ���﷨:
            this(ʵ��);
        -ͨ��һ�����췽��������һ�����췽��
        -Ŀ��ʹ�ô�������
        -this();��������ڹ��췽���ĵ�һ��
        
        -this�����������ֳ�Ա�����;ֲ�����
*/

public class ThisTest
{
    //String str; //�Ǿ�̬����, ���ڳ�Ա����
    
    public static void main(String[] args)
    {
        /*
        //��������
        MyDate t1 = new MyDate(2019, 5, 6);
        
        System.out.println(t1.year + "��" + t1.month + "��" + t1.day + "��");
        */
        /*
        Employee e = new Employee("Alice", 1234);
        
        e.work();
        e.m1();
        */
        
        /*
        Manager m1 = new Manager("Bob");
        System.out.println(m1.getName());
        
        m1.setName("Charlie");
        System.out.println(m1.getName());
        */
        
        /*
        Person p1 = new Person("David");
        
        p1.m1();
        */
        
        //System.out.println(str);//����, ��̬�����в���ֱ�ӷ��ʳ�Ա����
    }
}

/*
class MyDate
{
    //Field
    int year;
    int month;
    int day;
    
    //Constructor
    //ʹ��this()��ʽ
    MyDate()
    {
        this(1970, 1, 1);
        
        //�����﷨�������﷨�ȼ�
        //this.year = 1970;
        //this.month = 1;
        //this.day = 1;
    }
    
    MyDate(int _year, int _month, int _day)
    {
        year = _year;
        month = _month;
        day = _day;
    }
}
*/

/*
//���ڳ�Ա������this��ʹ��
class Employee
{
    //Field
    String name;
    int number;
    
    //Constructor
    Employee(){}
    
    Employee(String _name, int _number)
    {
        name = _name;
        number = _number;
    }
    
    //Ա����������
    public void work()
    {
        //�����������������ͬ
        System.out.println(name + " is working!");
        System.out.println(this.name + " is working!");
    }
    
    public void m1()
    {
        this.m2();
        m2();
    }
    
    public void m2()
    {
        System.out.println("Test");
    }
}
*/

    /*
        this���ڳ�Ա������, ˭ȥ�����������, this�ʹ���˭
        thisָ���ǵ�ǰ����
        this.����ʡ��
    */
    
/*
//����this���ֳ�Ա�����;ֲ�����
class Manager
{
    //Field
    private String name;
    
    //Constructor
    Manager(){}
    
    Manager(String name)
    {
        this.name = name; //�˴�nameΪ�ֲ�����, this���ó�Ա����
    }
    
    //Method
    public void setName(String name)
    {
        this.name = name; //���µ�name��������Ա����name
    }
    
    public String getName()
    {
        return name; //���س�Ա������name
    }
}
*/

/*
class Person
{
    //Field
    String name;
    
    //Constructor
    Person(){}
    
    Person(String name)
    {
        this.name = name;
    }
    
    /*
    //��̬����
    public static void m1()
    {
        //System.out.println(name); //����: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� name
        
        //����name
        Person p1 = new Person("David");
        System.out.println(p1.name);
    }
    
}
*/

/*
    this�������ھ�̬������:
        -��̬������ִ�и�������Ҫ����Ĵ���, ֱ��ʹ��"����."�ķ�ʽ����
        -��this������ǵ�ǰ����, ���Ծ�̬�����и���û��this
        -�����nameֻ��ͨ��:newһ������ -> ����. �ķ�ʽ
*/