package br.com.analisador.filmes.negocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.analisador.filmes.entidades.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
	
	@Query(nativeQuery = true, value = "SELECT P.*\r\n"
			+ " FROM PRODUTORES_FILMES PF\r\n"
			+ " INNER JOIN FILMES F ON F.ID = PF.FILME_ID\r\n"
			+ " INNER JOIN PRODUTORES P ON P.ID = PF.PRODUTOR_ID\r\n"
			+ " WHERE F.VENCEDOR = TRUE\r\n"
			+ " GROUP BY PF.PRODUTOR_ID\r\n"
			+ " HAVING COUNT(PF.PRODUTOR_ID) > 1")
	List<Produtor> filmesDosProdutoresComDuasOuMaisPremiacoes();
}
