/*
    ��������:
        -��һ����������
        -��һ�ּ򵥵�, ���Ե����ݽṹ
        -������һ������, �������洢���������������͵�Ԫ��
        
        -����ɷ�Ϊһά����, ��ά����..
        
        -�����д洢Ԫ�ص�������ͳһ��, ÿһ��Ԫ�����ڴ�����ռ�Ŀռ��С����ͬ��
        
        -���鳤�Ȳ��ɸı�
        
        -������ʹ����Ԫ�ص��ڴ��ַ��Ϊ���������ڴ��ַ
        
        -������ÿһ��Ԫ�ض����±�, ��0��ʼ
        -�κ�һ�����鶼��һ��length����������ȡ������Ԫ�صĸ���
        -����һ��ͨ��Ԫ�ص��±��ȡԪ��, ����:
            -ȡ�õ�һ��Ԫ��:
                a1[0]
            -ȡ�����һ��Ԫ��:
                a1[a1.length-1]
        
        -����Ч�ʸ�, ������ɾԪ��Ч�ʽϵ�
        
        -����һά����:
            -���ڳ�ʼ��һά����ķ�ʽ:
                -��̬��ʼ��
                    -�ڴ�������ʱ, �����е�Ԫ��ȷ��, ��ʹ�þ�̬��ʼ��
                    -�������� [] ������ = {Ԫ�ؼ���,����};
                    -��: int [] a = {1, 2, 3};
                -��̬��ʼ��
                    -�ڴ�������ʱ, ������Ԫ�ز�ȷ��, ���ȿ��ٿռ�, ��ʹ�ö�̬��ʼ��
                    -�������� [] ������ = new �������� [���鳤��];
                    -��: Object [] obj = new Object [5];
                    
        -���ڶ�ά����:
            -������ÿһ��Ԫ�ض���һά����
                    
    ����main�����еĲ����б� String[] args:
        -String[] args ���������������в�����, ����:
            java ArrayTest a b c d
        -JVM�ڵ���ArrayTest�е�main����֮ǰ, �Ƚ�"a b c d"����ַ�����"�ո�"�ķ�ʽ�ָ�, Ȼ��洢��String������
*/

public class ArrayTest
{
    /*
    //���ھ�̬��ʼ��һά����
    
    public static void main(String[] args)
    {
        //����һά����
        //����һά����, �洢int����
        int [] a1 = {100, 200, 150, 300}; //�÷�ʽ����"��̬��ʼ��һά����"
        //a1�����б������һά�������Ԫ�ص��ڴ��ַ
        
        //boolean���͵�����
        boolean [] b1 = {true, false, true};
        
        //String���͵�����
        String [] strs = {"Alpha", "Bravo", "Charlie"};
        
        //byte���͵�����
        byte [] b2 = {1, 2, 3};
        
        //char���͵�����
        char [] c1 = {'A', 'B', 'C'};
        
        //object���͵�����
        Object o1 = new Object(); //����һ��object����, ʹ��o1����ָ��ö���ĵ�ַ
        Object o2 = new Object();
        Object o3 = new Object();
        
        Object [] obj = {o1, o2, o3}; //�˴�obj����ָ��ĵ�ַ��o1���ڴ��ַ, ����o1ָ��Ķ�����ڴ��ַ, ���߲���ͬ
        
        System.out.println(a1[2]); //ȡ������Ԫ��
        System.out.println(a1[a1.length-1]); //ȡ���һ��Ԫ��
        System.out.println(a1.length);
        
        //����һά����
        for(int i = 0; i < a1.length; i++)
        {
            System.out.println(a1[i]);
        }
        
        //����2��Ԫ�ظ�Ϊ100
        a1[1] = 100;
        
        System.out.println(a1[1]);
    }
    */
    
    /*
    //���ڶ�̬��ʼ��һά����
    //�����ڶ��ڴ��з����������, ����������ÿһ��Ԫ�ض�����Ĭ��ֵ
    
    public static void main(String[] args)
    {
        //����һ��int���͵�һά����
        int [] a1 = new int [4]; //�÷�ʽ����"��̬��ʼ��һά����", �����Դ洢4��Ԫ��
        
        //����
        for(int i = 0; i < a1.length; i++)
        {
            System.out.println(a1[i]); //0 0 0 0
        }
        
        //��ֵ
        a1 [0] = 20;
        a1 [1] = 21;
        a1 [2] = 22;
        a1 [3] = 23;
        
        //���±���
        for(int i = 0; i < a1.length; i++)
        {
            System.out.println(a1[i]); //20 21 22 23
        }
        
        //�������͵�����
        Object [] obj = new Object [3];
        
        //����
        for(int i = 0; i < obj.length; i++)
        {
            Object o = obj [i];
            //o.toString(); //����: Exception in thread "main" java.lang.NullPointerException
            //��������Ĭ��ֵΪnull, ���ʹ��ʱҪע���ָ���쳣
            System.out.println(o); //null null null
        }
    }
    */
    
