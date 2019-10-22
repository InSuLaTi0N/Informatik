/*
    关于Collection集合:
        -所有集合都是可迭代的/可遍历的(iterable)
            -集合获取到迭代器(Iterator)后, 可以使用迭代器遍历集合
        -集合只能储存引用类型, 并且是单个存储
        -集合可以被当作容器
        -集合的父类为Iterable接口
            Iterable中存在Iterator方法
        
        -关于Iterator()
            -hasNext()
            -next()
            -remove()
            
            使用以上方法完成集合遍历
            
        -继承Collection的部分接口:
            -List
                -存储特点: 有序, 可重复
                 存入的顺序和取出的顺序相同, 即取出的顺序为存入的顺序
                 
                -部分实现类:
                    -ArrayList
                        -底层采用数组存储元素, 因此ArrayList集合适合查询, 不适合频繁地随机增删元素
                    -LinkedList
                        -底层采用双向链表数据结构存储数据
                        -链表适合频繁地增删元素, 不适合查询操作
                    -Vector
                        -底层和ArrayList相同, 但是Vector是线程安全的
                        -效率较低
            -Set
                -存储特点: 无序, 不可重复
                 存入的顺序和取出顺序不一定相同
                 
                -部分实现类:
                    -HashSet
                        -底层为哈希表/散列表
                -部分继承接口:
                    -SortedSet
                        -存储特点: 无序不可重复, 但存储进去的元素可以按照元素的大小自动排序
                        -该接口的一个实现类:
                            -TreeSet
        -Collection中的方法:
            boolean	add?(E e)	Ensures that this collection contains the specified element (optional operation).
            boolean	addAll?(Collection<? extends E> c)	Adds all of the elements in the specified collection to this collection (optional operation).
            void	clear()	    Removes all of the elements from this collection (optional operation).
            boolean	contains?(Object o)	Returns true if this collection contains the specified element.
            boolean	containsAll?(Collection<?> c)	Returns true if this collection contains all of the elements in the specified collection.
            boolean	equals?(Object o)	Compares the specified object with this collection for equality.
            int	hashCode()	Returns the hash code value for this collection.
            boolean	isEmpty()	Returns true if this collection contains no elements.
            Iterator<E>	iterator()	Returns an iterator over the elements in this collection.
            default Stream<E>	parallelStream()	Returns a possibly parallel Stream with this collection as its source.
            boolean	remove?(Object o)	Removes a single instance of the specified element from this collection, if it is present (optional operation).
            boolean	removeAll?(Collection<?> c)	Removes all of this collection's elements that are also contained in the specified collection (optional operation).
            default boolean	removeIf?(Predicate<? super E> filter)	Removes all of the elements of this collection that satisfy the given predicate.
            boolean	retainAll?(Collection<?> c)	Retains only the elements in this collection that are contained in the specified collection (optional operation).
            int	size()	Returns the number of elements in this collection.
            default Spliterator<E>	spliterator()	Creates a Spliterator over the elements in this collection.
            default Stream<E>	stream()	Returns a sequential Stream with this collection as its source.
            Object[]	toArray()	Returns an array containing all of the elements in this collection.
            default <T> T[]	toArray?(IntFunction<T[]> generator)	Returns an array containing all of the elements in this collection, using the provided generator function to allocate the returned array.
            <T> T[]	toArray?(T[] a)	Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
*/

