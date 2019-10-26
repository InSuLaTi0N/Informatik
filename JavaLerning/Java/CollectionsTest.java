/*
    关于Collections工具类:
        -java.util.Collections; 工具类
        -java.util.Collection; 集合接口
*/

import java.util.*;

public class CollectionsTest
{
    public static void main(String[] args)
    {
        //使用Collections工具类完成集合的排序
        List<Integer> list = new ArrayList<Integer>();
        
        //添加元素
        list.add(10);
        list.add(8);
        list.add(6);
        list.add(7);
        list.add(9);
        
        //遍历
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        
        System.out.println("---------------------------------");
        
        //排序
        Collections.sort(list);
        
        //重新遍历
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        
        System.out.println("---------------------------------");
        
        //对Set集合排序
        Set<Integer> set = new HashSet<Integer>();
        
        set.add(100);
        set.add(80);
        set.add(60);
        set.add(70);
        set.add(90);
        
        //Collections.sort(set); //报错, 无法对Set直接进行排序
        //将Set转换为List集合
        List<Integer> list2 = new ArrayList<Integer>(set);
        
        //重新排序
        Collections.sort(list2);
        
        //遍历
        for(int i = 0; i < list2.size(); i++)
        {
            System.out.println(list2.get(i));
        }
        
        
        System.out.println("---------------------------------");
        
        //创建List集合, List集合中存储Person类型
        //Collections工具类可以对List中的元素进行排序, 但是集合中的元素必须是可比较的(实现Comparable接口等)
        List<Person> list3 = new ArrayList<Person>();
        
        list3.add(new Person("Alpha", 15));
        list3.add(new Person("Bravo", 13));
        list3.add(new Person("Charlie", 20));
        list3.add(new Person("Delta", 10));
        list3.add(new Person("Echo", 11));
        
        //排序
        Collections.sort(list3);
        
        //遍历
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