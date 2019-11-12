/*
    ���������еķ���������չ:
        -�̳�(������϶Ƚϸ�, ��������Ŀ��չ))
        -װ����ģʽ:
            -������϶ȵ�, ������չ
            -Ҫ��:
                -װ�����к��б�װ���ߵ�����
                -װ���ߺͱ�װ����ʵ��ͬһ������
*/

public class Decorator {
    
    public static void main(String[] args) {
        /*
        //������װ����
        Laptop laptop = new Laptop();

        //����װ����
        Desktop desktop = new Desktop(laptop);
        */

        Computer desktop = new Desktop(new Laptop());

        //ִ��װ�����еķ���, ���ִ�б�װ���ߵķ���
        desktop.action();
    }
}

class Laptop extends Computer {

    public void action() {

        System.out.println("keypressed");
    }
}

//ʹ��Desktop��Laptop�еķ�����չ
class Desktop extends Computer { //Desktop����װ����

    //������ϵ
    Computer laptop; //LaptopΪ��װ����

    //���캯��
    Desktop(Computer laptop) {

        this.laptop = laptop;
    }

    //��Laptop�е�action������չ
    public void action() {

        //��չ����
        System.out.println("mousePressed");

        //������չǰ����
        laptop.action();

        //��չ����
        System.out.println("self build");
    }
}

abstract class Computer {

    abstract void action();
}