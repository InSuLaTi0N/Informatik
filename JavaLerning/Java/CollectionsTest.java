/*
    ����Collections������:
        -java.util.Collections; ������
        -java.util.Collection; ���Ͻӿ�
*/

import java.util.*;

public class CollectionsTest
{
    public static void main(String[] args)
    {
        //ʹ��Collections��������ɼ��ϵ�����
        List<Integer> list = new ArrayList<Integer>();
        
        //���Ԫ��
        list.add(10);
        list.add(8);
        list.add(6);
        list.add(7);
        list.add(9);
        
        //����
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        
        System.out.println("---------------------------------");
        
        //����
        Collections.sort(list);
        
        //���±���
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        
        System.out.println("---------------------------------");
        
        //��Set��������
        Set<Integer> set = new HashSet<Integer>();
        
        set.add(100);
        set.add(80);
        set.add(60);
        set.add(70);
        set.add(90);
        
        //Collections.sort(set); //����, �޷���Setֱ�ӽ�������
        //��Setת��ΪList����
        List<Integer> list2 = new ArrayList<Integer>(set);
        
        //��������
        Collections.sort(list2);
        
        //����
        for(int i = 0; i < list2.size(); i++)
        {
            System.out.println(list2.get(i));
        }
        
        
        System.out.println("---------------------------------");
        
        //����List����, List�����д洢Person����
        //Collections��������Զ�List�е�Ԫ�ؽ�������, ���Ǽ����е�Ԫ�ر����ǿɱȽϵ�(ʵ��Comparable�ӿڵ�)
        List<Person> list3 = new ArrayList<Person>();
        
        list3.add(new Person("Alpha", 15));
        list3.add(new Person("Bravo", 13));
        list3.add(new Person("Charlie", 20));
        list3.add(new Person("Delta", 10));
        list3.add(new Person("Echo", 11));
        
        //����
        Collections.sort(list3);
        
        //����
        for(int i = 0; i < list3.size(); i++)
        {
            System.out.println(list3.get(i));
        }
    }
}

class Person implements Comparable<Person>
{
    private String name;
    private int age;
    
    
    Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String toString()
    {
        return "[name -> " + name + ", age -> " + age + "]";
    }
    
    public int compareTo(Person p)
    {
        return this.age - p.getAge();
    }
}