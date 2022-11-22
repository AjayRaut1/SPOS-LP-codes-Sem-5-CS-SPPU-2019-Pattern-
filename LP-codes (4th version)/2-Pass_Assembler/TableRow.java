/*CLASS TO INSERT ROWS IN SYMBOL TABLE AND LITERAL TABLE*/

public class TableRow {
    int index, address;
    String symbol;

    public String getSymbol() {
        return symbol;
    }

    public TableRow(String symbol, int address) {
        super();
        this.symbol = symbol;
        this.address = address;
        index = 0;
    }

    public void setsymbol(String symbol) {
        this.symbol = symbol;
    }

    public TableRow(String symbol, int address, int index) {
        super();
        this.symbol = symbol;
        this.address = address;
        this.index = index;
    }

    public void setaddress(int address) {
        this.address = address;
    }

    public int getaddress() {
        return address;
    }

    public void setindex(int index) {
        this.index = index;
    }

    public int getindex() {
        return index;
    }

}
