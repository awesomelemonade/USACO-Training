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
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class wormhole3 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("wormhole.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
			Wormhole[] wormholes = new Wormhole[Integer.parseInt(reader.readLine())];
			for(int i=0;i<wormholes.length;++i){
				StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
				wormholes[i] = (new Wormhole(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
			}
			writer.println(permutations(wormholes, new Wormhole[wormholes.length], 0));
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static int permutations(Wormhole[] wormholes, Wormhole[] partners, int count){
		System.out.println(count+"/"+wormholes.length);
		if(count+1==wormholes.length){
			System.out.println(Arrays.toString(partners));
			//calculations;
			return 1;
		}
		int x = 0;
		for(int i=count+1;i<wormholes.length;++i){ //possibility to be matched up
			if(!wormholes[i].z){
				Wormhole[] temp = new Wormhole[wormholes.length];
				Wormhole[] temp2 = new Wormhole[partners.length];
				for(int k=0;k<temp.length;++k){
					temp[k] = new Wormhole(wormholes[k]);
				}
				for(int j=0;j<temp2.length;++j){
					temp2[j] = partners[j];
				}
				temp[i].z = true;
				temp[count].z = true;
				//count and i are partners
				temp2[count] = temp[i];
				temp2[i] = temp[i];
				x+=permutations(temp, temp2, count+1);
			}
		}
		return x;
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
