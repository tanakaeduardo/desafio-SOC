package org.apache.struts.crud.entity;

import java.io.Serializable;

/**
 * Models a Funcionario who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class Funcionario implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private Integer cd_funcionario;
    private String nm_funcionario;
    
    public Funcionario()  {
    }
    
    public Funcionario(Integer cd_funcionario, String nm_funcionario) {
    	this.setCd_funcionario(cd_funcionario);
        this.setNm_funcionario(nm_funcionario);
    }

    public String toString() {
        return "cd_funcionario: " + getCd_funcionario() + " | "+
        		"nm_funcionario: " + getNm_funcionario() + " | ";
    }

	public Integer getCd_funcionario() {
		return cd_funcionario;
	}

	public void setCd_funcionario(Integer cd_funcionario) {
		this.cd_funcionario = cd_funcionario;
	}

	public String getNm_funcionario() {
		return nm_funcionario;
	}

	public void setNm_funcionario(String nm_funcionario) {
		this.nm_funcionario = nm_funcionario;
	}

}
