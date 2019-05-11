/*
    关于super关键字:
        -super不是引用类型, super中存储的不是内存地址
        -super指向的不是父类对象, 而是当前子类对象中的父类特征
        
        -若子类和父类中都有某个数据(例如 name, work())
         当在子类中需要访问父类中的属性, 需要使用super
         
        -super可以用在成员方法中
        -super可以用在构造方法中
            -语法: super(实参)
        -作用:
            -通过子类的构造方法调用父类的构造方法
        
        -语法规则
            -一个构造方法第一行若没有this(...); 也没有显示的去调用super(...); 系统会默认调用super();
            -super(...);的调用只能放在构造方法的第一行
            
            -super(...);和this(...)不能共存
            
            -super(...);调用了父类的方法, 但是不会创建父类对象
            
            -在Java中只要是创建对象, 那么object中的无参构造方法一定会执行
        
        -super不能用在静态方法中
        
        关于单例模式的缺点:
            -构造方法私有化
*/
public class SuperTest02
{
    public static void main(String[] args)
    {
        Manager m = new Manager();
        m.m1();
        
        DebitAccount da = new DebitAccount();
    }
}

//定义父类

class Employee
{
    //定义变量
    String name = "Alpha";
    
    //定义成员方法
    public void work()
    {
        System.out.println("Employee is working");
    }
}

//定义子类

class Manager extends Employee
{
    String name = "Bravo";
    
    //方法重载
    public void work()
    {
        System.out.println("Manager is working");
    }
    
    public void m1()
    {
        this.work();
        work();
        
        super.work();
        
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }
    
    /*
    //this和super相同, 都不能用在静态上下文中
    public static void m2()
    {
        System.out.println(super.name);
    }
    */
}

class Account
{
    //Field
    private String actno;
    private double balance;
    
    //Constructor
    public Account()
    {
        //super(); //此处第一行默认执行super()
        System.out.println("无参构造方法");
    }
    
    public Account(String actno, double balance)
    {
        //super();
        this.actno = actno;
        this.balance = balance;
    }
    
    //获取私有数据
    public void setActno(String actno)
    {
        this.actno = actno;
    }
    
    public String getActno()
    {
        return actno;
    }
    
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    public double getBalance()
    {
        return balance;
    }
}

class DebitAccount extends Account
{
    //Field
    private double debit;
    
    //Constructor
    public DebitAccount()
    {
        //super(); 默认执行
    }
    
    public DebitAccount(String actno, double balance, double debit)
    {
        //this.actno = actno; //无法调用 私有化数据
        //this.balance = balance; //无法调用 私有化数据
        
        //通过子类的构造方法去调用父类的构造方法, 作用为: 给当前子类对象中的父类型特征赋值
        super(actno, balance);
        //super(actno, balance); //报错, 只能调用一次 在第一行
        
        this.debit = debit;
    }
    
    //获取私有数据
    public void setDebit(double debit)
    {
        this.debit = debit;
    }
    
    public double debit()
    {
        return debit;
    }
}

/*
//单例模式的类型没有子类, 无法被继承
//父类
class Servlet
{
    //构造方法私有
    private Servlet(){}
}

//子类
class HttpServlet extends Servlet{}
*/