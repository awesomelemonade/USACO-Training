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
import java.util.List;
import java.util.StringTokenizer;

public class wormhole4 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("wormhole.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
			int n = Integer.parseInt(reader.readLine());
			List<Wormhole> wormholes = new ArrayList<Wormhole>();
			for(int i=0;i<n;++i){
				StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
				wormholes.add(new Wormhole(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
			}
			writer.println(test(wormholes, new ArrayList<Wormhole>())/8);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static int test(List<Wormhole> unordered, List<Wormhole> ordered){
		if(unordered.size()==ordered.size()){
			for(Wormhole wormhole: ordered){
				if(solve(wormhole, ordered, new ArrayList<Wormhole>())){
					return 1;
				}
			}
			return 0;
		}
		int count = 0;
		for(Wormhole wormhole: unordered){
			if(ordered.contains(wormhole)){
				continue;
			}
			List<Wormhole> temp = new ArrayList<Wormhole>();
			for(Wormhole w: ordered){
				temp.add(w);
			}
			temp.add(wormhole);
			count+=test(unordered, temp);
		}
		return count;
	}
	public static boolean solve(Wormhole wormhole, List<Wormhole> wormholes, List<Wormhole> visited){ //pruning? cache?
		visited.add(wormhole);
		for(int i=0;i<wormholes.size();++i){
			if(wormholes.get(i).x>wormhole.x&&wormholes.get(i).y==wormhole.y){
				if(visited.contains(wormholes.get(i))){
					return true;
				}
				List<Wormhole> temp = new ArrayList<Wormhole>(visited);
				temp.add(wormholes.get(i));
				if(i%2==0){
					if(solve(wormholes.get(i+1), wormholes, temp)){
						return true;
					}
				}else{
					if(solve(wormholes.get(i-1), wormholes, temp)){
						return true;
					}
				}
			}
		}
		return false;
	}
	static class Wormhole{
		int x;
		int y;
		boolean z;
		public Wormhole(Wormhole wormhole){
			this.x = wormhole.x;
			this.y = wormhole.y;
			this.z = wormhole.z;
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
}