    /*
    //����һά������Ԫ�ص�����
    
    public static void main(String[] args)
    {
        //����: ��������, ȡ��ÿ������, ������ΪDogִ��eat, ΪCatִ��move
        //����Animal���͵�����, ����Animal��Dog��Cat�ĸ���, ��ʹ�ö�̬
        Animal [] a = new Animal [5];
    
        //������ÿ��Ԫ�ظ�ֵ
        Dog d1 = new Dog();
        Dog d2 = new Dog();
        Cat c1 = new Cat();
        Cat c2 = new Cat();
    
        a[0] = d1;
        a[1] = d2;
        a[2] = c1;
        a[3] = c2;
    
        //��������
        for(int i = 0; i < a.length; i++)
        {
            //�ж�������ǿ������ת��
            if(a[i] instanceof Dog)
            {
                Dog d = (Dog) a[i];
                d.eat();
            }
            else if(a[i] instanceof Cat)
            {
                Cat c = (Cat) a[i];
                c.move();
            }
            else
            {
                System.out.println(a[i]);
            }
        }
    }
    */
    
    /*
    //���ڵ��÷���, ��������
    
    public static void main(String[] args)
    {
        //����m1����, ����������Ϊ����
        //��һ�ַ���
        int [] a = {1, 2, 3, 4, 5};
        m1(a);
        
        //�ڶ��ַ���
        m1(new int [] {6, 7, 8, 9, 10});
    }
    
    public static void m1(int[] a)
    {
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
    */
    
    /*
    //����main���������б�
    
    public static void main(String[] args)
    {
        System.out.println("args���鳤��Ϊ: " + args.length);
        
        //����
        for(int i = 0; i < args.length; i++)
        {
            System.out.println(args[i]);
        }
    }
    */
    
    /*
    //��������Ŀ���
    //����: System.arraycopy(Դ����, Դ����Ŀ�ʼ�±�, Ŀ������, Ŀ������Ŀ�ʼ�±�, �����ĳ���);
    
    public static void main(String[] args)
    {
        int [] src = {2,3,4,5,6,7,8};
        int [] dest = {10,11,12,13,14,15,16};
        
        //��src��345������dest��, �滻13 14 15
        System.arraycopy(src, 1, dest, 3, 3);
        
        for(int i = 0; i < dest.length; i++)
        {
            System.out.println(dest[i]);
        }
    }
    */
    
    /*
    //����д���鿽������
    
    public static void main(String[] args)
    {
        int[] src = {1,2,3,4,5};
        int[] dest = {11,12,13,14,15};
        
        m1(src, 1, dest, 2, 3);
        for(int i = 0; i < dest.length; i++)
        {
            System.out.println(dest[i]);
        }
    }
    
    public static void m1(int[] src, int srcstart, int[] dest, int deststart, int copylength)
    {
        for(int i = srcstart; i < srcstart + copylength; i++)
        {
            dest[deststart] = src[i];
            deststart++;
        }
    }
    */
    
    /*
    //���ڶ�ά����
    public static void main(String[] args)
    {
        //��ά���龲̬��ʼ��
        int[][] a = {
                        {1, 2, 3}, 
                        {41, 52, 63}, 
                        {7, 865, 9},
                        {00}
                    };
                    
        //�Զ�ά�������
        for(int i = 0; i < a.length; i++)
        {
            for(int j = 0; j < a[i].length; j++)
            {
                System.out.print(a[i][j] + "\t");
            }
            
            System.out.println();
        }
        
        //��ά���鶯̬��ʼ��
        int[][] b = new int[3][4]; //��ʾ��3��һά����, ÿ��һά������4��Ԫ��
    }
    */
    
    //ʹ������ģ��ջ(Stack)
    //����ȳ� �Ƚ����
    public static void main(String[] args)
    {
        Stack s = new Stack();
        
        Name n1 = new Name("A");
        Name n2 = new Name("B");
        Name n3 = new Name("C");
        Name n4 = new Name("D");
        Name n5 = new Name("E");
        Name n6 = new Name("F");
        
        try
        {
            s.push(n1);
            s.push(n2);
            s.push(n3);
            s.push(n4);
            s.push(n5);
            
            //s.push(n6); //java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
        }
        catch(StackException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            for(int i = 1; i <= 5; i++)
            {
                System.out.println(s.pop());
            }
            
            //s.pop(); //java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 5
        }
        catch(StackException e)
        {
            e.printStackTrace();
        }        
    }
}

/*
class Animal
{
    
}

class Dog extends Animal
{
    public void eat()
    {
        System.out.println("dog is eating");
    }
}

class Cat extends Animal
{
    public void move()
    {
        System.out.println("cat is moving");
    }
}
*/

class Stack
{
    //����
    //ģ��ջ, ���Դ洢����������͵�Ԫ��
    Object[] elements;
    
    //ָ��ջ��Ԫ���Ϸ���ջ֡
    int index;
    
    //���췽��
    Stack()
    {
        this(5);
    }
    
    Stack(int max)
    {
        elements = new Object[max];
    }
    
    //�����ṩѹջ�ķ���
    public void push(Object element) throws StackException
    {
        if(index == elements.length)
        {
            throw new StackException("Stackoverflow");
        }
        elements[index++] = element;
    }
    
    //�����ṩһ����ջ�ķ���
    public Object pop() throws StackException
    {
        if(index == 0)
        {
            throw new StackException("NullStack");
        }
        return elements[--index];
    }
}

class Name
{
    String name;
    
    Name(){}
    
    Name(String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name;
    }
}

class StackException extends Exception
{
    public StackException(){}
    public StackException(String msg)
    {
        super(msg);
    }
}