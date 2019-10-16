package com.example.others.ast;


public class Parser {

    private Token m_crtToken;
    private final String m_Text;
    private int m_Index;

    public Parser(String str) {
        m_Text = str + "#";
        m_Index = 0;
        m_crtToken = new Token();
    }

    public static ASTNode parse(String expr) throws ParserException {
        Parser parser = new Parser(expr);
        parser.GetNextToken();
        return parser.Expression();
    }

    private ASTNode Expression() throws ParserException {
        ASTNode t_node = Term();
        ASTNode e1_node = Expression1();

        return new ASTNode(ASTNodeType.OPERATOR_PLUS, 0, t_node, e1_node);
    }

    private ASTNode Expression1() throws ParserException {
        ASTNode t_node;
        ASTNode e1_node;
        switch (m_crtToken.type) {
            case PLUS:
                GetNextToken();
                t_node = Term();
                e1_node = Expression1();
                return new ASTNode(ASTNodeType.OPERATOR_PLUS, 0, t_node, e1_node);
            case MINUS:
                GetNextToken();
                t_node = Term();
                e1_node = Expression1();
                return new ASTNode(ASTNodeType.OPERATOR_MINUS, 0, t_node, e1_node);
            default:
                return new ASTNode(ASTNodeType.NUMBER_VALUE, 0, null, null);
        }
    }

    private ASTNode Term() throws ParserException {
        ASTNode f_node = Factor();
//        GetNextToken();
        ASTNode t1_node = Term1();
        return new ASTNode(ASTNodeType.OPERATOR_MUL, 0, f_node, t1_node);
    }

    private ASTNode Term1() throws ParserException {
        ASTNode t_node;
        ASTNode e1_node;
        switch (m_crtToken.type) {
            case MUL:
                GetNextToken();
                t_node = Factor();
                e1_node = Term1();
                return new ASTNode(ASTNodeType.OPERATOR_MUL, 0, t_node, e1_node);
            case DIV:
                GetNextToken();
                t_node = Factor();
                e1_node = Term1();
                return new ASTNode(ASTNodeType.OPERATOR_DIV, 0, t_node, e1_node);
            default:
                return new ASTNode(ASTNodeType.NUMBER_VALUE, 1, null, null);
        }
    }

    private ASTNode Factor() throws ParserException {
        ASTNode node;
        switch (m_crtToken.type) {
            case OPEN_PARENTHESIS:
                GetNextToken();
                node = Expression();
                Match(')');
                return node;
            case MINUS:
                GetNextToken();
                node = Factor();
                return new ASTNode(ASTNodeType.UNARY_MINUS, 0, node, null);
            case NUMBER:
                double number = m_crtToken.value;
                GetNextToken();
                return new ASTNode(ASTNodeType.NUMBER_VALUE, number, null, null);
            case SIN:
                GetNextToken();
                node = Expression();
                Match(')');
                return new ASTNode(ASTNodeType.OPERATOR_SIN, 0, node, null);
            case COS:
                GetNextToken();
                node = Expression();
                Match(')');
                return new ASTNode(ASTNodeType.OPERATOR_COS, 0, node, null);
            default:
                String err_msg = "Unexpected token '" + m_Text.charAt(m_Index) + "' at position " + m_Index;
                throw new ParserException(err_msg, m_Index);
        }
    }

    private void Match(char expected) throws ParserException {
        if (m_Text.charAt(m_Index - 1) == expected)
            GetNextToken();
        else {
            String err_msg = "Unexpected token '" + m_Text.charAt(m_Index) + "' at position " + m_Index;
            throw new ParserException(err_msg, m_Index);
        }
    }

    private void SkipWhitespaces() {
        while (isSpace(m_Text.charAt(m_Index))) m_Index++;
    }

    private void GetNextToken() throws ParserException {
        // Ignore white spaces
        SkipWhitespaces();
        m_crtToken.value = 0;
        m_crtToken.symbol = 0;

        // Test for the end of test
        if (m_Text.charAt(m_Index) == '#') {
            m_crtToken.type = TokenType.EOT;
            return;
        }

        if (isDigit(m_Text.charAt(m_Index))) {
            m_crtToken.type = TokenType.NUMBER;
            m_crtToken.value = GetNumber();
            return;
        }

        m_crtToken.type = TokenType.ERROR;

        switch (m_Text.charAt(m_Index)) {
            case '+': m_crtToken.type = TokenType.PLUS; break;
            case '-': m_crtToken.type = TokenType.MINUS; break;
            case '*': m_crtToken.type = TokenType.MUL; break;
            case '/': m_crtToken.type = TokenType.DIV; break;
            case '(': m_crtToken.type = TokenType.OPEN_PARENTHESIS; break;
            case ')': m_crtToken.type = TokenType.CLOSE_PARENTHESIS; break;
            case 's':
                if (m_Text.substring(m_Index, m_Index + 4).equals("sin(")) {
                    m_crtToken.type = TokenType.SIN;
                    m_Index += 3;
                }
                break;
            case 'c':
                if (m_Text.substring(m_Index, m_Index + 4).equals("cos(")) {
                    m_crtToken.type = TokenType.COS;
                    m_Index += 3;
                }
                break;
        }

        if (m_crtToken.type != TokenType.ERROR) {
            m_crtToken.symbol = m_Text.charAt(m_Index);
            m_Index++;
        } else {
            String err_msg = "Unexpected token '" + m_Text.charAt(m_Index) + "' at position " + m_Index;
            throw new ParserException(err_msg, m_Index);
        }
    }

    private double GetNumber() throws ParserException {
        SkipWhitespaces();

        int index = m_Index;
        while (isDigit(m_Text.charAt(m_Index))) m_Index++;
        if (m_Text.charAt(m_Index) == '.') m_Index++;
        while (isDigit(m_Text.charAt(m_Index))) m_Index++;

        if (m_Index - index == 0)
            throw new ParserException("Number expected but not found!", m_Index);

        String buffer = m_Text.substring(index, m_Index);

        return Double.valueOf(buffer);
    }

    private boolean isSpace(char ch) {
        return ch == ' ';
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

}

enum TokenType {
    ERROR,
    PLUS,
    MINUS,
    MUL,
    DIV,
    SIN,
    COS,
    EOT,
    OPEN_PARENTHESIS,
    CLOSE_PARENTHESIS,
    NUMBER
}

class Token {
    TokenType type;
    double value;
    char symbol;

    Token() {
        type = TokenType.ERROR;
        value = 0;
    }
}