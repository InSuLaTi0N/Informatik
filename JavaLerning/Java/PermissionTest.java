/*
    ���ڷ��ʿ���Ȩ�����η�: ������, ���η���, ���α���
        -private ֻ���ڱ������
        -public �������κ�λ�÷���
        -protected ����, ͬһ������, ��ͬ���²���, �����������
        -default(ȱʡ) ����, ͬһ������, ��ͬ���²���, ���಻��
*/

package com;

public class PermissionTest //��ֻ��public �� default ��ʽ
{
    //protected����
    protected String name;
    
    //default����
    //ȱʡ
    int age;
}

/*
//default  ���ü���classǰ��, ����ȱʡ
class User
{
    //protected����
    protected String name;
    
    //default����
    //ȱʡ
    int age;
}
*/

/*
    ��������ͨ��, ��:
        protected�ڱ���ɷ���
        default�ڱ���Ҳ�ɷ���
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
    ��ͬһ�����±�������ͨ��, ��:
        protected��ͬһ�����¿ɷ���
        default��ͬһ������Ҳ���Է���
*/