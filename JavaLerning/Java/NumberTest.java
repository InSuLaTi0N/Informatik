/*
    关于数字格式化: java.text.DecimalFormat;
        -数字格式元素:
            #       任意数字
            ,       千分位
            .       小数点
            0       不够补0
            
    关于高精度: java.math.BigDecimal;
        -数据精确度高, 适合做财务软件
        
    关于随机数: 
*/

import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.Random;

public class NumberTest
{
    /*
    //数字格式化
    
    public static void main(String[] args)
    {
        //创建数字格式化对象
        //加入千分位 小数点2位
        DecimalFormat df = new DecimalFormat("###,###.##");
        
        //格式化
        System.out.println(df.format(1234567.145));
        
        //加入千分位 小数点4位  不够补零
        DecimalFormat df1 = new DecimalFormat("###,###.0000");
        System.out.println(df1.format(1234567.23));
    }
    */
    
    /*
    //高精度
    
    public static void main(String[] args)
    {
        BigDecimal num1 = new BigDecimal(1234.5);
        BigDecimal num2 = new BigDecimal(5432.1);
        
        BigDecimal sum = num1.add(num2);
        
        System.out.println(sum);
    }
    */
    
    /*
    //随机数
    public static void main(String[] args)
    {
        Random r = new Random();
        
        //int i = r.nextInt(101); //[0-100] 的随机数
        
        //System.out.println(i);
        
        //for(int j = 0; j < 5; j++)
        //{
        //    System.out.println(r.nextInt(101));
        //}
        
        //生成5个不重复的随机数[1-5]
        
        //创建动态数组
        int[] a = new int[5];
        
        int i = 0;
        
        while(i != a.length) //判断数组下标是否越界
        {
            a[i] = r.nextInt(6); //给当前项赋值
            
            for(int j = 0; j < i;) //判断当前项 与前面所有项是否相等
            {
                if(a[j] == a[i] || a[i] == 0)
                {
                    a[i] = r.nextInt(6); //重新赋值
                    j = 0;
                }
                else
                {
                    j++;
                }
            }
            
            i++; //跳至下一项
        }
        
        for(int j = 0; j < a.length; j++)
        {
            System.out.print(a[j] + " "); //判断最后结果
        }
        
        System.out.println();
    }
    */
    
    //关于随机数第二种方法
    //该方法维护,修改方便
    
    public static void main(String[] args)
    {
        Random r = new Random();
        
        //创建动态数组
        int[] a = new int[5];
        
        int index = 0;
        
        while(index != a.length)
        {
            int temp = r.nextInt(6);
            
            //伪代码
            if(temp != 0 && !contains(a, temp)) //创建一个方法 判断元素是否存在于一个数组中
            {
                a[index++] = temp;
            }
        }
        
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    
    //该方法完成判断temp元素在a数组中是否包含
    public static boolean contains(int[] a, int temp)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == temp)
            {
                return true;
            }
        }
        
        return false;
    }
    
    
    /*
    //枚举
    
    public static void main(String[] args)
    {
        int retValue = divide(10, 10);
        if(retValue == 1)
        {
            System.out.println("success");
        }
        else if (retValue == 0)
        {
            System.out.println("failed");
        }
        
        Result value = div(10, 0);
        if(value == Result.SUCCESS)
        {
            System.out.println("success");
        }
        else if (value == Result.FAILED)
        {
            System.out.println("failed");
        }
    }
    
    
    //定义一个方法, 该方法作用是计算两int数据的商
    //若计算成功则返回1, 否则返回0
    public static int divide(int a, int b)
    {
        try
        {
            int c = a / b;
            return 1; //success
        }
        catch(Exception e)
        {
            return 0; //failed
        }
    }
    
    //程序执行成功 但是存在风险
    //能在程序编译期解决的问题, 绝不放在运行期解决, 因此以下程序引入枚举类型
    
    public static Result div(int a, int b)
    {
        try
        {
            int c = a / b;
            return Result.SUCCESS;
        }
        catch(Exception e)
        {
            return Result.FAILED;
        }
    }
    */
}

//定义枚举类型
enum Result
{
    //success 和 failed
    //规范要求大写
    SUCCESS, FAILED
}