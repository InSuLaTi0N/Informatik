/*
    �����㷨:
        -ð������:
            -int��������: 3 1 6 2 5
            -��ǰһλ�Ⱥ�һλ��, �򽻻�˳�� ���򲻱�
            -��һ��ѭ��:
                3 1 6 2 5
                1 3 6 2 5 //ǰ��λ����
                1 3 6 2 5 //3��6����Ҫ���� ��һλ��ǰһλ��
                1 3 2 6 5 //����λ��6��2����
                1 3 2 5 6 //����λ����  ��ʱ���һλһ��������һλ  ��λ�ù̶�
            -�ڶ���ѭ��:
                1 3 2 5
                1 3 2 5 //ǰ��λ����Ҫ����
                1 2 3 5 //����λ����
                1 2 3 5 //����λ����Ҫ����  �����ڶ�λ�̶�
            -������ѭ��:
                1 2 3
                1 2 3 //ǰ��λ����Ҫ����
                1 2 3 //����λ����Ҫ����  ����λ�̶�
            -���Ĵ�ѭ��:
                1 2 //����Ҫ��λ
        -ѡ������:
            -int��������: 3 1 6 2 5
            -�ҳ���Сֵ, Ȼ�����ǰ������ݽ���λ��
            -��һ��ѭ��:
                3 1 6 2 5
                1 3 6 2 5 //�ҵ�����С�����ݷ�������ǰ�� ��λ�ù̶�
            -�ڶ���ѭ��:
                3 6 2 5
                2 6 3 5 //����ǰ�潻��  �̶�
            -������ѭ��:
                6 3 5
                3 6 5
            -���Ĵ�ѭ��:
                6 5
                5 6
        -���ַ�:
            -�������������֮��
            1 3 5 9 11 13 56
            -���������ź����int��������, �����ҳ�13���Ԫ�ص��±�
    
    ����Arrays������:
        -java.util.Arrays;
        
        -�ù�������Ҫ���������Ĳ���, ����: ����, ���ֲ��ҵ�.
*/

import java.util.Arrays;

public class AlgorithmTest
{
    /*
    //����ð������
    
    public static void main(String[] args)
    {
        int[] a = {3, 1, 6, 2, 5};
        
        //����
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
        
        //��֤
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    */
    
    /*
    //����ѡ������
    
    public static void main(String[] args)
    {
        int[] a = {3, 1, 6, 2, 5};
        
        //ѡ������
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
            
            //�жϽ���λ��
            if(min != i)
            {
                int index = a[i];
                a[i] = a[min];
                a[min] = index;
            }
            
            System.out.println();
        }
        
        //��֤
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    */
    
    /*
    //���ַ�
    
    public static void main(String[] args)
    {
        int[] a = {1, 3, 4, 5, 7, 9, 10, 11, 13, 20, 56};
        
        int index = binarySearch(a, 15);
        
        System.out.println((index == -1) ? "��Ԫ�ز�����" : "��Ԫ�������±�Ϊ " + index);
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
    
    //Arrays������
    
    public static void main(String[] args)
    {
        //����
        int[] a = {3, 1, 6, 2, 5};
        
        Arrays.sort(a);
        
        System.out.println("������:");
        
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        
        System.out.println();
        
        //���ֲ���
        int index = Arrays.binarySearch(a, 1);
        
        System.out.println("���ҽ��Ϊ: " + index);
    }
}