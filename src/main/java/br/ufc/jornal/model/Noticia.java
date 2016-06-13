package br.ufc.jornal.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="NOTICIA")
public class Noticia {
	
	@Id
	@Column(name="ID_NOTICIA", nullable=false)
	@SequenceGenerator(name="generator_noticia", sequenceName="sequencia_noticia")
	@GeneratedValue(generator="generator_noticia")
	private Long id;
	@Column(name="TITULO", nullable=false)
    private String titulo;
	@Column(name="SUBTITULO", nullable=false)
    private String subtitulo;
	@Column(name="TEXTO", nullable=false,columnDefinition="TEXT")
    private String texto;
	@Column(name="ID_AUTOR", nullable=false,insertable=false, updatable=false)
    private Integer id_autor;
    @Column(name="DATA_NOTICIA",nullable=false)
	@Temporal(TemporalType.DATE)
    private Calendar data_noticia;
    @Column(name="PATH")
    private String path;
    @Column(name="ID_SECAO", nullable=false,insertable=false, updatable=false)
    private Integer id_secao;
    @Column(name="HABILITADO")
    private Boolean habilitado;
    
    @ManyToOne(optional=false, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ID_AUTOR", referencedColumnName="ID_USUARIO")
    @Fetch(FetchMode.JOIN)
    private Usuario usuario;
    
    @OneToMany(mappedBy="noticia", 
			targetEntity=Comentario.class,
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	private Collection<Comentario> comentarios;
    
    @ManyToOne(optional=false)
	@JoinColumn(name="ID_SECAO", referencedColumnName="ID_SECAO")
    private Secao secao;
	
    public Noticia(){}
    
    public Noticia(String titulo, String subtitulo, String texto,
			Calendar data_noticia, String path) {
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.texto = texto;
		this.data_noticia = data_noticia;
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

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getId_autor() {
		return id_autor;
	}

	public void setId_autor(Integer id_autor) {
		this.id_autor = id_autor;
	}

	public Calendar getData_noticia() {
		return data_noticia;
	}

	public void setData_noticia(Calendar data_noticia) {
		this.data_noticia = data_noticia;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getId_secao() {
		return id_secao;
	}

	public void setId_secao(Integer id_secao) {
		this.id_secao = id_secao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Noticia))
			return false;
		Noticia n = (Noticia)obj;
		if(n.getId() == this.getId())
			return true;
		return false;
	}
    
    
    
    
}
