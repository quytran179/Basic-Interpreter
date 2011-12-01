import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LexicalAnalyzer {
	public static int[] data = new int[26];
	private static Statement[] statements = new Statement[1000];
	public static int programCounter;
	static ArrayList<String> calledVar = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("test1.txt");
		Scanner input = new Scanner(fis);
		scanLine(input);

		for (programCounter = 0; programCounter <= 999; programCounter++) {
			if (statements[programCounter] != null) {
				statements[programCounter].execute();
			}
		}
		fis.close();

	}

	public static void scanLine(Scanner input) {

		while (input.hasNext()) {
			String line = input.nextLine();
			translateLine(line);
		}
		input.close();
	}

	/**
	 * @param line
	 *            Line in the document to be translated
	 * @return translated line
	 * @throws Exception
	 */
	public static void translateLine(String line) {
		Scanner scan = new Scanner(line);

		while (scan.hasNext()) {

			String lineNumber = scan.next();

			String statement1 = scan.nextLine();
			String statement = lineNumber + statement1;
			String[] split = statement.split("\\s+");

			for (int i = 0; i < split.length; i++) {
				if (split[i].matches("[0-9]+") || split[i].matches("[a-zA-Z]")
						|| "LET".equals(split[i]) || "GOTO".equals(split[i])
						|| "STOP".equals(split[i]) || "IF".equals(split[i])
						|| "PRINT".equals(split[i]) || "=".equals(split[i])
						|| "<=".equals(split[i]) || ">=".equals(split[i])
						|| "<>".equals(split[i]) || "<".equals(split[i])
						|| ">".equals(split[i]) || "+".equals(split[i])
						|| "-".equals(split[i]) || "*".equals(split[i])
						|| "/".equals(split[i])) {
				} else {
					System.err.println("Syntax erorr:  " + statement);
					System.exit(0);
				}
			}

			if (statements[Integer.parseInt(lineNumber)] != null) {
				System.err.println("Duplicate Line Number Detected: "
						+ statement);
				System.exit(0);
			}
			try {
				if (statementType(statement).compareTo("LET") == 0) {
					statements[Integer.parseInt(lineNumber)] = new LetStatement(
							statement);
				} else if (statementType(statement).compareTo("GOTO") == 0) {
					statements[Integer.parseInt(lineNumber)] = new GOTOStatement(
							statement);
				} else if (statementType(statement).compareTo("PRINT") == 0) {
					statements[Integer.parseInt(lineNumber)] = new PrintStatement(
							statement);
				} else if (statementType(statement).compareTo("STOP") == 0) {
					statements[Integer.parseInt(lineNumber)] = new StopStatement(
							statement);
				} else if (statementType(statement).compareTo("IF") == 0) {
					statements[Integer.parseInt(lineNumber)] = new IfStatement(
							statement);
				} else if (statementType(statement).compareTo("END") == 0) {

				} else {
					System.err.println("Statement Decleration Expected at: "
							+ statement);
					System.exit(0);
				}
			} catch (java.lang.NumberFormatException s) {
				System.err.println("Invalid line number at: " + statement);
				System.exit(0);
			}

		}
		scan.close();

	}

	public static String statementType(String line) {
		String s = "";
		Scanner scan = new Scanner(line);
		scan.next();
		s = scan.next();

		scan.close();
		return s;

	}

}
