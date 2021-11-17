package it.epicode.beservice.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.StatoFattura;
@Repository
public interface FatturaRepository extends JpaRepository<Fattura,Long>{
	
	Page<Optional<Fattura>>findByCliente(Pageable page,Cliente cliente);
	
	Page<Optional<Fattura>>findByStato(Pageable page,StatoFattura stato);
	
	Page<Optional<Fattura>>findByData(Pageable page,LocalDate data);

	Page<Optional<Fattura>>findByAnno(Pageable page,String anno);
	
	@Query("select f from Fattura f where f.importo>=:min and f.importo<=:max")
	Page<Optional<Fattura>>findByImporto(Pageable page,Double min,Double max);
	
	@Query("select c from Fattura c where c.id=:id")
	Fattura findByIdFattura(Long id);

}
