import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;


public class Lexer {

    protected Character charact;                     // actual character
    protected String input;                  // The input text/buch O' code
    protected Integer line_count = 1;                 // This variable will let us count the line location
    protected Integer in_line_count = 0;              // This is the number of characters stored per line
    // SYMBOL TABLE check if we should create the hashtable every time we initialize the Lexer.
    // ERROR REPORTE dont know if i should create and erro reporte class or what should i do.
    public List<Token> token_list; // This is a list of tokens, that will be returned to python interpreter.

    public HashMap<String, String> keywords_tokens = new HashMap<>();

    public HashMap<String, SymbolInfo> SymbolsTable = new HashMap<>();

    protected void put_hm(){
        keywords_tokens.put("program",     "tk_program");
        keywords_tokens.put("int",         "tk_type_int");
        keywords_tokens.put("begin",       "tk_begin");
        keywords_tokens.put("read",        "tk_read");
        keywords_tokens.put("var",         "tk_var");
        keywords_tokens.put("float",       "tk_type_float");
        keywords_tokens.put("end",         "tk_end");
        keywords_tokens.put("write",       "tk_write");
        // single_character tokens
        keywords_tokens.put(":",           "tk_dos_ptos");
        keywords_tokens.put(";",           "tk_pto_coma");
        keywords_tokens.put("=",           "tk_op_assign");
        keywords_tokens.put(",",           "tk_coma");
        keywords_tokens.put("+",           "tk_op_sum");
        keywords_tokens.put("-",           "tk_op_resta");
        keywords_tokens.put("*",           "tk_op_mult");
        keywords_tokens.put("/",           "tk_op_div");
        // Colors
        // reserver keywords
        keywords_tokens.put("tk_program",        "blue");
        keywords_tokens.put("tk_type_int",       "green");
        keywords_tokens.put("tk_begin",          "blue");
        keywords_tokens.put("tk_read",           "cyan");
        keywords_tokens.put("tk_var",            "blue");
        keywords_tokens.put("tk_type_float",     "green");
        keywords_tokens.put("tk_end",            "blue");
        keywords_tokens.put("tk_write",          "cyan");
        // single character tokens
        keywords_tokens.put("tk_dos_ptos",       "black");
        keywords_tokens.put("tk_pto_coma",       "black");
        keywords_tokens.put("tk_op_assign",      "yellow");
        keywords_tokens.put("tk_coma",           "black");
        keywords_tokens.put("tk_op_sum",         "yellow");
        keywords_tokens.put("tk_op_resta",       "yellow");
        keywords_tokens.put("tk_op_mult",        "yellow");
        keywords_tokens.put("tk_op_div",         "yellow");
        // additional tokens
        keywords_tokens.put("tk_error",          "red");
        keywords_tokens.put("tk_val_float",      "black");
        keywords_tokens.put("tk_val_int",        "black");
        keywords_tokens.put("tk_id",             "magenta");

    }
    // Constructor
    public Lexer(){
        put_hm();
    }
    public Lexer(String t_input){
        put_hm();
        input     =   t_input;
        charact = input.charAt(0);
    }

    // Getters
    public Character get_character(){
        return charact;
    }
    public String get_input(){
        return input;
    }

    // Setters
    public void    set_character(Character t_character){
        charact = t_character;
    }
    public void    set_input(String t_input){
        input     = t_input;
    }

    // is ... boolean checkers
    // TODO set functionality or checkers to the character checkers.
    public Boolean isDigit(Character c){
        return Character.isDigit(c);
    }
    public Boolean isLetter(Character c){
        return Character.isAlphabetic(c);
    }
    public Boolean isSeparator(Character c){
        return Character.isSpaceChar(c);
    }





    /*
    It will save the first char of the string to character variable.
    Consume method will delete the first character of the input text.
    */
    public void consume(){
            /*
            if separator is a new line:
                set in_line_count to 0
                line_count ++
            */
        if(charact=='\n'){
            in_line_count = 0;
            line_count ++;
            in_line_count = 0;
        }
        else{
            in_line_count ++;
        }
            /*
            Delete firts character.
            string.erase(first character position, number characters to delete)
            */

        input = input.substring(1);
        charact = input.charAt(0);

    }

    /*
    Method that will calculate the coordinate of some token, depending on the
    character count by each line and the.
    */
    public int calculate_in_line_index(int buff){
        return in_line_count - buff;
    }


    /*
    This method is supposed to treat tokens that start with a letter or
    ID valid character. This method is used in the nextToken method.
    */
    public Token tokenId(){
            /*
            Define string variable that will work as a buffer, for appending
            id_valid characters to it and at the ebd checking what kind of
            token it is.
            */
        String buff="";


            /*
            While loop to check wether the current character is a letter or
            a digit. Into the while loop, it will append the current
            character to the string buffer variable.
            */
        while(isLetter(charact) || isDigit(charact)){                                    // O(n)
            buff += charact;      // append char to buff variable.
            consume();                      // consume current variable.
        }

            /*
            The loop will break when it detects a separator or another type
            of character.
            Then it should set the lexeme to be the buffer value
                buffer = lexeme
            */
        String lexeme = buff;

            /*
            Get the token kind depending on the lexeme.
            If statement to check if value is found on the reserves.
            Define variable that stores the token kind.
            */
        String token_kind;
        if(keywords_tokens.containsKey(lexeme)){
            token_kind = keywords_tokens.get(lexeme);
        } else{
            token_kind = "tk_id";
        }

            /*
            Add the entry to the symbol table
                st.insert({string lexeme, SymbolInfo})
                SymbolInfo(std::string t_lexeme, std::string t_tokenKind, bool t_declared, int t_address)
                address parameterr may be the number of character on row
                TODO : check if what the address parameter is for.
                Entry parameter is the table_id
            */
        SymbolInfo symbol_table_row = new SymbolInfo(lexeme, token_kind, true, 1);
        SymbolsTable.put(lexeme, symbol_table_row);

            /*
            create and return Token.
            TODO : check if it should be added to a list.
            Token(std::string t_kind, std::string t_lexeme, int t_line, int t_character_index, std::string t_color, int t_token_size){
            */
        Token token_id = new Token(token_kind, lexeme, line_count, calculate_in_line_index(buff.length()) , keywords_tokens.get(token_kind), buff.length());

        return token_id;
    }


