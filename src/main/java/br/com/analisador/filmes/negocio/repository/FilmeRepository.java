package br.com.analisador.filmes.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.analisador.filmes.entidades.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
