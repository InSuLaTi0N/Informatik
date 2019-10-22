/*
    ����Collection����:
        -���м��϶��ǿɵ�����/�ɱ�����(iterable)
            -���ϻ�ȡ��������(Iterator)��, ����ʹ�õ�������������
        -����ֻ�ܴ�����������, �����ǵ����洢
        -���Ͽ��Ա���������
        -���ϵĸ���ΪIterable�ӿ�
            Iterable�д���Iterator����
        
        -����Iterator()
            -hasNext()
            -next()
            -remove()
            
            ʹ�����Ϸ�����ɼ��ϱ���
            
        -�̳�Collection�Ĳ��ֽӿ�:
            -List
                -�洢�ص�: ����, ���ظ�
                 �����˳���ȡ����˳����ͬ, ��ȡ����˳��Ϊ�����˳��
                 
                -����ʵ����:
                    -ArrayList
                        -�ײ��������洢Ԫ��, ���ArrayList�����ʺϲ�ѯ, ���ʺ�Ƶ���������ɾԪ��
                    -LinkedList
                        -�ײ����˫���������ݽṹ�洢����
                        -�����ʺ�Ƶ������ɾԪ��, ���ʺϲ�ѯ����
                    -Vector
                        -�ײ��ArrayList��ͬ, ����Vector���̰߳�ȫ��
                        -Ч�ʽϵ�
            -Set
                -�洢�ص�: ����, �����ظ�
                 �����˳���ȡ��˳��һ����ͬ
                 
                -����ʵ����:
                    -HashSet
                        -�ײ�Ϊ��ϣ��/ɢ�б�
                -���ּ̳нӿ�:
                    -SortedSet
                        -�洢�ص�: ���򲻿��ظ�, ���洢��ȥ��Ԫ�ؿ��԰���Ԫ�صĴ�С�Զ�����
                        -�ýӿڵ�һ��ʵ����:
                            -TreeSet
        -Collection�еķ���:
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
    //����Collection���ϵļ򵥷���
    
    public static void main(String[] args)
    {
        //��������
        Collection c = new ArrayList(); //��̬
        
        //boolean add (E e) --> boolean add (Object element); �򼯺������Ԫ��
        c.add(1); //�˴�1Ϊ����, �Զ�װ��
        
        Object obj = new Object();
        c.add(obj); //Collection����ֻ�ܵ����洢Ԫ��, ����ֻ�ܴ洢��������
        
        Customer cus = new Customer("Alpha", 20);
        c.add(cus);
        
        //int	size() ��ȡԪ�صĸ���
        System.out.println(c.size()); //3
        
        //boolean	isEmpty() �жϼ������Ƿ���Ԫ��
        System.out.println(c.isEmpty()); //false
        
        //Object[]	toArray() ������ת��ΪObject���͵�����
        Object [] objs = c.toArray();
        
        //��������
        for(int i = 0; i < objs.length; i++)
        {
            System.out.println(objs [i]);
        }
        
        //void	clear() ��ռ���
        c.clear();
        
        System.out.println(c.size()); //0
        
        System.out.println(c.isEmpty()); //true
    }
    */
    /*
    //����Iterator����
    
    public static void main(String[] args)
    {
        //Iterator<E>	iterator() ��ȡ�����������ĵ���������
        //ͨ���������з�����ɼ��ϵĵ���(����)
        //���ַ��������м���ͨ�õı�����ʽ
        
        //��������
        Collection c = new ArrayList();
        
        //��Ӷ���
        c.add(100);
        c.add(3.14);
        c.add(false);
        
        //����/����
        Iterator it = c.iterator(); //��ȡ����������
                                    //������������ӿڱ��
                                    //it������, �������ڴ��ַ, ָ����еġ�����������
                                    
        //����Ҫ���ĵײ㼯�ϵľ�������, ���м��������ĵ�������ʵ����java.util.Iterator;�ӿ�
        
        System.out.println(it); //java.util.LinkedList$ListItr@368239c8
                                //java.util.ArrayList$Itr@5c8da962
        
        //java.util.LinkedList$ListItr����LinkedList�����������ĵ�����
        //java.util.ArrayList$Itr����ArrayList�����������ĵ�����
        
        //���÷���, ��ɱ���
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
        
        //ʹ��forѭ����ɵ���
        
        //for(Iterator it = c.iterator(); it.hasNext(); )
        //{
        //    System.out.println(it.next());
        //}
    }
    */
    
    //����contains������remove����
    
    public static void main(String[] args)
    {
        //boolean	contains(Object o) �жϼ������Ƿ����ĳ��Ԫ��
        //boolean	remove(Object o) ɾ��������ĳ��Ԫ��
        //�洢�ڼ����е�Ԫ��Ӧ����дequals����
        
        //��������
        Collection c = new ArrayList();
        
        //���Ԫ��
        Integer i1 = 10;
        
        c.add(i1);
        
        System.out.println(c.contains(i1)); //true
        
        Integer i2 = 10;
        
        //contains�����ײ���õ���equals����, Integer��д��equals
        System.out.println(c.contains(i2)); //true
        
        Customer cus1 = new Customer("Alpha", 20);
        Customer cus2 = new Customer("Alpha", 20);
        
        c.add(cus1);
        
        System.out.println(c.contains(cus1)); //true
        System.out.println(c.contains(cus2)); //false Customer��û����дequals����
                                              //��д֮��Ϊtrue
        System.out.println(c.size()); //2
        
        //ɾ��Ԫ��
        //remove�����ײ�ͬ������equals����
        c.remove(cus2);
        System.out.println(c.size()); //1
        
        //ʹ�õ�������remove����
        c.add(1);
        c.add(5);
        
        for(Iterator it = c.iterator(); it.hasNext(); )
        {
            
            it.next(); //�����ƶ�
            
            it.remove(); //ɾ��ָ���Ԫ��
            
            /*
            //ʹ�ü���remove
            Object obj = it.next(); //����
            
            c.remove(obj);
            //�ü���ɾ��Ԫ�ر������»�ȡ������, ��Ϊʹ�ü���ɾ��, ���ϸı�
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
    
    //��дtostring����
    public String toString()
    {
        return "Customer [name = " + name + ", age = " + age + "]";
    }
    
    //��дequals����
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