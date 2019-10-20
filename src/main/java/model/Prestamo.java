package model;

import java.util.Objects;

public class Prestamo extends PDetalle {

    private String CODPRES;
    private String CODBIBL;
    private String CODLECT;
    private String FSALPRES;
    private String FENTPRES;
    private int CANTFAL;
    private int CODLIB;
    
    //ULT
    private String NAMELIBRO;
    

    public String getCODPRES() {
        return CODPRES;
    }

    public void setCODPRES(String CODPRES) {
        this.CODPRES = CODPRES;
    }

    public String getCODBIBL() {
        return CODBIBL;
    }

    public void setCODBIBL(String CODBIBL) {
        this.CODBIBL = CODBIBL;
    }

    public String getCODLECT() {
        return CODLECT;
    }

    public void setCODLECT(String CODLECT) {
        this.CODLECT = CODLECT;
    }

    public String getFSALPRES() {
        return FSALPRES;
    }

    public void setFSALPRES(String FSALPRES) {
        this.FSALPRES = FSALPRES;
    }

    public String getFENTPRES() {
        return FENTPRES;
    }

    public void setFENTPRES(String FENTPRES) {
        this.FENTPRES = FENTPRES;
    }

    public int getCANTFAL() {
        return CANTFAL;
    }

    public void setCANTFAL(int CANTFAL) {
        this.CANTFAL = CANTFAL;
    }

    public int getCODLIB() {
        return CODLIB;
    }

    public void setCODLIB(int CODLIB) {
        this.CODLIB = CODLIB;
    }

    //AGREGE ULT
    public String getNAMELIBRO() {
        return NAMELIBRO;
    }

    public void setNAMELIBRO(String NAMELIBRO) {
        this.NAMELIBRO = NAMELIBRO;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.CODPRES);
        hash = 79 * hash + Objects.hashCode(this.CODBIBL);
        hash = 79 * hash + Objects.hashCode(this.CODLECT);
        hash = 79 * hash + Objects.hashCode(this.FSALPRES);
        hash = 79 * hash + Objects.hashCode(this.FENTPRES);
        hash = 79 * hash + this.CANTFAL;
        hash = 79 * hash + this.CODLIB;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        if (this.CANTFAL != other.CANTFAL) {
            return false;
        }
        if (this.CODLIB != other.CODLIB) {
            return false;
        }
        if (!Objects.equals(this.CODPRES, other.CODPRES)) {
            return false;
        }
        if (!Objects.equals(this.CODBIBL, other.CODBIBL)) {
            return false;
        }
        if (!Objects.equals(this.CODLECT, other.CODLECT)) {
            return false;
        }
        if (!Objects.equals(this.FSALPRES, other.FSALPRES)) {
            return false;
        }
        if (!Objects.equals(this.FENTPRES, other.FENTPRES)) {
            return false;
        }
        return true;
    }
    
    

}
