package de;

import com.PermissionTest;

public class PermissionTest01
{
    public static void main(String[] args)
    {
        PermissionTest p1 = new PermissionTest();
        //System.out.println(p1.name); //protected
        //����: name �� PermissionTest ���� protected ���ʿ���
        
        //System.out.println(p1.age); //default
        //����: age��PermissionTest�в��ǹ�����; �޷����ⲿ������ж�����з���
        
        /*
            �ڲ�ͬ���±����޷�ͨ��, ��:
                protected�ڲ�ͬ�����޷�����
                default�ڲ�ͬ����Ҳ�޷�����
        */
    }
}

class User extends PermissionTest
{
    //��Ա����
    public void m1()
    {
        System.out.println(this.name); //protected
        //����ͨ��
        
        System.out.println(this.age); //default
        //����: age��PermissionTest�в��ǹ�����; �޷����ⲿ������ж�����з���
        
        /*
            �������б����������, ��:
                protected���������з���
                default���������޷�����
        */
    }
}