/*
ID: awesomelemonade
LANG: JAVA
TASK: sort3
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class sort3 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("sort3.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
			int n = Integer.parseInt(reader.readLine());
			int[] set = new int[n];
			int[] counts = new int[3];
			for(int i=0;i<set.length;++i){
				set[i] = Integer.parseInt(reader.readLine());
				counts[set[i]-1]++;
			}
			int swaps = 0;
			int twoIndex = counts[0];
			int threeIndex = counts[0]+counts[1];
			for(int i=0;i<counts[0];++i){
				if(set[i]==2){
					int index = findSwap(set, twoIndex, counts[0]+counts[1], 1);
					if(index==-1){
						index = findSwap(set, threeIndex, set.length, 1);
					}
					swap(set, i, index);
					swaps++;
				}
				if(set[i]==3){
					int index = findSwap(set, threeIndex, set.length, 1);
					if(index==-1){
						index = findSwap(set, twoIndex, counts[0]+counts[1], 1);
					}
					swap(set, i, index);
					swaps++;
				}
			}
			for(int i=0;i<counts[0]+counts[1];++i){
				if(set[i]==3){
					int index = findSwap(set, threeIndex, set.length, 2);
					swap(set, i, index);
					swaps++;
				}
			}
			writer.println(swaps);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static int findSwap(int[] set, int start, int end, int target){
		for(int i=start;i<end;++i){
			if(set[i]==target){
				return i;
			}
		}
		return -1;
	}
	public static void swap(int[] set, int i, int j){
		int temp = set[i];
		set[i] = set[j];
		set[j] = temp;
	}
}
