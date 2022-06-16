import java.util.Vector;

public class pruebas {
    public static void main(String [] args){
        Lexer my_lexer = new Lexer("program programaejemplo\nvar\n\tperro, gato : int;\nbegin\n\tperro = 2 + 2;\n\tread gato;\n\twrite 2.2 + 3;\nend\0");
        Vector<Token> list = my_lexer.get_all_tokens();
    }
}
