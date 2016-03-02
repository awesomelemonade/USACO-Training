/*
ID: awesomelemonade
LANG: JAVA
TASK: milk3
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
import java.util.TreeSet;

public class milk3 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("milk3.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
			Set<Buckets> buckets = new HashSet<Buckets>();
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			int aSize = Integer.parseInt(tokenizer.nextToken());
			int bSize = Integer.parseInt(tokenizer.nextToken());
			int cSize = Integer.parseInt(tokenizer.nextToken());
			buckets.add(new Buckets(0, 0, cSize));
			buckets = resolve(buckets, new int[]{aSize, bSize, cSize});
			TreeSet<Integer> set = new TreeSet<Integer>();
			for(Buckets b: buckets){
				if(b.buckets[0]==0){
					set.add(b.buckets[2]);
				}
			}
			boolean first = true;
			for(int i: set){
				if(first){
					writer.print(i);
					first = false;
				}else{
					writer.print(" "+i);
				}
			}
			writer.println();
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static Set<Buckets> resolve(Set<Buckets> set, int[] sizes){
		Set<Buckets> newSet = new HashSet<Buckets>(set);
		for(Buckets buckets: set){
			for(int i=0;i<3;++i){
				for(int j=0;j<3;++j){
					if(i!=j){
						newSet.add(move(buckets, i, j, sizes));
					}
				}
			}
		}
		if(newSet.size()!=set.size()){
			newSet = resolve(newSet, sizes);
		}
		return newSet;
	}
	public static Buckets move(Buckets buckets, int from, int to, int[] sizes){
		Buckets newBuckets = new Buckets(buckets);
		newBuckets.buckets[to] = Math.min(buckets.buckets[to]+buckets.buckets[from], sizes[to]);
		newBuckets.buckets[from] = buckets.buckets[from]-(newBuckets.buckets[to]-buckets.buckets[to]);
		return newBuckets;
	}
	static class Buckets{
		int[] buckets = new int[3];
		public Buckets(Buckets buckets){
			this.buckets[0] = buckets.buckets[0];
			this.buckets[1] = buckets.buckets[1];
			this.buckets[2] = buckets.buckets[2];
		}
		public Buckets(int a, int b, int c){
			buckets[0] = a;
			buckets[1] = b;
			buckets[2] = c;
		}
		@Override
		public boolean equals(Object object){
			Buckets buckets = (Buckets)object;
			return (buckets.buckets[0]==this.buckets[0]&&buckets.buckets[1]==this.buckets[1]&&buckets.buckets[2]==this.buckets[2]);
		}
		@Override
		public int hashCode(){
			return buckets[0]*900+buckets[1]*30+buckets[2];
		}
	}
}
