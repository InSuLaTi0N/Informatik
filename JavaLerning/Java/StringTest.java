/*
    关于String类:
        -java.lang.String; //字符串类型
        -字符串一旦创建不可再改变. "abc"不可改变为"abcd"
        -提升字符串访问效率: 在程序中使用了"缓存"技术
        -Java中所有使用""的字符串都会在"字符串常量池"中创建一份
        -字符串常量池在方法区中被存储
        
        -在程序执行过程中, 如果程序用到某个字符串, 例"abc", 那么程序会在字符串常量池中搜索该字符串
         若没有找到该字符串, 则在字符串常量池中新建一个"abc"字符串, 若找到则直接使用
        -字符串常量池是一个缓存区, 为了提高访问字符串的效率
        
        -尽量不要频繁使用字符串拼接操作, 字符串一旦创建不可再改变
         频繁拼接会在字符串常量池中创建大量字符串对象, 给垃圾回收带来问题
         
        -关于正则表达式:
            -是一种字符模型, 用来做字符串格式匹配
            -正则表达式是通用的
            -例如:
                正则表达式 "^a{2}$" 表示 2个a字符 等同于 "aa"
                \d 数字
                \D 非数字
                \w 英文字母
                \W 非英文字母
                
        -关于StringBuffer和StringBuilder
            -是一个字符串缓冲区
            
            -java.lang.StringBuffer
                -线程安全 (可以在多线程环境下使用, 不会出现问题)
            -java.lang.StringBuilder
                -非线程安全 (在多线程环境下使用可能出现问题)
            
            -预先在内存中申请一块空间, 以容纳字符序列, 若预留空间不够用, 则进行自动扩容, 以容纳更多的字符序列
            
            -String是不可变的字符序列, 存储在常量池中; StringBuffer底层是一个char数组, 且该char数组可变可自动扩容
            -其默认初始化容量为16
            
            -为了减少底层数组的拷贝, 提高效率, 最好在创建StringBuffer之前, 预测其存储的字符数量
            然后再创建StringBuffer的时候采用指定初始化容量的方式创建StringBuffer
*/

public class StringTest
{
    /*
    //关于创建字符串
    
    public static void main(String[] args)
    {
        String a = "ab";
        a = "abc"; //此处为a引用重新指向"abc"对象, 并非"ab"改变为"abc"
        
        System.out.println(a); //abc
        
        String s1 = "Hello"; //字符串常量池中新建一个"Hello"
        String s2 = "Hello"; //在字符串常量池中搜索到
        
        //注: 比较两字符串相等不能用"=="
        System.out.println(s1 == s2); //true
    }
    */
    
    /*
    //关于创建字符串的区别
    
    public static void main(String[] args)
    {
        String s1 = "abc"; //在字符串常量池中未搜索到abc, 则创建一个新的字符串 abc, 保存在方法区-->字符串常量池中
        //栈中的main栈帧创建了一个引用s1, 指向方法区的字符串常量池中的abc
        
        String s2 = new String("abc"); //abc在字符串常量池中找到, 但是由于是创建的String类型对象, 即在堆中创建了一个新的对象
        //main栈帧创建了新的引用s2, 指向堆中的String类型对象abc
        //该方式较浪费内存
        
        System.out.println(s1 == s2); //false
    }
    */
    
    /*
    public static void main(String[] args)
    {
        String s1 = new String("hello");
        String s2 = new String("hello");
        //以上程序共创建了三个对象
        //堆区2个
        //方法区1个
    }
    */
    
    /*
    //关于字符串拼接
    
    public static void main(String[] args)
    {
        String[] ins = {"abc", "def", "ghi", "jkl"};
        
        //拼接
        String temp;
        for(int i = 0; i < ins.length; i++)
        {   
            if(i == ins.length - 1)
            {
                temp += ins[i];
            }
            else
            {
                temp += ins[i] + ",";
            }
        }
        
        System.out.println(temp);
        //以上字符串拼接方法不推荐使用
    }
    */
    
