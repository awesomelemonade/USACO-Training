import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/*
ID: awesomelemonade
LANG: JAVA
TASK: sprime
 */

public class sprime {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("sprime.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
			int n = Integer.parseInt(reader.readLine());
			Queue<Integer> primes = new ArrayDeque<Integer>();
			primes.add(2);
			primes.add(3);
			primes.add(5);
			primes.add(7);
			while(!primes.isEmpty()){
				int x = primes.poll();
				if(Integer.toString(x).length()==n){
					writer.println(x);
				}else{
					for(int i=1;i<=9;i+=2){
						int num = x*10+i;
						if(isPrime(num)){
							primes.add(num);
						}
					}
				}
			}
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static boolean isPrime(int num){
		if(num%2==0){
			return false;
		}
		for(int i=3;i*i<=num;i+=2){
			if(num%i==0){
				return false;
			}
		}
		return true;
	}
}
