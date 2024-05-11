package ejercicio4;

public class Test {
	public static void main(String[] args) throws ExceptionIsEmpty {
		String[] exp = { "((a+b)+(c+d))", "((a+b)+((c+d)))", "(a+b)-(c+d)", "(a+(b+(c+d)))", "(a+b)+((c+d))",
				"((a+b))+c" };
		for (String expresion : exp) {
			System.out.println(expresion + ": " + tieneParentesisExtra(expresion));
		}
	}

	public static boolean tieneParentesisExtra(String exp) throws ExceptionIsEmpty {
		StackLink<Character> stack = new StackLink<>();

		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (c == '(' || c == '+' || c == '-') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.top() == '(') {
					return true;
				}
				while (stack.top() != '(') {
					stack.pop();
				}
				stack.pop();
			}
		}
		return false;
	}
}
