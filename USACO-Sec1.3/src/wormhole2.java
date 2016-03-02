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

public class wormhole2 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("wormhole.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
			int num = Integer.parseInt(reader.readLine());
			List<Wormhole> wormholes = new ArrayList<Wormhole>();
			for(int i=0;i<num;++i){
				StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
				wormholes.add(new Wormhole(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
			}
			System.out.println(permutations(new ArrayList<Wormhole>(), wormholes));
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	Set<List<Wormhole>> set = new HashSet<List<Wormhole>>();
	public static int permutations(List<Wormhole> list, List<Wormhole> remaining){
		if(remaining.isEmpty()){
			System.out.println(list);
			for(Wormhole wormhole: list){
				if(solve(wormhole, list, new ArrayList<Wormhole>())){
					return 1;
				}
			}
			return 0;
		}
		int count = 0;
		for(int i=0;i<remaining.size();++i){
			for(int j=i+1;j<remaining.size();++j){
				List<Wormhole> temp = new ArrayList<Wormhole>(list);
				List<Wormhole> temp2 = new ArrayList<Wormhole>(remaining);
				temp.add(remaining.get(i));
				temp.add(remaining.get(j));
				temp2.remove(remaining.get(i));
				temp2.remove(remaining.get(j));
				count+=permutations(temp, temp2);
			}
		}
		return count;
	}
	public static boolean solve(Wormhole wormhole, List<Wormhole> wormholes, List<Wormhole> visited){ //pruning? cache?
		visited.add(wormhole);
		for(int i=0;i<wormholes.size();++i){
			if(wormholes.get(i).x>wormhole.x){
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
