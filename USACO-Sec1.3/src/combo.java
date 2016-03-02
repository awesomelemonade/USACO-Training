/*
ID: awesomelemonade
LANG: JAVA
TASK: combo
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class combo {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("combo.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
			int n = Integer.parseInt(reader.readLine());
			Combo combo = toCombo(reader.readLine());
			Combo master = toCombo(reader.readLine());
			Set<Combo> combos = new HashSet<Combo>();
			addCombos(combos, combo, 2, n);
			addCombos(combos, master, 2, n);
			writer.println(combos.size());
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static void addCombos(Set<Combo> combos, Combo combo, int error, int n){
		for(int i=combo.x-error;i<=combo.x+error;++i){
			for(int j=combo.y-error;j<=combo.y+error;++j){
				for(int k=combo.z-error;k<=combo.z+error;++k){
					combos.add(new Combo(normalize(i, n), normalize(j, n), normalize(k, n)));
				}
			}
		}
	}
	public static int normalize(int value, int n){
		value+=1;
		if(value<=0){
			value+=n;
		}
		return value%n;
	}
	public static Combo toCombo(String combo){
		StringTokenizer tokenizer = new StringTokenizer(combo);
		return new Combo(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
	}
	static class Combo{
		int x;
		int y;
		int z;
		public Combo(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
		}
		@Override
		public boolean equals(Object o){
			Combo combo = (Combo)o;
			return (combo.x==x&&combo.y==y&&combo.z==z);
		}
		@Override
		public int hashCode(){
			return x*31*31+y*31+z;
		}
	}
}
