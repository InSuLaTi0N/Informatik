/*
    ���ڷ���:
        -�����ڼ������
        
        -����ͳһ�����е���������
        -���Լ���ǿ������ת��
        
        -ֻ�ܴ洢һ����������
        
    ������ǿforѭ��:
        - for(���� ���� : ������/������){}
        
        -��������ʹ�������﷨, ����Ҫʹ�÷���
*/

import java.util.*;

public class GenericTest
{
    /*
    public static void main(String[] args)
    {
        //�˴���ʹ�÷���
        Set set = new HashSet();
        
        set.add(new A());
        set.add(new B());
        set.add(new C());
        
        //��������, ����A�����A�ķ���, B����B�ķ�����
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
        
        //���ϼ���δʹ�÷���, ������Ԫ�����Ͳ�ͳһ, �ڱ�������ʱ��Ҫ������ǿ������ת��
    }
    */
    
    //���ڷ����﷨
    /*
    public static void main(String[] args)
    {
        //����һ��List����, ֻ�ܴ洢String����
        List<String> list = new ArrayList<String>();
        
        //list.add(1); //����: �����ݵ�����: int�޷�ת��ΪString
        //�������﷨
        
        //���Ԫ��
        list.add("ABC");
        list.add("DEF");
        list.add("GHI");
        list.add("JKL");
        list.add("MNO");
        
        //��������
        for(Iterator<String> it = list.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }
        
    }
    */
    /*
    public static void main(String[] args)
    {
        //����һ��Map����, key�洢String, value�洢Integer
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        //�洢Ԫ��
        map.put("Alpha", 20);
        map.put("Bravo", 5);
        map.put("Charlie", 25);
        map.put("Delta", 15);
        map.put("Echo", 10);
        
        //����Map
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
        //SortedSet����ʹ�÷���
        SortedSet<Manager> ss = new TreeSet<Manager>();
        
        ss.add(new Manager("Alpha", 500.1));
        ss.add(new Manager("Bravo", 734.1));
        ss.add(new Manager("Charlie", 395.8));
        ss.add(new Manager("Delta", 406.0));
        ss.add(new Manager("Echo", 651.8));
        
        //����
        for(Iterator<Manager> it = ss.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }
    }
    */
    /*
    public static void main(String[] args)
    {
        //�����Զ��巺��
        MyClass<String> mc = new MyClass<String>();
        
        //���
        mc.m1("Hallo");
    }
    */
    /*
    //����foreach
    public static void main(String[] args)
    {
        int[] array = {1,2,3,4,5};
        
        //��������
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }
        
        System.out.println("----------------------------");
        
        //ʹ����ǿforѭ��
        for(int e : array) //int e �����������򼯺��е�ÿһ��Ԫ��
        {
            System.out.println(e);
        }
        
        System.out.println("----------------------------");
        
        //����ʹ����ǿforѭ������
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
        
        //���ϲ�ʹ�÷���
        List list = new ArrayList();
        
        list.add(12);
        list.add(34);
        list.add(56);
        list.add(78);
        list.add(90);
        
        //�����ϲ�ʹ�÷���, �򼯺�����forѭ����ǿʱӦ����Object����
        //������Ҫʱ��ǿ������ת��
        for(Object element : list)
        {
            System.out.println(element);
        }
    }
    */
    
    //����foreach��ȱ��
    public static void main(String[] args)
    {
        String[] course = {"Algorithm", "Algebra", "Multimedia", "Discret Structure"};
        
        //�����ַ���
        
        //�����ַ�������������
        StringBuffer sb = new StringBuffer();
        
        /*
        //��������
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
        
        //���
        System.out.println(sb);
        */
        
        //������ǿforû���±�, ���Ϸ���������
        for(String s : course)
        {
            sb.append(s);
            sb.append(", ");
        }
        
        //���ֻ�ܶ����һ��", "  Ȼ��ʹ��String��substring���������ȡ
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
    
    //��дtoString
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
//�����Զ��巺��
class MyClass<T> //�˴��Զ��巺��
{
    public void m1(T t)
    {
        System.out.println(t);
    }
}
*/