/* Generated By:JavaCC: Do not edit this line. ParserConstants.java */
package parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 8;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 9;
  /** RegularExpression Id. */
  int BOOLEAN = 11;
  /** RegularExpression Id. */
  int INT = 12;
  /** RegularExpression Id. */
  int FLOAT = 13;
  /** RegularExpression Id. */
  int STRING = 14;
  /** RegularExpression Id. */
  int IF = 15;
  /** RegularExpression Id. */
  int THEN = 16;
  /** RegularExpression Id. */
  int ELSE = 17;
  /** RegularExpression Id. */
  int ELSE_IF = 18;
  /** RegularExpression Id. */
  int FOR = 19;
  /** RegularExpression Id. */
  int WHILE = 20;
  /** RegularExpression Id. */
  int BEGIN = 21;
  /** RegularExpression Id. */
  int END = 22;
  /** RegularExpression Id. */
  int READ = 23;
  /** RegularExpression Id. */
  int WRITE = 24;
  /** RegularExpression Id. */
  int RETURN = 25;
  /** RegularExpression Id. */
  int INTEGER_LITERAL = 26;
  /** RegularExpression Id. */
  int DECIMAL_LITERAL = 27;
  /** RegularExpression Id. */
  int FLOATING_POINT_LITERAL = 28;
  /** RegularExpression Id. */
  int EXPONENT = 29;
  /** RegularExpression Id. */
  int STRING_LITERAL = 30;
  /** RegularExpression Id. */
  int BOOLEAN_LITERAL = 31;
  /** RegularExpression Id. */
  int IDENTIFIER = 32;
  /** RegularExpression Id. */
  int LETTER = 33;
  /** RegularExpression Id. */
  int PART_LETTER = 34;
  /** RegularExpression Id. */
  int LPAREN = 35;
  /** RegularExpression Id. */
  int RPAREN = 36;
  /** RegularExpression Id. */
  int SEMICOLON = 37;
  /** RegularExpression Id. */
  int COMMA = 38;
  /** RegularExpression Id. */
  int ASSIGN = 39;
  /** RegularExpression Id. */
  int GT = 40;
  /** RegularExpression Id. */
  int LT = 41;
  /** RegularExpression Id. */
  int BANG = 42;
  /** RegularExpression Id. */
  int QUESTION = 43;
  /** RegularExpression Id. */
  int COLON = 44;
  /** RegularExpression Id. */
  int EQ = 45;
  /** RegularExpression Id. */
  int LE = 46;
  /** RegularExpression Id. */
  int GE = 47;
  /** RegularExpression Id. */
  int NE = 48;
  /** RegularExpression Id. */
  int OR = 49;
  /** RegularExpression Id. */
  int AND = 50;
  /** RegularExpression Id. */
  int PLUS = 51;
  /** RegularExpression Id. */
  int MINUS = 52;
  /** RegularExpression Id. */
  int INCREMENT = 53;
  /** RegularExpression Id. */
  int DECREMENT = 54;
  /** RegularExpression Id. */
  int STAR = 55;
  /** RegularExpression Id. */
  int SLASH = 56;
  /** RegularExpression Id. */
  int REM = 57;
  /** RegularExpression Id. */
  int DOT = 58;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"//\"",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "<token of kind 10>",
    "\"boolean\"",
    "\"int\"",
    "\"float\"",
    "\"String\"",
    "\"if\"",
    "\"then\"",
    "\"else\"",
    "\"else if\"",
    "\"for\"",
    "\"while\"",
    "\"begin\"",
    "\"end\"",
    "\"read\"",
    "\"write\"",
    "\"return\"",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL>",
    "<BOOLEAN_LITERAL>",
    "<IDENTIFIER>",
    "<LETTER>",
    "<PART_LETTER>",
    "\"(\"",
    "\")\"",
    "\";\"",
    "\",\"",
    "\"=\"",
    "\">\"",
    "\"<\"",
    "\"!\"",
    "\"?\"",
    "\":\"",
    "\"==\"",
    "\"<=\"",
    "\">=\"",
    "\"!=\"",
    "\"||\"",
    "\"&&\"",
    "\"+\"",
    "\"-\"",
    "\"++\"",
    "\"--\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\".\"",
  };

}
