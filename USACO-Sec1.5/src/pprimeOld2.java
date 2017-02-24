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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class pprimeOld2 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("pprime.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			primes = new boolean[b];
			primes[0] = true; //pretend it's a prime :P
			for(int i=0;i<primes.length;++i){
				if(!primes[i]){
					for(int j=2*(i+1);j<=primes.length;j+=(i+1)){
						primes[j-1] = true;
					}
				}
			}
			Queue<Integer> pprimes = new PriorityQueue<Integer>();
			//8 digits
			for(int i=1;i<=9;i+=2){
				int one = i;
				int two = (i*10+i);
				if(isValid(one, a, b)){
					pprimes.add(one);
				}
				if(isValid(two, a, b)){
					pprimes.add(two);
				}
				for(int j=0;j<10;++j){
					int three = (i*100+j*10+i);
					int four = (i*1000+j*100+j*10+i);
					if(isValid(three, a, b)){
						pprimes.add(three);
					}
					if(isValid(four, a, b)){
						pprimes.add(four);
					}
					for(int k=0;k<10;++k){
						int five = (i*10000+j*1000+k*100+j*10+i);
						int six = (i*100000+j*10000+k*1000+k*100+j*10+i);
						if(isValid(five, a, b)){
							pprimes.add(five);
						}
						if(isValid(six, a, b)){
							pprimes.add(six);
						}
						for(int l=0;l<10;++l){
							int seven = (i*1000000+j*100000+k*10000+l*1000+k*100+j*10+i);
							int eight = (i*10000000+j*1000000+k*100000+l*10000+l*1000+k*100+j*10+i);
							if(isValid(seven, a, b)){
								pprimes.add(seven);
							}
							if(isValid(eight, a, b)){
								pprimes.add(eight);
							}
						}
					}
				}
			}
			while(!pprimes.isEmpty()){
				writer.println(pprimes.poll());
			}
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	private static boolean[] primes;
	public static boolean isValid(int num, int a, int b){
		if(num<a||num>b){
			return false;
		}
		return !primes[num-1];
	}
}
