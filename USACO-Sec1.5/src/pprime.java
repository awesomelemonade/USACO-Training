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
import java.util.StringTokenizer;

public class pprime {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("pprime.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			for(int i=1;i<=9;i+=2){
				int one = i;
				if(isValid(one, a, b)){
					writer.println(one);
				}
			}
			for(int i=1;i<=9;i+=2){
				int two = (i*10+i);
				if(isValid(two, a, b)){
					writer.println(two);
				}
			}
			for(int i=1;i<=9;i+=2){
				for(int j=0;j<10;++j){
					int three = (i*100+j*10+i);
					if(isValid(three, a, b)){
						writer.println(three);
					}
				}
			}
			for(int i=1;i<=9;i+=2){
				for(int j=0;j<10;++j){
					int four = (i*1000+j*100+j*10+i);
					if(isValid(four, a, b)){
						writer.println(four);
					}
				}
			}
			for(int i=1;i<=9;i+=2){
				for(int j=0;j<10;++j){
					for(int k=0;k<10;++k){
						int five = (i*10000+j*1000+k*100+j*10+i);
						if(isValid(five, a, b)){
							writer.println(five);
						}
					}
				}
			}
			for(int i=1;i<=9;i+=2){
				for(int j=0;j<10;++j){
					for(int k=0;k<10;++k){
						int six = (i*100000+j*10000+k*1000+k*100+j*10+i);
						if(isValid(six, a, b)){
							writer.println(six);
						}
					}
				}
			}
			for(int i=1;i<=9;i+=2){
				for(int j=0;j<10;++j){
					for(int k=0;k<10;++k){
						for(int l=0;l<10;++l){
							int seven = (i*1000000+j*100000+k*10000+l*1000+k*100+j*10+i);
							if(isValid(seven, a, b)){
								writer.println(seven);
							}
						}
					}
				}
			}
			for(int i=1;i<=9;i+=2){
				for(int j=0;j<10;++j){
					for(int k=0;k<10;++k){
						for(int l=0;l<10;++l){
							int eight = (i*10000000+j*1000000+k*100000+l*10000+l*1000+k*100+j*10+i);
							if(isValid(eight, a, b)){
								writer.println(eight);
							}
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
	public static boolean isValid(int num, int a, int b){
		if(num<a||num>b){
			return false;
		}
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
