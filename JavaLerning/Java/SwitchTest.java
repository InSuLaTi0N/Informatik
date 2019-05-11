/*
	Switch结构:
		switch(int/String){
			case int/String:
				...;
				...;
				...;
				break;
			case int/String:
				...;
				...;
				...;
				break;
			case int/String:
				...;
				...;
				...;
				break;
			...
			default:
				...;
		}
	原理:
		int/String 与 case中 int/String 自上而下一一匹配, 匹配成功的分支执行
		
		若有break:switch语句终止
		若无break:直接进入下一个分支(不进行匹配)[case穿透现象]
		break;可避免穿透
		若所有分支均不匹配, 若有default,执行default
		switch/case后面只能是int/string
		* byte short char可以存在于switch/case后面，因为可以进行自动类型转换
		case 可以合并
		int i = 1; // 此处 1, 2, 3, 10均运行同一程序
		
		switch(i){
			case 1: case 2: case 3: case 10: //将case合并
				System.out.println("Test");
		}
*/
import java.util.Scanner;
//import java.lang.Math;
public class SwitchTest{
	public static void main(String[] args){
		
		/*
			用switch实现计算器中的 + - * / %
			所有数据从键盘输入
			控制台输入数字,运算符,数字
			
			控制台显示:
				请输入第一个数字:
				
				请输入运算符:
				
				请输入第二个数字:
				
				运算结果:
				
				代码如下
			
		*/
		
		/*
		Scanner s = new Scanner(System.in);
		
		System.out.print("请输入第一个数字: ");
		int i = s.nextInt();
		
		System.out.print("请输入运算符: ");
		String operation = s.next();
		
		System.out.print("请输入第二个数字: ");
		int j = s.nextInt();
		
		int result = 0;
		
		switch(operation){			//注意switch大小写
			case "+":
				result = i + j;
				break;
			case "-":
				result = i - j;
				break;
			case "*":
				result = i * j;
				break;
			case "/":
				result = i / j;
				break;
			case "%":
				result = i % j;
				break;
			default :
				System.out.println("运算无效, 请检查您的输入是否正确");
		}
		
		System.out.println("结果为：" + i + " " + operation + " " + j + " = " + result);
		*/
		
		/*
			用switch判断成绩
			[0-100]为成绩区间
			可能有小数
			[90-100]-->A  [80-90)-->B  [70-80)-->C  [60-70)-->D  [0-60)-->E
			代码如下
		*/
		
		System.out.print("请输入成绩：");
		
		Scanner s = new Scanner(System.in);
		double score = s.nextDouble();
		
		
		String str1 = "您的成绩等级为：";
		String str2 = "A";
		
		switch((int)(score / 10)){
			case 9: case 10:		//程序有bug [100-110]区间不报错
				System.out.println(str1 + str2);
				break;
			case 8:
				str2 = "B";
				System.out.println(str1 + str2);
				break;
			case 7:
				str2 = "C";
				System.out.println(str1 + str2);
				break;
			case 6:
				str2 = "D";
				System.out.println(str1 + str2);
				break;
			case 5: case 4: case 3: case 2: case 1: case 0:
				str2 = "E";
				System.out.println(str1 + str2);
				break;
			default:
				System.out.println("无效的成绩");

		}
	}
}