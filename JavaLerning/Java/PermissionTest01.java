package de;

import com.PermissionTest;

public class PermissionTest01
{
    public static void main(String[] args)
    {
        PermissionTest p1 = new PermissionTest();
        //System.out.println(p1.name); //protected
        //报错: name 在 PermissionTest 中是 protected 访问控制
        
        //System.out.println(p1.age); //default
        //报错: age在PermissionTest中不是公共的; 无法从外部程序包中对其进行访问
        
        /*
            在不同包下编译无法通过, 即:
                protected在不同包下无法访问
                default在不同包下也无法访问
        */
    }
}

class User extends PermissionTest
{
    //成员方法
    public void m1()
    {
        System.out.println(this.name); //protected
        //编译通过
        
        System.out.println(this.age); //default
        //报错: age在PermissionTest中不是公共的; 无法从外部程序包中对其进行访问
        
        /*
            在子类中编译情况如上, 即:
                protected可在子类中访问
                default在子类中无法访问
        */
    }
}