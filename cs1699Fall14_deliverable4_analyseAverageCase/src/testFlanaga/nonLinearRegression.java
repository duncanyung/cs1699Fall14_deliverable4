package testFlanaga;
/*     Example of the use of the class Regression demonstrating
       the use of the non-linear regression method, Regression.simplexPlot
       in fitting data to the function, y = a + b.exp(-c.x)
       with b fixed and a and c unknown

       Michael Thomas Flanagan
       http://www.ee.ucl.ac.uk/~mflanaga/java/Regression.html
       April 2007

    Copyright (c) 2007 - 2014

 *   PERMISSION TO COPY:
 *   Permission to use, copy and modify this software and its documentation for 
 *   NON-COMMERCIAL purposes is granted, without fee, provided that an acknowledgement 
 *   to the author, Dr Michael Thomas Flanagan at www.ee.ucl.ac.uk/~mflanaga, 
 *   appears in all copies and associated documentation or publications. 
 *   
 *   Public listing of the source codes on the internet is not permitted. 
 *   
 *   Redistribution of the source codes or of the flanagan.jar file is not permitted. 
 *   
 *   Redistribution in binary form of all or parts of these classes is not permitted. 
 *   
 *   Dr Michael Thomas Flanagan makes no representations about the suitability 
 *   or fitness of the software for any or for a particular purpose. 
 *   Dr Michael Thomas Flanagan shall not be liable for any damages suffered 
 *   as a result of using, modifying or distributing this software or its derivatives.
 *
 ***************************************************************************************/

import java.io.FileWriter;
import java.io.Writer;

import flanagan.analysis.Regression;
import flanagan.analysis.RegressionFunction;

// Class to demonstrate non-linear regression method, Regression.simplex.
public class nonLinearRegression{

