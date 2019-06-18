/*
    �������ָ�ʽ��: java.text.DecimalFormat;
        -���ָ�ʽԪ��:
            #       ��������
            ,       ǧ��λ
            .       С����
            0       ������0
            
    ���ڸ߾���: java.math.BigDecimal;
        -���ݾ�ȷ�ȸ�, �ʺ����������
        
    ���������: 
*/

import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.Random;

public class NumberTest
{
    /*
    //���ָ�ʽ��
    
    public static void main(String[] args)
    {
        //�������ָ�ʽ������
        //����ǧ��λ С����2λ
        DecimalFormat df = new DecimalFormat("###,###.##");
        
        //��ʽ��
        System.out.println(df.format(1234567.145));
        
        //����ǧ��λ С����4λ  ��������
        DecimalFormat df1 = new DecimalFormat("###,###.0000");
        System.out.println(df1.format(1234567.23));
    }
    */
    
    /*
    //�߾���
    
    public static void main(String[] args)
    {
        BigDecimal num1 = new BigDecimal(1234.5);
        BigDecimal num2 = new BigDecimal(5432.1);
        
        BigDecimal sum = num1.add(num2);
        
        System.out.println(sum);
    }
    */
    
    /*
    //�����
    public static void main(String[] args)
    {
        Random r = new Random();
        
        //int i = r.nextInt(101); //[0-100] �������
        
        //System.out.println(i);
        
        //for(int j = 0; j < 5; j++)
        //{
        //    System.out.println(r.nextInt(101));
        //}
        
        //����5�����ظ��������[1-5]
        
        //������̬����
        int[] a = new int[5];
        
        int i = 0;
        
        while(i != a.length) //�ж������±��Ƿ�Խ��
        {
            a[i] = r.nextInt(6); //����ǰ�ֵ
            
            for(int j = 0; j < i;) //�жϵ�ǰ�� ��ǰ���������Ƿ����
            {
                if(a[j] == a[i] || a[i] == 0)
                {
                    a[i] = r.nextInt(6); //���¸�ֵ
                    j = 0;
                }
                else
                {
                    j++;
                }
            }
            
            i++; //������һ��
        }
        
        for(int j = 0; j < a.length; j++)
        {
            System.out.print(a[j] + " "); //�ж������
        }
        
        System.out.println();
    }
    */
    
    //����������ڶ��ַ���
    //�÷���ά��,�޸ķ���
    
    public static void main(String[] args)
    {
        Random r = new Random();
        
        //������̬����
        int[] a = new int[5];
        
        int index = 0;
        
        while(index != a.length)
        {
            int temp = r.nextInt(6);
            
            //α����
            if(temp != 0 && !contains(a, temp)) //����һ������ �ж�Ԫ���Ƿ������һ��������
            {
                a[index++] = temp;
            }
        }
        
        for(int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    
    //�÷�������ж�tempԪ����a�������Ƿ����
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
    //ö��
    
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
    
    
    //����һ������, �÷��������Ǽ�����int���ݵ���
    //������ɹ��򷵻�1, ���򷵻�0
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
    
    //����ִ�гɹ� ���Ǵ��ڷ���
    //���ڳ�������ڽ��������, �������������ڽ��, ������³�������ö������
    
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

//����ö������
enum Result
{
    //success �� failed
    //�淶Ҫ���д
    SUCCESS, FAILED
}