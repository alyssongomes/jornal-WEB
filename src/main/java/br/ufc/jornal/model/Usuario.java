package br.ufc.jornal.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;

import org.apache.catalina.Store;


@Entity(name="USUARIO")
public class Usuario {
	
	@Id
	@Column(name="ID_USUARIO", nullable=false)
	@SequenceGenerator(name="generator_usuario", sequenceName="sequencia_usuario")
	@GeneratedValue(generator="generator_usuario")
	private Long id;
	
	@Column(name="LOGIN", nullable=false)
	private String login;
	@Column(name="SENHA", nullable=false)
	private String senha;
	@Column(name="NOME", nullable=false)
	private String nome;
	@Column(name="EMAIL", nullable=false)
	private String email;
	@Column(name="PATH", nullable=false)
	private String path;
	@Column(name="ATIVO")
	private Boolean ativo;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIO_PAPEL",
				joinColumns= @JoinColumn(name="ID_USUARIO", referencedColumnName="ID_USUARIO"),
				inverseJoinColumns=@JoinColumn(name="ID_PAPEL", referencedColumnName="ID_PAPEL")
	)
	private Collection<Papel> papeis;
	
	@OneToMany(mappedBy="usuario", 
			targetEntity=Noticia.class,
			fetch=FetchType.EAGER,
			cascade=CascadeType.ALL
	)
	private Collection<Noticia> noticias;
	
	@OneToMany(mappedBy="usuario", 
			targetEntity=Classificado.class,
			fetch=FetchType.LAZY)
	private Collection<Classificado> classificados;
	
	@OneToMany(mappedBy="usuario", 
			targetEntity=Comentario.class,
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL
			)
	private Collection<Comentario> comentarios;
	
	public Usuario(){}
	
	public Usuario(String login, String senha, String nome, String email, String path) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Collection<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Collection<Papel> papeis) {
		this.papeis = papeis;
	}

	public Collection<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(Collection<Noticia> noticias) {
		this.noticias = noticias;
	}

	public Collection<Classificado> getClassificados() {
		return classificados;
	}

	public void setClassificados(Collection<Classificado> classificados) {
		this.classificados = classificados;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@PreRemove
    public void preRemove(){
        for(Classificado c : classificados){
            c.setUsuario(null);
        }
    }
	
}
