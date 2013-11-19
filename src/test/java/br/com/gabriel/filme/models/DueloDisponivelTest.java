package br.com.gabriel.filme.models;


import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.gabriel.filme.repositories.FilmeRepository;

public class DueloDisponivelTest {
	
	@Mock
	private FilmeRepository filmeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void devePegarProximoDueloDisponivel() {
		
		DueloDisponivel dueloVotado = new DueloDisponivel(filmeRepository);		
		Set<Duelo> duelosFeitos = new HashSet<Duelo>();
		Duelo proximoDuelo;
		
		while(dueloVotado.temDuelo()){
			proximoDuelo = dueloVotado.duelo();
			Assert.assertFalse(duelosFeitos.contains(proximoDuelo));
			duelosFeitos.add(proximoDuelo);
		}
	}
	
	@Test
	public void deveTrazerCombinacoesDeFilmesPossiveisParaDuelos() {
		
		Set<Duelo> duelosDisponiveisEsperados = criaSetComDuelosDisponiveis();
		
		Mockito.when(filmeRepository.getIdsFilmes()).thenReturn(criaIdsFilmes());
		DueloDisponivel dueloDisponivel = new DueloDisponivel(filmeRepository);
		
		
		int tamanhoSet = 0;
		while (dueloDisponivel.temDuelo()) {
			Assert.assertTrue(duelosDisponiveisEsperados.contains(dueloDisponivel.duelo()));
			tamanhoSet++;
		}
		Assert.assertEquals(duelosDisponiveisEsperados.size(), tamanhoSet);
	}

	private Set<Long> criaIdsFilmes() {
		Set<Long> idsFilmes = new HashSet<Long>();
		idsFilmes.add(1L);
		idsFilmes.add(2L);
		idsFilmes.add(3L);
		idsFilmes.add(4L);
		idsFilmes.add(5L);
		
		return idsFilmes;
	}

	private Set<Duelo> criaSetComDuelosDisponiveis() {
		Set<Duelo> duelosDisponiveis = new HashSet<Duelo>();
		
		duelosDisponiveis.add(new Duelo(filmeRepository.find(1L), filmeRepository.find(2L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(1L), filmeRepository.find(3L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(1L), filmeRepository.find(4L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(1L), filmeRepository.find(5L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(2L), filmeRepository.find(3L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(2L), filmeRepository.find(4L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(2L), filmeRepository.find(5L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(3L), filmeRepository.find(4L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(3L), filmeRepository.find(5L)));
		duelosDisponiveis.add(new Duelo(filmeRepository.find(4L), filmeRepository.find(5L)));
		
		return duelosDisponiveis;
	}

}
