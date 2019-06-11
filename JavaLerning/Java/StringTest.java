/*
    ����String��:
        -java.lang.String; //�ַ�������
        -�ַ���һ�����������ٸı�. "abc"���ɸı�Ϊ"abcd"
        -�����ַ�������Ч��: �ڳ�����ʹ����"����"����
        -Java������ʹ��""���ַ���������"�ַ���������"�д���һ��
        -�ַ����������ڷ������б��洢
        
        -�ڳ���ִ�й�����, ��������õ�ĳ���ַ���, ��"abc", ��ô��������ַ������������������ַ���
         ��û���ҵ����ַ���, �����ַ������������½�һ��"abc"�ַ���, ���ҵ���ֱ��ʹ��
        -�ַ�����������һ��������, Ϊ����߷����ַ�����Ч��
        
        -������ҪƵ��ʹ���ַ���ƴ�Ӳ���, �ַ���һ�����������ٸı�
         Ƶ��ƴ�ӻ����ַ����������д��������ַ�������, ���������մ�������
         
        -����������ʽ:
            -��һ���ַ�ģ��, �������ַ�����ʽƥ��
            -������ʽ��ͨ�õ�
            -����:
                ������ʽ "^a{2}$" ��ʾ 2��a�ַ� ��ͬ�� "aa"
                \d ����
                \D ������
                \w Ӣ����ĸ
                \W ��Ӣ����ĸ
                
        -����StringBuffer��StringBuilder
            -��һ���ַ���������
            
            -java.lang.StringBuffer
                -�̰߳�ȫ (�����ڶ��̻߳�����ʹ��, �����������)
            -java.lang.StringBuilder
                -���̰߳�ȫ (�ڶ��̻߳�����ʹ�ÿ��ܳ�������)
            
            -Ԥ�����ڴ�������һ��ռ�, �������ַ�����, ��Ԥ���ռ䲻����, ������Զ�����, �����ɸ�����ַ�����
            
            -String�ǲ��ɱ���ַ�����, �洢�ڳ�������; StringBuffer�ײ���һ��char����, �Ҹ�char����ɱ���Զ�����
            -��Ĭ�ϳ�ʼ������Ϊ16
            
            -Ϊ�˼��ٵײ�����Ŀ���, ���Ч��, ����ڴ���StringBuffer֮ǰ, Ԥ����洢���ַ�����
            Ȼ���ٴ���StringBuffer��ʱ�����ָ����ʼ�������ķ�ʽ����StringBuffer
*/

public class StringTest
{
    /*
    //���ڴ����ַ���
    
    public static void main(String[] args)
    {
        String a = "ab";
        a = "abc"; //�˴�Ϊa��������ָ��"abc"����, ����"ab"�ı�Ϊ"abc"
        
        System.out.println(a); //abc
        
        String s1 = "Hello"; //�ַ������������½�һ��"Hello"
        String s2 = "Hello"; //���ַ�����������������
        
        //ע: �Ƚ����ַ�����Ȳ�����"=="
        System.out.println(s1 == s2); //true
    }
    */
    
    /*
    //���ڴ����ַ���������
    
    public static void main(String[] args)
    {
        String s1 = "abc"; //���ַ�����������δ������abc, �򴴽�һ���µ��ַ��� abc, �����ڷ�����-->�ַ�����������
        //ջ�е�mainջ֡������һ������s1, ָ�򷽷������ַ����������е�abc
        
        String s2 = new String("abc"); //abc���ַ������������ҵ�, ���������Ǵ�����String���Ͷ���, ���ڶ��д�����һ���µĶ���
        //mainջ֡�������µ�����s2, ָ����е�String���Ͷ���abc
        //�÷�ʽ���˷��ڴ�
        
        System.out.println(s1 == s2); //false
    }
    */
    
    /*
    public static void main(String[] args)
    {
        String s1 = new String("hello");
        String s2 = new String("hello");
        //���ϳ��򹲴�������������
        //����2��
        //������1��
    }
    */
    
    /*
    //�����ַ���ƴ��
    
    public static void main(String[] args)
    {
        String[] ins = {"abc", "def", "ghi", "jkl"};
        
        //ƴ��
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
        //�����ַ���ƴ�ӷ������Ƽ�ʹ��
    }
    */
    
    /*
    //���÷���
    
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
        System.out.println(obj); //���ᱨ��  �˴������ʵ��ֵΪ(String.valueOf(obj))  �Կ�ָ������˴���
        //System.out.println(obj.toString()); //�ᱨ��  ��ָ���쳣
    }
    */
    
    /*
    //������ʽ
    
    public static void main(String[] args)
    {
        System.out.println("aa".matches("^a{2}$"));
        
        System.out.println("ab".matches("^a{2}$"));
        
        String s1 = "https://www.bilibili.com/video/av11477533/?p=126";
        
        //��b�滻Ϊd
        System.out.println(s1.replaceAll("b", "d"));
        
        //��tt�滻Ϊ��
        System.out.println(s1.replaceAll("t{2}", "��"));
        
        //�������滻Ϊ��
        System.out.println(s1.replaceAll("\\d", "��"));
        
        //���������滻Ϊ?
        System.out.println(s1.replaceAll("\\D", "?"));
    }
    */
    
    //�ַ���������
    
    public static void main(String[] args)
    {
        //�����ַ�������������
        StringBuffer sb = new StringBuffer(); //Ĭ�ϳ���Ϊ16
        
        //����ַ���
        String[] str = {"Mathe", "Algorithm", "multimedia", "Betriebssystem"};
        
        //�Ƽ��ַ���Ƶ��ƴ��ʹ��StringBuffer��StringBuilder
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