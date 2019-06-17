/*
    关于包装类型:
        基本数据类型      包装类型
        
        byte            java.lang.Byte;
        short           java.lang.Short;
        int             java.lang.Integer;
        long            java.lang.Long;
        
        float           java.lang.Float;
        double          java.lang.Double;
        
        boolean         java.lang.Boolean;
        
        char            java.lang.Character;
*/

public class WrapperClassTest
{
    /*
    public static void main(String[] args)
    {
        byte b = 10; //基本数据类型
        
        //Byte b1 = new Byte(b); //引用数据类型
        
        m1(b); //jdk11 可以直接使用基本数据类型 使用引用数据已过时
    }
    
    //规定m1方法可以接收任何一种参数
    public static void m1(Object obj){ System.out.println(obj); } //若没有包装类型 则八种基本数据类型无法被接收
    */
    
    /*
    public static void main(String[] args)
    {
        //获取Int类型最大值和最小值
        System.out.println("int最大值: " + Integer.MAX_VALUE);
        System.out.println("int最小值: " + Integer.MIN_VALUE);
        
        //关于Integer类的方法
        
        //static int	parseInt?(String s)  //将字符串类型数字转换为Int类型
        int age = Integer.parseInt("20");
        System.out.println(age);
        
        //Double类中
        double num = Double.parseDouble("3.14");
        System.out.println(num);
        
        //static String	toBinaryString(int i)  //将Int类型十进制转换为二进制
        String binarynum = Integer.toBinaryString(6);
        System.out.println(binarynum);
        
        //static String	toHexString(int i) //转换为十六进制
        String hexnum = Integer.toHexString(15);
        System.out.println(hexnum);
        
        //static String	toOctalString(int i) //转换为八进制
        String octalnum = Integer.toOctalString(8);
        System.out.println(octalnum);
        
        Integer i1 = 10; //自动装箱  老版本使用上面过时的方法
        int i2 = i1; //自动拆箱
        
        System.out.println("i1 = " + i1 + ", i2 = " + i2);
    }
    */
    
    //关于自动装箱和自动拆箱
    //是程序编译阶段的概念 和程序运行无关
    //主要目的是方便写代码
    
    public static void main(String[] args)
    {
        Integer i1 = 128; //创建了一个i1对象
        Integer i2 = 128; //创建了i2  但是和i1是两个地址
        System.out.println(i1 == i2); //false
        
        //数据在(-128 ~ 127)之间, Java中引入了"整型常量池", 储存在方法区中
        Integer i3 = 127; //不会创建对象 直接使用整形常量池中的数据
        Integer i4 = 127;
        System.out.println(i3 == i4); //true
    }
}