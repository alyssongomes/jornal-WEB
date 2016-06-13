package br.ufc.jornal.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.jornal.dao.IClassificadoDAO;
import br.ufc.jornal.dao.IComentarioDAO;
import br.ufc.jornal.dao.INoticiaDAO;
import br.ufc.jornal.dao.IPapelDAO;
import br.ufc.jornal.dao.ISecaoDAO;
import br.ufc.jornal.dao.IUsuarioDAO;
import br.ufc.jornal.model.Classificado;
import br.ufc.jornal.model.Comentario;
import br.ufc.jornal.model.Noticia;
import br.ufc.jornal.model.Papel;
import br.ufc.jornal.model.Secao;
import br.ufc.jornal.model.Usuario;
import br.ufc.jornal.util.FileUtil;

@Controller
public class NoticiaController {
	
	@Autowired
	private IUsuarioDAO uDAO;
	
	@Autowired
	private ISecaoDAO sDAO;
	
	@Autowired
	private INoticiaDAO nDAO;
	
	@Autowired
	private IClassificadoDAO cDAO;
	
	@Autowired
	private IComentarioDAO coDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	//Carrega a tela de gerenciamento de notícias
	@RequestMapping("/gerenciarNoticias")
	public String gerenciarNoticias(Model model, HttpSession session){
		List<Secao> secoes = sDAO.findAll();
		model.addAttribute("secoes", secoes);
		
		List<Noticia> noticias = (List<Noticia>) uDAO.findOne(((Usuario)session.getAttribute("usuario_logado")).getId()).getNoticias();
		model.addAttribute("noticias", noticias);
		return "gerenciarNoticias";
	}
	
	//Inserir uma nova Notícia
	@RequestMapping("/inserirNovaNoticia")
	public String inserirNovaNoticia(Noticia noticia, BindingResult result, @RequestParam(value="imagem",required=false) MultipartFile imagem, HttpSession session) throws IOException{
		
		if(imagem.getBytes().length != 0){
			FileUtil.salvarImagem(servletContext.getRealPath("/")+"resources/img/noticias/"+noticia.getTitulo()+".png", imagem);
			noticia.setPath(noticia.getTitulo()+".png");
		}else{
			noticia.setPath("news.png");
		}
		
		Usuario u = uDAO.findOne(((Usuario)session.getAttribute("usuario_logado")).getId());
		noticia.setUsuario(u);
		noticia.setId_autor(Integer.parseInt(u.getId().toString()));
		
		Date d = new Date();
		Calendar ca = Calendar.getInstance();
		ca.set(d.getYear(), d.getMonth(), d.getDate());
		
		noticia.setData_noticia(ca);
		
		noticia.setSecao(sDAO.findOne(Long.parseLong(noticia.getId_secao().toString())));
		
		noticia.setHabilitado(true);
		
		this.nDAO.save(noticia);
		
		return "redirect:gerenciarNoticias";
	}
	
	//Remover uma notícia
	@RequestMapping("/desabilitarNoticia")
	public String desabilitarNoticia(Long id){
		Noticia n = this.nDAO.findOne(id);
		n.setHabilitado(false);
		this.nDAO.save(n);
		return "redirect:gerenciarNoticias";
	}
	
	//Listar notícias de uma determinada secão para tela principal
	@RequestMapping("/listarPorSecao")
	public String listarPorSecao(Long id_secao, Model model){
		
		List<Secao> secoes = sDAO.findAll();
		model.addAttribute("secoes", secoes);
		
		List<Noticia> noticias = null;
		if(id_secao == -1){
			noticias = nDAO.findByHabilitadoTrue();
		}else{
			Secao s = sDAO.findOne(id_secao);
			noticias = nDAO.findBySecaoAndHabilitadoTrue(s);
		}
		model.addAttribute("noticias", noticias);
		
		List<Classificado> classificados = cDAO.findAll();
		model.addAttribute("classificados", classificados);
		return "main";
	}
	
	//Listar notícias de uma determinada secão para o index
	@RequestMapping("/listarPorSecaoIndex")
	public String listarPorSecaoIndex(Long id_secao, Model model){
			
			List<Secao> secoes = sDAO.findAll();
			model.addAttribute("secoes", secoes);
			
			List<Noticia> noticias = null;
			if(id_secao == -1){
				noticias = nDAO.findByHabilitadoTrue();
			}else{
				Secao s = sDAO.findOne(id_secao);
				noticias = nDAO.findBySecaoAndHabilitadoTrue(s);
			}
			model.addAttribute("noticias", noticias);
			
			List<Classificado> classificados = cDAO.findAll();
			model.addAttribute("classificados", classificados);
			return "../index";
		}
	
	@RequestMapping("/visualizarNoticia")
	public String visualizarNoticia(Long id, Model model){
		Noticia noticia = nDAO.findOne(id);
		model.addAttribute("noticia", noticia);
		return "visualizarNoticia";
	}
	
	@RequestMapping("/adicionarComentario")
	@ResponseBody
	public String adicionarComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Date d = new Date();
		Calendar ca = Calendar.getInstance();
		ca.set(d.getYear(), d.getMonth(), d.getDate());
		
		Comentario comentario = new Comentario();
		comentario.setId_autor(Long.parseLong(request.getParameter("autor")));
		comentario.setId_noticia(Long.parseLong(request.getParameter("noticia")));
		comentario.setTexto(request.getParameter("texto"));
		comentario.setUsuario(uDAO.findOne(Long.parseLong(request.getParameter("autor"))));
		comentario.setNoticia(nDAO.findOne(Long.parseLong(request.getParameter("noticia"))));
		comentario.setData(ca);
		
		if(coDAO.save(comentario) != null){
			String com = "{"+
					"\"autor\":\""+comentario.getUsuario().getNome()+"\","+
					"\"noticia\":\""+comentario.getId_noticia()+"\","+
					"\"texto\":\""+comentario.getTexto()+"\","+
					"\"data\":"+d.getTime()+"}";
			comentario = null;
			return com;
		}
		comentario = null;
		return "";

	}
	
	@RequestMapping("/verificarTitulo")
	@ResponseBody
	public boolean verificarTitulo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(nDAO.findByTitulo(request.getParameter("titulo")).size() > 0){
			return true;
		}
		return false;
	}
	
}
