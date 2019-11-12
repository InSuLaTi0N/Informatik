/*
    关于接口:
        -是一种抽象类型
        -语法:
            [修饰符] interface 接口名 [extends 其他接口名]
            {
                //声明变量
                //抽象方法
            }
            
        -接口中只能出现常量, 抽象方法
        -接口其实是一个特殊的抽象类, 特殊在接口是纯抽象的
        
        -接口中没有构造方法, 接口也无法被实例化
        
        -接口和接口之间可以多继承
        
        -一个类可以实现多个接口(这里的实现等同看作继承)
        
        -一个非抽象的类实现接口, 需要将接口中所有方法"实现/重写/覆盖"
        
        -当类实现接口时, 使用implements关键字
        -implements和extends意义相同
        
        作用:
            -可以使项目分层, 所有层面向接口开发, 开发效率提高
            -接口使代码间耦合度降低
             可以随意切换
    
    关于接口和抽象类汇总:
        -抽象类和接口都不能被直接实例化, 若要实例化需使用多态
            -若抽象类实例化, 抽象类定义的变量必须指向一个子类对象, 该子类继承了抽象类并实现了所有抽象方法
            -若接口实例化, 该接口定义的变量要指向一个子类对象, 该子类必须实现了该接口所有的方法
        -抽象类要被子类继承, 接口要被子类实现
        
        -接口中只能对方法进行声明, 抽象类既可以对方法声明也可以对方法进行实现
        
        -抽象类中的抽象方法必须全部被子类实现, 若不能全部实现, 那么子类也必须是抽象类
        -接口中的方法也必须全部被子类实现, 若子类不能实现则子类必须是抽象类
        
        -接口中的方法只能声明, 不能有具体的实现
            -说明接口是设计的结果, 抽象类是重构的结果
            
        -抽象类中可以没有抽象方法
        
        -若一个类中有抽象方法, 则该类一定是抽象类
        
        -抽象类中的方法都要被实现, 所以抽象方法不能是静态(static), 私有(private)
        
        -接口可以继承多个接口, 类只能继承一个类
        
        -抽象级别: 接口>抽象类>实例类
        
        -抽象类主要用来抽象类别, 接口主要用来抽象方法功能
        -当关注事物本质时, 使用抽象类; 当关注操作时, 使用接口
        
        -定义抽象类代价较高: Java中一个类只能继承一个父类
         在设计这个类的时候必须要抽象出所有这个类的子类所具有的共同属性和方法
        -接口可继承多个接口, 每个接口只需要将特定的动作方法抽象到这个接口即可
        
        -接口的设计具有更大的可扩展性，而抽象类的设计必须十分谨慎
*/

public class InterfaceTest
{
    public static void main(String[] args)
    {
        CustomerService cs = new CustomerServiceImpl(); //多态 接口--->接口实现类
        cs.logout();
        
        Engine e1 = new HONDA(); //多态 接口--->接口实现类 (抽象引擎--->实例化引擎)
        
        Car c = new Car(e1); //实例 汽车
        c.testEngine(); //汽车类测试方法
        
        c.e = new YAMAHA(); //替换接口实现类(引擎)
        c.testEngine();
        
        
    }
}

/*
public interface InterfaceTest
{
    
    //常量 必须用public static final修饰
    public static final String SUCCESS = "Success";
    public static final double PI = 3.14;
    
    //public static final 是可以省略的
    byte MAX_VALUE = 127; //常量, 不是成员变量
    
    //抽象方法:(接口中所有的抽象方法都是public abstract)
    public abstract void m1();
    
    //public abstract可以省略
    void m2();
    
}
*/

/*
interface A
{
    void m1();
}

interface B
{
    void m2();
}

interface C
{
    void m3();
}

interface D extends A,B,C
{
    void m4();
}

class MyClass implements A,B
{
    public void m1(){}
    public void m2(){}
}

class MyInterface implements D
{
    public void m1(){}
    public void m2(){}
    public void m3(){}
    public void m4(){}
}
*/

//关于接口作用
//定义一个接口
/*public*/
interface CustomerService
{
    //定义退出系统方法
    void logout();
}

//定义接口实现类
//面向接口实现
/*public*/
class CustomerServiceImpl implements CustomerService
{
    //对接口中的抽象方法实现
    public void logout()
    {
        System.out.println("logout");
    }
}

//定义一个接口: 汽车引擎
interface Engine
{
    void start();
}

//定义实现该接口的类
class HONDA implements Engine
{
    public void start()
    {
        System.out.println("HONDA");
    }
}

//定义实现接口的替换类
class YAMAHA implements Engine
{
    public void start()
    {
        System.out.println("YAMAHA");
    }
}

//定义实例类
class Car
{
    //Field
    //面向接口编程
    Engine e;
    
    //Constructor
    Car(){}
    
    Car(Engine e)
    {
        this.e = e;
    }
    
    //测试引擎
    public void testEngine()
    {
        e.start(); //面向接口调用
    }
}