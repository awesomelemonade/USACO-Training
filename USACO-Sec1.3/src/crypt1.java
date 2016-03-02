/*
ID: awesomelemonade
LANG: JAVA
TASK: crypt1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class crypt1 {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("crypt1.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
			int n = Integer.parseInt(reader.readLine());
			List<Character> digits = new ArrayList<Character>();
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			for(int i=0;i<n;++i){
				digits.add(tokenizer.nextToken().charAt(0));
			}
			Collections.sort(digits);
			int count = 0;
			StringBuilder builder = new StringBuilder("xxx");
			StringBuilder builder2 = new StringBuilder("xx");
			for(char c: digits){
				if(c=='0'){
					continue;
				}
				builder.setCharAt(0, c);
				for(char d: digits){
					builder.setCharAt(1, d);
					for(char e: digits){
						builder.setCharAt(2, e);
						for(char x: digits){
							if(x=='0'){
								continue;
							}
							int num = Integer.parseInt(builder.toString());
							String premultiply = Integer.toString(num*Character.getNumericValue(x));
							if(premultiply.length()>3){
								break;
							}
							if(!check(premultiply, digits)){
								continue;
							}
							builder2.setCharAt(0, x);
							for(char y: digits){
								builder2.setCharAt(1, y);
								String premultiply2 = Integer.toString(num*Character.getNumericValue(y));
								if(premultiply2.length()>3){
									break;
								}
								if(!check(premultiply2, digits)){
									continue;
								}
								String multiply = Integer.toString(num*Integer.parseInt(builder2.toString()));
								if(check(multiply, digits)){
									System.out.println(num+" - "+builder2.toString());
									count++;
								}
							}
						}
					}
				}
			}
			writer.println(count);
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static boolean check(String num, List<Character> digits){
		for(char c: num.toCharArray()){
			if(!digits.contains(c)){
				return false;
			}
		}
		return true;
	}
}
