public class SymbolInfo {
    protected String    lexeme;
    protected String    tokenKind;
    protected Boolean   declared = false;
    protected Integer   address;

    public SymbolInfo(String t_lexeme, String t_tokenKind, Boolean t_declared, Integer t_address){
        lexeme      = t_lexeme;
        tokenKind   = t_tokenKind;
        declared    = t_declared;
        address     = t_address;
    }

    public String get_lexeme(){
        return lexeme;
    }
    public String get_tokenKind(){
        return tokenKind;
    }
    public Boolean get_declared(){
        return declared;
    }
    public Integer get_address(){
        return address;
    }

    public void set_lexeme(String t_lexeme){
        lexeme = t_lexeme;
    }
    public void set_tokenKind(String t_tokenKind){
        tokenKind = t_tokenKind;
    }
    public void set_declared(Boolean t_declared){
        declared = t_declared;
    }
    public void set_address(Integer t_address){
        address = t_address;
    }
}