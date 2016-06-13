package br.ufc.jornal.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.jornal.dao.IClassificadoDAO;
import br.ufc.jornal.dao.IUsuarioDAO;
import br.ufc.jornal.model.Classificado;
import br.ufc.jornal.model.Usuario;
import br.ufc.jornal.util.Criptografia;
import br.ufc.jornal.util.FileUtil;

@Transactional
@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioDAO uDAO;
	
	@Autowired
	private IClassificadoDAO cDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	//Carrega a tela de cadastrar Usuário
	@RequestMapping("/cadastrarUsuario")
	public String cadastrarUsuario(){
		return "cadastroUsuario";
	}
	
	//Inserir um novo Usuario
	@RequestMapping("/inserirNovoUsuario")
	public String inserirNovoUsuario(Usuario usuario, BindingResult result, @RequestParam(value="imagem",required=false) MultipartFile imagem) throws IOException{
		if(imagem.getBytes().length != 0){
			FileUtil.salvarImagem(servletContext.getRealPath("/")+"resources/img/usuarios/"+usuario.getLogin()+".png", imagem);
			usuario.setPath(usuario.getLogin()+".png");
		}else{
			usuario.setPath("user.png");
		}
		
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		usuario.setAtivo(true);
		
		this.uDAO.save(usuario);
		
		return "login";
	}
	
	@RequestMapping("/mudarPapel")
	@ResponseBody
	public String mudarPapel(HttpServletRequest request, HttpSession session){
		session.setAttribute("papel",Integer.parseInt(request.getParameter("papel")));
		String papel = "";
		switch (request.getParameter("papel")) {
		case "1": papel += "LEITOR"; break;
		case "2": papel += "EDITOR"; break;
		case "3": papel += "JORNALISTA"; break;
		}
		return "Você está logado como "+papel;
	}
	
	@RequestMapping("/adicionarMelhorOferta")
	@ResponseBody
	public String adicionarMelhorOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Classificado classificado = cDAO.findOne(Long.parseLong(request.getParameter("classificado")));
		Float valor = Float.parseFloat(request.getParameter("valor"));
		Date d = new Date();
		Calendar ca = Calendar.getInstance();
		ca.set(d.getYear(), d.getMonth(), d.getDate());
		
		String result = "";
		
		if(valor > classificado.getPreco()){
			if(classificado.getUsuario() != null){
				if(valor > classificado.getMelhor_oferta()){
					classificado.setUsuario(uDAO.findOne(Long.parseLong(request.getParameter("usuario"))));
					classificado.setMelhor_oferta(valor);
					classificado.setData_oferta(ca);
					cDAO.save(classificado);
					result = "{\"resultado\":true,"
							+ "\"usuario\":\""+classificado.getUsuario().getNome()+"\","
							+ "\"valor\":\""+valor+"\","
							+ "\"classificado\":\""+classificado.getId()+"\","
							+ "\"mensagem\":\"Você adicionou uma oferta melhor!\"}";
					classificado = null;
					return result;
				}
			}
			System.out.println(request.getParameter("classificado"));
			System.out.println(request.getParameter("valor"));
			System.out.println(request.getParameter("usuario"));
			classificado.setUsuario(uDAO.findOne(Long.parseLong(request.getParameter("usuario"))));
			classificado.setMelhor_oferta(valor);
			classificado.setData_oferta(ca);
			cDAO.save(classificado);
			result = "{\"resultado\":true,"
					+ "\"usuario\":\""+classificado.getUsuario().getNome()+"\","
					+ "\"valor\":\""+valor+"\","
					+ "\"classificado\":\""+classificado.getId()+"\","
					+ "\"mensagem\":\"Você adicionou uma oferta melhor!\"}";
			classificado = null;
			return result;
		}
		return "{\"resultado\":false,\"mensagem\":\"Sua oferta não é a melhor!\"}";
	}
	
	@RequestMapping("/verificarLogin")
	@ResponseBody
	public boolean verificarLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(uDAO.findByLogin(request.getParameter("login")).size() > 0){
			return true;
		}
		return false;
	}
	
}
