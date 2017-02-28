/*
ID: awesomelemonade
LANG: JAVA
TASK: frac1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class frac1 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("frac1.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
			
			int n = Integer.parseInt(reader.readLine());
			writer.println("0/1");
			List<Integer> denominators = new ArrayList<Integer>();
			for(int i=2;i<=n;++i){
				denominators.add(i);
			}
			int[] numerators = new int[n+1];
			Arrays.fill(numerators, 1);
			while(!denominators.isEmpty()){
				int bestDenominator = 0;
				float lowestValue = 1;
				for(int i: denominators){
					//reduce numerators
					if(numerators[i]!=1){
						while(!isReduced(numerators[i], i)){ //can't use i%numerators[i] because of cases like 4/6
							numerators[i]++;
						}
					}
					float value = ((float)numerators[i])/((float)i);
					if(value<lowestValue){
						lowestValue = value;
						bestDenominator = i;
					}
				}
				writer.println(numerators[bestDenominator]+"/"+bestDenominator);
				numerators[bestDenominator]++;
				if(numerators[bestDenominator]==bestDenominator){
					denominators.remove((Object)bestDenominator);
				}
			}
			writer.println("1/1");
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static boolean isReduced(int numerator, int denominator){

		for(int i=2;i<=numerator;++i){
			if(numerator%i==0&&denominator%i==0){
				return false;
			}
		}
		return true;
	}
}
