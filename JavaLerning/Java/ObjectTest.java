/*
    关于对象和引用:
    
    关于Java内存:
        -java编译生成.class文件, 使用jvm读取
        -jvm管理的内存: 堆区(heap), 栈区(stack), 方法区(method)
        -方法区存放类的所有信息, 包括所有的方法, 静态变量, 常量
        -.class代码存储在方法区中
        -堆区存放new出来的对象, 此对象由垃圾收集器收集; 垃圾收集器针对的就是堆区
        -如果堆中的对象没有引用指向它, 则该对象变成了垃圾, 等待垃圾回收器回收
        -栈区存放方法, 每调用一个方法, 就会在栈区中创建一个栈帧, 存放局部变量
*/

public class ObjectTest
{
    public static void main(String[] args)
    {
        
        //u1是引用, 保存内存地址指向堆中的对象
        User u1 = new User();
        
        //u1不再指向堆中的对象, 对象变成垃圾
        u1 = null;
        
        //使用空的引用访问成员, 会发生空指针异常
        //System.out.println(u1.name); //Exception in thread "main" java.lang.NullPointerException(空指针异常)
        
        /*
        Star s = new Star();
        s = null;
        s.sing(); //报错, 空指针异常
        */
    }
}

/*
class Star
{
    //成员方法
    public void sing()
    {
        System.out.println("Sing");
    }
}
*/