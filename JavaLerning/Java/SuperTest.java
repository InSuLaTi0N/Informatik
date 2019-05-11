/*
    关于类的继承:
        -引入类继承最基本的作用是: 代码重用
        
        -语法:
            [修饰符列表] class 子类名 extends 父类名
            {
                类体;
            }
            
        -java中, 类和类之间只支持单继承
        -一个类若没有显示的继承其他类, 则该类默认继承Object
        -Object是SUN提供的Java中的根类
        
        -继承类被称子类, 被继承类被称为父类
        -java中子类继承父类, 会将父类中所有的数据全部继承, 包括私有数据
        -但是在子类中无法直接访问父类中的私有数据, 但是可以间接访问
        
        -构造方法无法被子类继承
*/

public class SuperTest
{
    public static void main(String[] args)
    {
        /*
        Sub s = new Sub();
        
        s.m1();
        s.m2();
        s.m3();
        */
        Sub s1 = new Sub();
        s1.setActno("23456");
        
        //Syetem.out.println(s1.actno); //报错 不能直接访问
        System.out.println(s1.getActno());
    }
}
/*
class Sub
{
    public void m1()
    {
        System.out.println("m1...");
    }
    
    public void m2()
    {
        System.out.println("m2...");
    }
    
    public void m3()
    {
        System.out.println("m3...");
    }
}
*/
//以上代码繁琐
//引入继承
class Sub extends SuperClass
{
    double debit;
    /*
    public void m3()
    {
        System.out.println("m3...");
    }
    */
}

class SuperClass
{
    private String actno;
    double balance;
    
    //Constructor
    SuperClass(){}
    
    SuperClass(String actno, double balance)
    {
        this.actno = actno;
        this.balance = balance;
    }
    
    //set and get
    public void setActno(String actno)
    {
        this.actno = actno;
    }
    
    public String getActno()
    {
        return actno;
    }
    /*
    public void m1()
    {
        System.out.println("m1...");
    }
    
    public void m2()
    {
        System.out.println("m2...");
    }
    */
}