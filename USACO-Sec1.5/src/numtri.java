/*
ID: awesomelemonade
LANG: JAVA
TASK: numtri
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class numtri {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("numtri.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
			int[] nums = new int[1];
			int r = Integer.parseInt(reader.readLine());
			nums[0] = Integer.parseInt(reader.readLine());
			for(int i=1;i<r;++i){
				int[] current = new int[nums.length+1];
				StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
				current[0] = nums[0]+Integer.parseInt(tokenizer.nextToken());
				for(int j=1;j<current.length-1;++j){
					current[j] = Math.max(nums[j-1], nums[j])+Integer.parseInt(tokenizer.nextToken());
				}
				current[current.length-1] = nums[current.length-2]+Integer.parseInt(tokenizer.nextToken());
				nums = current;
			}
			int max = 0;
			for(int i=0;i<nums.length;++i){
				if(nums[i]>max){
					max = nums[i];
				}
			}
			writer.println(max);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
