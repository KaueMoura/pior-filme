package br.com.analisador.filmes.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.analisador.filmes.negocio.services.inicializacao.CarregaDadosAplicacaoService;

@SpringBootApplication
@ComponentScan({ "br.com.analisador.filmes" })
@EntityScan("br.com.analisador.filmes.*")
@EnableJpaRepositories("br.com.analisador.filmes.*")
public class AnalisadorFilmesApplication implements CommandLineRunner {

	@Autowired
	CarregaDadosAplicacaoService carregaDadosAplicacaoService;

	public static void main(final String[] args) {
		SpringApplication.run(AnalisadorFilmesApplication.class, args);
	}

	/**
	 * Método chamado logo após start da aplicação responsável por carregar os dados
	 * iniciais.
	 */
	@Override
	public void run(final String... args) throws Exception {
		carregaDadosAplicacaoService.carregarDadosAplicacao(args);
	}

}
