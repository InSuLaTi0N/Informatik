/*
    以下代码不使用方法重载, 分析缺点
*/

public class OverloadTest{
    public static void main(String[] args){
        
        /*
        //调用方法
        int result1 = sumInt(1, 2);
        System.out.println(result1);
        
        double result2 = sumDouble(1.0, 2.0);
        System.out.println(result2);
        
        long result3 = sumLong(1L, 2L);
        System.out.println(result3);
        */
        
        //测试自定义(封装)类
        O.p(3.0);
        O.p(5);
        O.p('e');
        O.p(false);
        O.p("Hello");
    }
    
    /*
    //定义一个方法, 计算两个Int和
    public static int sumInt(int a, int b){
        return a + b;
    }
    
    //定义一个方法, 计算两个Double和
    public static double sumDouble(double a, double b){
        return a + b;
    }
    
    //定义一个方法, 计算两个long和
    public static long sumLong(long a, long b){
        return a + b;
    }
    */
    
    /*
        以上方法相似, 皆为求和
        在以上程序当中, 功能相似的方法,分别有三个不同的名字
        使得调用不方便, 程序员需要记忆更多的方法, 才能完成调用
    */
    
    /*
    //参数数量不同  构成重载
    public static void m1(){}
    public static void m1(int a){}
    
    //参数顺序不同  构成重载
    public static void m1(double a, int b){}
    public static void m1(int a, double b){}
    
    //参数类型不同  构成重载
    public static void m1(long a){}
    public static void m1(int a){}
    
    //以下不构成重载
    public static void m1(int a, int b){}
    public static void m1(int b, int a){}
    
    //以下报错
    public static void m1(){}
    public static int m1(){
        return 1;
    }
    */  
}

//自定义类
class O
{
    public static void p(byte b){
        System.out.println(b);
    }
    
    public static void p(short b){
        System.out.println(b);
    }
    
    public static void p(int b){
        System.out.println(b);
    }
    
    public static void p(long b){
        System.out.println(b);
    }
    
    public static void p(float b){
        System.out.println(b);
    }
    
    public static void p(double b){
        System.out.println(b);
    }
    
    public static void p(char b){
        System.out.println(b);
    }
    
    public static void p(String b){
        System.out.println(b);
    }
    
    public static void p(boolean b){
        System.out.println(b);
    }
}
//----------------------------------------------------------------------------------------------------------
/*
    关于方法重载(overload):
        -当功能相似时, 尽可能使方法名相同
        -功能不相似时, 尽可能区分方法名
        -在同一个类中, 方法名相同, 参数列表不同(数量不同, 顺序不同, 类型不同)  即构成重载
        -方法重载和方法名+参数列表有关系, 与返回值类型无关, 与修饰符列表无关
        
    关于方法重载的应用:
        见上方自定义类及使用
*/