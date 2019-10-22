/*
    ����Set����:
        -HashSet:
            -HashSet�ײ�ʵ������һ��HashMap, HashMap�ײ�����˹�ϣ�����ݽṹ
            -��ϣ���ֽ���ɢ�б�, ��ϣ��ײ���һ������, ��������ÿһ��Ԫ����һ����������.
            -ÿ�����������и���һ�޶��Ĺ�ϣֵ, ����������±�.
            -��ĳ�����������е�ÿһ���ڵ��ϵ�hashֵ����ȵ�.
            -hashֵʵ������key����HashCode����,��ͨ����hash funktion��ת���ɵ�ֵ.
            
            -�������ϣ�������Ԫ��:
                �ȵ��ñ��洢��key��hashCode����, �����㷨�ó�hashֵ, ����������ϣ���в��������hashֵ, ��ֱ�Ӽ����Ԫ��
                �����hashֵ�Ѿ�����, ��������key֮���equals����; ��equals��������false, �򽫸�Ԫ�����, ��equals����true
                �򸲸Ǹ�Ԫ��
                
            -HashSetʵ������HashMap�е�Key����, Key��HashSet������ͬ���ص�
            
            -������дhashCode��equals����:
                ���ڵ��õ���Object���hashCode��equals����, �Ƚϵ����ڴ��ַ
                �����ʵҵ���߼���Ҫ��д����
                
        -SortedSet:
            -�ӿ�, �洢�ص�Ϊ���򲻿��ظ�, ���Ǵ��ȥ��Ԫ�ؿ��԰���Ԫ�ش�С�Զ�����
                -���洢��Ԫ��ʵ����Comparable�ӿ�,SUN��дTreeSet���������Ԫ�ص�ʱ��,�����comepareTo������ɱȽ�.
                -Ҳ���Ե���ʵ��Comparator�ӿ�,��дһ���Ƚ���.(�����, �Ƽ�)
            -TreeSetΪʵ����
*/

import java.util.*;

public class SetTest
{
    /*
    //�������Ԫ��
    public static void main(String[] args)
    {
        //����Set����
        Set s = new HashSet();
        
        //���Ԫ��
        s.add(10);
        s.add(85);
        s.add(95);
        s.add(50);
        s.add(50);
        s.add(20);
        
        //����
        for(Iterator it = s.iterator(); it.hasNext(); )
        {
            System.out.println(it.next()); //���򲻿��ظ�
        }
    }
    */
    /*
    //������дhashCode��equals����
    public static void main(String[] args)
    {
        //��������
        Set s = new HashSet();
        
        //������Ҫ��ӵĶ���
        Employee e1 = new Employee(1, "Alpha");
        Employee e2 = new Employee(1, "Bravo");
        Employee e3 = new Employee(2, "Charlie");
        Employee e4 = new Employee(2, "Delta");
        Employee e5 = new Employee(3, "Echo");
        Employee e6 = new Employee(3, "Bravo");
        Employee e7 = new Employee(4, "Fox");
        Employee e8 = new Employee(4, "Fox");
        
        //���Ԫ��
        s.add(e1);
        s.add(e2);
        s.add(e3);
        s.add(e4);
        s.add(e5);
        s.add(e6);
        s.add(e7);
        s.add(e8);
        
        //�鿴������Ԫ�ظ���
        System.out.println(s.size()); //6 e1 e2����û����дhashCode, �Ƚϵ����ڴ��ַ,���ͬʱ����
        
        //�鿴Ԫ��hashCodeֵ
        System.out.println(e1.hashCode()); //1361960727
        System.out.println(e2.hashCode()); //739498517
    }
    */
    
    /*
    //����SortedSet
    public static void main(String[] args)
    {
        //��������
        SortedSet ints = new TreeSet();
        
        //���Integer����Ԫ��
        ints.add(10);
        ints.add(30);
        ints.add(25);
        ints.add(17);
        ints.add(57);
        ints.add(45);
        
        //����
        for(Iterator it = ints.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
        
        //��������
        SortedSet strs = new TreeSet();
        
        //���String����Ԫ��
        strs.add("Bravo");
        strs.add("Fox");
        strs.add("Charlie");
        strs.add("Echo");
        strs.add("Alpha");
        strs.add("Delta");
        
        //����
        for(Iterator it = strs.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
    }
    */
    
    //����ʵ��SortedSet�е�����
    public static void main(String[] args)
    {
        //��������
        SortedSet users = new TreeSet();
        
        //���Ԫ��
        User u1 = new User(15);
        User u2 = new User(20);
        User u3 = new User(30);
        User u4 = new User(10);
        User u5 = new User(5);
        
        //����ʱ�˴�����: java.lang.ClassCastException: class User cannot be cast to class java.lang.Comparable
        
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        
        //����
        for(Iterator it = users.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
        
        //ʹ�õڶ��ַ���: ������д�Ƚ���
        SortedSet products = new TreeSet(new ProductComparator()); //Comparator�ǽӿ�, ����ֱ��new
        
        //���Ԫ��
        Product p1 = new Product(3.4);
        Product p2 = new Product(3.5);
        Product p3 = new Product(3.7);
        Product p4 = new Product(3.3);
        Product p5 = new Product(3.1);
        
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        
        //����
        for(Iterator it = products.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
    }
}


//�����Զ�����
class Employee
{
    //����
    
    //����
    private String name;
    
    //���
    private Integer num;
    
    //���췽��
    Employee(int num, String name)
    {
        this.name = name;
        this.num = num;
    }
    
    public Integer getNum()
    {
        return num;
    }
    
    public String getName()
    {
        return name;
    }
    
    //��дequals����
    public boolean equals(Object o)
    {
        if(o == null || !(o instanceof Employee))
        {
            return false;
        }
        else
        {
            return (this.getNum().equals(((Employee) o).getNum()) && this.getName().equals(((Employee) o).getName()));
        }
    }
    
    //��дhashCode����
    public int hashCode()
    {
        //��Ա����ŷ���
        return num.hashCode();
    }
}

//�����Զ�����
//��143: �����ҪUserʵ��Comparable�ӿ�
class User implements Comparable
{
    int age;
    
    User(int age)
    {
        this.age = age;
    }
    
    public String toString()
    {
        return "age = " + age + "";
    }
    
    //�ڴ˴�ʵ�ֽӿڵķ���, ����������бȽ�
    //SUN�Ѿ��ṩ�˵��ø÷����ĳ���
    public int compareTo(Object o)
    {
        return this.age - ((User) o).age;
    }
}

class Product
{
    double price;
    
    Product(double price)
    {
        this.price = price;
    }
    
    public String toString()
    {
        return price + "";
    }
}

//������д�Ƚ���
class ProductComparator implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        double price1 = ((Product) o1).price;
        double price2 = ((Product) o2).price;
        
        if(price1 == price2)
        {
            return 0;
        }
        else
        {
            return price1 > price2 ? 1 : -1;
        }
    }
}