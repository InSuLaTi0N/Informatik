/*
    关于日期类型:
        -
*/

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest
{
    /*
    //获取系统当前时间
    
    public static void main(String[] args)
    {
        Date time = new Date();
        System.out.println(time); //Thu Jun 13 13:55:15 CEST 2019
        
        //格式化日期
        //java.util.Date; --> String
        //日期格式: y-->年, M-->月, d-->日, H-->时, m-->分, s-->秒, S-->毫秒
        
        //创建日期格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
        System.out.println(sdf.format(time));
        
    }
    */
    
    /*
    //获取特定的日期
    
    public static void main(String[] args) throws Exception
    {
        String strtime = "2008年08月08日 08:08:08 888";
        
        //将String转换为Date String --> Date
        
        //创建日期格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS"); //格式需要和字符串保持一致
        
        //将字符串转换为日期类型
        //日期 = sdf.parse(字符串);
        Date time = sdf.parse(strtime);
        System.out.println(time);
    }
    */
    
    /*
    //获取当前时间的前十分钟时间
    
    public static void main(String[] args)
    {
        //Date d = new Date(); 获取系统当前时间
        //Date d = new Date(long millis); 自1970-01-01 00:00:00 000 起的毫秒数
        
        Date time = new Date(1000);
        
        //转换为String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
        
        //System.out.println(sdf.format(time)); //1970年01月01日 01:00:01 000
        
        //获取当前时间的前十分钟时间
        Date t1 = new Date(System.currentTimeMillis() - 1000 * 60 * 10);
        System.out.println(t1); //Thu Jun 13 14:34:56 CEST 2019
    }
    */
    
    //关于日历
    
    public static void main(String[] args) throws Exception
    {
        //获取系统当前的日历
        Calendar c = Calendar.getInstance();
        
        //获取日期 星期
        int i = c.get(Calendar.DAY_OF_MONTH);
        int j = c.get(Calendar.DAY_OF_WEEK);
        
        System.out.println(i);
        System.out.println(j);
        
        //获取2008-08-08的星期数
        
        //创建日期
        String str1 = "2008年08月08日";
        
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        
        //创建Date对象  String --> Date
        Date d1 = sdf.parse(str1);
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d1);
        
        //获取星期
        System.out.println(c2.get(Calendar.DAY_OF_WEEK));
    }
}