package br.ufc.jornal.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="SECAO")
public class Secao {
	
	@Id
	@Column(name="ID_SECAO", nullable=false)
	@SequenceGenerator(name="generator_secao", sequenceName="sequencia_secao")
	@GeneratedValue(generator="generator_secao")
	private Long id;
	
	@Column(name="TITULO", nullable=false)
	private String titulo;
	
	@Column(name="DESCRICAO", nullable=false )
	private String descricao;
	
	@OneToMany(mappedBy="secao", 
			targetEntity=Noticia.class,
			fetch=FetchType.EAGER,
			cascade=CascadeType.ALL)
	private Collection<Noticia> noticias;
	
	public Secao(){}
	
	public Secao(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(Collection<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
	
}
