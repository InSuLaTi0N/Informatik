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
    }
}