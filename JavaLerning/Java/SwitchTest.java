/*
	Switch�ṹ:
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
	ԭ��:
		int/String �� case�� int/String ���϶���һһƥ��, ƥ��ɹ��ķ�ִ֧��
		
		����break:switch�����ֹ
		����break:ֱ�ӽ�����һ����֧(������ƥ��)[case��͸����]
		break;�ɱ��⴩͸
		�����з�֧����ƥ��, ����default,ִ��default
		switch/case����ֻ����int/string
		* byte short char���Դ�����switch/case���棬��Ϊ���Խ����Զ�����ת��
		case ���Ժϲ�
		int i = 1; // �˴� 1, 2, 3, 10������ͬһ����
		
		switch(i){
			case 1: case 2: case 3: case 10: //��case�ϲ�
				System.out.println("Test");
		}
*/
import java.util.Scanner;
//import java.lang.Math;
public class SwitchTest{
	public static void main(String[] args){
		
		/*
			��switchʵ�ּ������е� + - * / %
			�������ݴӼ�������
			����̨��������,�����,����
			
			����̨��ʾ:
				�������һ������:
				
				�����������:
				
				������ڶ�������:
				
				������:
				
				��������
			
		*/
		
		/*
		Scanner s = new Scanner(System.in);
		
		System.out.print("�������һ������: ");
		int i = s.nextInt();
		
		System.out.print("�����������: ");
		String operation = s.next();
		
		System.out.print("������ڶ�������: ");
		int j = s.nextInt();
		
		int result = 0;
		
		switch(operation){			//ע��switch��Сд
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
				System.out.println("������Ч, �������������Ƿ���ȷ");
		}
		
		System.out.println("���Ϊ��" + i + " " + operation + " " + j + " = " + result);
		*/
		
		/*
			��switch�жϳɼ�
			[0-100]Ϊ�ɼ�����
			������С��
			[90-100]-->A  [80-90)-->B  [70-80)-->C  [60-70)-->D  [0-60)-->E
			��������
		*/
		
		System.out.print("������ɼ���");
		
		Scanner s = new Scanner(System.in);
		double score = s.nextDouble();
		
		
		String str1 = "���ĳɼ��ȼ�Ϊ��";
		String str2 = "A";
		
		switch((int)(score / 10)){
			case 9: case 10:		//������bug [100-110]���䲻����
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
				System.out.println("��Ч�ĳɼ�");

		}
	}
}