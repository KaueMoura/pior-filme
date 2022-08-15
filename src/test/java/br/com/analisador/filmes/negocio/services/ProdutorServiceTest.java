package br.com.analisador.filmes.negocio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.analisador.filmes.boot.AnalisadorFilmesApplicationTests;
import br.com.analisador.filmes.entidades.Filme;
import br.com.analisador.filmes.entidades.Produtor;
import br.com.analisador.filmes.entidades.dto.MinMaxIntervaloPremiacaoProdutorDto;
import br.com.analisador.filmes.negocio.repository.ProdutorRepository;

@DisplayName("ProdutorServiceTest")
public class ProdutorServiceTest extends AnalisadorFilmesApplicationTests {
	
	@Autowired
	ProdutorService produtorService;

	@MockBean
	private ProdutorRepository produtorRepository;
	
	@Test
	@DisplayName("Deve retornar uma premiação com menor intervalo")
	public void deveRetornarPremiacaoComMenorIntervalo() {
		final List<Produtor> listaProdutores = criarListaProdutores();
		Mockito.when(produtorRepository.filmesDosProdutoresComDuasOuMaisPremiacoes())
				.thenReturn(listaProdutores);

		final MinMaxIntervaloPremiacaoProdutorDto calcularIntervaloPremios = produtorService.calcularIntervaloPremios();

		Mockito.verify(produtorRepository, Mockito.times(1)).filmesDosProdutoresComDuasOuMaisPremiacoes();
		Assertions.assertEquals(calcularIntervaloPremios.getMinPremiacoes().get(0).getNomeProdutor(), "Produtor2");
		Assertions.assertEquals(calcularIntervaloPremios.getMinPremiacoes().get(0).getAnoVitoriaAnterior(), 2021);
		Assertions.assertEquals(calcularIntervaloPremios.getMinPremiacoes().get(0).getAnoVitoriaSeguinte(), 2022);
		Assertions.assertEquals(calcularIntervaloPremios.getMinPremiacoes().get(0).getIntervalo(), 1);
	}

	@Test
	@DisplayName("Deve retornar uma premiação com maior intervalo")
	public void deveRetornarPremiacaoComMaiorIntervalo() {
		final List<Produtor> listaProdutores = criarListaProdutores();
		Mockito.when(produtorRepository.filmesDosProdutoresComDuasOuMaisPremiacoes()).thenReturn(listaProdutores);

		final MinMaxIntervaloPremiacaoProdutorDto calcularIntervaloPremios = produtorService.calcularIntervaloPremios();

		Mockito.verify(produtorRepository, Mockito.times(1)).filmesDosProdutoresComDuasOuMaisPremiacoes();
		Assertions.assertEquals(calcularIntervaloPremios.getMaxPremiacoes().get(0).getNomeProdutor(), "Produtor3");
		Assertions.assertEquals(calcularIntervaloPremios.getMaxPremiacoes().get(0).getAnoVitoriaAnterior(), 2010);
		Assertions.assertEquals(calcularIntervaloPremios.getMaxPremiacoes().get(0).getAnoVitoriaSeguinte(), 2019);
		Assertions.assertEquals(calcularIntervaloPremios.getMaxPremiacoes().get(0).getIntervalo(), 9);
	}

	@Test
	@DisplayName("Deve retornar 2 premiações com intervalo minimo iguais")
	public void deveRetornarPremiacaoComMesmoIntervalo() {
		// Cria lista padrão de produtores
		final List<Produtor> listaProdutores = criarListaProdutores();
		// Cria produtor que tem mesmo periodo de premiação que um outro já existente
		final Filme filme = Mockito.mock(Filme.class);
		Mockito.when(filme.getAno()).thenReturn(2022);
		Mockito.when(filme.isVencedor()).thenReturn(true);

		final Filme filme2 = Mockito.mock(Filme.class);
		Mockito.when(filme2.getAno()).thenReturn(2021);
		Mockito.when(filme2.isVencedor()).thenReturn(true);

		final Produtor produtorMesmoIntervalo = Mockito.mock(Produtor.class);
		Mockito.when(produtorMesmoIntervalo.getNome()).thenReturn("Produtor Mesmo Intervalo Minimo");

		final Set<Filme> listaFilmes = Set.of(filme, filme2);
		Mockito.when(produtorMesmoIntervalo.getFilmes()).thenReturn(listaFilmes);
		listaProdutores.add(produtorMesmoIntervalo);

		// Testes
		Mockito.when(produtorRepository.filmesDosProdutoresComDuasOuMaisPremiacoes()).thenReturn(listaProdutores);

		final MinMaxIntervaloPremiacaoProdutorDto calcularIntervaloPremios = produtorService.calcularIntervaloPremios();

		Mockito.verify(produtorRepository, Mockito.times(1)).filmesDosProdutoresComDuasOuMaisPremiacoes();
		Assertions.assertEquals(calcularIntervaloPremios.getMinPremiacoes().size(), 2);
		Assertions.assertEquals(calcularIntervaloPremios.getMinPremiacoes().get(0).getIntervalo(),
				calcularIntervaloPremios.getMinPremiacoes().get(1).getIntervalo());
	}

