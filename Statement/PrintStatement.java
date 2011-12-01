import java.util.Arrays;
import java.util.Scanner;

public class PrintStatement extends Statement {
	private String printStatement;

	public PrintStatement(String stringLine) {
		super(stringLine);
		printStatement = stringLine;
	}

	private Variable variable;

	@Override
	public void execute() {
		Scanner scan = new Scanner(printStatement);
		scan.next(); // Line number
		scan.next(); // Print statement
		String printable = null;
		try {
			printable = scan.next();
		} catch (java.util.NoSuchElementException s) {
			System.err.println("Error at line : [" + printStatement
					+ "] Variable required for printing");
			System.exit(0);
		}

		String[] splitStatement = printStatement.split("\\s+");
		if (splitStatement.length > 3) {
			System.err.println("Invaid Variable at line : " + printStatement);
			System.exit(0);
		}
		if (isVariable(printable)) {
			variable = new Variable(printable); // variable
			System.out.println(variable.getVariable());
			scan.close();
		} else {
			System.err.println("Invaid Variable at line : " + printStatement);
			System.exit(0);
		}

	}

	public boolean isVariable(String s) {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] characters = abc.toCharArray();
		String lowABC = "abcdefghijklmnopqrstuvwxyz";
		char[] lowchar = lowABC.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))
					&& !LexicalAnalyzer.calledVar.contains(Character.toString(
							s.charAt(i)).toLowerCase())
					&& !LexicalAnalyzer.calledVar.contains(Character.toString(
							s.charAt(i)).toUpperCase())) {

				System.err.println("Variable: " + s.charAt(i)
						+ " not delcared at : " + printStatement);
				System.exit(0);
			}
		}
		boolean result = false;
		if (s.length() > 1) {
			result = false;
		} else if ((Arrays.binarySearch(characters, s.charAt(0)) >= 0)) {
			result = true;
		} else if ((Arrays.binarySearch(lowchar, s.charAt(0)) >= 0)) {
			result = true;
		} else {
			result = false;
		}
		return result;

	}

	public String replaceVar(String line) {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] characters = abc.toCharArray();
		String lowABC = "abcdefghijklmnopqrstuvwxyz";
		char[] lowchar = lowABC.toCharArray();
		String result = line;

		if (Character.isLowerCase((line.charAt(0)))) {
			for (int i = 0; i < LexicalAnalyzer.data.length; i++) {
				result = result.replaceAll(Character.toString(characters[i]),
						Integer.toString(LexicalAnalyzer.data[i]));
			}
		} else {
			for (int i = 0; i < LexicalAnalyzer.data.length; i++) {
				result = result.replaceAll(Character.toString(lowchar[i]),
						Integer.toString(LexicalAnalyzer.data[i]));
			}
		}
		return result;
	}

}
