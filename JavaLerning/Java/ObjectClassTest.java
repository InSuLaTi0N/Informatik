/*
    ����Object��:
        -toString()����:
            -����Java������ַ�����ʾ��ʽ
            -�ڿ���������, object�е�toString�����޷���������
            -��Ϊobject�е�toStringʵ�ֵĽ���޷���������
            
            -object�е�toStringһ��ᱻ��д(override)
            
            -toStringд��:
            public String toString() {
                return getClass().getName() + "@" + Integer.toHexString(hashCode());
            }
            
            -object�е�toString��������:
                -����@Java������ڴ��ַ������ϣ�㷨(hashCode Algorithm)�ó���int����ֵ��ת����ʮ������
            -���������ɵ�ͬ����Java�����ڶ�(heap)�е��ڴ��ַ
            
        -equals()����:
            -Object�е�equals����:
            public boolean equals(Object obj) {
                return (this == obj);
            }
            
            -Object�е�equals�����Ƚϵ����������õ��ڴ��ַ
            
            -Java�����е�equals���������Ŀ��:
                �ж����������Ƿ�һ��
                
        -finalize()����:
            -finalize����ÿ��Java������
            -finalize������ϵͳ�Զ�����
            -Java������û�и�������ָ����ʱ, ��Java������Ϊ��������(Garbage)
             �ȴ�����������(Garbage Collection)�Ļ���
             �����������ڻ��ո�Java����֮ǰ���Զ����øö����finalize����
             
            -finalize������԰����ͷ���Դ
            -��finalize�����п�������������ָ��ö���, ʹ�øö��󲻻ᱻ����
            
        -hashCode()����:
            -public native int hashCode();
            -nativeΪ���ط�������
            -�÷������ǳ��󷽷�
            -�ײ����C++����ʵ��, ���ö�̬���ӿ��ļ�(.dll)
            
            -�÷������ض���Ĺ�ϣ��ֵ
            -java������ڴ��ַ����hashCode Algorithm�ó���int���͵���ֵ
*/

public class ObjectClassTest
{
    public static void main(String[] args)
    {
        //����toString���
        
        /*
        //����object��Ķ���
        Object o1 = new Object();
        
        //����toString
        String str1 = o1.toString();
        
        System.out.println(str1); //��� java.lang.Object@5c8da962
        
        //����Person���͵Ķ���
        Person p1 = new Person("Alpha", 20);
        
        //����toString
        String str2 = p1.toString();
        
        System.out.println(str2); // ��� Person@2c13da15
        //�޸ĺ����Person [name = Alpha, age = 20]
        
        Person p2 = new Person("Bravo", 25);
        System.out.println(p2.toString()); //��� Person [name = Bravo, age = 25]
        
        //print����������������������, ��print������Ĭ�ϵ����������͵�toString����
        System.out.println(p2); //��� Person [name = Bravo, age = 25]
        */
        
        /*
        //����equals���
        
        Object o1 = new Object();
        Object o2 = new Object();
        
        boolean b1 = o1.equals(o2);
        System.out.println(b1); //false
        
        //����o1����object�е�equals����, this = o1
        //o2Ϊ���ݽ�ȥ�Ĳ���, obj = o2
        //�����жϵ��� this == obj �� o1 == o2
        //�� == ��������������, ��Ƚ��ڴ��ַ
        //��ַ��ͬ����true, ��ͬ����false
        
        Star s1 = new Star("Alice", 1234);
        Star s2 = new Star("Alice", 1234);
        Star s3 = new Star("Alice", 2345);
        
        //System.out.println(s1.equals(s2)); //false  �˴�equals��object���е�equals  ��δ��д
        System.out.println(s1.equals(s2)); //true
        System.out.println(s2.equals(s3)); //false
        */
        
        //����finalize���
        //û������ָ����, �ȴ�����
        
        //ע: ����Աֻ��"����"������������������
        //�����������п��ܲ���"���ɽ���"
        //Runtime.getRuntime().gc();
        
        //����hashCode
        ObjectClassTest oct1 = new ObjectClassTest();
        System.out.println(oct1.hashCode()); //1552787810
    }
}

/*
class Person
{
    String name;
    int age;
    
    Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    //��дtoString����
    //������Ŀ�������
    //��: ��ʾ��ʽ: Person[name = ..., age = ...]
    public String toString()
    {
        return "Person [name = " + name + ", age = " + age + "]";
    }
}
*/

/*
class Star
{
    //Field
    String name;
    int id;
    
    //Constructor
    public Star(){}
    
    public Star(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
    
    //object���е�equals�����Ƚϵ����ڴ��ַ
    //������Ӧ�ñȽϵ�������
    //equals������д
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        
        if(obj instanceof Star)
        {
            Star s = (Star)obj;
            if(s.id == id && s.name.equals(name))
            {
                return true;
            }
        }
        
        return false;
    }
}
*/

/*
    java�й�����αȽ������ַ����Ƿ�һ��
    
    -java�бȽ������ַ����Ƿ�һ�� ������==
    -ֻ�ܵ���String���equals����
*/

/*
class StringTest
{
    public void m1()
    {
        String str1 = new String("ABC");
        String str2 = new String("ABC");
    
        System.out.println(str1 == str2); //false ԭ��ͬ�� (==)
    
        System.out.println(str1.equals(str2)); //true String�����Ѿ���д��equals����, �Ƚϵ�������
    }
}
*/