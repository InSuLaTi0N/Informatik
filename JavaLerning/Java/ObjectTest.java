/*
    ���ڶ��������:
    
    ����Java�ڴ�:
        -java��������.class�ļ�, ʹ��jvm��ȡ
        -jvm������ڴ�: ����(heap), ջ��(stack), ������(method)
        -������������������Ϣ, �������еķ���, ��̬����, ����
        -.class����洢�ڷ�������
        -�������new�����Ķ���, �˶����������ռ����ռ�; �����ռ�����Եľ��Ƕ���
        -������еĶ���û������ָ����, ��ö�����������, �ȴ���������������
        -ջ����ŷ���, ÿ����һ������, �ͻ���ջ���д���һ��ջ֡, ��žֲ�����
*/

public class ObjectTest
{
    public static void main(String[] args)
    {
        
        //u1������, �����ڴ��ַָ����еĶ���
        User u1 = new User();
        
        //u1����ָ����еĶ���, ����������
        u1 = null;
        
        //ʹ�ÿյ����÷��ʳ�Ա, �ᷢ����ָ���쳣
        //System.out.println(u1.name); //Exception in thread "main" java.lang.NullPointerException(��ָ���쳣)
        
        /*
        Star s = new Star();
        s = null;
        s.sing(); //����, ��ָ���쳣
        */
    }
}

/*
class Star
{
    //��Ա����
    public void sing()
    {
        System.out.println("Sing");
    }
}
*/