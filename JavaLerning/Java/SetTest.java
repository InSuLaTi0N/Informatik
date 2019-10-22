/*
    关于Set集合:
        -HashSet:
            -HashSet底层实际上是一个HashMap, HashMap底层采用了哈希表数据结构
            -哈希表又叫做散列表, 哈希表底层是一个数组, 该数组中每一个元素是一个单向链表.
            -每个单向链表都有个独一无二的哈希值, 代表数组的下标.
            -在某个单向链表中的每一个节点上的hash值是相等的.
            -hash值实际上是key调用HashCode方法,再通过「hash funktion」转换成的值.
            
            -关于向哈希表中添加元素:
                先调用被存储的key的hashCode方法, 经过算法得出hash值, 如果在这个哈希表中不存在这个hash值, 则直接加入该元素
                如果该hash值已经存在, 继续调用key之间的equals方法; 若equals方法返回false, 则将该元素添加, 若equals返回true
                则覆盖该元素
                
            -HashSet实际上是HashMap中的Key部分, Key和HashSet具有相同的特点
            
            -关于重写hashCode和equals方法:
                由于调用的是Object类的hashCode和equals方法, 比较的是内存地址
                因此现实业务逻辑需要重写方法
                
        -SortedSet:
            -接口, 存储特点为无序不可重复, 但是存进去的元素可以按照元素大小自动排列
                -被存储的元素实现了Comparable接口,SUN编写TreeSet集合在添加元素的时候,会调用comepareTo方法完成比较.
                -也可以单独实现Comparator接口,编写一个比较器.(低耦合, 推荐)
            -TreeSet为实现类
*/

import java.util.*;

public class SetTest
{
    /*
    //关于添加元素
    public static void main(String[] args)
    {
        //创建Set集合
        Set s = new HashSet();
        
        //添加元素
        s.add(10);
        s.add(85);
        s.add(95);
        s.add(50);
        s.add(50);
        s.add(20);
        
        //遍历
        for(Iterator it = s.iterator(); it.hasNext(); )
        {
            System.out.println(it.next()); //无序不可重复
        }
    }
    */
    /*
    //关于重写hashCode和equals方法
    public static void main(String[] args)
    {
        //创建集合
        Set s = new HashSet();
        
        //创建需要添加的对象
        Employee e1 = new Employee(1, "Alpha");
        Employee e2 = new Employee(1, "Bravo");
        Employee e3 = new Employee(2, "Charlie");
        Employee e4 = new Employee(2, "Delta");
        Employee e5 = new Employee(3, "Echo");
        Employee e6 = new Employee(3, "Bravo");
        Employee e7 = new Employee(4, "Fox");
        Employee e8 = new Employee(4, "Fox");
        
        //添加元素
        s.add(e1);
        s.add(e2);
        s.add(e3);
        s.add(e4);
        s.add(e5);
        s.add(e6);
        s.add(e7);
        s.add(e8);
        
        //查看集合中元素个数
        System.out.println(s.size()); //6 e1 e2由于没有重写hashCode, 比较的是内存地址,因此同时存在
        
        //查看元素hashCode值
        System.out.println(e1.hashCode()); //1361960727
        System.out.println(e2.hashCode()); //739498517
    }
    */
    
    /*
    //关于SortedSet
    public static void main(String[] args)
    {
        //创建集合
        SortedSet ints = new TreeSet();
        
        //添加Integer类型元素
        ints.add(10);
        ints.add(30);
        ints.add(25);
        ints.add(17);
        ints.add(57);
        ints.add(45);
        
        //遍历
        for(Iterator it = ints.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
        
        //创建集合
        SortedSet strs = new TreeSet();
        
        //添加String类型元素
        strs.add("Bravo");
        strs.add("Fox");
        strs.add("Charlie");
        strs.add("Echo");
        strs.add("Alpha");
        strs.add("Delta");
        
        //遍历
        for(Iterator it = strs.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
    }
    */
    
    //关于实现SortedSet中的排序
    public static void main(String[] args)
    {
        //创建集合
        SortedSet users = new TreeSet();
        
        //添加元素
        User u1 = new User(15);
        User u2 = new User(20);
        User u3 = new User(30);
        User u4 = new User(10);
        User u5 = new User(5);
        
        //运行时此处报错: java.lang.ClassCastException: class User cannot be cast to class java.lang.Comparable
        
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        
        //遍历
        for(Iterator it = users.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
        
        //使用第二种方法: 单独编写比较器
        SortedSet products = new TreeSet(new ProductComparator()); //Comparator是接口, 不能直接new
        
        //添加元素
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
        
        //遍历
        for(Iterator it = products.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
    }
}


//创建自定义类
class Employee
{
    //属性
    
    //姓名
    private String name;
    
    //编号
    private Integer num;
    
    //构造方法
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
    
    //重写equals方法
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
    
    //重写hashCode方法
    public int hashCode()
    {
        //以员工编号分组
        return num.hashCode();
    }
}

//创建自定义类
//接143: 因此需要User实现Comparable接口
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
    
    //在此处实现接口的方法, 根据需求进行比较
    //SUN已经提供了调用该方法的程序
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

//单独编写比较器
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