/*
    关于import:
        -import语句可以引入其他类
            javalerning.packagetest.PackageTest
            javalerning.packagetest.*
        
        -import语句只能出现在package语句之下, class之上
        
        -java.lang软件包下所有的类不需要手动导入, 系统自动导入
*/

package javalerning;

import javalerning.packagetest.PackageTest;
import java.util.Date;

public class ImportTest
{
    public static void main(String[] args)
    {
        //找不到PackageTest.class
        //PackageTest p1 = new PackageTest();
        
        //使用完整类名调用
        //javalerning.packagetest.PackageTest p1 = new javalerning.packagetest.PackageTest();
        //System.out.println(p1); //javalerning.packagetest.PackageTest@77556fd
        
        //创建多个对象
        //javalerning.packagetest.PackageTest p2 = new javalerning.packagetest.PackageTest();
        //System.out.println(p2);
        
        //javalerning.packagetest.PackageTest p3 = new javalerning.packagetest.PackageTest();
        //System.out.println(p3);
        
        PackageTest p4 = new PackageTest();
        System.out.println(p4); //javalerning.packagetest.PackageTest@77556fd
        
        String s1 = "string";
        System.out.println(s1); //string
        
        Object o1 = new Object();
        System.out.println(o1); //java.lang.Object@368239c8
        
        //非java.lang下的软件包必须手动import
        Date d1 = new Date();
        System.out.println(d1); //Thu May 16 17:26:04 CEST 2019
    }
}