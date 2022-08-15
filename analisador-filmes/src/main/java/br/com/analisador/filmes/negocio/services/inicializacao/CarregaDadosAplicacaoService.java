package br.com.analisador.filmes.negocio.services.inicializacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.analisador.filmes.entidades.Estudio;
import br.com.analisador.filmes.entidades.Filme;
import br.com.analisador.filmes.entidades.Produtor;
import br.com.analisador.filmes.negocio.services.FilmeService;
import br.com.analisador.filmes.negocio.services.csv.LeitorCsv;

/**
 * Classe com métodos responsáveis por carregar os dados da aplicação quando ela
 * é iniciada.
 * 
 * @author Kauê Moura
 *
 */
@Service
public class CarregaDadosAplicacaoService {
	private static final Logger LOG = LoggerFactory.getLogger(CarregaDadosAplicacaoService.class);

	private final LeitorCsv leitorCsv;

	private final FilmeService filmeService;

	public CarregaDadosAplicacaoService(final LeitorCsv leitorCsv, final FilmeService filmeService) {
		super();
		this.leitorCsv = leitorCsv;
		this.filmeService = filmeService;
	}

	/**
	 * Método responsável por pegar os dados do arquivo csv e salvar no banco de
	 * dados.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void carregarDadosAplicacao(final String... args) throws Exception {
		LOG.info("Carregando dados da aplicação...");
		// Carregando os dados do arquivo csv.
		leitorCsv.carregarValores();

		final List<Filme> listaFilmes = new ArrayList<>();
		final List<Produtor> listaProdutores = new ArrayList<>();
		final List<Estudio> listaEstudios = new ArrayList<>();

		for (final List<String> textosColunas : leitorCsv.getLinhas()) {

			Filme filme;
			final Optional<Filme> findFilme = listaFilmes.stream()
					.filter(filmes -> filmes.getTitulo().equals(textosColunas.get(1)))
					.findFirst();
			if (findFilme.isPresent()) {
				filme = findFilme.get();
			} else {

				filme = new Filme();
				filme.setAno(Integer.valueOf(textosColunas.get(0).trim()));
				filme.setTitulo(textosColunas.get(1).trim());
				if (textosColunas.size() == 5) {

					filme.setVencedor("yes".equals(textosColunas.get(4).trim()) ? true : false);
				}
				listaFilmes.add(filme);
			}

			final String[] nomesEstudio = textosColunas.get(2).replaceAll(" and ", ",").split(",");

			for (final String nome : nomesEstudio) {

				if (nome.isEmpty()) {
					continue;
				}

				Estudio estudio;

				final Optional<Estudio> findEstudio = listaEstudios.stream()
						.filter(e -> e.getNome().equals(nome.trim()))
						.findFirst();

				if (!findEstudio.isPresent()) {
					estudio = new Estudio();
					estudio.setNome(nome.trim());
					listaEstudios.add(estudio);
				} else {
					estudio = findEstudio.get();
				}

				// Vinculo o estudio ao filme.
				filme.getEstudios().add(estudio);
			}

			final String[] nomesProdutores = textosColunas.get(3).replaceAll(" and ", ",").split(",");
			for (final String nome : nomesProdutores) {

				if (nome.isEmpty()) {
					continue;
				}

				Produtor produtor;

				final Optional<Produtor> findProdutor = listaProdutores.stream()
						.filter(p -> p.getNome().equals(nome.trim()))
						.findFirst();

				if (!findProdutor.isPresent()) {

					produtor = new Produtor();
					produtor.setNome(nome.trim());
					listaProdutores.add(produtor);
				} else {
					produtor = findProdutor.get();
				}

				// Vinculo o estudio ao filme.
				filme.getProdutores().add(produtor);
			}


		}

		this.filmeService.salvar(listaFilmes);
		LOG.info("Dados carregados com sucesso.");
    }
}