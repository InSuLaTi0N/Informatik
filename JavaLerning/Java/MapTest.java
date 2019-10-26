/*
    关于Map集合:
        -顶级接口, 没有父接口
        -以键值对(key, value)方式存储元素
        -键特点: 无序 不可重复
        -和Collection没有关系
        -存储在Map集合key部分的元素需要同时重写hashCode + equals方法
        
        子接口:
            -SortedMap
                -key存储元素特点为无序 不可重复, 但是可以按照元素大小顺序自动排序
                -SortedMap中的key等同于SortedSet
                
                部分实现类:
                    -TreeMap
                        -TreeMap的key等同于一个TreeSet
        
        Map部分实现类:
            -HashMap
                -底层为哈希表/散列表数据结构
                -HashMap中的Key等同于一个Set集合
                -默认初始化容量为16, 默认加载因子为0.75
            -Hashtable
                -线程安全的, 效率较低
                -默认初始化容量为11, 默认加载因子为0.75
                
                子类:
                    -Properties
                        -属性类
                        -以key,value的方式存储元素, 但是key, value只能是字符串类型
                        
        Map常用方法:
            void	clear()	清空Map
            
            default V	compute(K key, BiFunction<? super K,?? super V,?? extends V> remappingFunction)	尝试计算指定键及其当前映射值的映射, 若无当前映射, 则为null
            
            default V	computeIfAbsent(K key, Function<? super K,?? extends V> mappingFunction)	如果指定的key不存在(或为null)，则通过指定的K -> V计算出新的值设置为key的值
            
            default V	computeIfPresent(K key, BiFunction<? super K,?? super V,?? extends V> remappingFunction)	如果指定的key存在，则根据旧的key和value计算新的值, 若新值为null, 则删除该key的值
            
            boolean	containsKey(Object key)	判断Map中是否包含这个key
            
            boolean	containsValue(Object value)	判断Map中是否包含这个value
            
            static <K,V>
            Map<K,V>	copyOf(Map<extends K, extends V> map)	对原Map进行复制
            
            static <K,V>
            Map.Entry<K,V>	entry(K k, V v)	返回一个具体的key, value对, Map.Entry是内部接口
            
            Set<Map.Entry<K,V>>	entrySet()	返回此映射中包含的映射关系的set视图
                        
            default void	forEach(BiConsumer<super K, super V> action)	遍历Map中所有的Entry, 对key,value进行处理, 接收参数(K, V) -> void
            
            V	get(Object key)	通过key获取value
            
            default V	getOrDefault(Object key, V defaultValue)	若指定key存在, 则返回该key对应的value, 若不存在则返回指定的值
            
            boolean	isEmpty()	判断该集合是否为空
            
            Set<K>	keySet()	获取Map中所有的key
            
            default V	merge(K key, V value, BiFunction<super V, super V, extends V> remappingFunction)	若指定key没有对应的value(或为null), 则给定一个新value
            
            static <K,V>
            Map<K,V>	of()	创建一个不包含映射的固定Map
            
            static <K,V>
            Map<K,V>	of(K k1, V v1)	创建一个包含一个键值对的固定Map
            
            static <K,V>
            Map<K,V>	ofEntries(Map.Entry<extends K, extends V>... entries)	创建一个包含所有给定键值对的Map
            
            V	put(K key, V value)	向集合中添加键值对
            
            default V	putIfAbsent(K key, V value)	如果传入key对应的value已经存在，就返回存在的value，不进行替换。如果不存在，就添加key和value，返回null
            
            V	remove(Object key)	通过key将键值对删除
            
            default boolean	remove(Object key, Object value)	通过key和value将键值对删除
            
            default V	replace(K key, V value)	若Map中存在key, 则替换该key指定的value
            
            default boolean	replace(K key, V oldValue, V newValue)	如果key存在且关联的值与指定的oldValue的值相等，则替换成新的value
            
            int	size()	获取Map中键值对的个数
            
            Collection<V>	values()	获取Map集合中所有的value
*/

import java.util.*;

public class MapTest
{
    /*
    //关于Map集合方法
    public static void main(String[] args)
    {
        //创建Map集合
        Map<String, String> people = new HashMap<String, String>(); //HashMap的默认初始化容量是16, 默认加载因子是0.75
        
        //存储键值对
        people.put("10000", "Alpha");
        people.put("10999", "Bravo");
        people.put("20999", "Charlie");
        people.put("29999", "Barvo");
        people.put("30001", "Delta");
        people.put("10000", "Echo");
        
        //判断键值对的个数
        //Map中的key是无序不可重复的, 和HashSet相同
        System.out.println(people.size()); //5
        
        //查看key对应的value
        System.out.println(people.get("10000")); //Echo
        //若key重复, 则value会进行覆盖
        
        //判断key是否存在
        System.out.println(people.containsKey("10999")); //true
        System.out.println(people.containsKey("10001")); //false
        
        //判断value是否存在
        System.out.println(people.containsValue("Charlie")); //true
        System.out.println(people.containsValue("Fox")); //false
        
        //通过key将键值对删除
        people.remove("30001");
        
        //输出所有value
        System.out.println(people.values()); //[Bravo, Echo, Barvo, Charlie]; "30001" -> "Delta"已删除
        
        //输出所有key
        System.out.println(people.keySet()); //[10999, 10000, 29999, 20999]; "30001" -> "Delta" 已删除
        
        //返回所有映射
        //entrySet将Map转换为Set集合
        System.out.println(people.entrySet()); //[10999=Bravo, 10000=Echo, 29999=Barvo, 20999=Charlie]
    }
    */
    
    /*
    //关于Properties(Hashtable)
    public static void main(String[] args)
    {
        //创建属性类
        Properties p = new Properties();
        
        //存储元素
        p.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
        p.setProperty("username", "Song");
        p.setProperty("password", "baisong");
        p.setProperty("url", "jdbc:oracle:thin:@192.168.1.100:1521:tu-dresden");
        
        //取出元素
        System.out.println(p.getProperty("url"));
    }
    */
    
    //关于SortedMap
    //key部分需要单独写一个比较器或实现Comparable接口
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