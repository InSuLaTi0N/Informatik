/*
    ʹ��Javaģ�ⵥ������
*/

public class LinkedTest
{
    //�ڵ�
    //Field
    Entry entry;
    
    //Constructor
    LinkedTest ()
    {
        //ֻ��һ��ͷ�ڵ�
    }
    //��̬�ڲ���
    static class Entry
    {
        Object data;
        
        Entry next;
        
        //Constructor
        Entry (){}
        Entry (Object data, Entry next)
        {
            this.data = data;
            this.next = next;
        }
    }
}