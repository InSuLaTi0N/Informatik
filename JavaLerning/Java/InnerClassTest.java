/*
    �����ڲ���:
        -���Է����ⲿ����˽�е�����
        
        -�ڲ����������:
            -��̬�ڲ���:
                -���Ե�ͬ������̬����
                -����ֱ�ӷ����ⲿ��ľ�̬����, �޷�ֱ�ӷ��ʳ�Ա
                
            -��Ա�ڲ���:
                -���Ե�ͬ������Ա����
                -�����о�̬����
                -���Է����ⲿ�����е�����
            
            -�ֲ��ڲ���:
                -���Ե�ͬ�����ֲ�����
                -�ڷ��ʾֲ�������ʱ��, �ֲ���������ʹ��final���λ���ʵ�ʵ����ձ���
            
            -�����ڲ���:
                -ָ��û������
                -ĳЩ����¿��Դ���ʵ�ֽӿڵ�����, ʹ���ٶ���һ����
                -�޷��ظ�ʹ��
*/

public class InnerClassTest
{
    /*
    //���ھ�̬�ڲ���
    
    //���徲̬����
    private static String s1 = "Alpha";
        
    //�����Ա����
    private String s2 = "Bravo";
        
    //���徲̬����
    private static void m1()
    {
        System.out.println("static m1 execute");
    }
        
    //�����Ա����
    private void m2()
    {
        System.out.println("member m2 execute");
    }
    
    //������
    public static void main(String[] args)
    {
        //����m3
        InnerClassTest.InnerClass.m3();
        InnerClass.m3(); //ʡ��д��
        
        //����m4
        InnerClass inner1 = new InnerClassTest.InnerClass();
        inner1.m4();
        
        InnerClass inner2 = new InnerClass(); //ʡ��д��
        inner2.m4();
    }
        
    //���徲̬�ڲ���
    //�����÷��ʿ���Ȩ�����η�����
    //public, protected, private, ȱʡ
    static class InnerClass
    {
        //���Է����ⲿ˽������
            
        //���徲̬����
        public static void m3()
        {
            //�����ⲿs1
            System.out.println(s1); //���Է���
            //System.out.println(s2); //����: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� s2
            
            m1(); //���Է���
            //m2(); //����: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� m2()
        }
            
        //�����Ա����
        public void m4()
        {
            System.out.println(s1);
            //System.out.println(s2); //����: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� s2
            
            m1();
            //m2(); //����: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� m2()
        }
    }
    */
    
    /*
    //���ڳ�Ա�ڲ���
    
    //���徲̬����
    private static String s1 = "Alpha";
        
    //�����Ա����
    private String s2 = "Bravo";
        
    //���徲̬����
    private static void m1()
    {
        System.out.println("static m1 execute");
    }
        
    //�����Ա����
    private void m2()
    {
        System.out.println("member m2 execute");
    }
    
    //������
    public static void main(String[] args)
    {
        
        //InnerClass inner1 = new InnerClassTest.InnerClass(); //����: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� m4()
        //inner1.m4(); //����Ҫ�����ⲿ�����
        
        
        InnerClassTest ict = new InnerClassTest();
        InnerClass inner = ict.new InnerClass();
        inner.m4();
    }
        
    //�����Ա�ڲ���
    //�����÷��ʿ���Ȩ�����η�����
    //public, protected, private, ȱʡ
    class InnerClass
    {
        //���Է����ⲿ˽������
            
        
        //���徲̬����
        //public static void m3(){} //����: �ڲ���InnerClassTest.InnerClass�еľ�̬�����Ƿ�
        
            
        //�����Ա����
        public void m4()
        {
            System.out.println(s1);
            System.out.println(s2);
            
            m1();
            m2();
            
            //���ϱ���ͨ��
        }
    }
    */
    
    /*
    //���ھֲ��ڲ���
    
    public void m1()
    {
        //�ֲ�����
        int i = 10;
        
        //����ֲ��ڲ���
        //�ֲ��ڲ��಻���÷��ʿ���Ȩ�����η�����
        //������public, protected, private
        class InnerClass
        {
            //���徲̬����
            //public static void m2(){} //����: �ڲ���InnerClass�еľ�̬�����Ƿ�
            
            //�����Ա����
            public void m3()
            {
                System.out.println(i);
                //i = 20;
                //System.out.println(i); //����: ���ڲ������õı��ر������������ձ�����ʵ���ϵ����ձ���
            }
        }
        
        //����m3����
        //�����Ǿֲ��ڲ���, �޷����������з���
        InnerClass inner = new InnerClass();
        inner.m3();
    }
    
    //������
    public static void main(String[] args)
    {
        //����m1����
        InnerClassTest ict = new InnerClassTest();
        ict.m1();
    }
    */
    
    //���������ڲ���
    
    //��̬����
    public static void m1(CustomerService cs)
    {
        cs.logout();
    }
    
    //������
    public static void main(String[] args)
    {
        //����test����
        //CustomerService cs = new Customer();
        //m1(cs);
        
        //ʹ�������ڲ���ķ�ʽִ��m1����
        //m1(new CustomerService()); //����, �ӿ��޷���������
        //���������ڲ���
        m1(new CustomerService()
        {
            public void logout()
            {
                System.out.println("logout");
            }
        }); //new CustomerService(){}���������ڲ���
    }
}

//����ӿ�
interface CustomerService
{
    void logout();
}

/*
//�������������ڲ���, ���Դ���ʵ�ֽӿڵ���
//����ʵ�ֽӿڵ�����
class Customer implements CustomerService
{
    public void logout()
    {
        System.out.println("logout");
    }
}
*/