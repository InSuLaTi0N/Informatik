/*
    ����List���ϴ洢Ԫ���ص�:
        -����(List�����д洢���±�)
        -���ظ�
*/

import java.util.*;

public class ListTest
{
    public static void main(String[] args)
    {
        //����һ��List����
        List l = new ArrayList();
        
        //���Ԫ��
        l.add(10);
        l.add(9);
        l.add(3);
        l.add(15);
        
        l.add(1, 555); //���±�Ϊ1��λ�������555
        
        System.out.println(l.get(0)); //ȡ�õ�һ��Ԫ��
        
        System.out.println("-------------------------------");
        
        //����(������)
        for(Iterator it = l.iterator(); it.hasNext(); )
        {
            System.out.println(it.next());
        }
        
        System.out.println("-------------------------------");
        
        //����(List��������)
        for(int i = 0; i < l.size(); i++)
        {
            System.out.println(l.get(i));
        }
    }
}