package br.ufc.jornal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.jornal.dao.IClassificadoDAO;
import br.ufc.jornal.dao.INoticiaDAO;
import br.ufc.jornal.dao.ISecaoDAO;
import br.ufc.jornal.model.Classificado;
import br.ufc.jornal.model.Noticia;
import br.ufc.jornal.model.Secao;

@Controller
public class RootController {
	
	@Autowired
	private ISecaoDAO sDAO;
	
	@Autowired
	private INoticiaDAO nDAO;
	
	@Autowired
	private IClassificadoDAO cDAO;
	
	@RequestMapping("/")
	public String index(Model model){
		
		List<Secao> secoes = sDAO.findAll();
		model.addAttribute("secoes", secoes);
		
		List<Noticia> noticias = nDAO.findByHabilitadoTrue();
		model.addAttribute("noticias", noticias);
		
		List<Classificado> classificados = cDAO.findAll();
		model.addAttribute("classificados", classificados);
		
		return "../index";
	}
	
	@RequestMapping("/mais")
	public String mais(){
		return "mais";
	}
	
}
