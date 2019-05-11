
public class RecursionTest
{
    //主方法
    public static void main(String[] args)
    {
        /*
        System.out.println("main begin");
        
        //调用doSome
        doSome();
        
        System.out.println("main over");
        */
        
        /*
        不使用递归计算1~10的和(可以不用尽量不要用)
        */
        
        /*
        int n = 10;
        int sum = 0;
        
        for(int i = 1; i <= 10, i++)
        {
            sum += i;
        }
        
        System.out.println(sum);
        */
        
        //使用递归
        /*
        int n = 10;
        int retValue = sum(n);
        System.out.println(retValue);
        */
        
        //不使用递归计算5的阶乘
        /*
        int n = 5;
        int fac = 1;
        
        for(; n >= 1; n--){
            fac *= n;
        }
        
        System.out.println(fac);
        */
        
        //使用递归
        int n = 5;
        int retValue = fac(n);
        System.out.println(retValue);
    }
    
    //以下的代码片段只有一份, 但可以被重复调用, 且每次调用都会在栈内存中分配新的内存空间
    /*
    public static void doSome()
    {
        System.out.println("Begin");
        doSome(); //这行代码不结束, 下一行不执行
        System.out.println("Over");
    }
    */
    
    /*
    public static int sum(int n)
    {
        if(n <= 1){
            return n;
        }
        
        return n + sum(n - 1);
    }
    */
    
    public static int fac(int n)
    {
        if(n <= 1){
            return 1;
        }
        
        return n * fac(n - 1);
    }
}

/*
    关于方法的递归调用
        -自己调用自己, 即:
        a(){
            a();
        }
        
        -递归很耗费栈内存, 尽量避免
        -java.lang.StackOverflowError  栈内存溢出错误
         错误无法挽回, 除非JVM停止工作
         
        -递归必须有结束条件, 否则一定会发生栈内存溢出错误
        -即使有结束条件, 也可能发生溢出错误, 可能由于递归过于深入
        
        -某些情况下功能的实现必须依靠递归方式. (例如 目录拷贝)
        
*/