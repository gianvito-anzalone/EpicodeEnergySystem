package it.epicode.beservice.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.StatoFatturaService;

@ExtendWith(MockitoExtension.class)
class StatoFatturaServiceTest {

	@Mock
	StatoFatturaService statofatturaService;

	StatoFattura stat;

	@BeforeEach
	void setUp() throws Exception {
		StatoFattura stat = new StatoFattura();
		stat.setNome("nome");

	}

	@Test
	@DisplayName("test salva fattura")
	void testSaveStatoFattura() {
		this.statofatturaService.saveStatoFattura(stat);
	}

}
