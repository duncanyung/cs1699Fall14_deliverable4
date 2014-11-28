package testFlanaga;
import flanagan.analysis.RegressionFunction;

public class n_powNFunction implements RegressionFunction{
//	private double b = 0.0D;

	public double function(double[ ] p, double[ ] x){
//		double y = p[0] + b*Math.exp(-p[1]*x[0]);
		double y = p[0] + Math.pow(x[0], x[0])*p[1];
//		double y = p[0] + Math.pow(x[0], x[0]);
//		double y =Math.pow(x[0], x[0])*p[0];
		return y;
	}

//	public void setB(double b){
	//	this.b = b;
	//}
}