package br.com.analisador.filmes.entidades.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class IntervaloPremiacaoProdutorDto {

	@JsonProperty(value = "producer")
	private String nomeProdutor;

	@JsonProperty(value = "interval")
	private int intervalo;

	@JsonProperty(value = "previousWin")
	private int anoVitoriaAnterior;

	@JsonProperty(value = "followingWin")
	private int anoVitoriaSeguinte;

	public String getNomeProdutor() {
		return nomeProdutor;
	}

	public void setNomeProdutor(final String nomeProdutor) {
		this.nomeProdutor = nomeProdutor;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(final int intervalo) {
		this.intervalo = intervalo;
	}

	public int getAnoVitoriaAnterior() {
		return anoVitoriaAnterior;
	}

	public void setAnoVitoriaAnterior(final int anoVitoriaAnterior) {
		this.anoVitoriaAnterior = anoVitoriaAnterior;
	}

	public int getAnoVitoriaSeguinte() {
		return anoVitoriaSeguinte;
	}

	public void setAnoVitoriaSeguinte(final int anoVitoriaSeguinte) {
		this.anoVitoriaSeguinte = anoVitoriaSeguinte;
	}

}
