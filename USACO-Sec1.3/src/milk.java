/*
ID: awesomelemonade
LANG: JAVA
TASK: milk
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("milk.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
			StringTokenizer info = new StringTokenizer(reader.readLine());
			int amount = Integer.parseInt(info.nextToken());
			Farmer[] farmers = new Farmer[Integer.parseInt(info.nextToken())];
			for(int i=0;i<farmers.length;++i){
				StringTokenizer farmer = new StringTokenizer(reader.readLine());
				farmers[i] = new Farmer(Integer.parseInt(farmer.nextToken()), Integer.parseInt(farmer.nextToken()));
			}
			Arrays.sort(farmers);
			int cost = 0;
			for(Farmer farmer: farmers){
				if(farmer.amount<amount){
					amount-=farmer.amount;
					cost+=farmer.amount*farmer.rate;
				}else{
					cost+=amount*farmer.rate;
					break;
				}
			}
			writer.println(cost);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	static class Farmer implements Comparable<Farmer> {
		int amount;
		int rate;
		public Farmer(int rate, int amount){
			this.amount = amount;
			this.rate = rate;
		}
		@Override
		public int compareTo(Farmer farmer) {
			return rate-farmer.rate;
		}
		public String toString(){
			return rate+" "+amount;
		}
	}
}
