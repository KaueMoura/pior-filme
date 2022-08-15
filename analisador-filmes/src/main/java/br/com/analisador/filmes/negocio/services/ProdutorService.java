package br.com.analisador.filmes.negocio.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.analisador.filmes.entidades.Filme;
import br.com.analisador.filmes.entidades.Produtor;
import br.com.analisador.filmes.entidades.dto.IntervaloPremiacaoProdutorDto;
import br.com.analisador.filmes.entidades.dto.MinMaxIntervaloPremiacaoProdutorDto;
import br.com.analisador.filmes.negocio.repository.ProdutorRepository;

/**
 * Classe negocial relacionado a produtores de filmes.
 * 
 * @author Kauê Moura
 *
 */
@Service
public class ProdutorService {

	private final ProdutorRepository produtorRepository;

	public ProdutorService(final ProdutorRepository produtorRepository) {
		super();
		this.produtorRepository = produtorRepository;
	}

	/**
	 * Retorna lista com os produtores com maior e menor intervalo de premiações.
	 * 
	 * @return MinMaxIntervaloPremiacaoProdutorDto
	 */
	public MinMaxIntervaloPremiacaoProdutorDto calcularIntervaloPremios() {
		final List<Produtor> listaProdutores = produtorRepository.filmesDosProdutoresComDuasOuMaisPremiacoes();
		
		final MinMaxIntervaloPremiacaoProdutorDto intervaloPremiacaoProdutorDto = new MinMaxIntervaloPremiacaoProdutorDto();
		int menorIntervalo = 9999;
		int maiorIntervalo = 0;
		for (final Produtor produtor : listaProdutores) {
			final ArrayList<Filme> listaFilmes = new ArrayList<>(produtor.getFilmes());

			// Filtramos os filmes vencedores de premiações do produtor.
			final List<Filme> filmesVencedores = listaFilmes.stream().filter(filme -> {
				return filme.isVencedor();
			}).collect(Collectors.toList());

			// Ordena os filmes do ano mais atual ao mais antigo
			Collections.sort(filmesVencedores, (filme1, filme2) -> {
				return filme2.getAno().compareTo(filme1.getAno());
			});

			// Percorre lista de filmes para calcular o intervalo entre as premiações.
			for (int i = 0; i < filmesVencedores.size() - 1; i++) {

				final Filme filmeMaisNovo = filmesVencedores.get(i);
				// O proximo filme da lista sempre é mais antigo pois ordenamos acima.
				final Filme filmeMaisAntigo = filmesVencedores.get(i + 1);
				
				final int intervalo = filmeMaisNovo.getAno() - filmeMaisAntigo.getAno();
				
				if (intervalo < menorIntervalo) {
					intervaloPremiacaoProdutorDto.getMinPremiacoes().clear();
					menorIntervalo = intervalo;
				}

				// Se menor intervalo é igual ao que já encontramos ele entra na lista de
				// premiações
				if (intervalo == menorIntervalo) {
					intervaloPremiacaoProdutorDto.getMinPremiacoes()
							.add(criarPremiacao(produtor, filmeMaisNovo, filmeMaisAntigo, intervalo));
				}

				if (intervalo > maiorIntervalo) {
					intervaloPremiacaoProdutorDto.getMaxPremiacoes().clear();
					maiorIntervalo = intervalo;
				}

				// Se maior intervalo é igual ao que já encontramos ele entra na lista de
				// premiações
				if (intervalo == maiorIntervalo) {
					intervaloPremiacaoProdutorDto.getMaxPremiacoes()
							.add(criarPremiacao(produtor, filmeMaisNovo, filmeMaisAntigo, intervalo));
				}
			}

		}

		return intervaloPremiacaoProdutorDto;
	}

	/**
	 * Cria objeto IntervaloPremiacaoProdutorDto com os dados informados.
	 * 
	 * @param produtor
	 * @param filmeMaisNovo
	 * @param filmeMaisAntigo
	 * @param intervalo
	 * @return
	 */
	private IntervaloPremiacaoProdutorDto criarPremiacao(final Produtor produtor, final Filme filmeMaisNovo,
			final Filme filmeMaisAntigo, final int intervalo) {
		IntervaloPremiacaoProdutorDto intervaloPremiacaoDto;
		intervaloPremiacaoDto = new IntervaloPremiacaoProdutorDto();
		intervaloPremiacaoDto.setNomeProdutor(produtor.getNome());
		intervaloPremiacaoDto.setIntervalo(intervalo);
		intervaloPremiacaoDto.setAnoVitoriaAnterior(filmeMaisAntigo.getAno());
		intervaloPremiacaoDto.setAnoVitoriaSeguinte(filmeMaisNovo.getAno());
		return intervaloPremiacaoDto;
	}

}
