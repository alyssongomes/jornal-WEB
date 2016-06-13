package br.ufc.jornal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.jornal.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario,Long>{
	
	public List<Usuario> findByLoginAndSenha(String login, String senha);
	
	public List<Usuario> findByLogin(String login);
	
	public List<Usuario> findByAtivoTrue();
	
}
