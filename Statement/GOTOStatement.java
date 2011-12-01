import java.util.Scanner;

public class GOTOStatement extends Statement {
	private String lineNumber;

	public GOTOStatement(String stringLine) {
		super(stringLine);
		lineNumber = stringLine;
	}

	@Override
	public void execute() {
		Scanner scan = new Scanner(lineNumber);
		String statementLine = null;
		String newLine;
		try {
			scan.next(); // line number
			statementLine = lineNumber
					.substring(lineNumber.lastIndexOf("GOTO"));
			newLine = statementLine.substring(5);// grabs the linenumber after
													// the GOTO
			LexicalAnalyzer.programCounter = Integer.parseInt(newLine) - 1; // variable
			scan.close();
		} catch (NumberFormatException s) {
			System.err.println("Invalid line number at: " + lineNumber);
			System.exit(0);
		}
	}

}
