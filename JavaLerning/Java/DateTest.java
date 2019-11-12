/*
    ������������:
        -
*/

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest
{
    /*
    //��ȡϵͳ��ǰʱ��
    
    public static void main(String[] args)
    {
        Date time = new Date();
        System.out.println(time); //Thu Jun 13 13:55:15 CEST 2019
        
        //��ʽ������
        //java.util.Date; --> String
        //���ڸ�ʽ: y-->��, M-->��, d-->��, H-->ʱ, m-->��, s-->��, S-->����
        
        //�������ڸ�ʽ������
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss SSS");
        System.out.println(sdf.format(time));
        
    }
    */
    
    /*
    //��ȡ�ض�������
    
    public static void main(String[] args) throws Exception
    {
        String strtime = "2008��08��08�� 08:08:08 888";
        
        //��Stringת��ΪDate String --> Date
        
        //�������ڸ�ʽ������
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss SSS"); //��ʽ��Ҫ���ַ�������һ��
        
        //���ַ���ת��Ϊ��������
        //���� = sdf.parse(�ַ���);
        Date time = sdf.parse(strtime);
        System.out.println(time);
    }
    */
    
    /*
    //��ȡ��ǰʱ���ǰʮ����ʱ��
    
    public static void main(String[] args)
    {
        //Date d = new Date(); ��ȡϵͳ��ǰʱ��
        //Date d = new Date(long millis); ��1970-01-01 00:00:00 000 ��ĺ�����
        
        Date time = new Date(1000);
        
        //ת��ΪString
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss SSS");
        
        //System.out.println(sdf.format(time)); //1970��01��01�� 01:00:01 000
        
        //��ȡ��ǰʱ���ǰʮ����ʱ��
        Date t1 = new Date(System.currentTimeMillis() - 1000 * 60 * 10);
        System.out.println(t1); //Thu Jun 13 14:34:56 CEST 2019
    }
    */
    
    //��������
    
    public static void main(String[] args) throws Exception
    {
        //��ȡϵͳ��ǰ������
        Calendar c = Calendar.getInstance();
        
        //��ȡ���� ����
        int i = c.get(Calendar.DAY_OF_MONTH);
        int j = c.get(Calendar.DAY_OF_WEEK);
        
        System.out.println(i);
        System.out.println(j);
        
        //��ȡ2008-08-08��������
        
        //��������
        String str1 = "2008��08��08��";
        
        //��ʽ������
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        
        //����Date����  String --> Date
        Date d1 = sdf.parse(str1);
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d1);
        
        //��ȡ����
        System.out.println(c2.get(Calendar.DAY_OF_WEEK));
    }
}