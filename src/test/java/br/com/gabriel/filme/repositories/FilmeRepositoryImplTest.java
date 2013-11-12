package br.com.gabriel.filme.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FilmeRepositoryImplTest {

    @Test public void fakeTest() {
  		assertNotNull("put something real.", new FilmeRepositoryImpl(null));
  	}
}

