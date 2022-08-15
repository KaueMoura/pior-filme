package br.com.analisador.filmes.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Classe que representa a entidade Filme.
 *
 * @author Kauê Moura
 *
 */
@Entity
@Table(name = "filmes")
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer ano;

	private String titulo;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "estudios_filmes", 
			joinColumns = { @JoinColumn(name = "filmeId") },
			inverseJoinColumns = { @JoinColumn(name = "estudioId") })
	private Set<Estudio> estudios;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "produtores_filmes", 
			joinColumns = { @JoinColumn(name = "filmeId") }, 
			inverseJoinColumns = { @JoinColumn(name = "produtorId") })
	private Set<Produtor> produtores;

	private boolean vencedor;

	public Integer getAno() {
		return ano;
	}

	public void setAno(final Integer ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public boolean isVencedor() {
		return vencedor;
	}

	public void setVencedor(final boolean vencedor) {
		this.vencedor = vencedor;
	}

	public Set<Estudio> getEstudios() {
		if (this.estudios == null) {
			this.estudios = new HashSet<>();
		}
		return estudios;
	}

	public void setEstudios(final Set<Estudio> estudios) {
		this.estudios = estudios;
	}

	public Set<Produtor> getProdutores() {
		if (this.produtores == null) {
			this.produtores = new HashSet<>();
		}
		return produtores;
	}

	public void setProdutores(final Set<Produtor> produtores) {
		this.produtores = produtores;
	}

	public Long getId() {
		return id;
	}

}
