import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: awesomelemonade
LANG: JAVA
TASK: skidesign
 */

public class skidesign {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("skidesign.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
			int n = Integer.parseInt(reader.readLine());
			int[] hills = new int[n]; //insertion sort?
			for(int i=0;i<n;++i){
				hills[i] = Integer.parseInt(reader.readLine());
			}
			Arrays.sort(hills);
			int minCost = Integer.MAX_VALUE;
			for(int i=hills[0];i+17<=hills[hills.length-1];++i){
				int cost = cost(hills, i, i+17);
				if(cost<minCost){
					minCost = cost;
				}
			}
			writer.println(minCost);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static int cost(int[] hills, int start, int end){
		int totalCost = 0;
		int index = 0;
		while(hills[index]<start){
			int diff = start-hills[index];
			totalCost+=diff*diff;
			index++;
		}
		index = hills.length-1;
		while(hills[index]>end){
			int diff = hills[index]-end;
			totalCost+=diff*diff;
			index--;
		}
		return totalCost;
	}
}
