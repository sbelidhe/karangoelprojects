import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArithmeticLexer {
  public static enum TokenType {
    // Token types cannot have underscores
	  CHARACTER("-?[a-zA-Z]+"), ASSIGNMENT("[=]") , NUMBER("-?[0-9]+"), BINARYOP("[*|/|+|-]"), WHITESPACE("[ \t\f\r\n]+");

    public final String pattern;

    private TokenType(String pattern) {
      this.pattern = pattern;
    }
  }

  public static class Token {
    public TokenType type;
    public String data;

    public Token(TokenType type, String data) {
      this.type = type;
      this.data = data;
    }

    @Override
    public String toString() {
      return String.format("(%s %s)", type.name(), data);
    }
  }

  public static ArrayList<Token> lex(String input) {
    // The tokens to return
    ArrayList<Token> tokens = new ArrayList<Token>();

    // Lexer logic begins here
    StringBuffer tokenPatternsBuffer = new StringBuffer();
    for (TokenType tokenType : TokenType.values())
      tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

    // Begin matching tokens
    Matcher matcher = tokenPatterns.matcher(input);
    while (matcher.find()) {
    	if (matcher.group(TokenType.CHARACTER.name()) != null) {
            tokens.add(new Token(TokenType.CHARACTER, matcher.group(TokenType.CHARACTER.name())));
            continue;
      } else if (matcher.group(TokenType.ASSIGNMENT.name()) != null) {
          tokens.add(new Token(TokenType.ASSIGNMENT, matcher.group(TokenType.ASSIGNMENT.name())));
          continue;
      } else if (matcher.group(TokenType.NUMBER.name()) != null) {
        tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
        continue;
      } else if (matcher.group(TokenType.BINARYOP.name()) != null) {
        tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name())));
        continue;
      } else if (matcher.group(TokenType.WHITESPACE.name()) != null)
        continue;
    }

    return tokens;
  }

  public static void main(String[] args) {
    String input = "A + B - C = D";

    // Create tokens and print them
    ArrayList<Token> tokens = lex(input);
    for (Token token : tokens)
      System.out.println(token);
  }
}
