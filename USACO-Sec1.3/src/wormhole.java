/*
ID: awesomelemonade
LANG: JAVA
TASK: wormhole
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class wormhole {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("wormhole.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
			Wormhole[] wormholes = new Wormhole[Integer.parseInt(reader.readLine())];
			for(int i=0;i<wormholes.length;++i){
				StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
				wormholes[i] = (new Wormhole(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
			}
			writer.println(solve(wormholes, create(wormholes.length)));
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	static Set<Order> used = new HashSet<Order>();
	public static int solve(Wormhole[] set, int[] partners){
		int count = 0;
		boolean flag = true;
		for(int i=0;i<set.length;++i){
			if(partners[i]==-1){
				for(int j=i;j<set.length;++j){ //int j=i?
					if(partners[j]==-1&&i!=j){
						//pair em up
						int[] temp = copy(partners);
						temp[i] = j;
						temp[j] = i;
						count+=solve(set, temp);
						flag = false;
					}
				}
				break;
			}
		}
		if(flag){
			Order order = new Order(partners);
			if(!used.contains(order)){
				used.add(order);
				for(int i=0;i<set.length;++i){
					if(test(i, set, partners)){
						return 1;
					}
				}
			}
		}
		return count;
	}
	public static boolean test(int x, Wormhole[] set, int[] partners){
		List<Integer> visited = new ArrayList<Integer>();
		int current = x;
		while(!visited.contains(current)){
			visited.add(current);
			int n = getNext(current, set);
			if(n==-1){
				return false;
			}
			current = partners[n];
		}
		return true;
	}
	public static int getNext(int index, Wormhole[] set){
		int lowestIndex = -1;
		int lowestDistance = Integer.MAX_VALUE;
		for(int i=0;i<set.length;++i){
			if(set[index].y==set[i].y){
				if(set[index].x<set[i].x){
					if(set[i].x-set[index].x<lowestDistance){
						lowestDistance = set[i].x-set[index].x;
						lowestIndex = i;
					}
				}
			}
		}
		return lowestIndex;
	}
	public static int[] copy(int[] wormholes){
		int[] x = new int[wormholes.length];
		for(int i=0;i<wormholes.length;++i){
			x[i] = wormholes[i];
		}
		return x;
	}
	public static int[] create(int length){
		int[] x = new int[length];
		for(int i=0;i<x.length;++i){
			x[i] = -1;
		}
		return x;
	}
	static class Wormhole{
		int x;
		int y;
		public Wormhole(Wormhole wormhole){
			this.x = wormhole.x;
			this.y = wormhole.y;
		}
		public Wormhole(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object o){
			Wormhole wormhole = (Wormhole)o;
			return wormhole.x==x&&wormhole.y==y;
		}
		@Override
		public String toString(){
			return "Wormhole: ["+x+", "+y+"]";
		}
	}
	static class Order{
		int[] x;
		public Order(int[] x){
			this.x = x;
		}
		@Override
		public boolean equals(Object o){
			Order order = (Order)o;
			for(int i=0;i<x.length;++i){
				if(order.x[i]!=x[i]){
					return false;
				}
			}
			return true;
		}
		@Override
		public int hashCode(){
			int total = 0;
			for(int i=0;i<x.length;++i){
				total*=37;
				total+=x[i];
			}
			return total;
		}
	}
}
