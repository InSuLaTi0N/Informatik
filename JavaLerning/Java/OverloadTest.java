/*
    ���´��벻ʹ�÷�������, ����ȱ��
*/

public class OverloadTest{
    public static void main(String[] args){
        
        /*
        //���÷���
        int result1 = sumInt(1, 2);
        System.out.println(result1);
        
        double result2 = sumDouble(1.0, 2.0);
        System.out.println(result2);
        
        long result3 = sumLong(1L, 2L);
        System.out.println(result3);
        */
        
        //�����Զ���(��װ)��
        O.p(3.0);
        O.p(5);
        O.p('e');
        O.p(false);
        O.p("Hello");
    }
    
    /*
    //����һ������, ��������Int��
    public static int sumInt(int a, int b){
        return a + b;
    }
    
    //����һ������, ��������Double��
    public static double sumDouble(double a, double b){
        return a + b;
    }
    
    //����һ������, ��������long��
    public static long sumLong(long a, long b){
        return a + b;
    }
    */
    
    /*
        ���Ϸ�������, ��Ϊ���
        �����ϳ�����, �������Ƶķ���,�ֱ���������ͬ������
        ʹ�õ��ò�����, ����Ա��Ҫ�������ķ���, ������ɵ���
    */
    
    /*
    //����������ͬ  ��������
    public static void m1(){}
    public static void m1(int a){}
    
    //����˳��ͬ  ��������
    public static void m1(double a, int b){}
    public static void m1(int a, double b){}
    
    //�������Ͳ�ͬ  ��������
    public static void m1(long a){}
    public static void m1(int a){}
    
    //���²���������
    public static void m1(int a, int b){}
    public static void m1(int b, int a){}
    
    //���±���
    public static void m1(){}
    public static int m1(){
        return 1;
    }
    */  
}

//�Զ�����
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
    ���ڷ�������(overload):
        -����������ʱ, ������ʹ��������ͬ
        -���ܲ�����ʱ, ���������ַ�����
        -��ͬһ������, ��������ͬ, �����б�ͬ(������ͬ, ˳��ͬ, ���Ͳ�ͬ)  ����������
        -�������غͷ�����+�����б��й�ϵ, �뷵��ֵ�����޹�, �����η��б��޹�
        
    ���ڷ������ص�Ӧ��:
        ���Ϸ��Զ����༰ʹ��
*/