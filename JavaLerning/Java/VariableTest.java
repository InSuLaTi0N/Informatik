/*
    ���ڲ�������:
        ���ݵ����ݿ����ǻ�����������, Ҳ������������������
*/

public class VariableTest
{
    public static void main(String[] args)
    {
        int i = 10;
        
        m1(i); //���ݻ�����������
        System.out.println("main--->" + i); //10
        
        Animal a = new Animal(10);
        
        m2(a); //����������������
        
        System.out.println("main--->" + a.age); //11
    }
    
    public static void m1(int i)
    {
        i++;
        System.out.println("m1--->" + i); //11
    }
    
    public static void m2(Animal a)
    {
        a.age++;
        System.out.println("m2--->" + a.age); //11
    }
}

//����
class Animal
{
    //Field
    int age;
    
    //Constructor
    Animal(int _age)
    {
        age = _age;
    }
}

/*
    -�ڴ��ݻ����������͵�ʱ��, ����Ϊ��������, ������ջ(Stack)��
    -���÷���, ����ѹջ����, ֵ�����뷽����
    -�������н���, ������ջ����, �ͷ��ڴ�
    -��������������, ��������
    
    -�ڴ��������������͵�ʱ��, ����ָ��һ����ַ, �õ�ַ��ֵ�����ڶ�(Heap)��
    -���÷���, ����ѹջ����, ���ò���ָ��ͬһ����ַ
    -��������, ������ջ����, ���ǵ�ַ�е�ֵ������heap��, ���ᱻ����
    -��������������, ����ָ���ַ���µ�ֵ
*/