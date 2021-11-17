package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.epicode.beservice.model.Comune;
@Repository
public interface ComuneRepository extends JpaRepository<Comune,Long>{
	
	@Query("select c from Comune c where c.id=:id")
	Comune findByIdComune(Long id);

}
