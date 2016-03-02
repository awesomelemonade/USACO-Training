/*
ID: awesomelemonade
LANG: JAVA
TASK: barn1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class barn1 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("barn1.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			int boards = Integer.parseInt(tokenizer.nextToken());
			int stalls = Integer.parseInt(tokenizer.nextToken());
			int[] cows = new int[Integer.parseInt(tokenizer.nextToken())];
			int lowest = stalls;
			int highest = 0;
			for(int i=0;i<cows.length;++i){
				cows[i] = Integer.parseInt(reader.readLine());
				if(cows[i]<lowest){
					lowest = cows[i];
				}
				if(cows[i]>highest){
					highest = cows[i];
				}
			}
			Arrays.sort(cows);
			List<Integer> gaps = new ArrayList<Integer>();
			for(int i=1;i<cows.length;++i){
				if(cows[i]-cows[i-1]!=1){
					gaps.add(cows[i]-cows[i-1]-1);
				}
			}
			Collections.sort(gaps);
			while(gaps.size()+1>boards){
				gaps.remove(0);
			}
			int total = 0;
			for(int i: gaps){
				total+=i;
			}
			writer.println((highest-lowest+1)-total);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