	public int solve(double[] xArray, double[] yArray){
		int ans=-1;
		RegressionFunction f6 = new nlognFunction();
		double[] nlognF=solver(xArray,yArray,f6);
		RegressionFunction f10 = new lognFunction();
		double[] lognF=solver(xArray,yArray,f10);
		//		RegressionFunction f7 = new nlog2nFunction();
		//		double[] nlog2nF=solver(xArray,yArray,f7);
		//		RegressionFunction f8 = new nlogn2Function();
		//		double[] nlogn2F=solver(xArray,yArray,f8);
//		RegressionFunction f0 = new constantFunction();
//		double[] cF=solver(xArray,yArray,f0);
		RegressionFunction f1 = new nFunction();
		double[] nF=solver(xArray,yArray,f1);
		RegressionFunction f2 = new nSqaureFunction();
		double[] nSF=solver(xArray,yArray,f2);
		RegressionFunction f3 = new nCubeFunction();
		double[] n3F=solver(xArray,yArray,f3);
		RegressionFunction f4 = new n_pow4Function();
		double[] n4F=solver(xArray,yArray,f4);
		RegressionFunction f9 = new n_pow5Function();
		double[] n5F=solver(xArray,yArray,f9);
/*
		System.out.println("function  error");
//		System.out.println("constant "+  cF[0]);
		System.out.println("n        "+  nF[0]);
		System.out.println("nSF      "+  nSF[0]);
		System.out.println("n3F      "+  n3F[0]);
		System.out.println("n4F      "+  n4F[0]);
		System.out.println("n5F      "+  n5F[0]);
		System.out.println("nlogn    "+  nlognF[0]);
		System.out.println("logn     "+  lognF[0]);
*/		//		System.out.println("nlog2n   "+  nlog2nF[0]);
		//		System.out.println("nlogn2   "+  nlogn2F[0]);

//		if(cF[0]<nSF[0] && cF[0]<nlognF[0] && cF[0]<n3F[0] && cF[0]<n4F[0] && cF[0]<n5F[0] && cF[0]<lognF[0] && cF[0]< nF[0]){// && nF[0]<nlog2nF[0] && nF[0]<nlogn2F[0]){
//			System.out.println("O(c)");
//			ans=0;
//		}
		if(nF[0]<nSF[0] && nF[0]<nlognF[0] && nF[0]<n3F[0] && nF[0]<n4F[0] && nF[0]<n5F[0] && nF[0]<lognF[0]){// && nF[0]< cF[0]){// && nF[0]<nlog2nF[0] && nF[0]<nlogn2F[0]){
			System.out.println("O(n)");
			ans=1;
		}
		if(nSF[0]<nF[0] && nSF[0]<nlognF[0] && nSF[0]<n3F[0] && nSF[0]<n4F[0] && nSF[0]<n5F[0] && nSF[0]<lognF[0]){// && nSF[0]< cF[0]){//nSF[0]<nlog2nF[0] && nSF[0]<nlogn2F[0]){
			System.out.println("O(n^2)");
			ans=2;
		}
		if(n3F[0]<nF[0] && n3F[0]<nlognF[0] && n3F[0]<nSF[0] && n3F[0]<n4F[0] && n3F[0]<n5F[0] && n3F[0]<lognF[0]){// && n3F[0]< cF[0]){//nlognF[0]<nlog2nF[0] && nlognF[0]<nlogn2F[0]){
			System.out.println("O(n^3)");
			ans=3;
		}
		if(n4F[0]<nF[0] && n4F[0]<nlognF[0] && n4F[0]<nSF[0] && n4F[0]<n3F[0] && n4F[0]<n5F[0] && n4F[0]<lognF[0]){// && n4F[0]< cF[0]){//nlognF[0]<nlog2nF[0] && nlognF[0]<nlogn2F[0]){
			System.out.println("O(n^4)");
			ans=4;
		}
		if(n5F[0]<nF[0] && n5F[0]<nlognF[0] && n5F[0]<nSF[0] && n5F[0]<n3F[0] && n5F[0]<n4F[0] && n5F[0]<lognF[0]){// && n5F[0]< cF[0]){//nlognF[0]<nlog2nF[0] && nlognF[0]<nlogn2F[0]){
			System.out.println("O(n^5)");
			ans=5;
		}
		if(nlognF[0]<nF[0] && nlognF[0]<nSF[0] && nlognF[0]<n3F[0] && nlognF[0]<n4F[0] && nlognF[0]<n5F[0] && nlognF[0]<lognF[0]){// && nlognF[0]< cF[0]){//nlognF[0]<nlog2nF[0] && nlognF[0]<nlogn2F[0]){
			System.out.println("O(nlogn)");
			ans=6;
		}
		if(lognF[0]<nF[0] && lognF[0]<nSF[0] && lognF[0]<n3F[0] && lognF[0]<n4F[0] && lognF[0]<n5F[0] && lognF[0]<nlognF[0]){// && lognF[0]< cF[0]){//nlognF[0]<nlog2nF[0] && nlognF[0]<nlogn2F[0]){
			System.out.println("O(logn)");
			ans=7;
		}
		return ans;
	}

	public double[] solver(double[] xArray, double[] yArray,RegressionFunction f){
		// initial estimates of a and c in y= a+ c f(x)
		double[] start = new double[2];
		start[0] = 6.0D;      // initial estimate of a
		start[1] = 0.1D;      // initial estimate of c

		// initial step sizes for a and c in y= a+ c f(x)
		double[] step = new double[2];
		step[0] = 0.06D;      // initial step size for a
		step[1] = 0.005D;     // initial step size for c

		// create an instance of Regression
		Regression reg = new Regression(xArray, yArray);//, sdArray);
		//set maximum number of step for gradient descent
		reg.setNmax(3000);

		// call non-linear regression using default tolerance and maximum iterations and plot display option
		//		reg.simplexPlot(f, start, step);
		reg.simplex(f, start, step);

		return reg.getBestEstimatesErrors();
	}

	public double[] solver2(double[] xArray, double[] yArray,RegressionFunction f){
		// initial estimates of a and c in y= a+ c f(x)
		double[] start = new double[1];
		start[0] = 6.0D;      // initial estimate of a

		// initial step sizes for a and c in y= a+ c f(x)
		double[] step = new double[1];
		step[0] = 0.06D;      // initial step size for a

		// create an instance of Regression
		Regression reg = new Regression(xArray, yArray);//, sdArray);
		//set maximum number of step for gradient descent
		reg.setNmax(100000);

		// call non-linear regression using default tolerance and maximum iterations and plot display option
		reg.simplexPlot(f, start, step);

		return reg.getBestEstimatesErrors();
	}
}