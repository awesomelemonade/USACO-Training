/*
ID: awesomelemonade
LANG: JAVA
TASK: milk
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class test {
	public static void main(String[] args){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("milk.in"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
			
			reader.close();
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
