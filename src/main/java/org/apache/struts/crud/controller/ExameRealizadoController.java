package org.apache.struts.crud.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.crud.dao.ExameDAO;
import org.apache.struts.crud.dao.ExameDAO;
import org.apache.struts.crud.dao.ExameRealizadoDAO;
import org.apache.struts.crud.dao.ExameRealizadoDAO;
import org.apache.struts.crud.dao.FuncionarioDAO;
import org.apache.struts.crud.entity.ExameRealizado;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as a controller to handle actions related to editing a ExameRealizado.
 * 
 * @author eduardo tanaka
 */
public class ExameRealizadoController extends ActionSupport {
    
    private ExameRealizado exameRealizado = new ExameRealizado();
    private ExameRealizadoDAO dao = new ExameRealizadoDaoImp();
   // private ExameDAO daoExame = new ExameDaoImpl();
    //private FuncionarioDAO daoFuncionario = new FuncionarioDAO();
    private List<ExameRealizado> exameRealizadoList;
    private int cd_exame_novo;
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
	public ExameRealizadoDAO getDao() {
		return dao;
	}
	public void setDao(ExameRealizadoDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws Exception {
		return SUCCESS;
	}
	
	public String listEreal() throws Exception {
		return SUCCESS;
	}
	
	public String listExameRealizados() throws Exception {
		this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
		this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
		this.exameRealizadoList = dao.getListExameRealizados(exameRealizado);
		for (ExameRealizado exameRealizado : this.exameRealizadoList) {
			//exameRealizado.setExame(daoExame.getExame(exameRealizado.getCd_exame()));
			//exameRealizado.setFuncionario(daoFuncionario.getFuncionario(exameRealizado.getCd_funcionario()));
		}
		return SUCCESS;
	}

	public String insertExameRealizado() {
		this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
		this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
		this.exameRealizado.setDt_realizacao(new Date());

		boolean status = dao.saveExameRealizado(this.exameRealizado);
		
		if(status) {
			setSm("ExameRealizado salvo com sucesso");
			return SUCCESS;
		} else {
			setEm("ExameRealizado nao salvo");
			return INPUT;
		}
	}
	
	public String editExameRealizado() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_exame = Integer.parseInt(request.getParameter("cd_exame"));
		int cd_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));

		ExameRealizado exameRealizado = dao.getExameRealizado(cd_exame, cd_funcionario);
		
		if(exameRealizado != null) {
			this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
			this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
			this.exameRealizado.setDt_realizacao(exameRealizado.getDt_realizacao());
			//this.exameRealizado.setExame(daoExame.getExame(exameRealizado.getCd_exame()));
			//this.exameRealizado.setFuncionario(daoFuncionario.getFuncionario(exameRealizado.getCd_funcionario()));

			return SUCCESS;
			
		}
		
		return INPUT;
		
	}
	
	public String updateExameRealizado() {
		this.exameRealizado.setCd_exame(exameRealizado.getCd_exame());
		this.exameRealizado.setCd_funcionario(exameRealizado.getCd_funcionario());
		this.exameRealizado.setDt_realizacao(new Date());
		this.cd_exame_novo = cd_exame_novo;
		
		boolean status = dao.updateExameRealizado(this.exameRealizado, cd_exame_novo);
		
		if(status) {
			setSm("ExameRealizado alterado com sucesso");
			return SUCCESS;
		} else {
			setEm("ExameRealizado nao alterado");
			return INPUT;
		}
	}
	
	public String deleteExameRealizado() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int cd_exame = Integer.parseInt(request.getParameter("cd_exame"));
		int cd_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));

		
		boolean status = dao.deleteExameRealizado(cd_exame, cd_funcionario);
		
		if(status) {
			setSm("ExameRealizado apagado com sucesso");
			this.exameRealizadoList = dao.getAllExameRealizados();
			return SUCCESS;
		} else {
			setEm("ExameRealizado nao apagado");
			return INPUT;
		}		
	}
	public int getCd_exame_novo() {
		return cd_exame_novo;
	}
	public void setCd_exame_novo(int cd_exame_novo) {
		this.cd_exame_novo = cd_exame_novo;
	}
}

