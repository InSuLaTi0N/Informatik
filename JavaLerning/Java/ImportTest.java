/*
    ����import:
        -import����������������
            javalerning.packagetest.PackageTest
            javalerning.packagetest.*
        
        -import���ֻ�ܳ�����package���֮��, class֮��
        
        -java.lang����������е��಻��Ҫ�ֶ�����, ϵͳ�Զ�����
*/

package javalerning;

import javalerning.packagetest.PackageTest;
import java.util.Date;

public class ImportTest
{
    public static void main(String[] args)
    {
        //�Ҳ���PackageTest.class
        //PackageTest p1 = new PackageTest();
        
        //ʹ��������������
        //javalerning.packagetest.PackageTest p1 = new javalerning.packagetest.PackageTest();
        //System.out.println(p1); //javalerning.packagetest.PackageTest@77556fd
        
        //�����������
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
        
        //��java.lang�µ�����������ֶ�import
        Date d1 = new Date();
        System.out.println(d1); //Thu May 16 17:26:04 CEST 2019
    }
}