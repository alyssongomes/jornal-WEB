package br.ufc.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.jornal.dao.IClassificadoDAO;
import br.ufc.jornal.dao.INoticiaDAO;
import br.ufc.jornal.dao.IPapelDAO;
import br.ufc.jornal.dao.ISecaoDAO;
import br.ufc.jornal.dao.IUsuarioDAO;
import br.ufc.jornal.model.Classificado;
import br.ufc.jornal.model.Noticia;
import br.ufc.jornal.model.Papel;
import br.ufc.jornal.model.Secao;
import br.ufc.jornal.model.Usuario;
import br.ufc.jornal.util.Criptografia;

@Transactional
@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioDAO uDAO;
	
	@Autowired
	private IPapelDAO pDAO;
	
	@Autowired
	private ISecaoDAO sDAO;
	
	@Autowired
	private INoticiaDAO nDAO;
	
	@Autowired
	private IClassificadoDAO cDAO;
	
	//Carrega a tela de login
	@RequestMapping("/telaLogin")
	public String telaLogin(Model model){
		List<Papel> papeis = pDAO.findAll();
		model.addAttribute("papeis", papeis);
		return "login";
	}
	
	//Método de Logar
	@RequestMapping("/login")
	public String login(Usuario usuario, Long papel, BindingResult result, HttpSession session, Model model){
		
		List<Secao> secoes = sDAO.findAll();
		model.addAttribute("secoes", secoes);
		
		List<Noticia> noticias = nDAO.findByHabilitadoTrue();
		model.addAttribute("noticias", noticias);
		
		List<Classificado> classificados = cDAO.findAll();
		model.addAttribute("classificados", classificados);
		
		if(session.getAttribute("usuario_logado") != null){
			return "main";
		}
		
		List<Usuario> u = uDAO.findByLoginAndSenha(usuario.getLogin(), Criptografia.criptografar(usuario.getSenha()));
		if(u.size() != 0){
			session.setAttribute("usuario_logado",u.get(0));
			session.setAttribute("papel",1);
			return "main";
		}
		return "redirect:telaLogin";
	}
	
	//Método de Logout
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:telaLogin";
	}
	
}
