package br.ufc.jornal.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="CLASSIFICADO")
public class Classificado {
	
	 @Id
	 @Column(name="ID_CLASSIFICADO", nullable=false)
	 @SequenceGenerator(name="generator_classificado", sequenceName="sequencia_classificado")
	 @GeneratedValue(generator="generator_classificado")
	 private Long id;
	 @Column(name="TITULO", nullable=false)
     private String titulo;
	 @Column(name="TEXTO", nullable=false)
     private String texto;
	 @Column(name="PRECO", nullable=false)
     private Float preco;//preço inicial
	 @Column(name="TELEFONE", nullable=false)
     private String telefone;
	 @Column(name="MELHOR_OFERTA")
     private Float melhor_oferta;
	 @Column(name="DATA_OFERTA")
     private Calendar data_oferta;
	 @Column(name="PATH")
     private String path;
	 @Column(name="ID_AUTOR",nullable=true,insertable=false, updatable=false)
     private Long id_autor;
	 
	 @ManyToOne(optional=true)
	 @JoinColumn(name="ID_AUTOR", referencedColumnName="ID_USUARIO", nullable=true)
	 private Usuario usuario;
	 
	 public Classificado(){}
	 
	 public Classificado(String titulo, String texto, Float preco,
			String telefone, Float melhor_oferta, Calendar data_oferta,
			String path) {
		this.titulo = titulo;
		this.texto = texto;
		this.preco = preco;
		this.telefone = telefone;
		this.melhor_oferta = melhor_oferta;
		this.data_oferta = data_oferta;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Float getMelhor_oferta() {
		return melhor_oferta;
	}

	public void setMelhor_oferta(Float melhor_oferta) {
		this.melhor_oferta = melhor_oferta;
	}

	public Calendar getData_oferta() {
		return data_oferta;
	}

	public void setData_oferta(Calendar data_oferta) {
		this.data_oferta = data_oferta;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getId_autor() {
		return id_autor;
	}

	public void setId_autor(Long id_autor) {
		this.id_autor = id_autor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	 
}
