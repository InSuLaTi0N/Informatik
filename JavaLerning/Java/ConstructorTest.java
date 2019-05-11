public class ConstructorTest
{
    public static void main(String[] args)
    {
        //创建User类型的对象
        User u1 = new User();
        
        User u2 = new User("Alpha"); //调用有参数的User构造函数
        
        User u3 = new User(20);
        
        User u4 = new User("Bravo", 25);
        
        System.out.println(u1.name); //初始值null
        System.out.println(u1.age); //初始值0
        
        System.out.println(u2.name); //已赋值Alpha
        System.out.println(u2.age); //初始值0
        
        System.out.println(u3.name); //初始值null
        System.out.println(u3.age); //已赋值20
        
        System.out.println(u4.name); //Bravo
        System.out.println(u4.age); //25
    }
}