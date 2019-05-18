/*
    关于内部类:
        -可以访问外部类中私有的数据
        
        -内部类分类如下:
            -静态内部类:
                -可以等同看作静态变量
                -可以直接访问外部类的静态数据, 无法直接访问成员
                
            -成员内部类:
                -可以等同看作成员变量
                -不能有静态声明
                -可以访问外部类所有的数据
            
            -局部内部类:
                -可以等同看作局部变量
                -在访问局部变量的时候, 局部变量必须使用final修饰或是实际的最终变量
            
            -匿名内部类:
                -指类没有名字
                -某些情况下可以代替实现接口的子类, 使得少定义一个类
                -无法重复使用
*/

public class InnerClassTest
{
    /*
    //关于静态内部类
    
    //定义静态变量
    private static String s1 = "Alpha";
        
    //定义成员变量
    private String s2 = "Bravo";
        
    //定义静态方法
    private static void m1()
    {
        System.out.println("static m1 execute");
    }
        
    //定义成员方法
    private void m2()
    {
        System.out.println("member m2 execute");
    }
    
    //主方法
    public static void main(String[] args)
    {
        //访问m3
        InnerClassTest.InnerClass.m3();
        InnerClass.m3(); //省略写法
        
        //访问m4
        InnerClass inner1 = new InnerClassTest.InnerClass();
        inner1.m4();
        
        InnerClass inner2 = new InnerClass(); //省略写法
        inner2.m4();
    }
        
    //定义静态内部类
    //可以用访问控制权限修饰符修饰
    //public, protected, private, 缺省
    static class InnerClass
    {
        //尝试访问外部私有数据
            
        //定义静态方法
        public static void m3()
        {
            //访问外部s1
            System.out.println(s1); //可以访问
            //System.out.println(s2); //错误: 无法从静态上下文中引用非静态 变量 s2
            
            m1(); //可以访问
            //m2(); //错误: 无法从静态上下文中引用非静态 方法 m2()
        }
            
        //定义成员方法
        public void m4()
        {
            System.out.println(s1);
            //System.out.println(s2); //错误: 无法从静态上下文中引用非静态 变量 s2
            
            m1();
            //m2(); //错误: 无法从静态上下文中引用非静态 方法 m2()
        }
    }
    */
    
    /*
    //关于成员内部类
    
    //定义静态变量
    private static String s1 = "Alpha";
        
    //定义成员变量
    private String s2 = "Bravo";
        
    //定义静态方法
    private static void m1()
    {
        System.out.println("static m1 execute");
    }
        
    //定义成员方法
    private void m2()
    {
        System.out.println("member m2 execute");
    }
    
    //主方法
    public static void main(String[] args)
    {
        
        //InnerClass inner1 = new InnerClassTest.InnerClass(); //错误: 无法从静态上下文中引用非静态 方法 m4()
        //inner1.m4(); //首先要创建外部类对象
        
        
        InnerClassTest ict = new InnerClassTest();
        InnerClass inner = ict.new InnerClass();
        inner.m4();
    }
        
    //定义成员内部类
    //可以用访问控制权限修饰符修饰
    //public, protected, private, 缺省
    class InnerClass
    {
        //尝试访问外部私有数据
            
        
        //定义静态方法
        //public static void m3(){} //错误: 内部类InnerClassTest.InnerClass中的静态声明非法
        
            
        //定义成员方法
        public void m4()
        {
            System.out.println(s1);
            System.out.println(s2);
            
            m1();
            m2();
            
            //以上编译通过
        }
    }
    */
    
    /*
    //关于局部内部类
    
    public void m1()
    {
        //局部变量
        int i = 10;
        
        //定义局部内部类
        //局部内部类不能用访问控制权限修饰符修饰
        //不能用public, protected, private
        class InnerClass
        {
            //定义静态方法
            //public static void m2(){} //错误: 内部类InnerClass中的静态声明非法
            
            //定义成员方法
            public void m3()
            {
                System.out.println(i);
                //i = 20;
                //System.out.println(i); //错误: 从内部类引用的本地变量必须是最终变量或实际上的最终变量
            }
        }
        
        //调用m3方法
        //由于是局部内部类, 无法在主方法中访问
        InnerClass inner = new InnerClass();
        inner.m3();
    }
    
    //主方法
    public static void main(String[] args)
    {
        //调用m1方法
        InnerClassTest ict = new InnerClassTest();
        ict.m1();
    }
    */
    
    //关于匿名内部类
    
    //静态方法
    public static void m1(CustomerService cs)
    {
        cs.logout();
    }
    
    //主方法
    public static void main(String[] args)
    {
        //调用test方法
        //CustomerService cs = new Customer();
        //m1(cs);
        
        //使用匿名内部类的方式执行m1方法
        //m1(new CustomerService()); //报错, 接口无法创建对象
        //引入匿名内部类
        m1(new CustomerService()
        {
            public void logout()
            {
                System.out.println("logout");
            }
        }); //new CustomerService(){}就是匿名内部类
    }
}

//定义接口
interface CustomerService
{
    void logout();
}

/*
//由于引入匿名内部类, 可以代替实现接口的类
//定义实现接口的子类
class Customer implements CustomerService
{
    public void logout()
    {
        System.out.println("logout");
    }
}
*/