import java.util.*;

public class InstTable {
    HashMap<String, Integer> IS, AD, DL, CC, RG;

    // CONTRUCTOR TO INTIALIZE OPTAB, CONDITION CODES,
    // ADN REGISTERS
    public InstTable() {
        IS = new HashMap<>();
        AD = new HashMap<>();
        DL = new HashMap<>();
        CC = new HashMap<>();
        RG = new HashMap<>();
        IS.put("STOP", 0);
        IS.put("ADD", 1);
        IS.put("SUB", 2);
        IS.put("MULT", 3);
        IS.put("MOVER", 4);
        IS.put("MOVEM", 5);
        IS.put("COMP", 6);
        IS.put("BC", 7);
        IS.put("DIV", 8);
        IS.put("READ", 9);
        IS.put("PRINT", 10);
        AD.put("START", 1);
        AD.put("END", 2);
        AD.put("ORIGIN", 3);
        AD.put("EQU", 4);
        AD.put("LTORG", 5);
        DL.put("DC", 1);
        DL.put("DS", 2);
        RG.put("AREG", 01);
        RG.put("BREG", 02);
        RG.put("CREG", 03);
        RG.put("DREG", 04);
        CC.put("LT", 1);
        CC.put("LE", 2);
        CC.put("EQ", 3);
        CC.put("GT", 4);
        CC.put("GE", 5);
        CC.put("ANY", 6);

    }

    public String gettype(String s) {
        s = s.toUpperCase();
        if (AD.containsKey(s)) {
            return "AD";
        } else if (IS.containsKey(s)) {
            return "IS";
        } else if (DL.containsKey(s)) {
            return "DL";
        } else if (RG.containsKey(s)) {
            return "RG";
        } else if (CC.containsKey(s)) {
            return "CC";
        }
        return "";
    }

    public int getcode(String s) {
        s = s.toUpperCase();
        if (AD.containsKey(s)) {
            return AD.get(s);
        } else if (IS.containsKey(s)) {
            return IS.get(s);
        } else if (DL.containsKey(s)) {
            return DL.get(s);
        } else if (RG.containsKey(s)) {
            return RG.get(s);
        } else if (CC.containsKey(s)) {
            return CC.get(s);
        }
        return -1;
    }

}