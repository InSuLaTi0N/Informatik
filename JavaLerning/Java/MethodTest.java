/*
	���³���ʹ�÷����﷨, ��������ȱ��
*/

public class MethodTest{
	
	//����
	//����������������
	
	//String[] ��������������  args�Ǿֲ�������
	//main�ǳ������
	public static void main(String[] args){
		
		/*
		//����, ��д�������10+20, ��������
		int a = 10;
		int b = 20;
		System.out.println(a + " + " + b + " = " + (a + b));
		
		//����2: ����666+888, ��������
		a = 666;
		b = 888;
		System.out.println(a + " + " + b + " = " + (a + b));
		
		//����3: ����111+222
		a = 111;
		b = 222;
		System.out.println(a + " + " + b + " = " + (a + b));
		
		/*
			��������������ʵΪһ��, ֻ�����ݲ�ͬ
			�������Ϊһ������ ʹ�ô����ظ�ʹ��
			java���л���, ʹ��ĳ���ܴ���ֻ��дһ��.
			ʹ���������, ֻ���������ܴ����������.
			������ɺ󷵻����ս��.
			������������ظ�ʹ�� ���ǳ�Ϊ"����"
			ʹ���������, ���ǳ�Ϊ"���� (invoke)"
		*/
		
		//MethodTest.sumInt(2, 5);
		
		//MethodTest.sumInt();   //������� ����������ͬ
		//MethodTest.sumInt(true, false); //����, ʵ���β����Ͳ�ͬ
		//MethodTest.sumInt(3.0, 15); //����, ���Ͳ�ͬ  ���ǿ���ǿ������ת��
		
		/*
		MethodTest.sumInt(12, 34);
		
		MethodTest.sumInt(56, 78);
		
		MethodTest.sumInt(90, 101);
		*/
		// ���õķ�����main��ͣ, ���뷽�� ���������н��������ִ��
		
		//�������� ��95��
        /*
		System.out.println("main begin");
		m1();
		System.out.println("main over");
        */
        
        /*
        //����divide
        divide(10, 3);//����û�н��շ�������
        
        //�ñ������շ���ֵ
        //�����������ͱ���ͷ���ֵ����������ͬ(���Զ�����ת��)
        
        //boolean b = divide(10, 3);// ����  ���Ͳ�����
        int i = divide(10, 3);
        System.out.println(i);
        
        long x  = divide(10, 3); //�Զ�����ת��
        System.out.println(x);
        
        System.out.println(divide(10, 3));
        */
        
        
	}
	
	//����Ϊ����:
	
	
	//�Զ��巽��  ���ǳ������
	//�����������ĺ�
	//���η� public static
	//����ֵ: void
	//������: sumInt
	//�β��б�(int m, int n)
	
	/*
	public static void sumInt(int m, int n){
		
		int sum = m + n;
		System.out.println(m + " + " + n + " = " + sum);
		
		//����dosome
		MethodTest.doSome();
	}
	*/
	
	/*
		�����ĵ��ò�һ����main��, �����ڱ�ķ������е���
		ֻҪ�ǳ������ִ�е���λ��, �����Ե��÷���
	*/
	
	/*
	public static void doSome(){
		System.out.println("do some");
	}
	*/
	/*
		��������������: 
        main begin
        m1 begin
        m2 begin
        m3 begin
        m3 over
        m2 over
        m1 over
        main over
        
        �Ըó���:
            main���ȱ�����, �����һ������
            ������m3, �����Ƚ���
            
            �������϶�������ִ��
            ��ǰ�г���δ����, ����һ���޷�ִ��
	*/
    
    /*
	public static void m1(){
		System.out.println("m1 begin");
		m2();
		System.out.println("m1 over");
	}
	
	public static void m2(){
		System.out.println("m2 begin");
		m3();
		System.out.println("m2 over");
	}
	
	public static void m3(){
		System.out.println("m3 begin");
		System.out.println("m3 over");
	}
    */
	
    //����Ϊ���ڷ��صĴ���
    
    /*
    //���� ȱ�ٷ������
    public static int divide (int a, int b){
        
    }
    */
    
    /*
    //���� ȱ�ٷ���ֵ
    public static int divide (int a, int b){
        return;
    }
    */
    
    /*
    //���� �������Ͳ�����  ֻ�ܷ���int����
    public static int divide (int a, int b){
        return true;
    }
    */
    
    /*
    //����ͨ��  ���ǲ�������Ҫ�ķ���ֵ
    public static int divide (int a, int b){
        return 1;
    }
    */
    
