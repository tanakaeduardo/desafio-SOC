package org.apache.struts.crud.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Models a Funcionario who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class ExameRealizado implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private Integer cd_funcionario;
    private Integer cd_exame;
    private Date dt_realizacao;
    private Exame exame;
    private Funcionario funcionario;
    
    public ExameRealizado()  {
    }
    
    public ExameRealizado(Integer cd_exame, Integer cd_funcionario, Date dt_realizacao) {
        this.setCd_exame(cd_exame);
        this.setCd_funcionario(cd_funcionario);
        this.setDt_realizacao(dt_realizacao);
        
    }

    public String toString() {
        return "cd_funcionario: " + getCd_funcionario() + " | " +
        		"cd_exame: " + getCd_exame() + " | " +
        		"dt_realizacao: " + getDt_realizacao() + " | "
               ;
    }

	public Integer getCd_funcionario() {
		return cd_funcionario;
	}

	public void setCd_funcionario(Integer cd_funcionario) {
		this.cd_funcionario = cd_funcionario;
	}

	public Integer getCd_exame() {
		return cd_exame;
	}

	public void setCd_exame(Integer cd_exame) {
		this.cd_exame = cd_exame;
	}

	public Date getDt_realizacao() {
		return dt_realizacao;
	}

	public void setDt_realizacao(Date dt_realizacao) {
		this.dt_realizacao = dt_realizacao;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
