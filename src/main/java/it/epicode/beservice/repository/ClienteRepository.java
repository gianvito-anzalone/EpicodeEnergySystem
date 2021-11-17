package it.epicode.beservice.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.epicode.beservice.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{

	Page<Optional<Cliente>>findByOrderByRagioneSociale(Pageable page);
	
	Page<Optional<Cliente>>findByOrderByDataInserimento(Pageable page);

	Page<Optional<Cliente>>findByOrderByFatturatoAnnuale(Pageable page);

	Page<Optional<Cliente>>findByOrderByDataUltimoContatto(Pageable page);
	
	
	Page<Optional<Cliente>>findByOrderByIndirizzoSedeLegaleComuneProvincia(Pageable page);
	
	@Query("Select c from Cliente c where fatturatoAnnuale>=:fatturato")
	Page<Optional<Cliente>>findByfatturatoAnnuale(Pageable page,Double fatturato);

	Page<Optional<Cliente>>findBydataInserimento(Pageable page,LocalDate data);

	Page<Optional<Cliente>>findBydataUltimoContatto(Pageable page,LocalDate data);
	
	@Query("select c from Cliente c  where c.ragioneSociale Like %:nome% ")
	Page<Optional<Cliente>>findByRagioneSociale(Pageable page,String nome);
	
	@Query("select c from Cliente c where c.id=:id")
	 Cliente findByIdCliente(Long id); 
	
	
}
