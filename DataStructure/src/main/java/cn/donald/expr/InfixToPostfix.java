package cn.donald.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {

	public static List<Token> convert(String expr) {

		List<Token> inFixTokens = new TokenParser().parse(expr);
		
		List<Token> postFixTokens = new ArrayList<Token>();
		
		Stack<Token> opStack = new Stack<Token>();

		for(Token token : inFixTokens){
			
			if(token.isOperator()){

				/**
				 * 1. 操作符栈不为空
				 * 2. 操作符栈顶元素优先级高于当前
				 * 3. 操作符栈顶元素不为 (
				 */
				while(!opStack.isEmpty() 
						&& !token.hasHigherPriority(opStack.peek())
						&& !"(".equals(opStack.peek().toString())){

					postFixTokens.add(opStack.pop());
				}

				if (")".equals(token.toString())) {

					while (!opStack.isEmpty()) {

						if ("(".equals(opStack.peek().toString())) {
							opStack.pop();
							break;
						}

						postFixTokens.add(opStack.pop());
					}

				} else {
					opStack.push(token);
				}

				
			}

			if(token.isNumber()){
				
				postFixTokens.add(token);
				
			}
		}
		
		while(!opStack.isEmpty()){
			postFixTokens.add(opStack.pop());	
		}
		
		return postFixTokens;
	}

	

}