    /*
    //常用方法
    
    public static void main(String[] args)
    {
        String s1 = "abc";
        
        String s2 = new String("abc");
        
        byte[] bytes = {97, 98, 99, 100};
        String s3 = new String(bytes);
        System.out.println(s3); //abcd
        
        String s4 = new String(bytes, 0, 3);
        System.out.println(s4); //abc
        
        char[] c1 = {'S', 't', 'r', 'i', 'n', 'g'};
        String s5 = new String(c1);
        System.out.println(s5); //String
        
        String s6 = new String(c1, 0, 3);
        System.out.println(s6); //Str
        
        //char charAt(int index);
        char c2 = s5.charAt(3);
        System.out.println(c2); //i
        
        //boolean endsWith(String endStr);
        System.out.println("Helloworld.java".endsWith("a")); //true
        System.out.println("Helloworld.java".endsWith(".java")); //true
        System.out.println("Helloworld.java".endsWith("Helloworld.java")); //true
        
        System.out.println(s5.endsWith("n")); //false
        
        //boolean equalsIgnoreCase?(String anotherString);
        System.out.println(s5.equalsIgnoreCase("STRING")); //true
        
        //byte[] getBytes();
        byte[] bytes1 = "abc".getBytes();
        for(int i = 0; i < bytes1.length; i++)
        {
            System.out.print(bytes1[i] + " "); //97 98 99
        }
        
        System.out.println();
        
        //int indexOf(String str);
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".indexOf("/av11477533")); //30
        
        //int indexOf(String str, int fromIndex);
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".indexOf("v", 24)); //25
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".indexOf("v", 26)); //32
        
        //int lastIndexOf(String str);
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".lastIndexOf("1")); //45
        
        //int lastIndexOf(String str, int fromIndex);
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".lastIndexOf("1", 44)); //34
        
        //int length();
        System.out.println("Hello".length()); //5
        
        //String replaceAll(String str1, String str2);
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".replaceAll("1", "0"));
        
        //String[] split(String str)
        String mytime = "2019,6,6";
        String[] ymd = mytime.split(",");
        
        for(int i = 0; i < ymd.length; i++)
        {
            System.out.println(ymd[i]);
        }
        
        //boolean	startsWith(String prefix)
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".startsWith("h"));
        
        //String	substring(int beginIndex, int endIndex)
        System.out.println("https://www.bilibili.com/video/av11477533/?p=126".substring(8, 24));
        
        //char[]	toCharArray()
        char[] c3 = "Hello".toCharArray();
        for(int i = 0; i < c3.length; i++)
        {
            System.out.print(c3[i] + " ");
        }
        
        System.out.println();
        
        //String	toUpperCase()
        System.out.println("HelloWorld".toUpperCase());
        
        //String	toLowerCase()
        System.out.println("HelloWorld".toLowerCase());
        
        //String	trim()
        System.out.println("    a b c d    ");
        System.out.println("    a b c d    ".trim());
        
        //static String	valueOf(Object obj)
        Object obj = null;
        System.out.println(obj); //不会报错  此处输出的实际值为(String.valueOf(obj))  对空指针进行了处理
        //System.out.println(obj.toString()); //会报错  空指针异常
    }
    */
    
    /*
    //正则表达式
    
    public static void main(String[] args)
    {
        System.out.println("aa".matches("^a{2}$"));
        
        System.out.println("ab".matches("^a{2}$"));
        
        String s1 = "https://www.bilibili.com/video/av11477533/?p=126";
        
        //将b替换为d
        System.out.println(s1.replaceAll("b", "d"));
        
        //将tt替换为中
        System.out.println(s1.replaceAll("t{2}", "中"));
        
        //将数字替换为正
        System.out.println(s1.replaceAll("\\d", "正"));
        
        //将非数字替换为?
        System.out.println(s1.replaceAll("\\D", "?"));
    }
    */
    
    //字符串缓冲区
    
    public static void main(String[] args)
    {
        //创建字符串缓冲区对象
        StringBuffer sb = new StringBuffer(); //默认长度为16
        
        //添加字符串
        String[] str = {"Mathe", "Algorithm", "multimedia", "Betriebssystem"};
        
        //推荐字符串频繁拼接使用StringBuffer或StringBuilder
        for(int i = 0; i < str.length; i++)
        {
            if(i == str.length - 1)
            {
                sb.append(str[i]);
            }
            else
            {
                sb.append(str[i]);
                sb.append(", ");
            }
        }
        
        System.out.println(sb);
    }
}