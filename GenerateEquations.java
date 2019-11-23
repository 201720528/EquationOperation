package test2;

import java.util.Stack;

public class GenerateEquations {

	public  Stack<String> generateEquations() {
		Stack<String> stack = new Stack<String>();	
			int n = (int) (Math.random()*10);
			if(n>5) {
				stack.push("+");
			}else {
				stack.push("-");
			}
			//System.out.println(stack.peek());
		return stack;
	}
}