    /*
    //����ͨ�� ����ֵΪ����ֵ
    public static int divide (int a, int b){
        return a / b;
    }
    */
    
    //���³�����뱨��: ȱ�ٷ������, ��������Ϊ�޷��ٷְٱ�֤return 1��ִ��
    /*
    public static int m(){
        int a = 10;
        if(a > 3){
            return 1;
        }
    }
    */
    
    //����ͨ�� ���³���ɱ�֤�з���ֵ
    /*
    public static int m(){
        int a = 10;
        if(a > 3){
            return 1;
        }else{
            return 0;
        }
    }
    */
    
    //��
    /*
    public static int m(){
        int a = 10;
        if(a > 3){
            return 1;
        }
        return 0;
    }
    */
    
    //��
    /*
    public static int m(){
        return 10 > 3 ? 1 : 0;
    }
    */
    
    /*
    public static void m(){
        
        for(int i = 0; i < 10; i++){
            if(i == 5){
                return; //��ֹm()���� ������ֹforѭ��
                //break; //��ֹforѭ��
            }
            System.out.println(i);
            
        }
        
        System.out.println("hello world");
    }
    */
    
    
	//-------------------------------------------------------------------------------------------------------------
	//ע��: �����в��ܶ��巽��
	//��һ��class�пɶ���������, ˳������
	//������һ��javaԴ�ļ���ֻ����һ��class
	
	/*
		����java�����еķ���:
			��������, �﷨�ṹ:
				[���η��б�] ����ֵ���� ������(�β��б�){
					
					������;
				}
			
			���������η��б�����static, ���÷���Ϊ:
				����.������(ʵ�ʲ���);
				
		���ڷ�������:
			һ�������������ĳ���ض�����, ��������ܽ�������Ҫ�������ս��, ִ�н��������һ��������ڵ�����.
			������ݾ��Ƿ���ֵ.
			
			����ֵ��ָ������ڵ�����, ���ݶ��������͵�, �˴���Ҫָ�����Ƿ���ֵ�ľ�������.
			
			����ֵ����ָ������һ����������. ���������������ͺ�������������.
			
			Ҳ���ܷ���ִ�н����󲻷����κ�����
			java�涨: ��һ������ִ�н����󲻷����κ�����, �򷵻�ֵ���ͱ����д: void
		
			����ֵ���Ͳ���void, ��ʾ�÷���ִ�н�������뷵��һ��������ֵ, ������������û�з����κ�����, ����뱨��.
			�ҷ���ֵ�������ͱ���ͷ����ķ���ֵ����һ��.
		
			����ֵ��voidʱ, �������в�����д"return ֵ;"���������, ���ǿ���д"return;"
            
            ����ֵ���Ͳ���voidʱ, �����б���ִ��"return xx;"  xxΪ����ֵ
            
            �������з���ֵ�ķ���ʱ, �����߿���ѡ�����,  Ҳ����ѡ�񲻽���, 
            �󲿷������ѡ����յ�
            ���63��
		
			ֻҪ����return�ؼ��ֵ����ִ��, return������ڵķ�������.
		
		���ڷ�����:
			ֻҪ�ǺϷ��ı�ʶ������
			��ü���֪��
			����Ƕ���
			����������ĸҪ��Сд, ����ÿ����������ĸ��д
			
		���ڷ�����:
			������{}��
			�������϶���ִ��
			��java��乹��, ;��β
			
		������ʽ����:
			�β��Ǿֲ�����
			�βεĸ���������0-n��
			����β���, ����
			�β�������������õ����β���������, �β���Ϊ�ֲ���������
			�����ڵ��õ�ʱ��, ʵ�ʸ��÷������ݵ���ʵ���ݱ���Ϊ:ʵ�ʲ���
			ʵ���б���β��б��������: ������ͬ, ���Ͷ�Ӧ��ͬ
			
			����:
				��������
				public static int sum(int x, int y){// ������Ϊ�Ϸ���ʶ������, (int x, int y)���β��б�
					
				}
				��������
				sum("abc", "def"); ���뱨��
				sum(10, 20);//(10, 20)Ϊʵ���б�
		���ڷ�������:
			����ֻ����, ������ʱ����ִ��, ֻ�е��òŻ�ִ��
			�﷨����(���������������б���static):
				����.������(ʵ���б�); //����һ��java���, ��ʾ����ĳ����ķ���, ����������ʵ��
			������ͬһ�����еķ���  ������ʡ�Բ�д
			����������кͱ�����ͬ�ķ����� ����������
	*/
}