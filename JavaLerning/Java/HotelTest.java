
//酒店管理系统

import java.util.Scanner;

//Test程序
public class HotelTest
{
    public static void main(String[] args)
    {
        //分别为「预退订操作」和「房间操作」创建Scanner对象
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        
        System.out.println("欢迎使用酒店管理系统, 酒店的房间列表如下:");
        
        //创建酒店对象
        Hotel h = new Hotel();
        
        //输出房间列表
        h.print();
        
        //System.out.print("若您需要预订房间, 请输入「*」; 若您需要退订, 请输入「#」: ");
        
        //String str = s1.next();
        
        //分支操作: 「预订」「退订」
        while(true) //设置多次预退订
        {
            System.out.print("若您需要预订房间, 请输入「*」; 若您需要退订, 请输入「#」; 查看房间请输入「$」; 退出请输入「exit」: ");
        
            String str = s1.next();
            
            if("*".equals(str))
            {
                System.out.print("选择您希望预订的房间, 输入0返回上一层: ");
                
                int num = s2.nextInt();
                
                if(num == 0)
                {
                    System.out.println("退出预订");
                    
                    continue; //仅跳出当前分支, 不跳出整个循环
                }
                else
                {
                    try
                    {
                        h.register(num);
                    }
                    catch(HotelException e)
                    {
                        System.out.println(e.getMessage()); //处理数字越界等可能的异常
                    }
                    
                    //h.print();
                }
            }
            else if("#".equals(str))
            {
                System.out.print("选择您要退订的房间, 输入0返回上一层: ");
                
                int num = s2.nextInt();
                
                if(num == 0)
                {
                    System.out.println("退出退订");
                    
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
                System.out.println("不正确的输入格式");
            }
        }
    }
}

class Hotel
{
    //属性
    Room[][] rooms;
    
    //构造方法
    Hotel()
    {
        this(5, 10);
    }
    
    Hotel(int rows, int cols)
    {
        rooms = new Room[rows][cols];
        
        //设置房间号, 类型, 状态
        for(int i = 0; i < rooms.length; i++)
        {
            for(int j = 0; j < rooms[i].length; j++)
            {
                if(i == 0 || i == 1)
                {
                    rooms[i][j] = new Room(((i + 1) * 100 + (j + 1)), "标准间", false);
                }
                else if(i == 2 || i == 3)
                {
                    rooms[i][j] = new Room(((i + 1) * 100 + (j + 1)), "双人间", false);
                }
                else
                {
                    rooms[i][j] = new Room(((i + 1) * 100 + (j + 1)), "豪华间", false);
                }
            }
        }
    }
    
    //打印房间列表
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
    
    
    //提供预订方法
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
                        System.out.println("房间" + num + "已成功预订");
                        rooms[i][j].setStatus(true);
                        
                        return;
                    }
                    else
                    {
                        System.out.println("房间" + num + "已被预订");
                        
                        return;
                    }
                }
            }
        }
        
        throw new HotelException("该房间不存在"); //执行至此处一定是数字越界
    }
    
    //提供退订方法
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
                        System.out.println("您已成功退订该房间");
                        rooms[i][j].setStatus(false);
                        
                        return;
                    }
                    else
                    {
                        System.out.println("您没有预订该房间或该房间已退订");
                        
                        return;
                    }
                }
            }
        }
        
        throw new HotelException("该房间不存在");
    }
    
}

class Room
{
    //属性
    private int num; //房间号
    private String type; //标间 双人间 豪华间
    private boolean used; //true 表示被占用, false表示空闲
    
    //构造方法
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
    
    //方法
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
    
    //重写toString
    public String toString()
    {
        return "[房间号: " + num + ", 房间类型: " + type + ", 使用状态: " + (used ? "被占用" : "空闲") + "]";
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