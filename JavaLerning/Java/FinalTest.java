/*
    关于final关键字:
        -final修饰的类无法被继承
        -final修饰的方法无法被覆盖
        -final修饰的局部变量一旦被赋值, 不可再改变
        -final修饰的成员变量必须显示地初始化
        -final修饰的成员变量一般和static联用
        
        -final修饰的引用类型, 该引用不可再指向其他的Java对象
         但是final修饰的引用, 该引用指向的对象的属性是可以修改的
*/
public class FinalTest
{
    public static void main(String[] args)
    {
        final Customer c = new Customer("Alpha", 20);
        //c = new Customer("Bravo", 25); //报错: c被final修饰, 无法重新赋值
        
        c.name = "Bravo";
        c.age = 25;
        
        System.out.println(c.name + " " + c.age);
    }
}

/*
final class A{}

class B extends A{} //报错  无法从最终A继承
*/

/*
class A
{
    public final void m1(){}
}

class B extends A
{
    public void m1(){} //报错, B中的m1无法覆盖A中的m1 被覆盖方法为final
}
*/

/*
class A
{
    public void m1()
    {
        //声明
        final int i;
        
        //赋值
        i = 100;
        
        //重新赋值
        //i = 200; //报错 已分配变量i
    }
}
*/

class A
{
    //final修饰的成员变量必须手动初始化
    //final int i; //报错 变量i未在默认构造器中初始化
    final int i = 100;
    
    final int k;
    
    A()
    {
        k = 100; //初始化
    }
}

class MyMath
{
    //常量(static和final一起修饰的量)
    //java规范要求, 所有常量大写
    public static final double PI = 3.14;
}

class Customer
{
    String name;
    int age;
    
    Customer(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
}