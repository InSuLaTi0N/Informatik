/*
    使用Java模拟单向链表
*/

public class LinkedTest
{
    //节点
    //Field
    Entry entry;
    
    //Constructor
    LinkedTest ()
    {
        //只有一个头节点
    }
    //静态内部类
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