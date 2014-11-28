package testFlanaga;
import flanagan.analysis.RegressionFunction;

public class lognFunction implements RegressionFunction{
//	private double b = 0.0D;

	public double function(double[ ] p, double[ ] x){
//		double y = p[0] + b*Math.exp(-p[1]*x[0]);
		double y = p[0] + (Math.log10(x[0])/Math.log10(2))*p[1];
//		double y = p[0] + x[0]*(Math.log10(x[0])/Math.log10(2));
//		double y = x[0]*(Math.log10(x[0])/Math.log10(2))*p[0];
		return y;
	}

//	public void setB(double b){
	//	this.b = b;
	//}
}