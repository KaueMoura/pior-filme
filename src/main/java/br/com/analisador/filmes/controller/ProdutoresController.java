package br.com.analisador.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.analisador.filmes.entidades.dto.MinMaxIntervaloPremiacaoProdutorDto;
import br.com.analisador.filmes.negocio.services.ProdutorService;

/**
 * Classe controller relacionado a produtores de filmes.
 * 
 * @author Kauê Moura
 *
 */
@RestController
@RequestMapping("/produtores")
public class ProdutoresController {

	@Autowired
	ProdutorService produtorService;

	/**
	 * Metodo responsável por retornar os produtores que tiveram premiações.
	 * 
	 * @return
	 */
	@GetMapping("/premios")
	public MinMaxIntervaloPremiacaoProdutorDto getIntervalorPremios() {
		return produtorService.calcularIntervaloPremios();
	}
}
