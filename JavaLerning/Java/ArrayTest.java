/*
    关于数组:
        -是一种引用类型
        -是一种简单的, 线性的数据结构
        -数组是一个容器, 可用来存储其他任意数据类型的元素
        
        -数组可分为一维数组, 多维数组..
        
        -数组中存储元素的类型是统一的, 每一个元素在内存中所占的空间大小是相同的
        
        -数组长度不可改变
        
        -数组中使用首元素的内存地址作为数组对象的内存地址
        
        -数组中每一个元素都有下标, 从0开始
        -任何一个数组都有一个length属性用来获取数组中元素的个数
        -数组一般通过元素的下标获取元素, 例如:
            -取得第一个元素:
                a1[0]
            -取得最后一个元素:
                a1[a1.length-1]
        
        -查找效率高, 随意增删元素效率较低
        
        -关于一维数组:
            -关于初始化一维数组的方式:
                -静态初始化
                    -在创建数组时, 数组中的元素确定, 则使用静态初始化
                    -数据类型 [] 变量名 = {元素间用,隔开};
                    -例: int [] a = {1, 2, 3};
                -动态初始化
                    -在创建数组时, 数组中元素不确定, 仅先开辟空间, 则使用动态初始化
                    -数据类型 [] 变量名 = new 数据类型 [数组长度];
                    -例: Object [] obj = new Object [5];
                    
        -关于二维数组:
            -数组中每一个元素都是一维数组
                    
    关于main方法中的参数列表 String[] args:
        -String[] args 是用来接收命令行参数的, 例如:
            java ArrayTest a b c d
        -JVM在调用ArrayTest中的main方法之前, 先将"a b c d"这个字符串以"空格"的方式分割, 然后存储在String数组中
*/

public class ArrayTest
{
    /*
    //关于静态初始化一维数组
    
    public static void main(String[] args)
    {
        //关于一维数组
        //声明一维数组, 存储int类型
        int [] a1 = {100, 200, 150, 300}; //该方式称作"静态初始化一维数组"
        //a1引用中保存的是一维数组的首元素的内存地址
        
        //boolean类型的数组
        boolean [] b1 = {true, false, true};
        
        //String类型的数组
        String [] strs = {"Alpha", "Bravo", "Charlie"};
        
        //byte类型的数组
        byte [] b2 = {1, 2, 3};
        
        //char类型的数组
        char [] c1 = {'A', 'B', 'C'};
        
        //object类型的数组
        Object o1 = new Object(); //创建一个object对象, 使得o1引用指向该对象的地址
        Object o2 = new Object();
        Object o3 = new Object();
        
        Object [] obj = {o1, o2, o3}; //此处obj引用指向的地址是o1的内存地址, 不是o1指向的对象的内存地址, 两者不相同
        
        System.out.println(a1[2]); //取第三个元素
        System.out.println(a1[a1.length-1]); //取最后一个元素
        System.out.println(a1.length);
        
        //遍历一维数组
        for(int i = 0; i < a1.length; i++)
        {
            System.out.println(a1[i]);
        }
        
        //将第2个元素改为100
        a1[1] = 100;
        
        System.out.println(a1[1]);
    }
    */
    
    /*
    //关于动态初始化一维数组
    //会先在堆内存中分配这个数组, 并且数组中每一个元素都采用默认值
    
    public static void main(String[] args)
    {
        //声明一个int类型的一维数组
        int [] a1 = new int [4]; //该方式称作"动态初始化一维数组", 最多可以存储4个元素
        
        //遍历
        for(int i = 0; i < a1.length; i++)
        {
            System.out.println(a1[i]); //0 0 0 0
        }
        
        //赋值
        a1 [0] = 20;
        a1 [1] = 21;
        a1 [2] = 22;
        a1 [3] = 23;
        
        //重新遍历
        for(int i = 0; i < a1.length; i++)
        {
            System.out.println(a1[i]); //20 21 22 23
        }
        
        //引用类型的数组
        Object [] obj = new Object [3];
        
        //遍历
        for(int i = 0; i < obj.length; i++)
        {
            Object o = obj [i];
            //o.toString(); //错误: Exception in thread "main" java.lang.NullPointerException
            //引用类型默认值为null, 因此使用时要注意空指针异常
            System.out.println(o); //null null null
        }
    }
    */
    
    /*
    //关于一维数组中元素的类型
    
    public static void main(String[] args)
    {
        //需求: 遍历数组, 取出每个对象, 若对象为Dog执行eat, 为Cat执行move
        //创建Animal类型的数组, 由于Animal是Dog和Cat的父类, 可使用多态
        Animal [] a = new Animal [5];
    
        //给数组每个元素赋值
        Dog d1 = new Dog();
        Dog d2 = new Dog();
        Cat c1 = new Cat();
        Cat c2 = new Cat();
    
        a[0] = d1;
        a[1] = d2;
        a[2] = c1;
        a[3] = c2;
    
        //遍历数组
        for(int i = 0; i < a.length; i++)
        {
            //判断类型做强制类型转换
            if(a[i] instanceof Dog)
            {
                Dog d = (Dog) a[i];
                d.eat();
            }
            else if(a[i] instanceof Cat)
            {
                Cat c = (Cat) a[i];
                c.move();
            }
            else
            {
                System.out.println(a[i]);
            }
        }
    }
    */
    
