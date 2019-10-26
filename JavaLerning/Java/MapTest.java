/*
    ����Map����:
        -�����ӿ�, û�и��ӿ�
        -�Լ�ֵ��(key, value)��ʽ�洢Ԫ��
        -���ص�: ���� �����ظ�
        -��Collectionû�й�ϵ
        -�洢��Map����key���ֵ�Ԫ����Ҫͬʱ��дhashCode + equals����
        
        �ӽӿ�:
            -SortedMap
                -key�洢Ԫ���ص�Ϊ���� �����ظ�, ���ǿ��԰���Ԫ�ش�С˳���Զ�����
                -SortedMap�е�key��ͬ��SortedSet
                
                ����ʵ����:
                    -TreeMap
                        -TreeMap��key��ͬ��һ��TreeSet
        
        Map����ʵ����:
            -HashMap
                -�ײ�Ϊ��ϣ��/ɢ�б����ݽṹ
                -HashMap�е�Key��ͬ��һ��Set����
                -Ĭ�ϳ�ʼ������Ϊ16, Ĭ�ϼ�������Ϊ0.75
            -Hashtable
                -�̰߳�ȫ��, Ч�ʽϵ�
                -Ĭ�ϳ�ʼ������Ϊ11, Ĭ�ϼ�������Ϊ0.75
                
                ����:
                    -Properties
                        -������
                        -��key,value�ķ�ʽ�洢Ԫ��, ����key, valueֻ�����ַ�������
                        
        Map���÷���:
            void	clear()	���Map
            
            default V	compute(K key, BiFunction<? super K,?? super V,?? extends V> remappingFunction)	���Լ���ָ�������䵱ǰӳ��ֵ��ӳ��, ���޵�ǰӳ��, ��Ϊnull
            
            default V	computeIfAbsent(K key, Function<? super K,?? extends V> mappingFunction)	���ָ����key������(��Ϊnull)����ͨ��ָ����K -> V������µ�ֵ����Ϊkey��ֵ
            
            default V	computeIfPresent(K key, BiFunction<? super K,?? super V,?? extends V> remappingFunction)	���ָ����key���ڣ�����ݾɵ�key��value�����µ�ֵ, ����ֵΪnull, ��ɾ����key��ֵ
            
            boolean	containsKey(Object key)	�ж�Map���Ƿ�������key
            
            boolean	containsValue(Object value)	�ж�Map���Ƿ�������value
            
            static <K,V>
            Map<K,V>	copyOf(Map<extends K, extends V> map)	��ԭMap���и���
            
            static <K,V>
            Map.Entry<K,V>	entry(K k, V v)	����һ�������key, value��, Map.Entry���ڲ��ӿ�
            
            Set<Map.Entry<K,V>>	entrySet()	���ش�ӳ���а�����ӳ���ϵ��set��ͼ
                        
            default void	forEach(BiConsumer<super K, super V> action)	����Map�����е�Entry, ��key,value���д���, ���ղ���(K, V) -> void
            
            V	get(Object key)	ͨ��key��ȡvalue
            
            default V	getOrDefault(Object key, V defaultValue)	��ָ��key����, �򷵻ظ�key��Ӧ��value, ���������򷵻�ָ����ֵ
            
            boolean	isEmpty()	�жϸü����Ƿ�Ϊ��
            
            Set<K>	keySet()	��ȡMap�����е�key
            
            default V	merge(K key, V value, BiFunction<super V, super V, extends V> remappingFunction)	��ָ��keyû�ж�Ӧ��value(��Ϊnull), �����һ����value
            
            static <K,V>
            Map<K,V>	of()	����һ��������ӳ��Ĺ̶�Map
            
            static <K,V>
            Map<K,V>	of(K k1, V v1)	����һ������һ����ֵ�ԵĹ̶�Map
            
            static <K,V>
            Map<K,V>	ofEntries(Map.Entry<extends K, extends V>... entries)	����һ���������и�����ֵ�Ե�Map
            
            V	put(K key, V value)	�򼯺�����Ӽ�ֵ��
            
            default V	putIfAbsent(K key, V value)	�������key��Ӧ��value�Ѿ����ڣ��ͷ��ش��ڵ�value���������滻����������ڣ������key��value������null
            
            V	remove(Object key)	ͨ��key����ֵ��ɾ��
            
            default boolean	remove(Object key, Object value)	ͨ��key��value����ֵ��ɾ��
            
            default V	replace(K key, V value)	��Map�д���key, ���滻��keyָ����value
            
            default boolean	replace(K key, V oldValue, V newValue)	���key�����ҹ�����ֵ��ָ����oldValue��ֵ��ȣ����滻���µ�value
            
            int	size()	��ȡMap�м�ֵ�Եĸ���
            
            Collection<V>	values()	��ȡMap���������е�value
*/

