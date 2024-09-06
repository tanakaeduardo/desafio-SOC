package org.apache.struts.crud.entity;

import java.io.Serializable;

/**
 * Models a Exam who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class Exame implements Serializable {
	
    private int cd_exame;
    private String nm_exame;
    private Boolean ic_ativo;
    private String ds_ativo;
    private String ds_detalhe_exame;
    private String ds_detalhe_exame1;
    
    public Exame()  {
    }
    
    public Exame(int cd_exame, String nm_exame, Boolean ic_ativo, String ds_detalhe_exame, 
                String ds_detalhe_exame1) {
    	this.setCd_exame(cd_exame);
        this.setNm_exame(nm_exame);
        this.setIc_ativo(ic_ativo);
        this.setDs_detalhe_exame(ds_detalhe_exame);
        this.setDs_detalhe_exame1(ds_detalhe_exame1);
    }

    public String toString() {
        return "cd_exame: " + getCd_exame() + " | "
                + "nm_exame: " + getNm_exame() + " | "
                + " ic_ativo:  " + getIc_ativo() + " | "
                + " ds_detalhe_exame: " + getDs_detalhe_exame() + " | "
                + " ds_detalhe_exame1: " + getDs_detalhe_exame1() + " | "
               ;
    }

	public int getCd_exame() {
		return cd_exame;
	}

	public void setCd_exame(int cd_exame) {
		this.cd_exame = cd_exame;
	}

	public String getNm_exame() {
		return nm_exame;
	}

	public void setNm_exame(String nm_exame) {
		this.nm_exame = nm_exame;
	}

	public Boolean getIc_ativo() {
		return ic_ativo;
	}

	public void setIc_ativo(Boolean ic_ativo) {
		this.ic_ativo = ic_ativo;
	}

	public String getDs_detalhe_exame() {
		return ds_detalhe_exame;
	}

	public void setDs_detalhe_exame(String ds_detalhe_exame) {
		this.ds_detalhe_exame = ds_detalhe_exame;
	}

	public String getDs_detalhe_exame1() {
		return ds_detalhe_exame1;
	}

	public void setDs_detalhe_exame1(String ds_detalhe_exame1) {
		this.ds_detalhe_exame1 = ds_detalhe_exame1;
	}

	public String getDs_ativo() {
		return ic_ativo?"Ativo":"Inativo";
	}

	public void setDs_ativo(String ds_ativo) {
		this.ds_ativo = ds_ativo;
	}
}
