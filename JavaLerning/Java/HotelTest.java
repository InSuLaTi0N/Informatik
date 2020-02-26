
//�Ƶ����ϵͳ

import java.util.Scanner;

//Test����
public class HotelTest
{
    public static void main(String[] args)
    {
        //�ֱ�Ϊ��Ԥ�˶��������͡��������������Scanner����
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        
        System.out.println("��ӭʹ�þƵ����ϵͳ, �Ƶ�ķ����б�����:");
        
        //�����Ƶ����
        Hotel h = new Hotel();
        
        //��������б�
        h.print();
        
        //System.out.print("������ҪԤ������, �����롸*��; ������Ҫ�˶�, �����롸#��: ");
        
        //String str = s1.next();
        
        //��֧����: ��Ԥ�������˶���
        while(true) //���ö��Ԥ�˶�
        {
            System.out.print("������ҪԤ������, �����롸*��; ������Ҫ�˶�, �����롸#��; �鿴���������롸$��; �˳������롸exit��: ");
        
            String str = s1.next();
            
            if("*".equals(str))
            {
                System.out.print("ѡ����ϣ��Ԥ���ķ���, ����0������һ��: ");
                
                int num = s2.nextInt();
                
                if(num == 0)
                {
                    System.out.println("�˳�Ԥ��");
                    
                    continue; //��������ǰ��֧, ����������ѭ��
                }
                else
                {
                    try
                    {
                        h.register(num);
                    }
                    catch(HotelException e)
                    {
                        System.out.println(e.getMessage()); //��������Խ��ȿ��ܵ��쳣
                    }
                    
                    //h.print();
                }
            }
            else if("#".equals(str))
            {
                System.out.print("ѡ����Ҫ�˶��ķ���, ����0������һ��: ");
                
                int num = s2.nextInt();
                
                if(num == 0)
                {
                    System.out.println("�˳��˶�");
                    
                    continue;
                }
                else
                {
                    try
                    {
                        h.logout(num);
                    }
                    catch(HotelException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    
                    //h.print();
                }
            }
            else if("$".equals(str))
            {
                h.print();
            }
            else if("exit".equals(str))
            {
                break;
            }
            else
            {
                System.out.println("����ȷ�������ʽ");
            }
        }
    }
}

class Hotel
{
    //����
    Room[][] rooms;
    
    //���췽��
    Hotel()
    {
        this(5, 10);
    }
    
    Hotel(int rows, int cols)
    {
        rooms = new Room[rows][cols];
        
        //���÷����, ����, ״̬
        for(int i = 0; i < rooms.length; i++)
        {
            for(int j = 0; j < rooms[i].length; j++)
            {
                if(i == 0 || i == 1)
                {
                    rooms[i][j] = new Room(((i + 1) * 100 + (j + 1)), "��׼��", false);
                }
                else if(i == 2 || i == 3)
                {
                    rooms[i][j] = new Room(((i + 1) * 100 + (j + 1)), "˫�˼�", false);
                }
                else
                {
                    rooms[i][j] = new Room(((i + 1) * 100 + (j + 1)), "������", false);
                }
            }
        }
    }
    
    //��ӡ�����б�
    public void print()
    {
        for(int i = 0; i < rooms.length; i++)
        {
            for(int j = 0; j < rooms[i].length; j++)
            {
                System.out.print(rooms[i][j] + " ");
            }
            
            System.out.println();
        }
    }
    
    
    //�ṩԤ������
    public void register(int num) throws HotelException
    {
        for(int i = 0; i < rooms.length; i++)
        {
            for(int j = 0; j < rooms[i].length; j++)
            {
                if(rooms[i][j].getNum() == num)
                {
                    if(rooms[i][j].getStatus() == false)
                    {
                        System.out.println("����" + num + "�ѳɹ�Ԥ��");
                        rooms[i][j].setStatus(true);
                        
                        return;
                    }
                    else
                    {
                        System.out.println("����" + num + "�ѱ�Ԥ��");
                        
                        return;
                    }
                }
            }
        }
        
        throw new HotelException("�÷��䲻����"); //ִ�����˴�һ��������Խ��
    }
    
    //�ṩ�˶�����
    public void logout(int num) throws HotelException
    {
        for(int i = 0; i < rooms.length; i++)
        {
            for(int j = 0; j < rooms[i].length; j++)
            {
                if(rooms[i][j].getNum() == num)
                {
                    if(rooms[i][j].getStatus() == true)
                    {
                        System.out.println("���ѳɹ��˶��÷���");
                        rooms[i][j].setStatus(false);
                        
                        return;
                    }
                    else
                    {
                        System.out.println("��û��Ԥ���÷����÷������˶�");
                        
                        return;
                    }
                }
            }
        }
        
        throw new HotelException("�÷��䲻����");
    }
    
}

class Room
{
    //����
    private int num; //�����
    private String type; //��� ˫�˼� ������
    private boolean used; //true ��ʾ��ռ��, false��ʾ����
    
    //���췽��
    public Room()
    {
        super();
    }
    
    public Room(int num, String type, boolean used)
    {
        super();
        this.num = num;
        this.type = type;
        this.used = used;
    }
    
    //����
    public void setNum(int num)
    {
        this.num = num;
    }
    
    public int getNum()
    {
        return num;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setStatus(boolean used)
    {
        this.used = used;
    }
    
    public boolean getStatus()
    {
        return used;
    }
    
    //��дtoString
    public String toString()
    {
        return "[�����: " + num + ", ��������: " + type + ", ʹ��״̬: " + (used ? "��ռ��" : "����") + "]";
    }
}

class HotelException extends Exception
{
    public HotelException(){}
    
    public HotelException(String msg)
    {
        super(msg);
    }
}