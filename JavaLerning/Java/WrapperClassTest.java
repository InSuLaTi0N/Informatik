/*
    ���ڰ�װ����:
        ������������      ��װ����
        
        byte            java.lang.Byte;
        short           java.lang.Short;
        int             java.lang.Integer;
        long            java.lang.Long;
        
        float           java.lang.Float;
        double          java.lang.Double;
        
        boolean         java.lang.Boolean;
        
        char            java.lang.Character;
*/

public class WrapperClassTest
{
    /*
    public static void main(String[] args)
    {
        byte b = 10; //������������
        
        //Byte b1 = new Byte(b); //������������
        
        m1(b); //jdk11 ����ֱ��ʹ�û����������� ʹ�����������ѹ�ʱ
    }
    
    //�涨m1�������Խ����κ�һ�ֲ���
    public static void m1(Object obj){ System.out.println(obj); } //��û�а�װ���� ����ֻ������������޷�������
    */
    
    /*
    public static void main(String[] args)
    {
        //��ȡInt�������ֵ����Сֵ
        System.out.println("int���ֵ: " + Integer.MAX_VALUE);
        System.out.println("int��Сֵ: " + Integer.MIN_VALUE);
        
        //����Integer��ķ���
        
        //static int	parseInt?(String s)  //���ַ�����������ת��ΪInt����
        int age = Integer.parseInt("20");
        System.out.println(age);
        
        //Double����
        double num = Double.parseDouble("3.14");
        System.out.println(num);
        
        //static String	toBinaryString(int i)  //��Int����ʮ����ת��Ϊ������
        String binarynum = Integer.toBinaryString(6);
        System.out.println(binarynum);
        
        //static String	toHexString(int i) //ת��Ϊʮ������
        String hexnum = Integer.toHexString(15);
        System.out.println(hexnum);
        
        //static String	toOctalString(int i) //ת��Ϊ�˽���
        String octalnum = Integer.toOctalString(8);
        System.out.println(octalnum);
        
        Integer i1 = 10; //�Զ�װ��  �ϰ汾ʹ�������ʱ�ķ���
        int i2 = i1; //�Զ�����
        
        System.out.println("i1 = " + i1 + ", i2 = " + i2);
    }
    */
    
    //�����Զ�װ����Զ�����
    //�ǳ������׶εĸ��� �ͳ��������޹�
    //��ҪĿ���Ƿ���д����
    
    public static void main(String[] args)
    {
        Integer i1 = 128; //������һ��i1����
        Integer i2 = 128; //������i2  ���Ǻ�i1��������ַ
        System.out.println(i1 == i2); //false
        
        //������(-128 ~ 127)֮��, Java��������"���ͳ�����", �����ڷ�������
        Integer i3 = 127; //���ᴴ������ ֱ��ʹ�����γ������е�����
        Integer i4 = 127;
        System.out.println(i3 == i4); //true
    }
}