	@Test
	@DisplayName("Deve retornar 2 premiações com intervalo máximo iguais")
	public void deveRetornarPremiacaoComMesmoIntervaloMaximo() {
		// Cria lista padrão de produtores
		final List<Produtor> listaProdutores = criarListaProdutores();
		// Cria produtor que tem mesmo periodo de premiação que um outro já existente
		final Filme filme = Mockito.mock(Filme.class);
		Mockito.when(filme.getAno()).thenReturn(2019);
		Mockito.when(filme.isVencedor()).thenReturn(true);

		final Filme filme2 = Mockito.mock(Filme.class);
		Mockito.when(filme2.getAno()).thenReturn(2010);
		Mockito.when(filme2.isVencedor()).thenReturn(true);

		final Produtor produtorMesmoIntervalo = Mockito.mock(Produtor.class);
		Mockito.when(produtorMesmoIntervalo.getNome()).thenReturn("Produtor Mesmo Intervalo Máximo");

		final Set<Filme> listaFilmes = Set.of(filme, filme2);
		Mockito.when(produtorMesmoIntervalo.getFilmes()).thenReturn(listaFilmes);
		listaProdutores.add(produtorMesmoIntervalo);

		// Testes
		Mockito.when(produtorRepository.filmesDosProdutoresComDuasOuMaisPremiacoes()).thenReturn(listaProdutores);

		final MinMaxIntervaloPremiacaoProdutorDto calcularIntervaloPremios = produtorService.calcularIntervaloPremios();

		Mockito.verify(produtorRepository, Mockito.times(1)).filmesDosProdutoresComDuasOuMaisPremiacoes();
		Assertions.assertEquals(calcularIntervaloPremios.getMaxPremiacoes().size(), 2);
		Assertions.assertEquals(calcularIntervaloPremios.getMaxPremiacoes().get(0).getIntervalo(),
				calcularIntervaloPremios.getMaxPremiacoes().get(1).getIntervalo());
	}

	private List<Produtor> criarListaProdutores() {
		final List<Produtor> listaProdutores = new ArrayList<>();
		final Produtor produtor1 = Mockito.mock(Produtor.class);
		Mockito.when(produtor1.getNome()).thenReturn("Produtor1");
		final Set<Filme> listaFilmesProdutor1 = criarListaFilmesProdutor1();
		Mockito.when(produtor1.getFilmes()).thenReturn(listaFilmesProdutor1);

		final Produtor produtor2 = Mockito.mock(Produtor.class);
		Mockito.when(produtor2.getNome()).thenReturn("Produtor2");
		final Set<Filme> listaFilmesProdutor2 = criarListaFilmesProdutor2();
		Mockito.when(produtor2.getFilmes()).thenReturn(listaFilmesProdutor2);
		
		final Produtor produtor3 = Mockito.mock(Produtor.class);
		Mockito.when(produtor3.getNome()).thenReturn("Produtor3");
		final Set<Filme> listaFilmesProdutor3 = criarListaFilmesProdutor3();
		Mockito.when(produtor3.getFilmes()).thenReturn(listaFilmesProdutor3);

		listaProdutores.add(produtor1);
		listaProdutores.add(produtor2);
		listaProdutores.add(produtor3);
		
		return listaProdutores;
	}

	private Set<Filme> criarListaFilmesProdutor1() {

		final Filme filme = Mockito.mock(Filme.class);
		Mockito.when(filme.getAno()).thenReturn(2022);
		Mockito.when(filme.isVencedor()).thenReturn(true);
		
		final Filme filme2 = Mockito.mock(Filme.class);
		Mockito.when(filme2.getAno()).thenReturn(2020);
		Mockito.when(filme2.isVencedor()).thenReturn(true);
		
		
		return Set.of(filme, filme2);
	}

	private Set<Filme> criarListaFilmesProdutor2() {

		final Filme filme3 = Mockito.mock(Filme.class);
		Mockito.when(filme3.getAno()).thenReturn(2022);
		Mockito.when(filme3.isVencedor()).thenReturn(true);

		final Filme filme4 = Mockito.mock(Filme.class);
		Mockito.when(filme4.getAno()).thenReturn(2021);
		Mockito.when(filme4.isVencedor()).thenReturn(true);

		return Set.of(filme3, filme4);
	}

	private Set<Filme> criarListaFilmesProdutor3() {

		final Filme filme5 = Mockito.mock(Filme.class);
		Mockito.when(filme5.getAno()).thenReturn(2010);
		Mockito.when(filme5.isVencedor()).thenReturn(true);

		final Filme filme6 = Mockito.mock(Filme.class);
		Mockito.when(filme6.getAno()).thenReturn(2019);
		Mockito.when(filme6.isVencedor()).thenReturn(true);

		return Set.of(filme5, filme6);
	}
}
