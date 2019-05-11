// 定义一个顾客类
public class Customer
{
    //定义属性
    //成员变量
    private int age; //private 修饰的数据只能在本类中访问
    
    //由于加了private, 使得外部无法访问age
    //对外提供两个公开的方法
    
    //set
    public void setAge(int _age)
    {
        //方法更改
        if(_age < 0 || _age > 100)
        {
            System.out.println("不合法的数据");
            return;
        }
        age = _age;
    }
    
    //get
    public int getAge() //该方法称为成员方法或非静态方法, 必须使用"引用."方式访问
    {
        return age;
    }
}