package br.com.gabriel.filme.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FilmeControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new FilmeController(null, null, null));
 	}
}
