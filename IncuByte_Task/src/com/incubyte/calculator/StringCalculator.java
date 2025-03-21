package com.incubyte.calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class StringCalculator {

	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Give input here : ");
		String input = sc.nextLine();
		StringCalculator stringCalc = new StringCalculator();
		try {
			stringCalc.add(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int add(String stringOfNumbers) throws Exception {
		int sum = 0;
		String delimeter = ",";
		if(stringOfNumbers.equalsIgnoreCase("")) {
			System.out.println("0");
			return 0;
		}
		
		if(stringOfNumbers.startsWith("//")) {
			String stringOfDelim = stringOfNumbers.split("\\\\n")[0].replace("//", "");  // To seperate delimeter pattern
			stringOfNumbers = stringOfNumbers.substring(stringOfDelim.length() + 4); // To seperate string from the delimeter part

			// If delimeter is multiple or more than single alphabet
			if(stringOfDelim.contains("[")) {
				stringOfDelim = stringOfDelim.replace("][", "|").replace("[", "").replace("]", ""); // Removing all brackets and adding another delimeter (|) to split between more than two input delimeters
				String[] stringArrayOfDelim = stringOfDelim.split("\\|"); // Converting into string array to perform loop operation for each delimeter
				for (String string : stringArrayOfDelim) {
					stringOfNumbers = stringOfNumbers.replace(string, ","); // Replacing the given delimeter by "," to make it easy to loop and sum.
				}
			}
			else {
				delimeter = stringOfDelim;				// If no array present then single delimeter is there hence assigning it directly
				stringOfNumbers = stringOfNumbers.replace(delimeter, ","); // Replacing input delimeter by  "," as in if condition I'm converting delimeter to ","
			}
		}
		
		delimeter = ",";
		
		stringOfNumbers = stringOfNumbers.replace("\\n", delimeter); // Removing all new line code
		String[] arrayOfNumbers = stringOfNumbers.split(delimeter); // String array of pure integers
		
		System.out.println();
		
		int eachNum = 0;
		String negativeNumbers = "";
		for (String eachNumberInString : arrayOfNumbers) {
			eachNum = Integer.parseInt(eachNumberInString);
			if(eachNum<0) {
				negativeNumbers+= " " + (eachNum);
			}
			else {
				sum += (eachNum >1000 ? 0 : eachNum);
			} 
		}
		if(negativeNumbers.length() >0) {
			throw new Exception("negatives not allowed" + negativeNumbers);
		}
		System.out.println(sum);
		return sum;
	}
	
}
