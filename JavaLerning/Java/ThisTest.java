/*
    关于This关键字:
        -是引用类型, 保存了内存地址, 其地址指向自身
        -在堆中的每一个java对象都有属于自己的this关键字
        
        -this可以用在成员方法中
        -this可以用在构造方法中
        -当this用在构造方法时, 可使用语法:
            this(实参);
        -通过一个构造方法调用另一个构造方法
        -目的使得代码重用
        -this();必须出现在构造方法的第一行
        
        -this可以用来区分成员变量和局部变量
*/

public class ThisTest
{
    //String str; //非静态变量, 属于成员变量
    
    public static void main(String[] args)
    {
        /*
        //创建对象
        MyDate t1 = new MyDate(2019, 5, 6);
        
        System.out.println(t1.year + "年" + t1.month + "月" + t1.day + "日");
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
        
        //System.out.println(str);//报错, 静态方法中不能直接访问成员变量
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
    //使用this()方式
    MyDate()
    {
        this(1970, 1, 1);
        
        //以上语法与以下语法等价
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
//关于成员方法中this的使用
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
    
    //员工工作方法
    public void work()
    {
        //下面两句输出意义相同
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
        this用在成员方法中, 谁去调用这个方法, this就代表谁
        this指的是当前对象
        this.可以省略
    */
    
/*
//关于this区分成员变量和局部变量
class Manager
{
    //Field
    private String name;
    
    //Constructor
    Manager(){}
    
    Manager(String name)
    {
        this.name = name; //此处name为局部变量, this引用成员变量
    }
    
    //Method
    public void setName(String name)
    {
        this.name = name; //将新的name保存至成员变量name
    }
    
    public String getName()
    {
        return name; //返回成员变量的name
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
    //静态方法
    public static void m1()
    {
        //System.out.println(name); //错误: 无法从静态上下文中引用非静态 变量 name
        
        //访问name
        Person p1 = new Person("David");
        System.out.println(p1.name);
    }
    
}
*/

/*
    this不能用在静态方法中:
        -静态方法的执行根本不需要对象的存在, 直接使用"类名."的方式访问
        -而this代表的是当前对象, 所以静态方法中根本没有this
        -想访问name只能通过:new一个对象 -> 引用. 的方式
*/