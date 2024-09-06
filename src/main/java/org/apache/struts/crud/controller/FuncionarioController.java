package org.apache.struts.crud.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.crud.dao.ExameRealizadoDAO;
import org.apache.struts.crud.dao.ExameRealizadoDAO;
import org.apache.struts.crud.entity.Funcionario;
import org.apache.struts.crud.service.FuncionarioService;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as a controller to handle actions related to editing a Funcionario.
 * 
 * @author eduardo tanaka
 */
public class FuncionarioController extends ActionSupport {
    
    private static final long serialVersionUID = 1L;
	private Funcionario funcionario = new Funcionario();
    private FuncionarioService service = new FuncionarioService();

    private List<Funcionario> funcionarioList;
    private String sm = "";
    private String em = "";

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
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		return SUCCESS;
	}
	
	public String listFunc() throws Exception {
		return SUCCESS;
	}
	
	public String listFuncionarios() throws Exception {
		this.funcionario.setCd_funcionario(funcionario.getCd_funcionario());
		this.funcionario.setNm_funcionario(funcionario.getNm_funcionario());
		this.funcionarioList = service.listarFuncionario(funcionario);
		return SUCCESS;
	}

	public String insertFuncionario() {
		this.funcionario.setNm_funcionario(funcionario.getNm_funcionario());
		
		boolean status;
		try {
			status = service.salvarFuncionario(this.funcionario);
			if(status) {
				setSm("Funcionario salvo com sucesso");
				this.funcionario.setNm_funcionario("");
				return SUCCESS;
			} else {
				setEm("Funcionario nao salvo");
				return INPUT;
			}
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("Erro na conexao");
			return INPUT;
		}
		

	}
	
	public String editFuncionario() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));
		Funcionario funcionario;
		try {
			funcionario = service.buscarFuncionario(cd_funcionario);
			this.funcionario.setCd_funcionario(funcionario.getCd_funcionario());
			this.funcionario.setNm_funcionario(funcionario.getNm_funcionario());
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			setSm("Funcionario não encontrado");
			return INPUT;
		}
		
	}
	
	public String updateFuncionario() {

		boolean status;
		try {
			status = service.alterarFuncionario(funcionario.getCd_funcionario(), funcionario.getNm_funcionario());
			if(status) {
				setSm("Funcionario alterado com sucesso");
				this.funcionarioList = service.listarFuncionario(new Funcionario());
				return SUCCESS;
			} else {
				setEm("Funcionario nao alterado");
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		

	}
	
	public String deleteFuncionario() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));
		
//	    daoExameRealizado.deleteExameRealizado(0, cd_funcionario);
//		boolean statusExameFuncionario = serviceExame.apagarFuncionario(cd_funcionario);
//		if(statusExameFuncionario) {
//			setSm("Exame Funcionario apagado com sucesso");
//		} else {
//			setEm("Exame Funcionario nao apagado");
//		}	
		
		boolean status;
		try {
			status = service.apagarFuncionario(cd_funcionario);
			if(status) {
				setSm("Funcionario apagado com sucesso");
				this.funcionarioList = service.listarFuncionario(new Funcionario());
				return SUCCESS;
			} else {
				setEm("Funcionario nao apagado");
				return INPUT;
			}		
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public FuncionarioService getService() {
		return service;
	}
	
	public void setService(FuncionarioService service) {
		this.service = service;
	}
}
