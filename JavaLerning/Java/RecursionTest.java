
public class RecursionTest
{
    //������
    public static void main(String[] args)
    {
        /*
        System.out.println("main begin");
        
        //����doSome
        doSome();
        
        System.out.println("main over");
        */
        
        /*
        ��ʹ�õݹ����1~10�ĺ�(���Բ��þ�����Ҫ��)
        */
        
        /*
        int n = 10;
        int sum = 0;
        
        for(int i = 1; i <= 10, i++)
        {
            sum += i;
        }
        
        System.out.println(sum);
        */
        
        //ʹ�õݹ�
        /*
        int n = 10;
        int retValue = sum(n);
        System.out.println(retValue);
        */
        
        //��ʹ�õݹ����5�Ľ׳�
        /*
        int n = 5;
        int fac = 1;
        
        for(; n >= 1; n--){
            fac *= n;
        }
        
        System.out.println(fac);
        */
        
        //ʹ�õݹ�
        int n = 5;
        int retValue = fac(n);
        System.out.println(retValue);
    }
    
    //���µĴ���Ƭ��ֻ��һ��, �����Ա��ظ�����, ��ÿ�ε��ö�����ջ�ڴ��з����µ��ڴ�ռ�
    /*
    public static void doSome()
    {
        System.out.println("Begin");
        doSome(); //���д��벻����, ��һ�в�ִ��
        System.out.println("Over");
    }
    */
    
    /*
    public static int sum(int n)
    {
        if(n <= 1){
            return n;
        }
        
        return n + sum(n - 1);
    }
    */
    
    public static int fac(int n)
    {
        if(n <= 1){
            return 1;
        }
        
        return n * fac(n - 1);
    }
}

/*
    ���ڷ����ĵݹ����
        -�Լ������Լ�, ��:
        a(){
            a();
        }
        
        -�ݹ�ܺķ�ջ�ڴ�, ��������
        -java.lang.StackOverflowError  ջ�ڴ��������
         �����޷����, ����JVMֹͣ����
         
        -�ݹ�����н�������, ����һ���ᷢ��ջ�ڴ��������
        -��ʹ�н�������, Ҳ���ܷ����������, �������ڵݹ��������
        
        -ĳЩ����¹��ܵ�ʵ�ֱ��������ݹ鷽ʽ. (���� Ŀ¼����)
        
*/