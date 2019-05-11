/*
    关于static关键字:
        -static修饰的变量称为"静态变量"
            -
        -static修饰的方法称为"静态方法"
            -一般情况下工具类(Math, Scanner等)中的方法大部分都是静态方法
            -静态方法不用创建对象也能直接访问该方法
        -static可以定义静态语句块
            -static定义的静态语句块在类加载阶段执行, 且只执行一次
            -执行顺序:静态语句块自上而下
            
    关于实例语句块:
        -每次调用构造方法之前执行
        -每调用一次执行一次
        -执行顺序: 自上而下
        
    关于变量:
        -局部变量
        -成员变量(一个对象一份)
            -在创建对象的时候初始化, 并存储在堆中的每一个对象中
        -静态变量(方法区)
            -在类加载阶段赋值, 并且只赋值一次
            -所有的Java对象共享一份变量
            -静态变量属于类级别, 使用"类名."方式访问
            -如果该属性所有对象拥有, 并且值相同, 那么该变量声明为静态变量
*/

public class StaticTest
{
    public static void main(String[] args)
    {
        /*
        System.out.println("main excute!");
        
        //调用构造方法
        new StaticTest();
        */
        
        /*
        //调用m1(无static关键字)
        StaticTest st1 = new StaticTest();
        st1.m1();
        
        //调用m2(有static关键字)
        //使用类名.方法名的方式(同一类下可省略类名.)
        m2();
        
        //静态方法正常使用"类名."访问
        //静态方法使用"引用."访问
        st1.m2(); //编译通过 编译阶段检查出st是StaticTest类型, 编译通过; 运行时仍然使用"StaticTest."方式访问
        //该方法执行不需要对象
        
        //空的引用访问成员时, 会发生空指针异常
        //m2为静态
        //引用为空也不会报空指针异常
        StaticTest st2 = null;
        st2.m2();
        */
    }
    /*
    //静态语句块
    static
    {
        System.out.println("1");
    }
    
    static
    {
        System.out.println("2");
    }
    
    static
    {
        System.out.println("3");
    }
    
    static
    {
        System.out.println("4");
    }
    
    //实例语句块
    {
        System.out.println("A");
    }
    
    {
        System.out.println("B");
    }
    
    {
        System.out.println("C");
    }
    */
    
    /*
    //成员方法
    //必须使用引用.调用
    public void m1(){}
    
    //静态方法
    //可以使用"类名."方式调用, 也可以使用"引用."方式调用, 底层运行时仍然使用"类名."方法
    //静态方法中不能直接访问非静态数据
    //静态方法中不能使用this
    public static void m2(){}
    */
    
    /*
    //以下程序运行报错
    static
    {
        System.out.println(i);
    }
    
    static i = 100;
    //static在类加载阶段运行 静态变量也在类加载阶段运行 顺序自上而下
    */
}