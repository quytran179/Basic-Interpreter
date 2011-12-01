
public class StopStatement extends Statement {

	private String printStatement;

	public StopStatement(String stringLine) {
		super(stringLine);
		// TODO Auto-generated constructor stub
		printStatement = stringLine;
	}

	@Override
	public void execute() {
		System.exit(0);

	}

}
