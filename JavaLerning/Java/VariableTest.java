/*
    关于参数传递:
        传递的数据可以是基本数据类型, 也可以是引用数据类型
*/

public class VariableTest
{
    public static void main(String[] args)
    {
        int i = 10;
        
        m1(i); //传递基本数据类型
        System.out.println("main--->" + i); //10
        
        Animal a = new Animal(10);
        
        m2(a); //传递引用数据类型
        
        System.out.println("main--->" + a.age); //11
    }
    
    public static void m1(int i)
    {
        i++;
        System.out.println("m1--->" + i); //11
    }
    
    public static void m2(Animal a)
    {
        a.age++;
        System.out.println("m2--->" + a.age); //11
    }
}

//构造
class Animal
{
    //Field
    int age;
    
    //Constructor
    Animal(int _age)
    {
        age = _age;
    }
}

/*
    -在传递基本数据类型的时候, 参数为基本数据, 储存在栈(Stack)中
    -调用方法, 发生压栈动作, 值被传入方法中
    -方法运行结束, 发生弹栈动作, 释放内存
    -继续运行主方法, 参数不变
    
    -在传递引用数据类型的时候, 参数指向一个地址, 该地址的值储存在堆(Heap)中
    -调用方法, 发生压栈动作, 调用参数指向同一个地址
    -方法结束, 发生弹栈动作, 但是地址中的值储存在heap中, 不会被丢弃
    -继续运行主方法, 参数指向地址中新的值
*/