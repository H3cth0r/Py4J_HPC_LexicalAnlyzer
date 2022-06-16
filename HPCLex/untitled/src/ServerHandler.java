import py4j.*;

import java.util.Vector;

public class ServerHandler {

    public Lexer my_lex = new Lexer();

    public Vector<Token> list_of_tokens(String txt_input){
        Lexer new_lex = new Lexer(txt_input);
        return new_lex.get_all_tokens();
    }
    public Lexer get_lexer(String txt_input){
        my_lex = new Lexer(txt_input);
        return my_lex;
    }

    public static void main(String[] args){
        ServerHandler app = new ServerHandler();
        // app is now the gateway.entry_point
        GatewayServer server = new GatewayServer(app);
        server.start();
        System.out.println("ServerRunning");
    }
}
