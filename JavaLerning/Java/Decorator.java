/*
    对类中已有的方法进行扩展:
        -继承(代码耦合度较高, 不利于项目扩展))
        -装饰者模式:
            -代码耦合度低, 易于扩展
            -要求:
                -装饰者中含有被装饰者的引用
                -装饰者和被装饰者实现同一个类型
*/

public class Decorator {
    
    public static void main(String[] args) {
        /*
        //创建被装饰者
        Laptop laptop = new Laptop();

        //创建装饰者
        Desktop desktop = new Desktop(laptop);
        */

        Computer desktop = new Desktop(new Laptop());

        //执行装饰者中的方法, 间接执行被装饰者的方法
        desktop.action();
    }
}

class Laptop extends Computer {

    public void action() {

        System.out.println("keypressed");
    }
}

//使用Desktop对Laptop中的方法扩展
class Desktop extends Computer { //Desktop就是装饰者

    //关联关系
    Computer laptop; //Laptop为被装饰者

    //构造函数
    Desktop(Computer laptop) {

        this.laptop = laptop;
    }

    //对Laptop中的action方法扩展
    public void action() {

        //扩展代码
        System.out.println("mousePressed");

        //调用扩展前代码
        laptop.action();

        //扩展代码
        System.out.println("self build");
    }
}

abstract class Computer {

    abstract void action();
}