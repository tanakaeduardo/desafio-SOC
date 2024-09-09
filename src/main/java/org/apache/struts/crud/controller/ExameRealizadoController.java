package org.apache.struts.crud.controller;


import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.crud.entity.Exame;
import org.apache.struts.crud.entity.ExameRealizado;
import org.apache.struts.crud.entity.Funcionario;
import org.apache.struts.crud.service.ExameRealizadoService;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.RegraDeNegocioException;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as a controller to handle actions related to editing a ExameRealizado.
 * 
 * @author eduardo tanaka
 */
public class ExameRealizadoController extends ActionSupport {
    
    private static final long serialVersionUID = 1L;
	private ExameRealizado exameRealizado = new ExameRealizado();
    private ExameRealizadoService service = new ExameRealizadoService();
    private List<ExameRealizado> exameRealizadoList;
    private int cd_exame_novo;
    private String sm = "";
    private String em = "";
    private List<Funcionario> funcionarioList;
    private Funcionario funcionarioSelecionado;
    private List<Exame> exameList;
    private Exame exameSelecionado;


	
	@Override
	public String execute() throws Exception {
		this.setFuncionarioList(service.listarFuncionarios());
		this.setExameList(service.listarExames());
		return SUCCESS;
	}
	
	public String list() throws Exception {
		return SUCCESS;
	}
	
	public String listEreal() throws Exception {
		return SUCCESS;
	}
	
	public Integer getCd_exame_novo() {
		return cd_exame_novo;
	}
	public void setCd_exame_novo(Integer cd_exame_novo) {
		this.cd_exame_novo = cd_exame_novo;
	}
	
	public String listExameRealizados() throws Exception {
		this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
		this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
		this.exameRealizadoList = service.listarExameRealizado(exameRealizado);
		this.setFuncionarioList(service.listarFuncionarios());
		this.setExameList(service.listarExames());
		
		return SUCCESS;
	}

	public String insertExameRealizado() {
		this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
		this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
		this.exameRealizado.setDt_realizacao(new Date(System.currentTimeMillis()));

		boolean status;
		try {
			status = service.salvarExameRealizado(this.exameRealizado);
			if(status) {
				setSm("ExameRealizado salvo com sucesso");
				return SUCCESS;
			} else {
				setEm("ExameRealizado nao salvo");
				return INPUT;
			}
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("ExameRealizado nao salvo");
			return INPUT;
		}	
	}
	
	public String editExameRealizado() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_exame = Integer.parseInt(request.getParameter("cd_exame"));
		int cd_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));

		ExameRealizado exameRealizado;
		try {
			exameRealizado = service.buscarExameRealizado(cd_exame, cd_funcionario);
			if(exameRealizado != null) {
				this.exameRealizado = exameRealizado;
			}
			return SUCCESS;
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("Falha na conexao, tente novamente mais tarde.");
			return INPUT;		
		} catch (RegraDeNegocioException e) {
			e.printStackTrace();
			setEm("ExameRealizado nao encontrado");
			return INPUT;	
		}
	}
	
	
	public String updateExameRealizado() {
		this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
		this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
		this.exameRealizado.setDt_realizacao(new Date(System.currentTimeMillis()));
		this.cd_exame_novo = cd_exame_novo;
		
		boolean status;
		try {
			status = service.alterarExameRealizado(this.exameRealizado, this.cd_exame_novo);
			if(status) {
				setSm("ExameRealizado alterado com sucesso");
				return SUCCESS;
			} else {
				setEm("ExameRealizado nao alterado");
				return INPUT;
			}
		} catch (ConexaoFalhouException e) {
			e.printStackTrace();
			setEm("ExameRealizado nao salvo");
			return INPUT;	
		}		
	}
	
	public String deleteExameRealizado() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_exame = Integer.parseInt(request.getParameter("cd_exame"));
		int cd_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));

		boolean status;
		try {
			status = service.apagarExameRealizado(cd_exame, cd_funcionario);
			if(status) {
				setSm("ExameRealizado apagado com sucesso");
				this.exameRealizadoList = service.listarExameRealizado(new ExameRealizado());
				return SUCCESS;
			} else {
				setEm("ExameRealizado nao apagado");
				return INPUT;
			}		
		} catch (Exception e) {
			e.printStackTrace();
			setEm("ExameRealizado nao apagado");
			return INPUT;
		}
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
	public List<ExameRealizado> getExameRealizadoList() {
		return exameRealizadoList;
	}
	public void setExameRealizadoList(List<ExameRealizado> exameRealizadoList) {
		this.exameRealizadoList = exameRealizadoList;
	}
	public ExameRealizado getExameRealizado() {
		return exameRealizado;
	}
	public void setExameRealizado(ExameRealizado exameRealizado) {
		this.exameRealizado = exameRealizado;
	}
	
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}
	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
	public List<Exame> getExameList() {
		return exameList;
	}
	public void setExameList(List<Exame> exameList) {
		this.exameList = exameList;
	}
	public Exame getExameSelecionado() {
		return exameSelecionado;
	}
	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}
}

