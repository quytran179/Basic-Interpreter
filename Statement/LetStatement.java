import java.util.Scanner;

public class LetStatement extends Statement {
	private String statement;

	public LetStatement(String stringLine) {
		super(stringLine);
		// TODO Auto-generated constructor stub
		this.statement = stringLine;
	}

	private Variable variable;

	private ArithmetricExpression arithExpress;

	@Override
	public void execute() {
		Scanner scan = new Scanner(statement);
		// line number
		scan.next();
		// LET
		scan.next();
		// Variable
		String var = scan.next();
		// assign variable
		variable = new Variable(var);
		// Equal sign
		if (!scan.next().equals("=")) {
			System.err.println("ERROR: Equal Sign expected at: " + statement);
			System.exit(0);
		}
		// Give the class ArithmetricExpresssion everything after the equal sign
		arithExpress = new ArithmetricExpression(statement.substring(statement
				.lastIndexOf("=") + 1));
		// Gives the variable the data for it to set in memory
		variable.setVariable(arithExpress.getExpression());
		// Stores the called variables to make sure they are being used before I
		LexicalAnalyzer.calledVar.add(var);
		scan.close();

	}

}
