package es.tododev.sandbox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.tododev.sandbox.InvokeAndGet.SyncException;

public class InvokeAndGetTest {

	@Test
	public void execute() throws InterruptedException, SyncException {
		Integer result = InvokeAndGet.execute(() -> 1+1, 5000);
		assertEquals(2, result.intValue());
	}
	
	@Test(expected = SyncException.class)
	public void executeException() throws InterruptedException, SyncException {
		InvokeAndGet.execute(() -> 1/0, 5000);
	}
	
}
