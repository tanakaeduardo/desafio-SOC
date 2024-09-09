package org.apache.struts.crud.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.crud.entity.Exame;
import org.apache.struts.crud.entity.ExameRealizado;
import org.apache.struts.crud.service.ExameService;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as a controller to handle actions related to editing a Exame.
 * 
 * @author eduardo tanaka
 */
public class ExameController extends ActionSupport {
    
	private static final long serialVersionUID = 1L;
	private String ativo;
    private Exame exame = new Exame();
    private ExameService service = new ExameService();
    private List<Exame> exameList;
    private String sm = "";
    private String em = "";
	private String [] ativoList = {"Ativo", "Inativo", "Ambos" };
	
	public String [] getAtivoList() {
		return ativoList;
	}
	public void setAtivoList(String [] ativoList) {
		this.ativoList = ativoList;
	}
	public String getEm() {
		return em;
	}
	private void setEm(String em) {
		this.em = em;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public List<Exame> getExameList() {
		return exameList;
	}
	public void setExameList(List<Exame> exameList) {
		this.exameList = exameList;
	}
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		ativo="Ambos";
		return SUCCESS;
	}
	
	public String listFunc() throws Exception {
		return SUCCESS;
	}
	
	public String listExames() throws Exception {
		this.exame.setCd_exame(exame.getCd_exame());
		this.exame.setNm_exame(exame.getNm_exame());
		if(ativo == null) {
			this.exame.setIc_ativo(null);
		} else {
			this.exame.setIc_ativo(ativo.equals("Ambos") ? null: ativo.equals("Ativo")?Boolean.TRUE:Boolean.FALSE);
		}
		this.exameList = service.listarExame(exame);
		ativo = "Ambos";
		return SUCCESS;
	}

	public String insertExame() {
		this.exame.setNm_exame(exame.getNm_exame());
		this.exame.setIc_ativo(ativo.equals("Ativo")?Boolean.TRUE:ativo.equals("Inativo")?Boolean.FALSE:null);
		this.exame.setDs_detalhe_exame(exame.getDs_detalhe_exame());
		this.exame.setDs_detalhe_exame1(exame.getDs_detalhe_exame1());
		
		boolean status;
		try {
			status = service.salvarExame(this.exame);
			if(status) {
				setSm("Exame salvo com sucesso");
				this.exame.setNm_exame("");
				this.exame.setIc_ativo(null);
				return SUCCESS;
			} else {
				setEm("Exame nao salvo");
				return INPUT;
			}
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("Exame nao salvo");
			return INPUT;
		}
		
	
	}
	
	public String editExame() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_exame = Integer.parseInt(request.getParameter("cd_exame"));
		try {
			exame = service.buscarExame(cd_exame);
			if(exame != null) {
				ativo = exame.getIc_ativo()?"Ativo":"Inativo";
				return SUCCESS;
			} else {
				return INPUT;
			}
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("Exame nao alterado");
			return INPUT;
		}
	}
	
	public String updateExame() {
		this.exame.setCd_exame(exame.getCd_exame());
		this.exame.setNm_exame(exame.getNm_exame());
		this.exame.setIc_ativo(ativo.equals("Ativo")?Boolean.TRUE:Boolean.FALSE);
		this.exame.setDs_detalhe_exame(exame.getDs_detalhe_exame());
		this.exame.setDs_detalhe_exame1(exame.getDs_detalhe_exame1());
		
		boolean status;
		try {
			status = service.alterarExame(this.exame);
			if(status) {
				setSm("Exame alterado com sucesso");
				return SUCCESS;
			} else {
				setEm("Exame nao alterado");
				return INPUT;
			}
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("Exame nao alterado");
			return INPUT;
		}
	}
	
	public String deleteExame() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_exame = Integer.parseInt(request.getParameter("cd_exame"));
		ExameRealizado exameRealizado = new ExameRealizado(cd_exame, 0, null);
		//List<ExameRealizado> exameRealizadoList = daoExameRealizado.getListExameRealizados(exameRealizado);
		
//		if(exameRealizadoList != null && exameRealizadoList.size() > 0) {
//			setEm("Exame sendo realizado não pode ser deletado");
//			return INPUT;
//		}	
		
		boolean status;
		try {
			status = service.apagarExame(cd_exame);
			if(status) {
				setSm("Exame apagado com sucesso");
				return SUCCESS;
			} else {
				setEm("Exame nao apagado");
				return INPUT;
			}	
		} catch (Exception e) {
			e.printStackTrace();
			setEm("Exame nao apagado");
			return INPUT;
		}	
	}
}

