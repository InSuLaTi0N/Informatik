// ����һ���˿���
public class Customer
{
    //��������
    //��Ա����
    private int age; //private ���ε�����ֻ���ڱ����з���
    
    //���ڼ���private, ʹ���ⲿ�޷�����age
    //�����ṩ���������ķ���
    
    //set
    public void setAge(int _age)
    {
        //��������
        if(_age < 0 || _age > 100)
        {
            System.out.println("���Ϸ�������");
            return;
        }
        age = _age;
    }
    
    //get
    public int getAge() //�÷�����Ϊ��Ա������Ǿ�̬����, ����ʹ��"����."��ʽ����
    {
        return age;
    }
}