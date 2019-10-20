package model;

public class PDetalle {

    private int CODDET;
    private int CODPRESD;
    private String LECTOR;
    private String LIBRO;
    private String CANTDET;
    private String FECHENT;
    private String TIPDET;

    public int getCODDET() {
        return CODDET;
    }

    public void setCODDET(int CODDET) {
        this.CODDET = CODDET;
    }

    public int getCODPRESD() {
        return CODPRESD;
    }

    public void setCODPRESD(int CODPRESD) {
        this.CODPRESD = CODPRESD;
    }

    public String getCANTDET() {
        return CANTDET;
    }

    public void setCANTDET(String CANTDET) {
        this.CANTDET = CANTDET;
    }

    public String getFECHENT() {
        return FECHENT;
    }

    public void setFECHENT(String FECHENT) {
        this.FECHENT = FECHENT;
    }

    public String getTIPDET() {
        return TIPDET;
    }

    public void setTIPDET(String TIPDET) {
        this.TIPDET = TIPDET;
    }

    public String getLECTOR() {
        return LECTOR;
    }

    public void setLECTOR(String LECTOR) {
        this.LECTOR = LECTOR;
    }

    public String getLIBRO() {
        return LIBRO;
    }

    public void setLIBRO(String LIBRO) {
        this.LIBRO = LIBRO;
    }    
    
}
