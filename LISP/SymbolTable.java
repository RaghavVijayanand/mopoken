import java.util.*;
public class SymbolTable {
    private static SymbolTable instance;
    private HashMap<String, String> vars;
    private SymbolTable() {
        vars = new HashMap<>();
    }
    public static SymbolTable getInstance() {
        if (instance == null) {
            instance = new SymbolTable();
        }
        return instance;
    }
    public void set(String name, String value) {
        vars.put(name, value);
    }
    public String get(String name) {
        return vars.getOrDefault(name, name);
    }
}
