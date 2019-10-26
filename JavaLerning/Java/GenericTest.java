/*
    关于泛型:
        -编译期检查类型
        
        -可以统一集合中的数据类型
        -可以减少强制类型转换
        
        -只能存储一种数据类型
        
    关于增强for循环:
        - for(类型 变量 : 数组名/集合名){}
        
        -集合若想使用这种语法, 则需要使用泛型
*/

import java.util.*;

public class GenericTest
{
    /*
    public static void main(String[] args)
    {
        //此处不使用泛型
        Set set = new HashSet();
        
        set.add(new A());
        set.add(new B());
        set.add(new C());
        
        //遍历集合, 若是A则调用A的方法, B调用B的方法等
        for(Iterator it = set.iterator(); it.hasNext(); )
        {
            Object o = it.next();
            
            if(o instanceof A)
            {
                A a = (A)o;
                a.m1();
            }
            else if(o instanceof B)
            {
                B b = (B)o;
                b.m1();
            }
            else
            {
                C c = (C)o;
                c.m1();
            }
        }
        
        //以上集合未使用泛型, 集合中元素类型不统一, 在遍历集合时需要做大量强制类型转换
    }
    */
    
    //关于泛型语法
    /*
    public static void main(String[] args)
    {
        //创建一个List集合, 只能存储String类型
        List<String> list = new ArrayList<String>();
        
        //list.add(1); //错误: 不兼容的类型: int无法转换为String
        //编译期语法
        
        //添加元素
        list.add("ABC");
        list.add("DEF");
        list.add("GHI");
        list.add("JKL");
        list.add("MNO");
        
        //遍历集合
        for(Iterator<String> it = list.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }
        
    }
    */
    /*
    public static void main(String[] args)
    {
        //创建一个Map集合, key存储String, value存储Integer
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        //存储元素
        map.put("Alpha", 20);
        map.put("Bravo", 5);
        map.put("Charlie", 25);
        map.put("Delta", 15);
        map.put("Echo", 10);
        
        //遍历Map
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println("key --> " + entry.getKey() + ", value --> " + entry.getValue());
        }
    }
    */
    /*
    public static void main(String[] args)
    {
        //SortedSet集合使用泛型
        SortedSet<Manager> ss = new TreeSet<Manager>();
        
        ss.add(new Manager("Alpha", 500.1));
        ss.add(new Manager("Bravo", 734.1));
        ss.add(new Manager("Charlie", 395.8));
        ss.add(new Manager("Delta", 406.0));
        ss.add(new Manager("Echo", 651.8));
        
        //遍历
        for(Iterator<Manager> it = ss.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }
    }
    */
    /*
    public static void main(String[] args)
    {
        //关于自定义泛型
        MyClass<String> mc = new MyClass<String>();
        
        //输出
        mc.m1("Hallo");
    }
    */
    /*
    //关于foreach
    public static void main(String[] args)
    {
        int[] array = {1,2,3,4,5};
        
        //遍历数组
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }
        
        System.out.println("----------------------------");
        
        //使用增强for循环
        for(int e : array) //int e 代表的是数组或集合中的每一个元素
        {
            System.out.println(e);
        }
        
        System.out.println("----------------------------");
        
        //集合使用增强for循环遍历
        Set<String> set = new HashSet<String>();
        
        set.add("ABC");
        set.add("DEF");
        set.add("GHI");
        set.add("JKL");
        set.add("MNO");
        
        for(String wort : set)
        {
            System.out.println(wort);
        }
        
        System.out.println("----------------------------");
        
        //集合不使用泛型
        List list = new ArrayList();
        
        list.add(12);
        list.add(34);
        list.add(56);
        list.add(78);
        list.add(90);
        
        //若集合不使用泛型, 则集合在用for循环增强时应该用Object定义
        //具体需要时做强制类型转换
        for(Object element : list)
        {
            System.out.println(element);
        }
    }
    */
    
    //关于foreach的缺点
    public static void main(String[] args)
    {
        String[] course = {"Algorithm", "Algebra", "Multimedia", "Discret Structure"};
        
        //连接字符串
        
        //创建字符串缓冲区对象
        StringBuffer sb = new StringBuffer();
        
        /*
        //进行连接
        for(int i = 0; i < course.length; i++)
        {
            if(i == course.length - 1)
            {
                sb.append(course[i]);
            }
            else
            {
                sb.append(course[i]);
                sb.append(", ");
            }
        }
        
        //输出
        System.out.println(sb);
        */
        
        //由于增强for没有下标, 以上方法不可用
        for(String s : course)
        {
            sb.append(s);
            sb.append(", ");
        }
        
        //最后只能多输出一个", "  然后使用String类substring方法将其截取
        System.out.println(sb.substring(0, sb.length() - 2));
    }
}

/*
class A
{
    public void m1()
    {
        System.out.println("A ---> m1");
    }
}

class B
{
    public void m1()
    {
        System.out.println("B ---> m1");
    }
}

class C
{
    public void m1()
    {
        System.out.println("C ---> m1");
    }
}
*/
/*
class Manager implements Comparable<Manager>
{
    private String name;
    private int num;
    private double sal;
    
    Manager(String name, double sal)
    {
        this.name = name;
        this.sal = sal;
    }
    
    Manager(double sal)
    {
        this.sal = sal;
    }
    
    Manager(String name, int num, double sal)
    {
        this.name = name;
        this.num = num;
        this.sal = sal;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getNum()
    {
        return num;
    }
    
    public double getSal()
    {
        return sal;
    }
    
    //重写toString
    public String toString()
    {
        return "[name -> " + name + ", sal -> " + sal + "]";
    }
    
    public int compareTo(Manager m)
    {
        if(this.sal == m.getSal())
        {
            return 0;
        }
        else
        {
            return this.sal > m.getSal() ? 1 : -1;
        }
    }
}
*/
/*
//关于自定义泛型
class MyClass<T> //此处自定义泛型
{
    public void m1(T t)
    {
        System.out.println(t);
    }
}
*/