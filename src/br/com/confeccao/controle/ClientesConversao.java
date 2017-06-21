package br.com.confeccao.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.confeccao.dao.PessoaDAO;
import br.com.confeccao.model.Pessoa;

@FacesConverter("clientesConversao")
public class ClientesConversao implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try{
			Long codigo = Long.parseLong(arg2);
			PessoaDAO daoPessoa = new PessoaDAO();
			Pessoa pessoa = daoPessoa.buscaPorCodigo(codigo);
			return pessoa;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try{
			Pessoa pessoa = (Pessoa) arg2;
			Long codigo = pessoa.getCodigo();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
