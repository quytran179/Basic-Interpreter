import java.util.Arrays;

public class Variable {
	private String stringOperand;

	public Variable(String operand) {
		stringOperand = operand;

	}

	public void setVariable(int i) {
		LexicalAnalyzer.data[searchCharArray(stringOperand)] = i;

	}

	public int getVariable() {
		int result = 0;
		result = LexicalAnalyzer.data[searchCharArray(stringOperand)];

		return result;
	}

	public int searchCharArray(String b) {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] a = abc.toCharArray();
		String lowABC = "abcdefghijklmnopqrstuvwxyz";
		char[] c = lowABC.toCharArray();
		int result = 0;
		if (b.length() == 1 && (Arrays.binarySearch(a, b.charAt(0)) >= 0)) {
			result = Arrays.binarySearch(a, b.charAt(0));
		} else if (b.length() == 1
				&& (Arrays.binarySearch(c, b.charAt(0)) >= 0)) {
			result = Arrays.binarySearch(c, b.charAt(0));
		} else {
			System.err.println("ERROR: " + stringOperand
					+ " Is Not A Valid Variable");
			System.exit(0);
		}

		return result;
	}

}
