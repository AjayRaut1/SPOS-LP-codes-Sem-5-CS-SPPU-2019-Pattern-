import java.io.*;
import java.util.*;

public class PassTwo{
    
    ArrayList<TableRow> SYMTAB,LITTAB;
    public PassTwo(){
        SYMTAB = new ArrayList<>();
        LITTAB = new ArrayList<>();
    }
    public static void main(String[] args) {
        PassTwo p2 = new PassTwo();
        try{
            p2.generatecodes("IC.txt");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void generatecodes(String filename) throws IOException{
        readtables();
        FileReader fr = new FileReader(filename);
        FileWriter fw = new FileWriter("PassTwo.txt");
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);
        String line,code;
        while((line=br.readLine())!=null){
            String parts[] = line.split("\\s+");
            int opcode = 0;
                int regcode = 0;
            if(parts[0].contains("AD") || parts[0].contains("(DL,02)")){
                bw.write("\n");
                continue;
            }
            else if(parts.length==2){
                if(parts[0].contains("(DL,01)")){
                    parts[0] = parts[0].replaceAll("[^0-9]","");
                    parts[1] = parts[1].replaceAll("[^0-9]","");
                    String c = String.format("%03d",Integer.parseInt(parts[1]));
                    code = "00\t0\t"+c;
                    bw.write(code+"\n");
                }
                else if(parts[0].contains("IS")){
                    int operand = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                    if(operand == 10){
                        if(parts[1].contains("S")){
                            int symindex = Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
                            code = String.format("%02d",operand)+"\t0\t"+String.format("%03d", SYMTAB.get(symindex-1).getaddress());
                            bw.write(code+"\n");
                        }
                        else if(parts[1].contains("L")){
                            int litindex = Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
                            code = String.format("%02d",operand)+"\t0\t"+String.format("%03d", LITTAB.get(litindex-1).getaddress());
                            bw.write(code+"\n");
                        }
                    }
                    else if(operand == 9){
                        if(parts[1].contains("S")){
                            int symindex = Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
                            code = String.format("%02d",operand)+"\t0\t"+String.format("%03d", SYMTAB.get(symindex-1).getaddress());
                            bw.write(code+"\n");
                        }
                        else if(parts[1].contains("L")){
                            int litindex = Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
                            code = String.format("%02d",operand)+"\t0\t"+String.format("%03d", LITTAB.get(litindex-1).getaddress());
                            bw.write(code+"\n");
                        }
                    }
                }
            }
            else if(parts.length==1 && parts[0].contains("IS")){
                    int opcode1 = Integer.parseInt(parts[0].replaceAll("[^0-9]","" ));
                    code = String.format("%02d",opcode1)+"\t0\t000";
                    bw.write(code+"\n");
                }
                
                else if(parts.length==3 && parts[0].contains("IS")){
                    opcode = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                    regcode = Integer.parseInt(parts[1]);
                }
                if(parts.length==3){
                    if(parts[2].contains("S")){
                        int symindex = Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
                        code = String.format("%02d", opcode)+"\t"+regcode+"\t"+String.format("%03d",SYMTAB.get(symindex-1).getaddress());
                        bw.write(code+"\n");
                    }
                    else if(parts[2].contains("L")){
                        int litindex = Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
                        code = String.format("%02d", opcode)+"\t"+regcode+"\t"+String.format("%03d",LITTAB.get(litindex-1).getaddress());
                        bw.write(code+"\n");
                    }
                }
                
            
            }
            br.close();
            bw.close();
        }
    public void readtables() throws IOException{
        BufferedReader br;
        FileReader fr;
        String line;
        try{
            fr = new FileReader("SYMTAB.txt");
            br = new BufferedReader(fr);
            while((line = br.readLine())!=null){
                String parts[] = line.split("\\s+");
                SYMTAB.add(new TableRow(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[0])));
            }
            br.close();
            fr = new FileReader("LITTAB.txt");
            br = new BufferedReader(fr);
            while((line = br.readLine())!=null){
                String parts[] = line.split("\\s+");
                LITTAB.add(new TableRow(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[0])));
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
