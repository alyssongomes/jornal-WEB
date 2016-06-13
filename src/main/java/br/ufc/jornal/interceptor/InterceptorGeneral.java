package br.ufc.jornal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.ufc.jornal.model.Usuario;

@Component
public class InterceptorGeneral extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_logado");
		int papel = 0;
		
		if(request.getSession().getAttribute("papel") != null){
			papel = (int) request.getSession().getAttribute("papel");
		}
		
		
		if(uri.endsWith("/") ||  uri.endsWith("cadastrarUsuario") || uri.endsWith("inserirNovoUsuario") || uri.endsWith("telaLogin") || 
			uri.endsWith("login") || uri.endsWith("mais") || uri.endsWith("listarPorSecaoIndex") || 
			uri.contains("visualizarNoticia") || uri.endsWith("verificarLogin")){
			return true;
		}
		
		if(usuario != null){
			
			if(uri.endsWith("/") ||  uri.endsWith("cadastrarUsuario") || uri.endsWith("login") || 
				uri.endsWith("logout") || uri.endsWith("mudarPapel") || uri.endsWith("adicionarMelhorOferta") || 
				uri.endsWith("verificarLogin") || uri.endsWith("listarPorSecao") ||
				uri.endsWith("listarPorSecao") || uri.endsWith("adicionarComentario") ||
				uri.endsWith("verificarTitulo")){
				return true;
			}
			
			if(papel == 1){
				if(uri.endsWith("visualizarNoticia")){
					return true;
				}
			}else if(papel == 2){
				if(uri.endsWith("inserirSecao") || uri.endsWith("inserirJornalista") || 
						uri.endsWith("removerJornalista") || uri.endsWith("inserirClassificado") ||  uri.endsWith("noticiasJornalista") || 
						uri.endsWith("desabilitarNoticia") || uri.endsWith("desabilitarNoticiaEditor")){
					return true;
				}
				
				if(uri.endsWith("cadastrosEditor")){
					return true;
				}
			}else{
				if(uri.endsWith("gerenciarNoticias") || uri.endsWith("desabilitarNoticia") || uri.endsWith("inserirNovaNoticia")){
					return true;
				}
			}
			response.sendRedirect("/login");
			return false;
		}
		response.sendRedirect("/");
		return false;
	}
	
}
