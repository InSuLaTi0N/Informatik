/*
    关于Java中的向上转型(upcasting):
        - 子 ---> 父
    关于Java中的向下转型(downcasting):
        - 父 ---> 子
    注:
        无论向上转型还是向下转型, 两个类之间必须有继承关系
*/
public class PolymorphTest
{
    public static void main(String[] args)
    {
        /*
        Animal a1 = new Animal();
        a1.eat();
        
        Cat c1 = new Cat();
        c1.eat();
        
        Dog d1 = new Dog();
        d1.eat();
        
        //向上转型, 即自动类型转换
        //父类型引用指向子类型对象
        Animal a2 = new Cat();
        
        //程序在编译阶段a2被编译器看做Animal类型
        //所以程序在编译阶段a2引用绑定的是Animal类的eat方法(静态绑定)
        //程序在运行时, 堆中的对象实际是Cat类型, 而Cat重写了eat方法
        //所以程序在运行阶段对象的绑定方法是Cat中的eat方法(动态绑定)
        a2.eat();
        
        //向下转型, 即强制类型转换
        //需加强制类型转换符
        Cat c2 = (Cat)a2;
        
        //执行move方法
        c2.move();
        
        /*
        //判断程序
        Animal a3 = new Dog();
        
        Cat c3 = (Cat)a3; //java.lang.ClassCastException 类型转换异常
        */
        //做强制类型转换, 程序存在风险
        //为了避免异常, Java引入了 instanceof
        /*
            用法:
                -instanceof运算符结果为boolean类型
                -(引用 instanceof 类型) --> true/false
                
                exp: (a instanceof Cat) 若为true, a引用指向堆中的Java对象是Cat类型
        */
        
        /*
        //修改程序
        Animal a3 = new Dog();
        
        if(a3 instanceof Cat)
        {
            Cat c3 = (Cat)a3;
        }
        */
    }
}

class Person
{
    public void feed(Animal a)
    {
        a.eat();
    }
}

class Dog extends Animal
{
    //重写
    public void eat()
    {
        System.out.println("Dog is eating");
    }
}

class Cat extends Animal
{
    //重写
    public void eat()
    {
        System.out.println("Cat is eating");
    }
    
    //Cat特有的方法
    public void move()
    {
        System.out.println("Cat is running");
    }
}

class Animal
{
    //成员方法
    public void eat()
    {
        System.out.println("eating");
    }
}

//尽量不要面向具体编程, 面向父类型编程, 面向抽象编程

/*
    关于多态:
        -使用多态可使代码耦合度降低
        -项目的扩展能力增强
        -
*/