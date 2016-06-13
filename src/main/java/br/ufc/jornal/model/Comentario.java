package br.ufc.jornal.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="COMENTARIO")
public class Comentario {
	
	@Id
	@Column(name="ID_COMENTARIO", nullable=false)
	@SequenceGenerator(name="generator_comentario", sequenceName="sequencia_comentario")
	@GeneratedValue(generator="generator_comentario")
	private Long id;
	@Column(name="ID_NOTICIA", nullable=false, insertable=false, updatable=false)
    private Long id_noticia;
	@Column(name="ID_AUTOR", nullable=false, insertable=false, updatable=false)
    private Long id_autor;
	@Column(name="TEXTO", nullable=false)
    private String texto;
	@Column(name="DATA",nullable=false)
	private Calendar data;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_AUTOR", referencedColumnName="ID_USUARIO")
    private Usuario usuario;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_NOTICIA", referencedColumnName="ID_NOTICIA")
    private Noticia noticia;
	
	public Comentario(){}
	
	public Comentario(String texto) {
		this.texto = texto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(Long id_noticia) {
		this.id_noticia = id_noticia;
	}

	public Long getId_autor() {
		return id_autor;
	}

	public void setId_autor(Long id_autor) {
		this.id_autor = id_autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	
	
}
