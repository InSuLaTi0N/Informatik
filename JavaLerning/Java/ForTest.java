public class ForTest{
	
	public static void main(String[] args){
		
		/*
		for(int i = 1; i <= 10; i++){
			
			System.out.println(i);
		}
		
		for(int i = 1; i <= 10; ++i){
			
			System.out.println(i);
		}
		*/
		
		/*
			��ӡ�žų˷���
			
			��������
		*/
		
		/*
		for(int i = 1; i < 10; i++){
			
			for(int j = 1; j <= i; j++){
				
				System.out.print(i + " * " + j + " = " + (i * j));
				System.out.print("\t");
			}
			
			System.out.println("");
		}
		*/
		
		/*
			�ҳ�1-100�����е�����
			
			��������
		*/
		
		/*
		for(int i = 2; i <= 100; i++){
			
			for(int j = 2; j <= i; j++){
				
				if(i % j == 0){
					
					if(i != j){
						
						break;
						
					}else{
						
						System.out.println(i);
						
					}
				}
			}
			
		}
		*/
		
		/*
			������ 1-10000��������
			ÿ8����һ��
			
			��������
		*/
		
		/*
		int flag = 0;
		
		for(int i = 2; i <= 10000; i++){
			
			for(int j = 2; j <= i; j++){
				
				if(i % j == 0){
					
					if(i != j){
						
						break;
						
					}else{
						
						System.out.print(i);
						flag += 1;
						
						if(flag == 8){
							
							System.out.println("");
							flag = 0;
							
						}else{
							
							System.out.print("  ");
							
						}
					}
				}
			}
		}
		*/
		
		
		
	}
}