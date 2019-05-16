/*
    关于Object类:
        -toString()方法:
            -返回Java对象的字符串表示形式
            -在开发过程中, object中的toString方法无法满足需求
            -因为object中的toString实现的结果无法令人满意
            
            -object中的toString一般会被重写(override)
            
            -toString写法:
            public String toString() {
                return getClass().getName() + "@" + Integer.toHexString(hashCode());
            }
            
            -object中的toString方法返回:
                -类名@Java对象的内存地址经过哈希算法(hashCode Algorithm)得出的int类型值再转换成十六进制
            -该输出结果可等同看作Java对象在堆(heap)中的内存地址
            
        -equals()方法:
            -Object中的equals方法:
            public boolean equals(Object obj) {
                return (this == obj);
            }
            
            -Object中的equals方法比较的是两个引用的内存地址
            
            -Java对象中的equals方法的设计目的:
                判断两个对象是否一样
                
        -finalize()方法:
            -finalize方法每个Java对象都有
            -finalize方法由系统自动调用
            -Java对象在没有更多引用指向它时, 该Java对象会成为垃圾数据(Garbage)
             等待垃圾回收器(Garbage Collection)的回收
             垃圾回收器在回收该Java对象之前会自动调用该对象的finalize方法
             
            -finalize对象可以帮助释放资源
            -在finalize方法中可以让引用重新指向该对象, 使得该对象不会被回收
            
        -hashCode()方法:
            -public native int hashCode();
            -native为本地方法调用
            -该方法不是抽象方法
            -底层调用C++代码实现, 调用动态链接库文件(.dll)
            
            -该方法返回对象的哈希码值
            -java对象的内存地址经过hashCode Algorithm得出的int类型的数值
*/

public class ObjectClassTest
{
    public static void main(String[] args)
    {
        //关于toString详解
        
        /*
        //创建object类的对象
        Object o1 = new Object();
        
        //调用toString
        String str1 = o1.toString();
        
        System.out.println(str1); //输出 java.lang.Object@5c8da962
        
        //创建Person类型的对象
        Person p1 = new Person("Alpha", 20);
        
        //调用toString
        String str2 = p1.toString();
        
        System.out.println(str2); // 输出 Person@2c13da15
        //修改后输出Person [name = Alpha, age = 20]
        
        Person p2 = new Person("Bravo", 25);
        System.out.println(p2.toString()); //输出 Person [name = Bravo, age = 25]
        
        //print方法括号中若是引用类型, 则print方法会默认调用引用类型的toString方法
        System.out.println(p2); //输出 Person [name = Bravo, age = 25]
        */
        
        /*
        //关于equals详解
        
        Object o1 = new Object();
        Object o2 = new Object();
        
        boolean b1 = o1.equals(o2);
        System.out.println(b1); //false
        
        //由于o1调用object中的equals方法, this = o1
        //o2为传递进去的参数, obj = o2
        //由于判断的是 this == obj 即 o1 == o2
        //若 == 两边是引用类型, 则比较内存地址
        //地址相同返回true, 不同返回false
        
        Star s1 = new Star("Alice", 1234);
        Star s2 = new Star("Alice", 1234);
        Star s3 = new Star("Alice", 2345);
        
        //System.out.println(s1.equals(s2)); //false  此处equals是object类中的equals  并未重写
        System.out.println(s1.equals(s2)); //true
        System.out.println(s2.equals(s3)); //false
        */
        
        //关于finalize详解
        //没有引用指向它, 等待回收
        
        //注: 程序员只能"建议"垃圾回收器回收垃圾
        //垃圾回收器有可能不会"采纳建议"
        //Runtime.getRuntime().gc();
        
        //关于hashCode
        ObjectClassTest oct1 = new ObjectClassTest();
        System.out.println(oct1.hashCode()); //1552787810
    }
}

/*
class Person
{
    String name;
    int age;
    
    Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    //重写toString方法
    //根据项目需求而定
    //例: 显示格式: Person[name = ..., age = ...]
    public String toString()
    {
        return "Person [name = " + name + ", age = " + age + "]";
    }
}
*/

/*
class Star
{
    //Field
    String name;
    int id;
    
    //Constructor
    public Star(){}
    
    public Star(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
    
    //object类中的equals方法比较的是内存地址
    //需求中应该比较的是内容
    //equals方法重写
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        
        if(obj instanceof Star)
        {
            Star s = (Star)obj;
            if(s.id == id && s.name.equals(name))
            {
                return true;
            }
        }
        
        return false;
    }
}
*/

/*
    java中关于如何比较两个字符串是否一致
    
    -java中比较两个字符串是否一致 不能用==
    -只能调用String类的equals方法
*/

/*
class StringTest
{
    public void m1()
    {
        String str1 = new String("ABC");
        String str2 = new String("ABC");
    
        System.out.println(str1 == str2); //false 原理同上 (==)
    
        System.out.println(str1.equals(str2)); //true String类中已经重写了equals方法, 比较的是内容
    }
}
*/