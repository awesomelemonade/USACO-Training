/*
ID: awesomelemonade
LANG: JAVA
TASK: castle
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
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class castle {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("castle.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
			StringTokenizer info = new StringTokenizer(reader.readLine());
			int m = Integer.parseInt(info.nextToken());
			int n = Integer.parseInt(info.nextToken());
			int[][] modules = new int[m][n];
			for(int i=0;i<n;++i){
				StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
				for(int j=0;j<m;++j){
					modules[j][i] = Integer.parseInt(tokenizer.nextToken());
				}
			}
			int maxRoomSize = 0;
			int[][] flood = new int[m][n];
			int id = 1;
			List<Integer> roomSizes = new ArrayList<Integer>();
			for(int i=0;i<m;++i){
				for(int j=0;j<n;++j){
					if(flood[i][j]==0){
						int roomSize = floodFill(flood, i, j, id, modules);
						roomSizes.add(roomSize);
						maxRoomSize = Math.max(maxRoomSize, roomSize);
						id++;
					}
				}
			}
			writer.println(id-1);
			writer.println(maxRoomSize);
			writer.println(nameWall(flood, roomSizes));
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static String nameWall(int[][] flood, List<Integer> roomSizes){
		String name = "Not Found :(";
		int maxSize = 0;
		for(int i=0;i<flood.length;++i){
			for(int j=flood[0].length-1;j>=0;--j){
				if(j>0&&flood[i][j]!=flood[i][j-1]){
					int size = roomSizes.get(flood[i][j]-1)+roomSizes.get(flood[i][j-1]-1);
					if(size>maxSize){
						name = (j+1)+" "+(i+1)+" N";
						maxSize = size;
					}
				}
				if(i<flood.length-1&&flood[i+1][j]!=flood[i][j]){
					int size = roomSizes.get(flood[i][j]-1)+roomSizes.get(flood[i+1][j]-1);
					if(size>maxSize){
						name = (j+1)+" "+(i+1)+" E";
						maxSize = size;
					}
				}
			}
		}
		return maxSize+"\n"+name;
	}
	public static int floodFill(int[][] flood, int i, int j, int id, int[][] modules){
		int total = 1;
		flood[i][j] = id;
		if((modules[i][j]&1)==0&&flood[i-1][j]==0){
			total+=floodFill(flood, i-1, j, id, modules);
		}
		if((modules[i][j]&2)==0&&flood[i][j-1]==0){
			total+=floodFill(flood, i, j-1, id, modules);
		}
		if((modules[i][j]&4)==0&&flood[i+1][j]==0){
			total+=floodFill(flood, i+1, j, id, modules);
		}
		if((modules[i][j]&8)==0&&flood[i][j+1]==0){
			total+=floodFill(flood, i, j+1, id, modules);
		}
		return total;
	}
	public static void addAdjacency(Map<Integer, Set<Integer>> adjacencies, int a, int b){
		if(a==b){
			return;
		}
		if(b>a){
			addAdjacency(adjacencies, b, a);
		}
		if(!adjacencies.containsKey(a)){
			adjacencies.put(a, new HashSet<Integer>());
		}
		adjacencies.get(a).add(b);
	}
}
