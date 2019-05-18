/*
    关于访问控制权限修饰符: 修饰类, 修饰方法, 修饰变量
        -private 只能在本类访问
        -public 可以在任何位置访问
        -protected 本类, 同一个包下, 不同包下不行, 但是子类可以
        -default(缺省) 本类, 同一个包下, 不同包下不行, 子类不行
*/

package com;

public class PermissionTest //类只有public 和 default 形式
{
    //protected修饰
    protected String name;
    
    //default修饰
    //缺省
    int age;
}

/*
//default  不用加在class前面, 属于缺省
class User
{
    //protected修饰
    protected String name;
    
    //default修饰
    //缺省
    int age;
}
*/

/*
    编译运行通过, 即:
        protected在本类可访问
        default在本类也可访问
*/

class Test01
{
    public static void main(String[] args)
    {
        PermissionTest p1 = new PermissionTest();
        
        System.out.println(p1.name);
        System.out.println(p1.age);
    }
}

/*
    在同一个包下编译运行通过, 即:
        protected在同一个包下可访问
        default在同一个包下也可以访问
*/