    /*
    //关于调用方法, 传递数组
    
    public static void main(String[] args)
    {
        //调用m1方法, 传递数组作为参数
        //第一种方法
        int [] a = {1, 2, 3, 4, 5};
        m1(a);
        
        //第二种方法
        m1(new int [] {6, 7, 8, 9, 10});
    }
    
    public static void m1(int[] a)
    {
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
    */
    
    /*
    //关于main方法参数列表
    
    public static void main(String[] args)
    {
        System.out.println("args数组长度为: " + args.length);
        
        //遍历
        for(int i = 0; i < args.length; i++)
        {
            System.out.println(args[i]);
        }
    }
    */
    
    /*
    //关于数组的拷贝
    //方法: System.arraycopy(源数组, 源数组的开始下标, 目标数组, 目标数组的开始下标, 拷贝的长度);
    
    public static void main(String[] args)
    {
        int [] src = {2,3,4,5,6,7,8};
        int [] dest = {10,11,12,13,14,15,16};
        
        //将src中345拷贝至dest中, 替换13 14 15
        System.arraycopy(src, 1, dest, 3, 3);
        
        for(int i = 0; i < dest.length; i++)
        {
            System.out.println(dest[i]);
        }
    }
    */
    
    /*
    //尝试写数组拷贝方法
    
    public static void main(String[] args)
    {
        int[] src = {1,2,3,4,5};
        int[] dest = {11,12,13,14,15};
        
        m1(src, 1, dest, 2, 3);
        for(int i = 0; i < dest.length; i++)
        {
            System.out.println(dest[i]);
        }
    }
    
    public static void m1(int[] src, int srcstart, int[] dest, int deststart, int copylength)
    {
        for(int i = srcstart; i < srcstart + copylength; i++)
        {
            dest[deststart] = src[i];
            deststart++;
        }
    }
    */
    
    /*
    //关于二维数组
    public static void main(String[] args)
    {
        //二维数组静态初始化
        int[][] a = {
                        {1, 2, 3}, 
                        {41, 52, 63}, 
                        {7, 865, 9},
                        {00}
                    };
                    
        //对二维数组遍历
        for(int i = 0; i < a.length; i++)
        {
            for(int j = 0; j < a[i].length; j++)
            {
                System.out.print(a[i][j] + "\t");
            }
            
            System.out.println();
        }
        
        //二维数组动态初始化
        int[][] b = new int[3][4]; //表示有3个一维数组, 每个一维数组有4个元素
    }
    */
    
    //使用数组模拟栈(Stack)
    //后进先出 先进后出
    public static void main(String[] args)
    {
        Stack s = new Stack();
        
        Name n1 = new Name("A");
        Name n2 = new Name("B");
        Name n3 = new Name("C");
        Name n4 = new Name("D");
        Name n5 = new Name("E");
        Name n6 = new Name("F");
        
        try
        {
            s.push(n1);
            s.push(n2);
            s.push(n3);
            s.push(n4);
            s.push(n5);
            
            //s.push(n6); //java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
        }
        catch(StackException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            for(int i = 1; i <= 5; i++)
            {
                System.out.println(s.pop());
            }
            
            //s.pop(); //java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 5
        }
        catch(StackException e)
        {
            e.printStackTrace();
        }        
    }
}

/*
class Animal
{
    
}

class Dog extends Animal
{
    public void eat()
    {
        System.out.println("dog is eating");
    }
}

class Cat extends Animal
{
    public void move()
    {
        System.out.println("cat is moving");
    }
}
*/

class Stack
{
    //属性
    //模拟栈, 可以存储多个引用类型的元素
    Object[] elements;
    
    //指向栈顶元素上方的栈帧
    int index;
    
    //构造方法
    Stack()
    {
        this(5);
    }
    
    Stack(int max)
    {
        elements = new Object[max];
    }
    
    //对外提供压栈的方法
    public void push(Object element) throws StackException
    {
        if(index == elements.length)
        {
            throw new StackException("Stackoverflow");
        }
        elements[index++] = element;
    }
    
    //对外提供一个弹栈的方法
    public Object pop() throws StackException
    {
        if(index == 0)
        {
            throw new StackException("NullStack");
        }
        return elements[--index];
    }
}

class Name
{
    String name;
    
    Name(){}
    
    Name(String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name;
    }
}

class StackException extends Exception
{
    public StackException(){}
    public StackException(String msg)
    {
        super(msg);
    }
}