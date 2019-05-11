import java.math.BigDecimal;

/*
	作业要求: 尝试输出正确的值: x1y1 + x2y2 + x3y3 + x4y4 + x5y5 +x6y6
	gegeben: x1 = 10^20, x2 = 1223, x3 = 10^18, x4 = 10^15, x5 = 3, x6 = -10^12
			 y1 = 10^20, y2 = 2, y3 = -10^22, y4 = 10^13, y5 = 2111, y6 = 10^16
			 
			 正确值为 8779
*/


public class Aufgabe{
    public static void main(String[] args){
        
        //以下为强制类型转换 double --> long
        
        long x1y1 = Aufgabe.sumLong((long)1E20, (long)1E20);
        long x2y2 = Aufgabe.sumLong(1223L, 2L);
        long x3y3 = Aufgabe.sumLong((long)1E18, (long)-1E22);
        long x4y4 = Aufgabe.sumLong((long)1E15, (long)1E13);
        long x5y5 = Aufgabe.sumLong(3L, 2111L);
        long x6y6 = Aufgabe.sumLong((long)-1E12, (long)1E16);
        long sum = x1y1 + x2y2 + x3y3 + x4y4 + x5y5 + x6y6;
        
        System.out.println(sum);
        
        //以上结果输出为8780  与原结果差1
        
		/*
		//以下为BigDecimal尝试过程
		
		BigDecimal a1 = new BigDecimal(Aufgabe.mul(Math.pow(10, 20), Math.pow(10, 20)));
		BigDecimal a2 = new BigDecimal(Aufgabe.mul(1223, 2));
		BigDecimal a3 = new BigDecimal(Aufgabe.mul(Math.pow(10, 18), Math.pow(-10, 22)));
		BigDecimal a4 = new BigDecimal(Aufgabe.mul(Math.pow(10, 15), Math.pow(10, 13)));
		BigDecimal a5 = new BigDecimal(Aufgabe.mul(3, 2111));
		BigDecimal a6 = new BigDecimal(Aufgabe.mul(Math.pow(-10, 12), Math.pow(10, 16)));
		BigDecimal sum = new BigDecimal(0);
		
		sum = sum.add(a6.add(a5.add(a4.add(a3.add(a2.add(a1))))));
		
		System.out.println(sum);
		
		//最后输出结果不符
		*/
	}
    
    public static long sumLong(long m, long n){
        return m * n;
    }
	
	/*
	public static double mul(double m, double n){
		BigDecimal b1 = new BigDecimal(Double.toString(m));
		BigDecimal b2 = new BigDecimal(Double.toString(n));
		BigDecimal product = new BigDecimal(String.valueOf(1));
		product = b1.multiply(b2);
		return product.doubleValue();
	}
	*/
        //以下为普通浮点数计算
		
	/*
	public static double mul(double m, double n){
		double product = m * n;
		return product;
	}
	
	public static void main(String[] args){
		double sum = Aufgabe.mul(1E20, 1E20) + Aufgabe.mul(1223, 2)
		+ Aufgabe.mul(1E18, -E22) + Aufgabe.mul(1E15, 1E13)
		+ Aufgabe.mul(3, 2111) + Aufgabe.mul(-E12, 1E16)
		
		System.out.println(sum);
	}
	*/
	
		//以上结果输出为0.0
}
