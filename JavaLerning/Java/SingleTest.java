/*
    关于单例模式:
        -属于设计模式
        -设计模式是可以重复利用的解决方案
        
        -是23种设计模式中最简单的一种
        -为了保证java虚拟机(jvm)中某一个类型的java对象永远只有一个
        
        -为了节省内存的开销
        
        -主要分为两种单例模式:
            -饿汉式单例
                -类加载阶段就创建了对象
            -懒汉式单例
                -用到对象才会创建
        
    实现单例模式:
        -构造方法私有化
        -对外提供一个公开(public)静态(static)的获取当前对象的方法
        -提供一个当前类型的静态变量
*/

public class SingleTest
{
    public static void main(String[] args)
    {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
        
        Singleton1 c1 = Singleton1.getInstance();
        Singleton1 c2 = Singleton1.getInstance();
        System.out.println(c1 == c2);
    }
}

//实现单例模式
//懒汉式单例
class Singleton
{
    //将构造方法私有化
    private Singleton(){}
    
    //只初始化一次s, 使用静态变量
    private static Singleton s;
    
    //对外提供一个公开获取Singleton对象的方法
    public  static Singleton getInstance()
    {
        //判断s
        if(s == null)
        {
            s = new Singleton();
        }
        
        return s;
    }
}

//饿汉式单例
class Singleton1
{
    //创建变量
    //类加载只执行一次
    private static Singleton1 c = new Singleton1();
    
    //构建方法私有化
    private Singleton1(){}
    
    //提供公开的方法
    public static Singleton1 getInstance()
    {
        return c;
    }
}