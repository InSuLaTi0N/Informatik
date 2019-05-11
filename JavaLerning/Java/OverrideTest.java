/*
    关于方法覆盖:
        -若父类方法无法满足业务需求, 需要进行重写(覆盖, Override)
        
        -子类重写父类方法后, 子类对象调用重写后的方法
        -父类对象仍然调用父类方法
        
        -发生方法覆盖的条件:
            -发生在具有继承关系的两个类之间
            -必须具有相同的方法名, 相同的返回值类型, 相同的参数列表
            -重写的方法不能比被重写的方法拥有更低的访问权限
            -重写的方法不能比被重写的方法抛出更宽泛的异常(异常机制)
            -私有的方法不能被覆盖(多态)
            -构造方法无法被覆盖
            -静态方法不存在覆盖(多态)
            -覆盖指的是成员方法, 和成员变量无关
            
        -继承的作用:
            -代码重用
            -方法可以重写
*/

public class OverrideTest
{
    public static void main(String[] args)
    {
        /*
        //创建子类对象
        Cat c = new Cat();
        
        //调用方法
        c.move();
        
        //多态
        OverrideTest a = new B();
        a.m1(); //输出method A m1
        */
        
        /*
        //多态
        OverrideTest b = new C();
        b.m1(); //输出method A m1
        */
        //静态方法不存在覆盖, 因为运行静态方法和执行和对象无关
    }
    /*
    //私有方法无法被覆盖
    private void m1()
    {
        System.out.println("A'm1 invoke");
    }
    */
    
    //静态方法无法被覆盖
    public static void m1()
    {
        System.out.println("Method A m1");
    }
}

/*
//父类
class Animal
{
    //成员方法
    public void move()
    {
        System.out.println("动物在移动");
    }
}
*/

/*
//子类
class Cat extends Animal
{
    //Cat的move方法应该输出 "猫在移动"
    //Animal中的move无法满足
    //所以在Cat类中对Animal类的方法重新定义
    //Override
    public void move()
    {
        System.out.println("猫在移动");
    }
    
    //权限更低  报错
    void move()
    {
        System.out.println("猫在移动");
    }
    
}
*/


/*
class B extends OverrideTest
{
    //尝试重写父类私有方法
    public void m1()
    {
        System.out.println("B'm1 invoke");
    }
}
*/

class C extends OverrideTest
{
    //尝试重写父类静态方法
    public static void m1()
    {
        System.out.println("Method B m1");
    }
}