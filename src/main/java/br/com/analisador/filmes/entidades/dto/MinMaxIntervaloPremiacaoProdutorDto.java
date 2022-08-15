package br.com.analisador.filmes.entidades.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class MinMaxIntervaloPremiacaoProdutorDto {

	@JsonProperty("min")
	private List<IntervaloPremiacaoProdutorDto> minPremiacoes;

	@JsonProperty("max")
	private List<IntervaloPremiacaoProdutorDto> maxPremiacoes;

	public List<IntervaloPremiacaoProdutorDto> getMinPremiacoes() {
		if (minPremiacoes == null) {
			minPremiacoes = new ArrayList<>();
		}
		return minPremiacoes;
	}

	public void setMinPremiacoes(final List<IntervaloPremiacaoProdutorDto> minPremiacoes) {
		this.minPremiacoes = minPremiacoes;
	}

	public List<IntervaloPremiacaoProdutorDto> getMaxPremiacoes() {
		if (maxPremiacoes == null) {
			maxPremiacoes = new ArrayList<>();
		}
		return maxPremiacoes;
	}

	public void setMaxPremiacoes(final List<IntervaloPremiacaoProdutorDto> maxPremiacoes) {
		this.maxPremiacoes = maxPremiacoes;
	}

}
