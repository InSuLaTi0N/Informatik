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
*/

public class InterfaceTest
{
    public static void main(String[] args)
    {
        CustomerService cs = new CustomerServiceImpl(); //多态 接口--->接口实现类
        cs.logout();
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