    /*
        This method treats tokens that start with a digit. will decide if either is a
        int or float token value.
    */
    public Token tokenValIntFloat(){
            /*
            Define string variable that works as buffer to contain an integer or float value.
            */
        String buff="";

            /*
            Define boolean variable to mark in case there was a decimal point ocurrence.
            */
        Boolean decimal_point_ocurrence = false;


            /*
            While loop to check if current character is an digit or a ".".
            In case there is an ocurrence of ".", then mark the previous
            variable with a true.
            Set an if "if" for in case the point occyrrence is true or false.
            If it is true, then break the while loop.
            The character is appended to the buffer string variable.
            */
        while(isDigit(charact) || (charact == '.')){
            if(charact == '.' ){
                decimal_point_ocurrence = true;
            }
            buff += charact;
            consume();
        }



            /*
            if decimal point ocurrence is true, then create float_val_token
            */
            /*
            if last if condition was false, return as default token as int_val_token
            */
        String token_kind;
        if(decimal_point_ocurrence == true){
            token_kind = "tk_val_float";
        } else{
            token_kind = "tk_val_int";
        }

            /*
            In case the character is a letter, return an error token
            */
        if(isLetter(charact)) token_kind = "tk_error";

            /*
            Add entry to symbol table.
            */
        String lexeme = buff;
        SymbolInfo symbol_table_row = new SymbolInfo(lexeme, token_kind, true, 1);
        SymbolsTable.put(lexeme, symbol_table_row);

            /*
            Create Token
            Token(std::string t_kind, std::string t_lexeme, int t_line, int t_character_index, std::string t_color, int t_token_size){
            */
        Token token_id = new Token(token_kind, lexeme, line_count, calculate_in_line_index(buff.length()) , keywords_tokens.get(token_kind), buff.length());

        return token_id;
    }






    /*
        This is the main method where the input will be analyzed in search
        of the tokens.
        TODO : this method it is supposed to return an EOD token when the
        program ends. So try changing the the method return type.
    */
    public Token nextToken(){
            /*
            While the present token is not the EOF or \0 cotinue reading the
            input.
            */
        while(charact != '\0'){
                /*
                In case of character being a separator, skip to next iteration.
                */
            if(isSeparator(charact)){
                consume();
                continue;
            }

                /*
                Check if the single & simple character exists on the reserver_kerwords
                unodered_map. if exists create token.
                Token(std::string t_kind, std::string t_lexeme, int t_line, int t_character_index, std::string t_color, int t_token_size){
                */
            if(keywords_tokens.containsKey(Character.toString(charact))){
                String token_kind = keywords_tokens.get(Character.toString(charact));
                Token token_id = new Token(token_kind, Character.toString(charact), line_count, calculate_in_line_index(0), keywords_tokens.get(token_kind), 1);
                consume();
                // continue;
                return token_id;
            }

                /*
                Here we check the first character of new token, depending on its type,
                it will redirect us to some other method.
                Check if current character is a letter. -> run method for tokenID
                Check if current character is a digit.  -> run method for tokenValDigit
                if is another kind of character return and error.
                Token(std::string t_kind, std::string t_lexeme, int t_line, int t_character_index, std::string t_color, int t_token_size){
                */
            if(isLetter(charact)) return tokenId();
            if(isDigit(charact))  return tokenValIntFloat();
            else{
                String token_kind = "tk_error";
                Token token_id = new Token(token_kind, Character.toString(charact), line_count, calculate_in_line_index(0), keywords_tokens.get(token_kind), 1);
                consume();
                return token_id;
            }

        }

            /*
            In case the the while loop end, do something.
            */
        return new Token("tk_EOF", Character.toString(charact), line_count, calculate_in_line_index(charact), "red", 1);
    }


        /*
            This method returns a vector of all the tokens on the txt input.
        */
    Vector<Token> get_all_tokens(){
        Token token_iterator = new Token();
        Vector<Token> vector_of_tokens = new Vector<>();
        while(token_iterator.get_kind() != "tk_EOF"){
            System.out.println(token_iterator.get_kind());
            token_iterator = nextToken();
            vector_of_tokens.add(token_iterator);
        }
        return vector_of_tokens;
    }

        /*
        This method returns a vector of all SymbolInfo object.
        */
    Vector<SymbolInfo> get_symbol_table(){
        Vector<SymbolInfo> vector_of_symbols = new Vector<>();
        for(Map.Entry<String, SymbolInfo> set : SymbolsTable.entrySet()){
            vector_of_symbols.add(set.getValue());
        }
        return vector_of_symbols;
    }











}
