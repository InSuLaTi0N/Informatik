/*
    关于异常:
        -在程序运行过程中出现的错误被称为异常
        
        -java中采用类的方式模拟异常
        -类可以创建对象, 例如:
            NullPointerException e = 0x1234;
            e是引用类型, e中保存的内存地址指向堆中的对象
            这个对象是NullPointerException类型
            该对象表示真实存在的异常事件
            NullPointerException是一类异常
            
        -Java为人们提供了一种完善的异常处理机制
        -程序发生异常事件之后, 为我们输出详细的信息
        -通过这个信息可以对程序进行改善处理, 使程序更加完善
        
        Object父类:
            -Throwable子类继承Object, 其表示所有的异常均可抛出; 
            -被以下类继承:
                -Error
                    -Java程序运行过程中如果出现了错误, 错误是不能处理的, 只能退出JVM, 例如: StackOverflowError
                    
                -Exception
                    -Exception可处理, 如果没有处理异常, 则程序直接退出JVM
                    
                    -分为以下两类:
                        -编译时异常:
                            -所有Exception的直接子类都是编译时异常
                            -发生几率较高
                            -所有的编译时异常, 要求程序员在编写阶段必须对它进行处理, 若不处理则编译无法通过
                            -处理异常有两种方式:
                                -捕捉: try...catch
                                -声明抛出: 在方法声明的位置上使用throws关键字抛出异常
                                
                        -运行时异常(RuntimeException)
                            -所有的RuntimeException的子类都是运行时异常
                            -发生几率较低
                            -运行时异常, 程序员在编写阶段不需要对它进行处理
*/

public class ExceptionTest
{
    public static void main(String[] args)
    {
        //异常举例
        int a = 10;
        int b = 0;
        
        System.out.println(a / b); //java.lang.ArithmeticException 算术异常
        
        System.out.println("Test"); //该语句未执行
        
        //以上程序编译通过, 但是运行时发生上述异常, 表示发生某个异常事件
        //本质: 程序执行过程中发生了算术异常, JVM创建了一个ArithmeticException类型的对象
        //并且该对象包含了详细的异常信息, 且JVM将这个对象中的信息输出到控制台
        //当发生异常时, 若异常未处理, 异常之后的语句不会执行
    }
}