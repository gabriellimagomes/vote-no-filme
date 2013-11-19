package br.com.gabriel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.gabriel.filme.controllers.UsuarioControllerTest;
import br.com.gabriel.filme.controllers.VotoControllerTest;
import br.com.gabriel.filme.models.DueloDisponivelTest;
import br.com.gabriel.filme.repositories.FilmeRepositoryImplTest;
import br.com.gabriel.filme.repositories.VotoRepositoryImplTest;

@RunWith(Suite.class)
@SuiteClasses({
	VotoControllerTest.class,
	UsuarioControllerTest.class,
	
	DueloDisponivelTest.class,
	
	VotoRepositoryImplTest.class,
	FilmeRepositoryImplTest.class
	})
public class AllTests {

}
