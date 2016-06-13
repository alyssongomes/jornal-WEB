package br.ufc.jornal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
import br.ufc.jornal.util.FileUtil;

@Transactional
@Controller
public class EditorController {
	
	@Autowired
	private ISecaoDAO sDAO;
	
	@Autowired
	private IUsuarioDAO uDAO;
	
	@Autowired
	private IClassificadoDAO cDAO;
	
	@Autowired
	private IPapelDAO pDAO;
	
	@Autowired
	private INoticiaDAO nDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	//Carrega a tela de cadastros do Editor
	@RequestMapping("/cadastrosEditor")
	public String cadastrosEditor(Model model){
		List<Usuario> jornalistas = listarJornalistas();
		model.addAttribute("jornalistas", jornalistas);		
		return "visaoEditor";
	}
	
	//Inserir uma nova seção
	@RequestMapping("/inserirSecao")
	public String inserirSecao(Secao secao, BindingResult result){
		this.sDAO.save(secao);
		return "redirect:cadastrosEditor";
	}
	
	//Inserir um novo jornalista
	@RequestMapping("/inserirJornalista")
	public String inserirJornalista(Usuario usuario, BindingResult result, @RequestParam(value="imagem",required=false) MultipartFile imagem) throws IOException{
		if(imagem.getBytes().length != 0){
			FileUtil.salvarImagem(servletContext.getRealPath("/")+"resources/img/usuarios/"+usuario.getLogin()+".png", imagem);
			usuario.setPath(usuario.getLogin()+".png");
		}else{
			usuario.setPath("user.png");
		}
		
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		
		Collection<Papel> papeis = new ArrayList<Papel>();
		for (Papel papel : usuario.getPapeis()) {
			papeis.add(pDAO.findOne(papel.getId()));
		}
		usuario.setPapeis(papeis);
		usuario.setAtivo(true);
		
		this.uDAO.save(usuario);
		
		return "redirect:cadastrosEditor";
	}
	
	@RequestMapping("/removerJornalista")
	public String removerJornalista(Long id){
		Usuario usuario = this.uDAO.findOne(id);
		usuario.setAtivo(false);
		this.uDAO.save(usuario);
		return "redirect:cadastrosEditor";
	}
	
	
	//Inserir um novo classificado
	@RequestMapping("/inserirClassificado")
	public String inserirClassificado(Classificado classificado, BindingResult result, @RequestParam(value="imagem",required=false) MultipartFile imagem) throws IOException{
		if(imagem.getBytes().length != 0){
			FileUtil.salvarImagem(servletContext.getRealPath("/")+"resources/img/classificados/"+classificado.getTitulo()+".png", imagem);
			classificado.setPath(classificado.getTitulo()+".png");
		}else{
			classificado.setPath("user.png");
		}
		
		cDAO.save(classificado);
		return "redirect:cadastrosEditor";
	}
	

	@RequestMapping("/noticiasJornalista")
	public String noticiasJornalista(Long id_jornalista, Model model){
		List<Noticia> noticias = nDAO.findByUsuario(uDAO.findOne(id_jornalista));
		model.addAttribute("noticiasJornalista", noticias);
		
		List<Usuario> jornalistas = listarJornalistas();
		model.addAttribute("jornalistas", jornalistas);	
		
		return "visaoEditor";
	}
	
	
	@RequestMapping("/desabilitarNoticiaEditor")
	public String desabilitarNoticiaEditor(Long id){
		Noticia n = this.nDAO.findOne(id);
		n.setHabilitado(false);
		this.nDAO.save(n);
		return "redirect:cadastrosEditor";
	}
	
	
	private List<Usuario> listarJornalistas() {
		List<Usuario> usuarios = uDAO.findByAtivoTrue();
		List<Usuario> jornalistas = new ArrayList<Usuario>();
		
		for (Usuario usuario : usuarios) {
			if(this.contem(usuario, 3L)){
				jornalistas.add(usuario);
			}
		}
		
		return jornalistas;
	}
	
	private boolean contem(Usuario usuario, Long idPapel){
		for(Papel papel: usuario.getPapeis()){
			if(papel.getId() == idPapel){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
}
