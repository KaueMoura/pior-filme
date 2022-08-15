package br.com.analisador.filmes.negocio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.analisador.filmes.entidades.Filme;
import br.com.analisador.filmes.negocio.repository.FilmeRepository;

/**
 * Classe negocial referente a filmes.
 * 
 * @author Kauê Moura
 *
 */
@Service
public class FilmeService {

	private final FilmeRepository filmeRepository;
	
	public FilmeService(final FilmeRepository filmeRepository) {
		this.filmeRepository = filmeRepository;
	}

	/**
	 * Método responsável por salvar a lista de filmes informado.
	 * 
	 * @param pFilmes
	 */
	public void salvar(final List<Filme> pFilmes) {
		this.filmeRepository.saveAll(pFilmes);
	}

}
