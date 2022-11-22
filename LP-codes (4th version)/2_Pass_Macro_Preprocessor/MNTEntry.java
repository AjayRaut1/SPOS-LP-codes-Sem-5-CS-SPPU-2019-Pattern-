public class MNTEntry {
    String name;
    int pp, kp, mdtn, kpdtn;

    MNTEntry(String name, int pp, int kp, int mdtn, int kpdtn) {
        super();
        this.name = name;
        this.pp = pp;
        this.kp = kp;
        this.mdtn = mdtn;
        this.kpdtn = kpdtn;
    }

    public void setName(String setName) {
        this.name = setName;
    }

    public String getName() {
        return name;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPp() {
        return pp;
    }

    public void setKp(int kp) {
        this.kp = kp;
    }

    public int getKp() {
        return kp;
    }

    public void setMdtp(int mdtn) {
        this.mdtn = mdtn;
    }

    public int getMdtp() {
        return mdtn;
    }

    public void setKpdtp(int kpdtn) {
        this.kpdtn = kpdtn;
    }

    public int getKpdtp() {
        return kpdtn;
    }
}
