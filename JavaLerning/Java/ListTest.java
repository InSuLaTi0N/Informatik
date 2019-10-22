/*
    关于List集合存储元素特点:
        -有序(List集合中存储有下标)
        -可重复
*/

import java.util.*;

public class ListTest
{
    public static void main(String[] args)
    {
        //创建一个List集合
        List l = new ArrayList();
        
        //添加元素
        l.add(10);
        l.add(9);
        l.add(3);
        l.add(15);
        
        l.add(1, 555); //在下标为1的位置上添加555
        
        System.out.println(l.get(0)); //取得第一个元素
        
        System.out.println("-------------------------------");
        
        //遍历(迭代器)
        for(Iterator it = l.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
        
        System.out.println("-------------------------------");
        
        //遍历(List集合特有)
        for(int i = 0; i < l.size(); i++)
        {
            System.out.println(l.get(i));
        }
    }
}