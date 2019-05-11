/*
    关于对象的创建
        -java中所有new的数据统一存储在"堆(heap)"中, 人们无法对堆数据直接操作, 只能通过内存地址间接操作
        -成员变量在堆中的对象中存储
        -成员变量若没有赋值, 系统会自动赋值 称为默认值
        
    关于系统默认值:
        基本数据类型:
            byte, short, int, lang --> 0
            float, double --> 0
            boolean --> false
            char --> \u0000
        引用数据类型: null
    关于面向对象的封装性:
        -属性私有化
        -对外提供公开的方法
        
        注: set方法和get方法的方法名应该遵循规范
*/

public class ClassTest
{
    public static void main(String[] args)
    {
        /*
        //创建对象
        //stu1 是局部变量, stu1 是Student类型, 引用数据类型
        //stu1实际被称为引用, 引用中保存了对象在堆中的内存地址
        //通过"引用"间接访问堆中的对象
        Student stu1 = new Student();
        
        //一个类可创建多个对象
        //Student stu2 = new Student();
        
        //使用对象
        //访问成员变量 必须使用"引用."
        
        //name是成员变量, 对象相关, 必须先有对象才能访问, 必须使用"引用."
        //System.out.println(Student.name); //Error
        
        //读取过程
        System.out.println("id = " + stu1.id); //0
        System.out.println("name = " + stu1.name); //null
        System.out.println("age = " + stu1.age); //0
        System.out.println("sex = " + stu1.sex); //false
        System.out.println("address = " + stu1.address); //null
        
        //赋值过程
        stu1.id = 1234;
        stu1.name = "Alice";
        //stu1.age = -20; 虽然程序编译运行通过, 但不符合业务要求  存在缺陷
        stu1.age = 20;
        stu1.sex = true;
        stu1.address = "Dresden";
        
        //重新读取
        System.out.println("id = " + stu1.id); //1234
        System.out.println("name = " + stu1.name); //Alice
        System.out.println("age = " + stu1.age); //20
        System.out.println("sex = " + (stu1.sex ? "女" : "男")); //true
        System.out.println("address = " + stu1.address); //Dresden
        */
        
        //创建Customer对象
        Customer c = new Customer();
        
        //由于加上private关键字, 导致age无法在外部访问
        /*
        //读取(get)
        System.out.println(c.age);
        
        //赋值(set)
        c.age = 20; //因为age属性没有任何限制, 导致外部程序可以随意访问age, 导致age不安全
        //c.age = -20; //不合法的数据
        
        //读取
        System.out.println(c.age);
        */
        
        //重新赋值
        c.setAge(20);
        
        //c.setAge(-20); //仍然不符合要求, 但是可以在class中更改方法
        
        //重新读取
        /*
        int age = c.getAge;
        System.out.println(age);
        */
        
        System.out.println(c.getAge());
    }
}