import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Pass1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        FileWriter mnt = new FileWriter("mnt.txt");
        FileWriter mdt = new FileWriter("mdt.txt");
        FileWriter pntab = new FileWriter("pntab.txt");
        FileWriter kpdtab = new FileWriter("kpdtab.txt");
        FileWriter ic = new FileWriter("ic.txt");

        LinkedHashMap<String, Integer> pntb = new LinkedHashMap<>();

        int flag = 0, pp = 0, kp = 0, mdtp = 1, kpdtp = 0, paramNo = 1;

        String line;
        String macroname = null;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s+");

            if (parts[0].equals("MACRO")) {
                flag = 1;
                line = br.readLine();
                parts = line.split("\\s+");
                macroname = parts[0];
                if (parts.length <= 1) {
                    mnt.write(macroname + "\t" + pp + "\t" + kp + "\t" + mdtp + "\t" + ((kpdtp == 0) ? kpdtp
                            : (kpdtp + 1)) + "\n");
                    continue;
                }
                for (int i = 1; i < parts.length; i++) {
                    parts[i] = parts[i].replaceAll("[&,]", "");
                    if (parts[i].contains("=")) {
                        ++kp;
                        String keyparam[] = parts[i].split("=");
                        pntb.put(keyparam[0], paramNo++);
                        if (keyparam.length == 2) {
                            kpdtab.write(keyparam[0] + "\t" + keyparam[1] + "\n");
                        } else {
                            kpdtab.write(keyparam[0] + "\t-\n");
                        }
                    } else {
                        pntb.put(parts[i], paramNo++);
                        pp++;
                    }
                }
                mnt.write(macroname + "\t" + pp + "\t" + kp + "\t" + mdtp + "\t" + ((kp == 0) ? kpdtp
                        : (kpdtp + 1)) + "\n");
                kpdtp += kp;
            } else if (parts[0].equals("MEND")) {
                mdt.write(line + "\n");
                flag = 0;
                kp = 0;
                pp = 0;
                mdtp++;
                paramNo = 1;
                pntab.write(macroname + ":\t");
                Iterator<String> it = pntb.keySet().iterator();
                while (it.hasNext()) {
                    pntab.write(it.next() + "\t");
                }
                pntab.write("\n");
                pntb.clear();
            } else if (flag == 1) {
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].contains("&")) {
                        parts[i] = parts[i].replaceAll("[&,]", "");
                        mdt.write("(P," + pntb.get(parts[i]) + ")\t");
                    } else {
                        mdt.write(parts[i] + "\t");
                    }
                }
                mdt.write("\n");
                mdtp++;
            } else {
                ic.write(line + "\n");
            }

        }
        mnt.close();
        ic.close();
        mdt.close();
        pntab.close();
        kpdtab.close();
        br.close();
        System.out.println("MACRO PASS 1 PROCESSSING DONE");
    }
}