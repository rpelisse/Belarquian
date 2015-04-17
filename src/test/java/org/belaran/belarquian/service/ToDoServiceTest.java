package org.belaran.belarquian.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ToDoServiceTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(TodoService.class).addClass(ToDoStorageInfinispan.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject 
	private TodoService todoService;

	@Test
	public void test() {
		todoService.addToDo(new ToDoItem(1L, "first item"));
		assertEquals(1, todoService.getTodos().size());
	}
	
}
