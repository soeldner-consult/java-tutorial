package lambdas;

import java.util.Arrays;

public class ClassMethodReferenceExample {
	public static int compareTrimmed(String s1, String s2) {
		return s1.trim().compareTo(s2.trim());
	}
	
	public static void main(String[] args) {
		String[] strings = { " Fido ", " Bello ", " Lassie" };
		
		Arrays.sort(strings, ClassMethodReferenceExample::compareTrimmed);
		System.out.println(Arrays.toString(strings));
    }
}