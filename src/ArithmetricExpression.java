import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ArithmetricExpression {
	private String statement;

	public ArithmetricExpression(String statement) {
		super();
		this.statement = statement;
	}

	public int getExpression() {
		CharSequence sQ = "/0";
		CharSequence sQ2 = "/ 0";
		int o = 0;
		try {
			if (statement.contains(sQ) || statement.contains(sQ2)) {
				System.err.println("Arithmetric Error: Can't Divide By Zero: "
						+ statement);
				System.exit(0);
			} else {
				String result = replaceVar(statement);
				// Java let you import a script manager. So I gave the script
				// manager the arithmetric expression because
				// javascript allows you to have operators in java
				// it also allows you to have order of operations
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("JavaScript");
				Object i = engine.eval(result);
				double u = Double.parseDouble(i.toString());
				o = (int) u;
			}
		} catch (ScriptException s) {
			System.err.println("ERROR: Arithmetric Error encountered :  "
					+ statement);
			System.exit(0);
		}
		return o;

	}

	public String replaceVar(String line) {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] characters = abc.toCharArray();
		String lowABC = "abcdefghijklmnopqrstuvwxyz";
		char[] c = lowABC.toCharArray();
		String result = line;
		for (int i = 0; i < line.length(); i++) {
			if (Character.isLetter(line.charAt(i))
					&& (!LexicalAnalyzer.calledVar.contains(Character.toString(
							line.charAt(i)).toLowerCase()) && !LexicalAnalyzer.calledVar
							.contains(Character.toString(line.charAt(i))
									.toUpperCase()))) {
				System.err.println("Variable: " + line.charAt(i)
						+ " not delcared");
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
