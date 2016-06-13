package br.ufc.jornal.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="PAPEL")
public class Papel {
	
	@Id
	@Column(name="ID_PAPEL", nullable=false)
	@SequenceGenerator(name="generator_papel", sequenceName="sequencia_papel")
	@GeneratedValue(generator="generator_papel")
	private Long id;
	@Column(name="PAPEL", nullable=false)
	private String papel;
	
	@ManyToMany(mappedBy="papeis", fetch=FetchType.LAZY)
	private Collection<Usuario> usuarios;
	
	public Papel(){}
	
	public Papel(String papel) {
		this.papel = papel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

	
}
