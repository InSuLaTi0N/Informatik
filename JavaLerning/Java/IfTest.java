import java.util.Scanner;
public class IfTest{
	
	public static void main(String[] args){
		
		/*
			需求 分数 [90-100]-->A, [80-90]-->B, ...
			代码如下
		*/
		
		
		/*System.out.println("Your Score is: ");
		int score = 60;
		
		if(score >= 90 && score <= 100){
			System.out.println("A");
		}else if(score >= 80){
			System.out.println("B");
		}else if(score >= 70){
			System.out.println("C");
		}else if(score >= 60){
			System.out.println("D");
		}else if(score >= 0){
			System.out.println("E");
		}*/
		//判断年龄
		//age and life
		//age[0-150]
		//[0-5] kid
		//[6-12] child
		//[13-18] teener
		//[19-35] youth
		//[36-55] adult
		//[56-150] elder
		//代码如下
		
		/*System.out.print("Please enter your age: ");
		
		//input
		Scanner s = new Scanner(System.in);
		
		//Method next() from scanner
		//recive input value
		//s.next(); wait input
		
		int userInput = s.nextInt();
		
		
		if(userInput > 150 || userInput < 0){
			
			System.out.println("Error");
		}else if(userInput <= 150){
			
			System.out.println("You are elder");
		}else if(userInput <= 55){
			
			System.out.println("You are adult");
		}else if(userInput <= 35){
			
			System.out.println("You are youth");
		}else if(userInput <= 18){
			
			System.out.println("You are teener");
		}else if(userInput <= 12 && userInput >=6){
			
			System.out.println("You are child");
		}else{
			
			System.out.println("You are kid");
		}*/
		
		System.out.println("你好"); //判断中文编码
		/*
			判断天气：
				下雨：
					带伞：
						判断性别：
							男：黑伞
							女：花伞
						
				晴天：
					判断温度：
						30°以上：
							男：戴墨镜
							女：防晒霜
							
			注意:
				天气、性别、温度需要从控制台输入
							
			
			代码如下
		*/
		
		/*
		System.out.print("今天天气是： ");
		Scanner s = new Scanner(System.in);
		String weather = s.next();
		
		if(weather.equals("下雨")){
			System.out.print("请输入性别： ");
			String sex = s.next();
			
			if(sex.equals("男")){
				System.out.println("带黑伞");
			}else if(sex.equals("女")){
				System.out.println("带花伞");
			}else{
				System.out.println("您输入的性别格式不符");
			}
			
		}else if(weather.equals("晴天")){
			System.out.print("请输入温度：");
			int grad = s.nextInt();
			
			if(grad >= 30){
				System.out.print("请输入性别：");
				String sex = s.next();
				
				if(sex.equals("男")){
					System.out.println("戴墨镜");
				}else if(sex.equals("女")){
					System.out.println("涂防晒霜");
				}else{
					System.out.println("您输入的性别格式不符");
				}
				
			}else if(grad >= 15){
				System.out.println("正常出行");
			}else{
				System.out.println("请注意保暖");
			}
			
		}else{
			System.out.println("您输入的天气格式不符");
		}
		*/
		
		
		
	}
}