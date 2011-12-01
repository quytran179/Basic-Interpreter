import java.util.Scanner;

public class IfStatement extends Statement {
	private String lineStatement;

	public IfStatement(String stringLine) {
		super(stringLine);
		lineStatement = stringLine;
	}

	private BooleanExpression boolExpress;
	private GOTOStatement gotoState;

	@Override
	public void execute() {
		Scanner scan = new Scanner(lineStatement);
		scan.next();// line number
		scan.next(); // IF Statement
		boolExpress = new BooleanExpression(lineStatement.substring(
				lineStatement.lastIndexOf("IF"), lineStatement
						.lastIndexOf("GOTO"))); // variable
		scan.next(); // = sign
		if (boolExpress.compare()) {
			gotoState = new GOTOStatement(lineStatement.substring(lineStatement
					.lastIndexOf("GOTO"))); // rest of
			gotoState.execute();
		}

		scan.close();
	}
}
