package testFlanaga;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class measureAverageComplexity {
	//use "runs" to make 1 decision and we make "repeat" decision
	static int sampleSize=1000, repeat=50,runs=50,
			cN=0, oN=0, oN2=0, oN3=0, oN4=0, oN5=0, onlogn=0, ologn=0, err=0, domainSize=10, totalSamples=1000;
	static double diminishFactor=0.00001;
	sorting sorter=new sorting();
	searching searcher=new searching();
	arrayAlgorithms algos=new arrayAlgorithms();
	Permutation p=new Permutation();
	Random generator= new Random();
	String timeFile="time.txt";
//	static int[] nSize={1,2,3,4,5,6,7};//different size of input
	static int[] nSize={1,2,5,10,50,100};//different size of input
//	static int[] nSize={10,20,30,40,50};//different size of input
//	static int[] nSize={1,2,5,10,20,50,100};//different size of input

	public ArrayList<ArrayList<Integer>> generateUniformInput(int n,long setSize){		
		ArrayList<ArrayList<Integer>> inputSet= new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<setSize; i++){
			ArrayList<Integer> input=new ArrayList<Integer>();
			for(int j=0; j<n; j++)
				input.add(generator.nextInt(domainSize));
//				input.add(generator.nextInt(Integer.MAX_VALUE));

			inputSet.add(input);
		}
		return inputSet;
	}

	public ArrayList<Integer> generateSearchTarget(ArrayList<ArrayList<Integer>> inputSet){
		ArrayList<Integer> targets=new ArrayList<Integer>();
		for(int i=0; i<inputSet.size(); i++)
			targets.add(generator.nextInt(inputSet.get(i).size()));

		return targets;
	}

	public long mergeSortTest(ArrayList<Integer> input) {		
		long startTime1 = System.nanoTime();
		sorter.mergeSort(input, 0, input.size()-1);
		//Arrays.sort(input.toArray());
		return (System.nanoTime() - startTime1);
	}

	public long quickSortTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		//sorter.quickSort(input, 0, input.size()-1);
		Arrays.sort(intArray);
		return (System.nanoTime() - startTime1);
	}

	public long oNFunctionTest(ArrayList<Integer> input) {
		long startTime1 = System.nanoTime();
		sorter.oNFunctionWithSwap(input);
		return (System.nanoTime() - startTime1);
	}

	public long bubbleSortTest(ArrayList<Integer> input) {
		long startTime1 = System.nanoTime();
		sorter.bubbleSort(input);
		return (System.nanoTime() - startTime1);
	}

	public long n3Test(ArrayList<Integer> input) {
		long startTime1 = System.nanoTime();
		sorter.n3Sort(input);
		return (System.nanoTime() - startTime1);
	}

	public long n4Test(ArrayList<Integer> input) {
		long startTime1 = System.nanoTime();
		sorter.n4Sort(input);
		return (System.nanoTime() - startTime1);
	}

	public long linearSearchTest(ArrayList<Integer> input,int target) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		searcher.linearSearch(intArray, target);
		return (System.nanoTime() - startTime1);
	}

	public long binarySearchTest(ArrayList<Integer> input,int target) {
		int[] intArray=convertIntegers(input);
		Arrays.sort(intArray);
		long startTime1 = System.nanoTime();
		searcher.binarySearch(intArray, target);
		return (System.nanoTime() - startTime1);
	}

	public long arraysToStringTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		String result=Arrays.toString(intArray);
		return (System.nanoTime() - startTime1);
	}

	public double arraysCopyOfTest(ArrayList<Integer> input,int newLength) {
		int[] intArray=convertIntegers(input);
		double startTime1 = System.nanoTime();
		intArray=Arrays.copyOf(intArray, newLength);
		return (System.nanoTime() - startTime1);
	}	

	public long arraysFillTest(ArrayList<Integer> input,int val) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		Arrays.fill(intArray, val);
		return (System.nanoTime() - startTime1);
	}

	public long arraysHashCodeTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		int hashCode=Arrays.hashCode(intArray);
		return (System.nanoTime() - startTime1);
	}

	public long removeDuplicatesTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		int result=algos.removeDuplicates(intArray);
		return (System.nanoTime() - startTime1);
	}

	public long searchInRotateSortedArrayTest(ArrayList<Integer> input,int target) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		int result=algos.searchInRotatedSortedArray(intArray,target);
		return (System.nanoTime() - startTime1);
	}

	public long threeSumTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		ArrayList<ArrayList<Integer>> result=algos.threeSum(intArray);
		return (System.nanoTime() - startTime1);
	}

	public long plusOneTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		int[] result=algos.plusOne(intArray);
		return (System.nanoTime() - startTime1);
	}

	public long candyTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		int result=algos.candy(intArray);
		return (System.nanoTime() - startTime1);
	}

	public long longestConsecutiveSequenceTest(ArrayList<Integer> input) {
		int[] intArray=convertIntegers(input);
		long startTime1 = System.nanoTime();
		int result=algos.longestConsecutive(intArray);
		return (System.nanoTime() - startTime1);
	}

	public static double[] convertDoubles(List<Double> doubles){
		double[] ret = new double[doubles.size()];
		for (int i=0; i < ret.length; i++){
			ret[i] = doubles.get(i).intValue();
		}
		return ret;
	}

	public static int[] convertIntegers(List<Integer> integers){
		int[] ret = new int[integers.size()];
		for (int i=0; i < ret.length; i++){
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}

	public static double factorial(int n,double diminishFactor) {
		double fact = 1*diminishFactor; // this  will be the result
		for (int i = 1; i <= n; i++) {
			fact= (fact*i);
		}
		return fact;
	}

	public static void ansWriter(int cN,int oN,int oN2,int oN3,int oN4,int oN5,int onlogn,int ologn,int err,double time,String outFile){
		try{
			Writer writer = new FileWriter(outFile,true);
			time=time/(double)1000000000;
			System.out.println("cN="+cN+" oN="+oN+" oN2="+oN2+" oN3="+oN3+" oN4="+oN4+" oN5="+oN5+" onlogn="+onlogn+" ologn="+ologn+" err="+err+" exeTime="+time);
			writer.write(      "cN="+cN+" oN="+oN+" oN2="+oN2+" oN3="+oN3+" oN4="+oN4+" oN5="+oN5+" onlogn="+onlogn+" ologn="+ologn+" err="+err+" exeTime="+time+"\n");
			writer.close();
		}catch(Exception e){

		}
	}

	public double nPr(int n,int r){
		return factorial(n,diminishFactor)/factorial(n-r,diminishFactor);
	}
	
	public void writeTime(double t,int sampleSize,String outFile){
		try{
			Writer writer = new FileWriter(outFile,true);
			writer.write(sampleSize+" "+t+"\n");
			writer.close();
		}catch(Exception e){

		}
	}
	
	public double computeSampleSize(int n,int domainSize,int totalSamples){
		double sum=0;
		
		for(int i=0;i<nSize.length;i++)
			sum=sum+nPr(nSize[i],domainSize);
		return ((nPr(n,domainSize)/sum)*totalSamples);
	}
	
	public void runTest(int algoType,String outFile,measureAverageComplexity ST){
		long startTime = System.nanoTime(), totalTime=0;
		for(int k=0; k<repeat; k++){
			cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
			for(int run=0; run<runs; run++){
				ArrayList<Double> Y=new ArrayList<Double>();
				ArrayList<Double> x=new ArrayList<Double>();

				for(int i=0;i<nSize.length;i++){
					double Time=0;
//					sampleSize=Math.max(30,(int)computeSampleSize(nSize[i],domainSize,totalSamples));
//					sampleSize=totalSamples;
					ArrayList<ArrayList<Integer>> inputSet= ST.generateUniformInput(nSize[i],sampleSize);
//					ArrayList<Integer> list=new ArrayList<Integer>();
	//				for(int j=0;j<nSize[i];j++)
		//				list.add(j);
			//		ArrayList<ArrayList<Integer>> inputSet= p.permutation(list);
					
					for(int j=0; j<inputSet.size(); j++){
						double t=0;
						if(algoType==1)
							t=ST.mergeSortTest(inputSet.get(j));
						else if(algoType==2)
							t=ST.quickSortTest(inputSet.get(j));
						else if(algoType==3)
							t=ST.oNFunctionTest(inputSet.get(j));
						else if(algoType==4)
							t=ST.bubbleSortTest(inputSet.get(j));
						else if(algoType==5)
							t=ST.n3Test(inputSet.get(j));
						else if(algoType==6) 
							t=ST.n4Test(inputSet.get(j));
						else if(algoType==7)
							t=ST.linearSearchTest(inputSet.get(j), generator.nextInt());
						else if(algoType==8)
							t=ST.binarySearchTest(inputSet.get(j), generator.nextInt());
						else if(algoType==9)
							t=ST.arraysToStringTest(inputSet.get(j));
						else if(algoType==10)
							t=ST.arraysCopyOfTest(inputSet.get(j),generator.nextInt(inputSet.get(j).size()));
						else if(algoType==11)
							t=ST.arraysFillTest(inputSet.get(j), 1);
						else if(algoType==12)
							t=ST.arraysHashCodeTest(inputSet.get(j));
						else if(algoType==13)
							t=ST.removeDuplicatesTest(inputSet.get(j));
						else if(algoType==14)
							t=ST.searchInRotateSortedArrayTest(inputSet.get(j), generator.nextInt());
						else if(algoType==15)
							t=ST.threeSumTest(inputSet.get(j));
						else if(algoType==16)
							t=ST.plusOneTest(inputSet.get(j));
						else if(algoType==17)
							t=ST.candyTest(inputSet.get(j));
						else if(algoType==18)
							t=ST.longestConsecutiveSequenceTest(inputSet.get(j));
						Time=Time+t;
					}
					x.add((double)nSize[i]);
					Y.add(Time/(double)inputSet.size());
//					System.out.println(" n="+nSize[i]+" Time="+Time/(double)inputSet.size()+" SampleSize="+sampleSize);
//					System.out.println(" n="+nSize[i]+" Time="+Time/(double)inputSet.size()+" SampleSize="+sampleSize);
					//writeTime(Time/(double)inputSet.size(),sampleSize,timeFile);
//					System.out.println(" n="+nSize[i]+" Time="+Time/(double)inputSet.size()+" SampleSize="+sampleSize);
				}

				nonLinearRegression NLR=new nonLinearRegression();
				int ans=NLR.solve(convertDoubles(x),convertDoubles(Y));

				if(ans==0)
					cN++;
				else if(ans==1)
					oN++;
				else if (ans==2)
					oN2++;
				else if(ans==3)
					oN3++;
				else if(ans==4)
					oN4++;
				else if(ans==5)
					oN5++;
				else if(ans==6)
					onlogn++;
				else if(ans==7)
					ologn++;
				else
					err++;
				
				System.out.println("ans="+ans);
			}
			long exeTime=(System.nanoTime() - startTime);
			totalTime=totalTime+exeTime;
			ansWriter(cN,oN,oN2,oN3,oN4,oN5,onlogn,ologn,err,exeTime,outFile);
		}
		ansWriter(0,0,0,0,0,0,0,0,0,totalTime/(double)repeat,outFile);
	}

	public static void main(String args[]){
		measureAverageComplexity ST=new measureAverageComplexity();
		String outFile="";
		/*
		outFile="mergeSortResult.txt";
		System.out.println(outFile);
		ST.runTest(1, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;

		outFile="quickSortResult.txt";
		System.out.println(outFile);
		ST.runTest(2, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;

		outFile="oNFunctionResult.txt";
		System.out.println(outFile);
		ST.runTest(3, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;

		outFile="bubbleSortResult.txt";
		System.out.println(outFile);
		ST.runTest(4, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
*/		 	
		outFile="n3SortResult.txt";
		System.out.println(outFile);
		ST.runTest(5, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
/*
		outFile="n4SortResult.txt";
		System.out.println(outFile);
		ST.runTest(6, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;

		outFile="linearSearchResult.txt";
		System.out.println(outFile);
		ST.runTest(7, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;

		outFile="binarySearchResult.txt";
		System.out.println(outFile);
		ST.runTest(8, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="Arrays.toStringResult.txt";
		System.out.println(outFile);
		ST.runTest(9, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="Arrays.CopyOfResult.txt";
		System.out.println(outFile);
		ST.runTest(10, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="Arrays.fillResult.txt";
		System.out.println(outFile);
		ST.runTest(11, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="Arrays.hashCodeResult.txt";
		System.out.println(outFile);
		ST.runTest(12, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="removeDuplicatesResult.txt";
		System.out.println(outFile);
		ST.runTest(13, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="searchInRotatedSortedArrayResult.txt";
		System.out.println(outFile);
		ST.runTest(14, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="threeSumResult.txt";
		System.out.println(outFile);
		ST.runTest(15, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="plusOneResult.txt";
		System.out.println(outFile);
		ST.runTest(16, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="candyResult.txt";
		System.out.println(outFile);
		ST.runTest(17, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
		
		outFile="longestConsecutiveSequenceResult.txt";
		System.out.println(outFile);
		ST.runTest(18, outFile,ST);
		cN=0; oN=0; oN2=0; oN3=0; oN4=0; oN5=0; onlogn=0; ologn=0; err=0;
	*/
	}
}
