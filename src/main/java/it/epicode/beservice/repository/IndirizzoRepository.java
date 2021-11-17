package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo,Long>{
	@Query("select c from Indirizzo c where c.id=:id")
	Indirizzo findByIdIndirizzo(Long id);

}
