package DeepLearning;
import java.util.Random;
public class utils {

	public static double uniform(double min,double max,Random rng){
		return rng.nextDouble()*(max-min)+min;
//		nextDouble() 方法用于获取下一个从这个伪随机数生成器的序列中均匀分布的0.0和1.0之间的double值
	}
	
	public static int binomial(int n,double p,Random rng){//二项分布。
					if(p<0||p>1)return 0;
					
					int c=0;
					double r;
					for(int i=0;i<n;i++){
						r=rng.nextDouble();
						if(r<p) c++;
					}
					return c;
	}
	public static double sigmoid(double x){
		return 1./(1.+Math.pow(Math.E, -x));
	}
	
	public static double tanh(double x){
		return Math.tanh(x);
	}
	
	public static double dtanh(double x){
		return 1.-x*x;
	}
	
	public static double ReLU(double x){
		if(x>0){
			return x;
		}else{
			return 0;
		}
	}
	
	public static double dRrLU(double x){
		if(x>0){
			return 1;
		}else{
			return 0;
		}
	}
}
