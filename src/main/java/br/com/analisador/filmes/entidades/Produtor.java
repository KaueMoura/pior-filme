package br.com.analisador.filmes.entidades;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Classe que representa entidade Produtor.
 * 
 * @author Kauê Moura
 *
 */
@Entity
@Table(name = "produtores")
public class Produtor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	@ManyToMany
	@JoinTable(name = "produtores_filmes", 
			joinColumns = { @JoinColumn(name = "produtorId") }, 
			inverseJoinColumns = { @JoinColumn(name = "filmeId") })
	private Set<Filme> filmes;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public Set<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(final Set<Filme> filmes) {
		this.filmes = filmes;
	}

}
