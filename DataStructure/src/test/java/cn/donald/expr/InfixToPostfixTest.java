package cn.donald.expr;

import org.junit.Assert;

import org.junit.Test;

import java.util.List;


public class InfixToPostfixTest {

	@Test
	public void test() {
		{
			List<Token> tokens = InfixToPostfix.convert("2+3");
			Assert.assertEquals("[2, 3, +]", tokens.toString());
		}
		{

			List<Token> tokens = InfixToPostfix.convert("2+3*4");
			Assert.assertEquals("[2, 3, 4, *, +]", tokens.toString());
		}

		{

			List<Token> tokens = InfixToPostfix.convert("2-3*4+5");
			Assert.assertEquals("[2, 3, 4, *, -, 5, +]", tokens.toString());
		}

		{
			List<Token> tokens = InfixToPostfix.convert("10-2*3+50");
			Assert.assertEquals("[10, 2, 3, *, -, 50, +]", tokens.toString());
		}

		{
			List<Token> tokens = InfixToPostfix.convert("5-2+8*10");
			Assert.assertEquals("[5, 2, -, 8, 10, *, +]", tokens.toString());
		}

		{
			List<Token> tokens = InfixToPostfix.convert("(2+3)*10");
			Assert.assertEquals("[2, 3, +, 10, *]", tokens.toString());
		}

		{
			List<Token> tokens = InfixToPostfix.convert("1*3+5*(10-1)");
			Assert.assertEquals("[1, 3, *, 5, 10, 1, -, *, +]", tokens.toString());
		}



	}

}
