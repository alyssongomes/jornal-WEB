package br.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.jornal.model.Comentario;

public interface IComentarioDAO extends JpaRepository<Comentario,Long>{}
