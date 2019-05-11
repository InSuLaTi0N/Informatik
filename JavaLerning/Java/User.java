/*
    关于java 类的构造函数:
        -用来创建类的实例化对象
        -构造方法语法:
            [修饰符列表] 构造方法名(形参列表)
            {
                方法体;
            }
        -构造方法没有返回值类型
        -构造方法的方法名必须和类名一致
        -任何类都有构造方法, 如果没有显而易见的定义, 系统则会为该类定义一个默认的构造器, 该构造器不含任何参数
         若有定义的构造器, 系统则不会创建默认的不含参数的构造器
        
        -构造方法的作用:
            -可以创建对象
            -给成员变量赋值(初始化成员变量)
        -构造方法的调用:
            new 构造方法名(实参); //在堆中开辟空间存储对象
            注: static方法使用"类名."调用, 成员方法使用"引用."调用
        -如果一个类没有提供构造方法, 系统默认提供无参数构造方法
         如果一个类已经手动提供了构造方法, 那么系统不会再提供任何构造方法
         
        -关于成员变量赋值:
            只有在调用构造方法时, 才会给成员变量赋值
            
    关于类中可存在的内容:
        -成员变量(定义在方法外的变量)
        
        -成员方法("引用." 方式调用)
        
        -构造函数(不具有返回值)
        
        -静态方法(static)
        
        -普通方法(具有返回值)
*/

public class User
{
    //定义成员变量
    //field
    String name;
    int age;
    
    //constructor
    //定义构造方法(构造方法构成重载)
    
    User()
    {
        //System.out.println("User");
    }
    
    
    //User(String s){} //编译报错 必须传入实参
    
    User(int _age)
    {
        age = _age;
    }
    
    User(String _name)
    {
        name = _name;
    }
    
    User(String _name, int _age)
    {
        name = _name;
        age = _age;
    }
}