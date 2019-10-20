package model;

import java.util.Objects;

public class Libro {

    private String CODLIB;
    private String TITLIB;
    private String CANTLIB;
    private String ESTLIB;
    private String CODAUT;
    private int CODAUT2;
    private String CODCAT;
    private int CODCAT2;
    private String CODEDIT;
    private int CODEDIT2;
    private String ANNLNZLIB;
    private String PAGLIB;
    private String DESCLIB;
    private String FORINGLIB;
    private String DIRUBILIB;

   
    public void clear() {
        this.CODLIB = null;
        this.TITLIB = null;
        this.CANTLIB = null;
        this.ESTLIB = null;
        this.CODAUT = null;
        this.CODAUT2=0;
        this.CODCAT = null;
        this.CODCAT2=0;
        this.CODEDIT = null;
        this.CODEDIT2=0;
        this.ANNLNZLIB = null;
        this.PAGLIB = null;
        this.DESCLIB = null;
        this.FORINGLIB = null;
        this.DIRUBILIB= null;
    }

    @Override
    public String toString() {
        return "Libro{" + "CODLIB=" + CODLIB + ", TITLIB=" + TITLIB + ", CANTLIB=" + CANTLIB + ", ESTLIB=" + ESTLIB + ", CODAUT=" + CODAUT + ", CODAUT2=" + CODAUT2 + ", CODCAT=" + CODCAT + ", CODCAT2=" + CODCAT2 + ", CODEDIT=" + CODEDIT + ", CODEDIT2=" + CODEDIT2 + ", ANNLNZLIB=" + ANNLNZLIB + ", PAGLIB=" + PAGLIB + ", DESCLIB=" + DESCLIB + ", FORINGLIB=" + FORINGLIB + ", DIRUBILIB=" + DIRUBILIB +'}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.CODLIB);
        hash = 59 * hash + Objects.hashCode(this.TITLIB);
        hash = 59 * hash + Objects.hashCode(this.CANTLIB);
        hash = 59 * hash + Objects.hashCode(this.ESTLIB);
        hash = 59 * hash + Objects.hashCode(this.CODAUT);
        hash = 59 * hash + this.CODAUT2;
        hash = 59 * hash + Objects.hashCode(this.CODCAT);
        hash = 59 * hash + this.CODCAT2;
        hash = 59 * hash + Objects.hashCode(this.CODEDIT);
        hash = 59 * hash + this.CODEDIT2;
        hash = 59 * hash + Objects.hashCode(this.ANNLNZLIB);
        hash = 59 * hash + Objects.hashCode(this.PAGLIB);
        hash = 59 * hash + Objects.hashCode(this.DESCLIB);
        hash = 59 * hash + Objects.hashCode(this.FORINGLIB);
        hash = 59 * hash + Objects.hashCode(this.DIRUBILIB);
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
        final Libro other = (Libro) obj;
        if (this.CODAUT2 != other.CODAUT2) {
            return false;
        }
        if (this.CODCAT2 != other.CODCAT2) {
            return false;
        }
        if (this.CODEDIT2 != other.CODEDIT2) {
            return false;
        }
        if (!Objects.equals(this.CODLIB, other.CODLIB)) {
            return false;
        }
        if (!Objects.equals(this.TITLIB, other.TITLIB)) {
            return false;
        }
        if (!Objects.equals(this.CANTLIB, other.CANTLIB)) {
            return false;
        }
        if (!Objects.equals(this.ESTLIB, other.ESTLIB)) {
            return false;
        }
        if (!Objects.equals(this.CODAUT, other.CODAUT)) {
            return false;
        }
        if (!Objects.equals(this.CODCAT, other.CODCAT)) {
            return false;
        }
        if (!Objects.equals(this.CODEDIT, other.CODEDIT)) {
            return false;
        }
        if (!Objects.equals(this.ANNLNZLIB, other.ANNLNZLIB)) {
            return false;
        }
        if (!Objects.equals(this.PAGLIB, other.PAGLIB)) {
            return false;
        }
        if (!Objects.equals(this.DESCLIB, other.DESCLIB)) {
            return false;
        }
        if (!Objects.equals(this.FORINGLIB, other.FORINGLIB)) {
            return false;
        }
        if (!Objects.equals(this.DIRUBILIB, other.DIRUBILIB)) {
            return false;
        }
        return true;
    }


    

    public String getCODLIB() {
        return CODLIB;
    }

    public void setCODLIB(String CODLIB) {
        this.CODLIB = CODLIB;
    }

    public String getTITLIB() {
        return TITLIB;
    }

    public void setTITLIB(String TITLIB) {
        this.TITLIB = TITLIB;
    }

    public String getCANTLIB() {
        return CANTLIB;
    }

    public void setCANTLIB(String CANTLIB) {
        this.CANTLIB = CANTLIB;
    }

    public String getESTLIB() {
        return ESTLIB;
    }

    public void setESTLIB(String ESTLIB) {
        this.ESTLIB = ESTLIB;
    }

    public String getCODAUT() {
        return CODAUT;
    }

    public void setCODAUT(String CODAUT) {
        this.CODAUT = CODAUT;
    }

    public String getCODCAT() {
        return CODCAT;
    }

    public void setCODCAT(String CODCAT) {
        this.CODCAT = CODCAT;
    }

    public String getCODEDIT() {
        return CODEDIT;
    }

    public void setCODEDIT(String CODEDIT) {
        this.CODEDIT = CODEDIT;
    }

    public String getANNLNZLIB() {
        return ANNLNZLIB;
    }

    public void setANNLNZLIB(String ANNLNZLIB) {
        this.ANNLNZLIB = ANNLNZLIB;
    }

    public String getPAGLIB() {
        return PAGLIB;
    }

    public void setPAGLIB(String PAGLIB) {
        this.PAGLIB = PAGLIB;
    }

    public String getDESCLIB() {
        return DESCLIB;
    }

    public void setDESCLIB(String DESCLIB) {
        this.DESCLIB = DESCLIB;
    }

    public String getFORINGLIB() {
        return FORINGLIB;
    }

    public void setFORINGLIB(String FORINGLIB) {
        this.FORINGLIB = FORINGLIB;
    }
    
     public String getDIRUBILIB() {
        return DIRUBILIB;
    }

    public void setDIRUBILIB(String DIRUBILIB) {
        this.DIRUBILIB = DIRUBILIB;
    }

    public int getCODAUT2() {
        return CODAUT2;
    }

    public void setCODAUT2(int CODAUT2) {
        this.CODAUT2 = CODAUT2;
    }

    public int getCODCAT2() {
        return CODCAT2;
    }

    public void setCODCAT2(int CODCAT2) {
        this.CODCAT2 = CODCAT2;
    }

    public int getCODEDIT2() {
        return CODEDIT2;
    }

    public void setCODEDIT2(int CODEDIT2) {
        this.CODEDIT2 = CODEDIT2;
    }

}
