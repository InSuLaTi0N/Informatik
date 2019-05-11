/*
    关于抽象类:
        -定义抽象类方法:
            abstract class ..{}
        -抽象类无法被实例化
        
        -虽然抽象类无法实例化, 但是抽象类也有构造方法, 作用是给子类创建对象
        
        -抽象类中可以定义抽象方法
        -抽象方法的语法:
            -在方法的修饰符列表中添加abstract关键字, 并且抽象方法以;结束, 不能带有{}
             例: public abstract void m1();
        
        -抽象类中不一定有抽象方法, 但抽象方法必须存在于抽象类中
        -一个非抽象的类继承抽象类, 必须将抽象类中的抽象方法覆盖, 实现, 重写
*/
public abstract class AbstractTest
{
    public static void main(String[] args)
    {
        
    }
    
    //public abstract void m1(){} //报错: 抽象方法不能有主体
    public abstract void m1();
}
/*
abstract class A
{
    //Constractor
    A()
    {
        System.out.println("A");
    }
    public void m1()
    {
        //A a = new A(); //报错: A是抽象的 无法实例化 即无法创建对象
        
        //多态
        A a = new B();
    }
    
    public abstract void m2(); //报错: B不是抽象的, 并且未覆盖A中的抽象方法m2
}

class B extends A
{
    B()
    {
        //此处执行了super();
        //父类的构造方法虽然调用了, 但是并没有创建父类对象
        System.out.println("B");
    }
}
*/