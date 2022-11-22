import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class PassOne {
    int lc = 0;
    int lit_ptr = 0, pool_ptr = 0;
    int sym_index = 0, lit_index = 0;
    LinkedHashMap<String, TableRow> SYMTAB;
    ArrayList<TableRow> LITTAB;
    ArrayList<Integer> POOLTAB;

    public static void main(String[] args) throws Exception {
        PassOne p1 = new PassOne();
        try {
            p1.parseFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PassOne() {
        SYMTAB = new LinkedHashMap<>();
        LITTAB = new ArrayList<>();
        POOLTAB = new ArrayList<>();
        lc = 0;
        POOLTAB.add(0);
    }

    public void parseFile() throws Exception {
        FileReader fr = new FileReader("TestCase2.txt");
        FileWriter fw = new FileWriter("IC.txt");
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);
        InstTable lookup = new InstTable();
        String line, code;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s+");
            if (!parts[0].isEmpty()) {
                if (SYMTAB.containsKey(parts[0])) {
                    SYMTAB.put(parts[0], new TableRow(parts[0], lc, SYMTAB.get(parts[0]).getindex()));
                } else {
                    SYMTAB.put(parts[0], new TableRow(parts[0], lc, ++sym_index));
                }
            }
            if (parts[1].equals("START")) {
                lc = expr(parts[2]);
                code = "(AD,01)\t(C, " + lc + ")";
                bw.write(code + "\n");
            }
            if (parts[1].equals("ORIGIN")) {
                lc = expr(parts[2]);
                code = "";
                if (parts[2].contains("+")) {
                    String[] splits = parts[2].split("\\+");
                    code = "(AD,03)\t(S,0" + SYMTAB.get(splits[0]).getindex() + ")+" + Integer.parseInt(splits[1]);
                } else if (parts[2].contains("-")) {
                    String[] splits = parts[2].split("\\-");
                    code = "(AD,03)\t(S,0" + SYMTAB.get(splits[0]).getindex() + ")-" + Integer.parseInt(splits[1]);
                }

                bw.write(code + "\n");
            }
            if (parts[1].equals("EQU")) {
                int loc = expr(parts[2]);
                if (SYMTAB.containsKey(parts[0])) {
                    SYMTAB.put(parts[0], new TableRow(parts[0], loc, SYMTAB.get(parts[0]).getindex()));
                } else {
                    SYMTAB.put(parts[0], new TableRow(parts[0], loc, ++sym_index));
                }
                bw.write("\n");
            }
            if (parts[1].equals("DC")) {
                lc++;
                int cnst = Integer.parseInt(parts[2].replace("'", ""));
                code = "(DL,01)\t(C," + cnst + ")";
                bw.write(code + "\n");
            } else if (parts[1].equals("DS")) {
                int size = Integer.parseInt(parts[2].replace("'", ""));
                code = "(DL,02)\t(C," + size + ")";
                bw.write(code + "\n");
                lc = lc + size;
            }
            if (lookup.gettype(parts[1]).equals("IS")) {
                code = "(IS,0" + lookup.getcode(parts[1]) + ")\t";
                int j = 2;
                String code2 = "";
                while (j < parts.length) {
                    parts[j] = parts[j].replace(",", "");
                    if (lookup.gettype(parts[j]).equals("RG")) {
                        code2 += lookup.getcode(parts[j]) + "\t";
                    } else if (lookup.gettype(parts[j]).equals("CC")) {
                        code2 += lookup.getcode(parts[j]) + "\t";
                    } else {
                        if (parts[j].contains("=")) {
                            parts[j] = parts[j].replace("=", "");
                            parts[j] = parts[j].replace("'", "");
                            LITTAB.add(new TableRow(parts[j], -1, ++lit_index));
                            lit_ptr++;
                            code2 += "(L,0" + (lit_index) + ")";
                        } else if (SYMTAB.containsKey(parts[j])) {
                            int ind = SYMTAB.get(parts[j]).getindex();
                            code2 += "(S,0" + ind + ")";
                        } else {
                            SYMTAB.put(parts[j], new TableRow(parts[j], -1, ++sym_index));
                            int ind = SYMTAB.get(parts[j]).getindex();
                            code2 += "(S,0" + ind + ")";
                        }

                    }
                    j++;
                }
                lc++;
                code = code + code2;
                bw.write(code + "\n");
            }
            if (parts[1].equals("LTORG")) {
                int ptr = POOLTAB.get(pool_ptr);
                for (int j = ptr; j < lit_ptr; j++) {
                    lc++;
                    LITTAB.set(j, new TableRow(LITTAB.get(j).getSymbol(), lc));
                    code = "(DL,01)\t(C," + LITTAB.get(j).symbol + ")";
                    bw.write(code + "\n");
                }
                pool_ptr++;
                POOLTAB.add(lit_ptr - 1);
            }
            if (parts[1].equals("END")) {
                if (parts.length == 3) {
                    if (!parts[2].isEmpty() && parts[2] != null) {
                        int ind = SYMTAB.get(parts[2]).getindex();
                        code = "(AD,02)\t(S,0" + ind + ")";
                    } else {
                        code = "(AD,02)";
                    }
                } else {
                    code = "(AD,02)";
                }

                bw.write(code + "\n");
                int ptr = POOLTAB.get(pool_ptr) + 1;
                for (int j = ptr; j < lit_ptr; j++) {
                    lc++;
                    LITTAB.set(j, new TableRow(LITTAB.get(j).getSymbol(), lc));
                    code = "(DL,01)\t(C," + LITTAB.get(j).symbol + ")";
                    bw.write(code + "\n");
                }
                pool_ptr++;
                POOLTAB.add(lit_ptr);
            }
        }
        br.close();
        fr.close();
        bw.close();
        fw.close();
        printSymTab();
        System.out.println();
        printLitTab();
        System.out.println();
        printPoolTab();
        System.out.println();
    }

    void printSymTab() throws IOException {
        FileWriter fw = new FileWriter("SYMTAB.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        Iterator<String> iterator = SYMTAB.keySet().iterator();
        System.out.println("SYMBOL TABLE");
        System.out.println("Index\tSymbol\tAddress");
        while (iterator.hasNext()) {
            String key = iterator.next();
            TableRow value = SYMTAB.get(key);
            System.out.println(value.getindex() + "\t" + value.getSymbol() + "\t" + value.getaddress());
            bw.write(value.getindex() + "\t" + value.getSymbol() + "\t" + value.getaddress() + "\n");
        }
        bw.close();
    }

    void printLitTab() throws IOException {
        FileWriter fw = new FileWriter("LITTAB.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("LITERAL TABLE");
        System.out.println("Index\tLiteral\tAddress");
        for (int i = 0; i < LITTAB.size(); i++) {
            TableRow row = LITTAB.get(i);
            System.out.println((i + 1) + "\t='" + row.getSymbol() + "'\t" + (row.getaddress() - 1));
            bw.write((i + 1) + "\t='" + row.getSymbol() + "'\t" + (row.getaddress() - 1) + "\n");
        }
        bw.close();

    }

    void printPoolTab() throws IOException {
        FileWriter fw = new FileWriter("POOLTAB.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("POOLTAB");
        for (int i = 1; i < POOLTAB.size(); i++) {
            System.out.println("#" + POOLTAB.get(i));
            bw.write("#" + POOLTAB.get(i) + "\n");
        }
        bw.close();

    }

    public int expr(String str) {
        int temp = 0;
        if (str.contains("+")) {
            String splits[] = str.split("\\+");
            temp = SYMTAB.get(splits[0]).getaddress() + Integer.parseInt(splits[1]);
        } else if (str.contains("-")) {
            String splits[] = str.split("\\-");
            temp = SYMTAB.get(splits[0]).getaddress() - Integer.parseInt(splits[1]);
        } else {
            if (SYMTAB.containsKey(str)) {
                temp = SYMTAB.get(str).getaddress();
            } else {
                temp = Integer.parseInt(str);
            }
        }
        return temp;
    }
}
