/*
    ����super�ؼ���:
        -super������������, super�д洢�Ĳ����ڴ��ַ
        -superָ��Ĳ��Ǹ������, ���ǵ�ǰ��������еĸ�������
        
        -������͸����ж���ĳ������(���� name, work())
         ������������Ҫ���ʸ����е�����, ��Ҫʹ��super
         
        -super�������ڳ�Ա������
        -super�������ڹ��췽����
            -�﷨: super(ʵ��)
        -����:
            -ͨ������Ĺ��췽�����ø���Ĺ��췽��
        
        -�﷨����
            -һ�����췽����һ����û��this(...); Ҳû����ʾ��ȥ����super(...); ϵͳ��Ĭ�ϵ���super();
            -super(...);�ĵ���ֻ�ܷ��ڹ��췽���ĵ�һ��
            
            -super(...);��this(...)���ܹ���
            
            -super(...);�����˸���ķ���, ���ǲ��ᴴ���������
            
            -��Java��ֻҪ�Ǵ�������, ��ôobject�е��޲ι��췽��һ����ִ��
        
        -super�������ھ�̬������
        
        ���ڵ���ģʽ��ȱ��:
            -���췽��˽�л�
*/
public class SuperTest02
{
    public static void main(String[] args)
    {
        Manager m = new Manager();
        m.m1();
        
        DebitAccount da = new DebitAccount();
    }
}

//���常��

class Employee
{
    //�������
    String name = "Alpha";
    
    //�����Ա����
    public void work()
    {
        System.out.println("Employee is working");
    }
}

//��������

class Manager extends Employee
{
    String name = "Bravo";
    
    //��������
    public void work()
    {
        System.out.println("Manager is working");
    }
    
    public void m1()
    {
        this.work();
        work();
        
        super.work();
        
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }
    
    /*
    //this��super��ͬ, ���������ھ�̬��������
    public static void m2()
    {
        System.out.println(super.name);
    }
    */
}

class Account
{
    //Field
    private String actno;
    private double balance;
    
    //Constructor
    public Account()
    {
        //super(); //�˴���һ��Ĭ��ִ��super()
        System.out.println("�޲ι��췽��");
    }
    
    public Account(String actno, double balance)
    {
        //super();
        this.actno = actno;
        this.balance = balance;
    }
    
    //��ȡ˽������
    public void setActno(String actno)
    {
        this.actno = actno;
    }
    
    public String getActno()
    {
        return actno;
    }
    
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    public double getBalance()
    {
        return balance;
    }
}

class DebitAccount extends Account
{
    //Field
    private double debit;
    
    //Constructor
    public DebitAccount()
    {
        //super(); Ĭ��ִ��
    }
    
    public DebitAccount(String actno, double balance, double debit)
    {
        //this.actno = actno; //�޷����� ˽�л�����
        //this.balance = balance; //�޷����� ˽�л�����
        
        //ͨ������Ĺ��췽��ȥ���ø���Ĺ��췽��, ����Ϊ: ����ǰ��������еĸ�����������ֵ
        super(actno, balance);
        //super(actno, balance); //����, ֻ�ܵ���һ�� �ڵ�һ��
        
        this.debit = debit;
    }
    
    //��ȡ˽������
    public void setDebit(double debit)
    {
        this.debit = debit;
    }
    
    public double debit()
    {
        return debit;
    }
}

/*
//����ģʽ������û������, �޷����̳�
//����
class Servlet
{
    //���췽��˽��
    private Servlet(){}
}

//����
class HttpServlet extends Servlet{}
*/