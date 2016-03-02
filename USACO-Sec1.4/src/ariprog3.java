/*
ID: awesomelemonade
LANG: JAVA
TASK: ariprog
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class ariprog3 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("ariprog.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
			int n = Integer.parseInt(reader.readLine());
			int m = Integer.parseInt(reader.readLine());
			int squared = 2*m*m;
			Set<Integer> nums = new HashSet<Integer>();
			for(int p=0;p<=m;p++){
				for(int q=p;q<=m;q++){
					nums.add(p*p+q*q);
				}
			}
			boolean flag = false;
			for(int b=1;b<=squared/(n-1);b++){
				for(int a=0;a<=squared-b*(n-1);a++){
					if(test(a, b, n, nums)){
						writer.println(a+" "+b);
						flag = true;
					}
				}
			}
			if(!flag){
				writer.println("NONE");
			}
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static boolean test(int a, int b, int n, Set<Integer> nums){
		for(int i=0;i<n;++i){
			if(!nums.contains(a+i*b)){
				return false;
			}
		}
		return true;
	}
}
