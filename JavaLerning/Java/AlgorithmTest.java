/*
    关于算法:
        -冒泡排序:
            -int类型数组: 3 1 6 2 5
            -若前一位比后一位大, 则交换顺序 否则不变
            -第一次循环:
                3 1 6 2 5
                1 3 6 2 5 //前两位交换
                1 3 6 2 5 //3和6不需要交换 后一位比前一位大
                1 3 2 6 5 //三四位即6和2交换
                1 3 2 5 6 //四五位交换  此时最后一位一定是最大的一位  该位置固定
            -第二次循环:
                1 3 2 5
                1 3 2 5 //前两位不需要交换
                1 2 3 5 //二三位交换
                1 2 3 5 //三四位不需要交换  倒数第二位固定
            -第三次循环:
                1 2 3
                1 2 3 //前两位不需要交换
                1 2 3 //后两位不需要交换  第三位固定
            -第四次循环:
                1 2 //不需要换位
        -选择排序:
            -int类型数组: 3 1 6 2 5
            -找出最小值, 然后和最前面的数据交换位置
            -第一次循环:
                3 1 6 2 5
                1 3 6 2 5 //找到了最小的数据放在了最前面 该位置固定
            -第二次循环:
                3 6 2 5
                2 6 3 5 //和最前面交换  固定
            -第三次循环:
                6 3 5
                3 6 5
            -第四次循环:
                6 5
                5 6
        -二分法:
            -建立在排序基础之上
            1 3 5 9 11 13 56
            -以上是已排好序的int类型数组, 快速找出13这个元素的下标
    
    关于Arrays工具类:
        -java.util.Arrays;
        
        -该工具类主要是针对数组的操作, 例如: 排序, 二分查找等.
*/

import java.util.Arrays;

public class AlgorithmTest
{
    /*
    //关于冒泡排序
    
    public static void main(String[] args)
    {
        int[] a = {3, 1, 6, 2, 5};
        
        //排序
        for(int i = a.length - 1; i > 0; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(a[j] > a[j + 1])
                {
                    int index = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = index;
                }
                
                System.out.print(a[j] + " ");
            }
            
            System.out.println();
        }
        
        //验证
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    */
    
    /*
    //关于选择排序
    
    public static void main(String[] args)
    {
        int[] a = {3, 1, 6, 2, 5};
        
        //选择排序
        for(int i = 0; i < a.length - 1; i++)
        {
            int min = i;
            
            for(int j = i; j < a.length; j++)
            {
                if(a[j] < a[min])
                {
                    min = j;
                }
                
                System.out.print(a[j] + " ");
            }
            
            //判断交换位置
            if(min != i)
            {
                int index = a[i];
                a[i] = a[min];
                a[min] = index;
            }
            
            System.out.println();
        }
        
        //验证
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    */
    
    /*
    //二分法
    
    public static void main(String[] args)
    {
        int[] a = {1, 3, 4, 5, 7, 9, 10, 11, 13, 20, 56};
        
        int index = binarySearch(a, 15);
        
        System.out.println((index == -1) ? "该元素不存在" : "该元素数组下标为 " + index);
    }
    
    public static int binarySearch(int[] a, int destElement)
    {
        int begin = 0;
        int end = a.length - 1;
        int middle;
        
        for(int i = 0; i <= Math.log(a.length) / Math.log(2); i++)
        {
            //System.out.println(middle);
            middle = (begin + end) / 2;
            
            if(a[middle] < destElement)
            {
                begin = middle + 1;
            }
            else if(a[middle] > destElement)
            {
                end = middle - 1;
            }
            else
            {
                //System.out.println(middle);
                return middle;
            }
            
            //System.out.println(middle);
            //middle = (begin + end) / 2;
        }
        
        return -1;
    }
    */
    
    //Arrays工具类
    
    public static void main(String[] args)
    {
        //排序
        int[] a = {3, 1, 6, 2, 5};
        
        Arrays.sort(a);
        
        System.out.println("排序结果:");
        
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        
        System.out.println();
        
        //二分查找
        int index = Arrays.binarySearch(a, 1);
        
        System.out.println("查找结果为: " + index);
    }
}