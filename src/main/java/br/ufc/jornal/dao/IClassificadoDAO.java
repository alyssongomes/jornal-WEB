package br.ufc.jornal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.jornal.model.Classificado;

public interface IClassificadoDAO extends JpaRepository<Classificado, Long> {}
