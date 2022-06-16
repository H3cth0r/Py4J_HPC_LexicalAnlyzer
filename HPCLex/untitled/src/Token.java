public class Token {
    protected String    kind;
    protected String    lexeme;
    protected Integer   line;
    protected Integer   character_index;
    protected String    color;
    protected Integer   token_size;
    protected String    index;

    public Token(){}
    public Token(String t_kind, String t_lexeme, Integer t_line, Integer t_character_index, String t_color, Integer t_token_sie){
        kind            = t_kind;
        lexeme          = t_lexeme;
        line            = t_line;
        character_index = t_character_index;
        color           = t_color;
        token_size      = t_token_sie;
    }
    public String get_kind(){
        return kind;
    }
    public String get_lexeme(){
        return  lexeme;
    }
    public Integer get_line(){
        return line;
    }
    public Integer get_character_index(){
        return character_index;
    }
    public String get_index(){
        return index;
    }
    public String get_color(){
        return color;
    }
    public Integer get_token_size(){
        return token_size;
    }

    public void set_kind(String t_kind){
        kind = t_kind;
    }
    public void set_lexeme(String t_lexeme){
        lexeme = t_lexeme;
    }
    public void set_line(Integer t_line){
        line = t_line;
    }
    public void set_character_index(Integer t_character_index){
        character_index = t_character_index;
    }
    public void set_index(String t_index){
        index = t_index;
    }
    public void set_color(String t_color){
        color = t_color;
    }
    public void set_token_size(Integer t_token_size){
        token_size = t_token_size;
    }
}