import java.util.*;

public class MapTest
{
    /*
    //����Map���Ϸ���
    public static void main(String[] args)
    {
        //����Map����
        Map<String, String> people = new HashMap<String, String>(); //HashMap��Ĭ�ϳ�ʼ��������16, Ĭ�ϼ���������0.75
        
        //�洢��ֵ��
        people.put("10000", "Alpha");
        people.put("10999", "Bravo");
        people.put("20999", "Charlie");
        people.put("29999", "Barvo");
        people.put("30001", "Delta");
        people.put("10000", "Echo");
        
        //�жϼ�ֵ�Եĸ���
        //Map�е�key�����򲻿��ظ���, ��HashSet��ͬ
        System.out.println(people.size()); //5
        
        //�鿴key��Ӧ��value
        System.out.println(people.get("10000")); //Echo
        //��key�ظ�, ��value����и���
        
        //�ж�key�Ƿ����
        System.out.println(people.containsKey("10999")); //true
        System.out.println(people.containsKey("10001")); //false
        
        //�ж�value�Ƿ����
        System.out.println(people.containsValue("Charlie")); //true
        System.out.println(people.containsValue("Fox")); //false
        
        //ͨ��key����ֵ��ɾ��
        people.remove("30001");
        
        //�������value
        System.out.println(people.values()); //[Bravo, Echo, Barvo, Charlie]; "30001" -> "Delta"��ɾ��
        
        //�������key
        System.out.println(people.keySet()); //[10999, 10000, 29999, 20999]; "30001" -> "Delta" ��ɾ��
        
        //��������ӳ��
        //entrySet��Mapת��ΪSet����
        System.out.println(people.entrySet()); //[10999=Bravo, 10000=Echo, 29999=Barvo, 20999=Charlie]
    }
    */
    
    /*
    //����Properties(Hashtable)
    public static void main(String[] args)
    {
        //����������
        Properties p = new Properties();
        
        //�洢Ԫ��
        p.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
        p.setProperty("username", "Song");
        p.setProperty("password", "baisong");
        p.setProperty("url", "jdbc:oracle:thin:@192.168.1.100:1521:tu-dresden");
        
        //ȡ��Ԫ��
        System.out.println(p.getProperty("url"));
    }
    */
    
    //����SortedMap
    //key������Ҫ����дһ���Ƚ�����ʵ��Comparable�ӿ�
    public static void main(String[] args)
    {
        SortedMap<Product, Integer> map = new TreeMap<Product, Integer>(new ProductComparator());
        
        map.put(new Product("Algorithm", 7.3), 104);
        map.put(new Product("Mathematic", 27.1), 95);
        map.put(new Product("Japanese", 3.1), 301);
        map.put(new Product("Media", 4.0), 192);
        
        for(Product key : map.keySet())
        {
            System.out.println(key);
        }
    }
}

class Product
{
    private String name;
    private double price;
    
    //Constructor
    Product(String name)
    {
        this.name = name;
    }
    
    Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public String toString()
    {
        return "[name -> " + name + ", price -> " + price + "]";
    }
}

class ProductComparator implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        double price1 = ((Product) o1).getPrice();
        double price2 = ((Product) o2).getPrice();
        
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