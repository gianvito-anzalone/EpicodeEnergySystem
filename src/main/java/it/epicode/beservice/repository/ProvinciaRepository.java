package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.epicode.beservice.model.Provincia;
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,Long>{
	@Query("select c from Provincia c where c.id=:id")
	Provincia findByidProvincia(Long id);

	Provincia findByNome(String nome);
}
