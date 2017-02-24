/*
ID: awesomelemonade
LANG: JAVA
TASK: pprime
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

public class pprimeOld {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("pprime.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			List<Integer> primes = getPrimes(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
			for(int i: primes){
				if(isPalindrome(i)){
					writer.println(i);
				}
			}
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static boolean isPalindrome(int num){
		String n = Integer.toString(num);
		for(int i=0;i<n.length()/2;++i){
			if(n.charAt(i)!=n.charAt(n.length()-i-1)){
				return false;
			}
		}
		return true;
	}
	public static List<Integer> getPrimes(int a, int b){
		boolean[] primes = new boolean[b];
		for(int i=0;i<primes.length;++i){
			primes[i] = true;
		}
		primes[0] = false; //1 is not prime
		for(int i=0;i<b;++i){
			if(primes[i]==true){
				//System.out.println("Found Prime: "+(i+1));
				for(int j=i+i+1;j<b;j+=(i+1)){
					primes[j] = false;
				}
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=a-1;i<primes.length;++i){
			if(primes[i]){
				list.add(i+1);
			}
		}
		return list;
	}
}
