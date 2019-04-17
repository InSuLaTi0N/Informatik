/*
	以下程序不使用方法语法, 分析程序缺点
*/

public class MethodTest{
	
	//类体
	//方法出现在类体中
	
	//String[] 是引用数据类型  args是局部变量名
	//main是程序入口
	public static void main(String[] args){
		
		/*
		//需求, 编写程序计算10+20, 并输出结果
		int a = 10;
		int b = 20;
		System.out.println(a + " + " + b + " = " + (a + b));
		
		//需求2: 计算666+888, 并输出结果
		a = 666;
		b = 888;
		System.out.println(a + " + " + b + " = " + (a + b));
		
		//需求3: 计算111+222
		a = 111;
		b = 222;
		System.out.println(a + " + " + b + " = " + (a + b));
		
		/*
			以上三个需求其实为一个, 只是数据不同
			因此整合为一个方法 使得代码重复使用
			java中有机制, 使得某功能代码只需写一遍.
			使用这个功能, 只需给这个功能传输具体数据.
			功能完成后返回最终结果.
			这样代码可以重复使用 我们称为"方法"
			使用这个代码, 我们称为"调用 (invoke)"
		*/
		
		MethodTest.sumInt(2, 5);
		
		//MethodTest.sumInt();   //编译错误 参数数量不同
		//MethodTest.sumInt(true, false); //错误, 实参形参类型不同
		//MethodTest.sumInt(3.0, 15); //错误, 类型不同  但是可以强制类型转换
		
		/*
		MethodTest.sumInt(12, 34);
		
		MethodTest.sumInt(56, 78);
		
		MethodTest.sumInt(90, 101);
		*/
		// 调用的方法处main暂停, 进入方法 方法体运行结束后继续执行
	}
	
	//以下为方法:
	
	
	//自定义方法  不是程序入口
	//计算两个数的和
	//修饰符 public static
	//返回值: void
	//方法名: sumInt
	//形参列表(int m, int n)
	public static void sumInt(int m, int n){
		
		int sum = m + n;
		System.out.println(m + " + " + n + " = " + sum);
		
		//调用dosome
		MethodTest.doSome();
	}
	
	/*
		方法的调用不一定在main中, 可以在别的方法体中调用
		只要是程序可以执行到的位置, 都可以调用方法
	*/
	
	
	public static void doSome(){
		System.out.println("do some");
	}
	
	//注意: 方法中不能定义方法
	//在一个class中可定义多个方法, 顺序随意
	
	/*
		关于java语言中的方法:
			方法定义, 语法结构:
				[修饰符列表] 返回值类型 方法名(形参列表){
					
					方法体;
				}
			
			方法的修饰符列表中有static, 调用方法为:
				类名.方法名(实际参数);
				
		关于返回类型:
			一个方法可以完成某个特定功能, 大多数功能结束后需要返回最终结果, 执行结果可能是一个具体存在的数据.
			这个数据就是返回值.
			
			返回值是指具体存在的数据, 数据都是有类型的, 此处需要指定的是返回值的具体类型.
			
			返回值可以指定任意一种数据类型. 包括基本数据类型和引用数据类型.
			
			也可能方法执行结束后不返回任何数据
			java规定: 若一个方法执行结束后不返回任何数据, 则返回值类型必须编写: void
		
			返回值类型不是void, 表示该方法执行结束后必须返回一个具体数值, 当方法结束后没有返回任何数据, 则编译报错.
			且返回值数据类型必须和方法的返回值类型一致.
		
			返回值是void时, 方法体中不可以写"return 值;"这样的语句, 但是可以写"return;"
		
			只要带有return关键字的语句执行, return语句所在的方法结束.
		
		关于方法名:
			只要是合法的标识符即可
			最好见名知意
			最好是动词
			方法名首字母要求小写, 其余每个单词首字母大写
			
		关于方法体:
			必须在{}内
			代码自上而下执行
			由java语句构成, ;结尾
			
		关于形式参数:
			形参是局部变量
			形参的个数可以是0-n个
			多个形参由, 隔开
			形参中起决定性作用的是形参数据类型, 形参名为局部变量名字
			方法在调用的时候, 实际给该方法传递的真实数据被称为:实际参数
			实参列表和形参列表必须满足: 数量相同, 类型对应相同
			
			例如:
				方法定义
				public static int sum(int x, int y){// 变量名为合法标识符即可, (int x, int y)是形参列表
					
				}
				方法调用
				sum("abc", "def"); 编译报错
				sum(10, 20);//(10, 20)为实参列表
		关于方法调用:
			方法只定义, 不调用时不会执行, 只有调用才会执行
			语法规则(仅当方法的修饰列表有static):
				类名.方法名(实参列表); //这是一条java语句, 表示调用某个类的方法, 传递这样的实参
	*/
}