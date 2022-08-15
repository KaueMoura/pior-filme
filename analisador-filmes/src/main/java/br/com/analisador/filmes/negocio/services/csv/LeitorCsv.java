package br.com.analisador.filmes.negocio.services.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * Classe abstrata para leitura de arquivo csv.
 * 
 * @author Kauê Moura
 *
 */
@Service
public abstract class LeitorCsv {

	private static Logger LOGGER = LoggerFactory.getLogger(LeitorCsv.class);

	protected static final String PATH_CSV = "dadosCsv/movielist.csv";

	protected String delimitador;

	protected List<List<String>> linhas;

	protected boolean ignorarHeader;

	public LeitorCsv() {
		super();
		this.delimitador = ";";
		this.linhas = new ArrayList<>();
		this.ignorarHeader = true;
	}

	public String getDelimitador() {
		return delimitador;
	}

	public void setDelimitador(final String delimitador) {
		this.delimitador = delimitador;
	}

	public boolean isIgnorarHeader() {
		return ignorarHeader;
	}

	public void setIgnorarHeader(final boolean ignorarHeader) {
		this.ignorarHeader = ignorarHeader;
	}

	/**
	 * Carrega os dados do arquivo csv.
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	public void carregarValores() throws IOException {
		String line;
		try {
			final BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:" + PATH_CSV)));
			while ((line = br.readLine()) != null) {
				final String[] values = line.split(this.getDelimitador());
				this.linhas.add(Arrays.asList(values));
			}
		} catch (final FileNotFoundException exception) {
			LOGGER.error("Arquivo csv não encontrado.", exception);
			throw exception;
		} catch (final IOException exception) {
			LOGGER.error("Erro ao efetuar leitura do arquivo csv.", exception);
			throw exception;
		}

	}

	/**
	 * Retorna a lista com as linha do arquivo csv. Ignora a primeira linha se o
	 * atributo ignorarHeader for igual a true.
	 * 
	 * @return
	 */
	public List<List<String>> getLinhas() {
		if (this.ignorarHeader) {
			return linhas.subList(1, linhas.size());
		}
		return linhas;
	}

	public void setLinhas(final List<List<String>> linhas) {
		this.linhas = linhas;
	}

}
