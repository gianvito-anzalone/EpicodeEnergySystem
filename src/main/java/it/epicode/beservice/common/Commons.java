package it.epicode.beservice.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.repository.RoleRepository;
import it.epicode.beservice.security.model.Erole;
import it.epicode.beservice.security.model.Role;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.service.ProvinciaService;

@Component
public class Commons implements CommandLineRunner {

	@Autowired
	ComuneService comuneService;

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	RoleRepository roleRepo;
	private static final String PATH_PROVINCIA = "C:\\Users\\Lama Donna\\eclips-workspace-jpa\\EPICENERGYSERVICES\\CSV\\comuni-italiani.csv";

	private static final String PATH_COMUNI = "C:\\Users\\Lama Donna\\eclips-workspace-jpa\\EPICENERGYSERVICES\\CSV\\province-italiane.csv";

	@Override
	public void run(String... args) throws Exception {
		Role role=new Role();
		role.setErole(Erole.ADMIN);
		this.roleRepo.save(role);
		Role roleUser=new Role();
		roleUser.setErole(Erole.USER);
		this.roleRepo.save(roleUser);
		recuperoDaCsvProvincia();
		recuperodaCSVComuni();

	}

	private void recuperodaCSVComuni() {
		try (BufferedReader br = new BufferedReader(new FileReader(PATH_COMUNI))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] part = line.split(";");
				Comune comune = new Comune();
				comune.setNome(part[2]);
				Provincia prov = this.provinciaService.findByNome(part[3]);
				comune.setProvincia(prov);
				this.comuneService.saveComune(comune);
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void recuperoDaCsvProvincia() {
		try (BufferedReader br = new BufferedReader(new FileReader(PATH_PROVINCIA))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] part = line.split(";");
				Provincia prov = new Provincia();
				prov.setSigla(part[0]);
				prov.setNome(part[1]);
				this.provinciaService.saveProvincia(prov);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
