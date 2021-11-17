package it.epicode.beservice.security.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@Table(name="role")
public class Role {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Enumerated(EnumType.STRING)
		private Erole erole;
		
		
		

}
