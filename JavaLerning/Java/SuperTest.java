/*
    ������ļ̳�:
        -������̳��������������: ��������
        
        -�﷨:
            [���η��б�] class ������ extends ������
            {
                ����;
            }
            
        -java��, �����֮��ֻ֧�ֵ��̳�
        -һ������û����ʾ�ļ̳�������, �����Ĭ�ϼ̳�Object
        -Object��SUN�ṩ��Java�еĸ���
        
        -�̳��౻������, ���̳��౻��Ϊ����
        -java������̳и���, �Ὣ���������е�����ȫ���̳�, ����˽������
        -�������������޷�ֱ�ӷ��ʸ����е�˽������, ���ǿ��Լ�ӷ���
        
        -���췽���޷�������̳�
*/

public class SuperTest
{
    public static void main(String[] args)
    {
        /*
        Sub s = new Sub();
        
        s.m1();
        s.m2();
        s.m3();
        */
        Sub s1 = new Sub();
        s1.setActno("23456");
        
        //Syetem.out.println(s1.actno); //���� ����ֱ�ӷ���
        System.out.println(s1.getActno());
    }
}
/*
class Sub
{
    public void m1()
    {
        System.out.println("m1...");
    }
    
    public void m2()
    {
        System.out.println("m2...");
    }
    
    public void m3()
    {
        System.out.println("m3...");
    }
}
*/
//���ϴ��뷱��
//����̳�
class Sub extends SuperClass
{
    double debit;
    /*
    public void m3()
    {
        System.out.println("m3...");
    }
    */
}

class SuperClass
{
    private String actno;
    double balance;
    
    //Constructor
    SuperClass(){}
    
    SuperClass(String actno, double balance)
    {
        this.actno = actno;
        this.balance = balance;
    }
    
    //set and get
    public void setActno(String actno)
    {
        this.actno = actno;
    }
    
    public String getActno()
    {
        return actno;
    }
    /*
    public void m1()
    {
        System.out.println("m1...");
    }
    
    public void m2()
    {
        System.out.println("m2...");
    }
    */
}