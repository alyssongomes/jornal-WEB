package br.ufc.jornal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.jornal.model.Noticia;
import br.ufc.jornal.model.Secao;
import br.ufc.jornal.model.Usuario;

public interface INoticiaDAO extends JpaRepository<Noticia, Long> {
	
	public List<Noticia> findBySecaoAndHabilitadoTrue(Secao secao);
	
	public List<Noticia> findByHabilitadoTrue();
	
	public List<Noticia> findByUsuario(Usuario usuario);
	
	public List<Noticia> findByTitulo(String titulo);

}
