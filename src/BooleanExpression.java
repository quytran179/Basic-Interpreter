import java.util.Scanner;

public class BooleanExpression {
	private String statement;

	public BooleanExpression(String statement) {
		super();
		this.statement = statement;

	}

	private int leftOperand;
	private int rightOperand;
	private String relationOperate;

	public boolean compare() {
		boolean result = false;
		Scanner scan = new Scanner(replaceVar(statement));
		// scan.next();// if statement
		leftOperand = Integer.parseInt(scan.next());
		relationOperate = scan.next(); // 
		rightOperand = Integer.parseInt(scan.next());
		if (relationOperate.equals("<=")) {
			if (leftOperand <= rightOperand) {
				result = true;
			}
		} else if (relationOperate.equals(">=")) {
			if (leftOperand >= rightOperand) {
				result = true;
			}
		} else if (relationOperate.equals("=")) {
			if (leftOperand == rightOperand) {
				result = true;
			}
		} else if (relationOperate.equals("<>")) {
			if (leftOperand != rightOperand) {
				result = true;
			}
		} else if (relationOperate.equals("<")) {
			if (leftOperand < rightOperand) {
				result = true;
			}
		} else if (relationOperate.equals(">")) {
			if (leftOperand > rightOperand) {
				result = true;
			}
		} else {
			System.err.println("Realtional Operator Expected");
			System.exit(0);
		}
		scan.close();
		return result;

	}

	public String replaceVar(String line2) {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] characters = abc.toCharArray();
		String lowABC = "abcdefghijklmnopqrstuvwxyz";
		char[] c = lowABC.toCharArray();
		String line = line2.substring(3);
		String result = line;
		for (int i = 0; i < line.length(); i++) {
			if (Character.isLetter(line.charAt(i))
					&& (!LexicalAnalyzer.calledVar.contains(Character.toString(
							line.charAt(i)).toLowerCase()) && !LexicalAnalyzer.calledVar
							.contains(Character.toString(line.charAt(i))
									.toUpperCase()))) {
				System.err.println("Variable: " + line + " not delcared");
				System.exit(0);
			}
		}

		for (int i = 0; i < LexicalAnalyzer.data.length; i++) {
			result = result.replaceAll(Character.toString(characters[i]),
					Integer.toString(LexicalAnalyzer.data[i]));
			result = result.replaceAll(Character.toString(c[i]), Integer
					.toString(LexicalAnalyzer.data[i]));

		}
		return result;
	}
}