import java.util.*;
public class CollectionTest
{
    /*
    //关于Collection集合的简单方法
    
    public static void main(String[] args)
    {
        //创建集合
        Collection c = new ArrayList(); //多态
        
        //boolean add (E e) --> boolean add (Object element); 向集合中添加元素
        c.add(1); //此处1为引用, 自动装箱
        
        Object obj = new Object();
        c.add(obj); //Collection集合只能单个存储元素, 并且只能存储引用类型
        
        Customer cus = new Customer("Alpha", 20);
        c.add(cus);
        
        //int	size() 获取元素的个数
        System.out.println(c.size()); //3
        
        //boolean	isEmpty() 判断集合中是否有元素
        System.out.println(c.isEmpty()); //false
        
        //Object[]	toArray() 将集合转换为Object类型的数组
        Object [] objs = c.toArray();
        
        //遍历数组
        for(int i = 0; i < objs.length; i++)
        {
            System.out.println(objs [i]);
        }
        
        //void	clear() 清空集合
        c.clear();
        
        System.out.println(c.size()); //0
        
        System.out.println(c.isEmpty()); //true
    }
    */
    /*
    //关于Iterator方法
    
    public static void main(String[] args)
    {
        //Iterator<E>	iterator() 获取集合所依赖的迭代器对象
        //通过迭代器中方法完成集合的迭代(遍历)
        //这种方法是所有集合通用的遍历方式
        
        //创建集合
        Collection c = new ArrayList();
        
        //添加对象
        c.add(100);
        c.add(3.14);
        c.add(false);
        
        //迭代/遍历
        Iterator it = c.iterator(); //获取迭代器对象
                                    //迭代器是面向接口编程
                                    //it是引用, 保存了内存地址, 指向堆中的「迭代器对象」
                                    
        //不需要关心底层集合的具体类型, 所有集合依赖的迭代器都实现了java.util.Iterator;接口
        
        System.out.println(it); //java.util.LinkedList$ListItr@368239c8
                                //java.util.ArrayList$Itr@5c8da962
        
        //java.util.LinkedList$ListItr类是LinkedList集合所依赖的迭代器
        //java.util.ArrayList$Itr类是ArrayList集合所依赖的迭代器
        
        //调用方法, 完成遍历
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
        
        //使用for循环完成迭代
        
        //for(Iterator it = c.iterator(); it.hasNext(); )
        //{
        //    System.out.println(it.next());
        //}
    }
    */
    
    //关于contains方法和remove方法
    
    public static void main(String[] args)
    {
        //boolean	contains(Object o) 判断集合中是否包含某个元素
        //boolean	remove(Object o) 删除集合中某个元素
        //存储在集合中的元素应该重写equals方法
        
        //创建集合
        Collection c = new ArrayList();
        
        //添加元素
        Integer i1 = 10;
        
        c.add(i1);
        
        System.out.println(c.contains(i1)); //true
        
        Integer i2 = 10;
        
        //contains方法底层调用的是equals方法, Integer重写了equals
        System.out.println(c.contains(i2)); //true
        
        Customer cus1 = new Customer("Alpha", 20);
        Customer cus2 = new Customer("Alpha", 20);
        
        c.add(cus1);
        
        System.out.println(c.contains(cus1)); //true
        System.out.println(c.contains(cus2)); //false Customer中没有重写equals方法
                                              //重写之后为true
        System.out.println(c.size()); //2
        
        //删除元素
        //remove方法底层同样调用equals方法
        c.remove(cus2);
        System.out.println(c.size()); //1
        
        //使用迭代器的remove方法
        c.add(1);
        c.add(5);
        
        for(Iterator it = c.iterator(); it.hasNext(); )
        {
            
            it.next(); //向下移动
            
            it.remove(); //删除指向的元素
            
            /*
            //使用集合remove
            Object obj = it.next(); //报错
            
            c.remove(obj);
            //用集合删除元素必须重新获取迭代器, 因为使用集合删除, 集合改变
            */
            
            //java.util.ConcurrentModificationException
        //at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1042)
        //at java.base/java.util.ArrayList$Itr.next(ArrayList.java:996)
        //at CollectionTest.main(CollectionTest.java:205)
        }
        
        System.out.println(c.size()); //0
    }
}

class Customer
{
    String name;
    int age;
    
    Customer (String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    //重写tostring方法
    public String toString()
    {
        return "Customer [name = " + name + ", age = " + age + "]";
    }
    
    //重写equals方法
    public boolean equals(Object o)
    {
        if(this == o) return true;
        
        if(o instanceof Customer)
        {
            Customer cus = (Customer) o;
            if(cus.age == this.age && cus.name.equals(this.name))
            {
                return true;
            }
        }
        
        return false;
    }
}