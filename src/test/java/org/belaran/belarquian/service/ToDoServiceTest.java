package org.belaran.belarquian.service;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ToDoServiceTest {
	
	@Deployment
	public static WebArchive createDeployment() {
	    return ShrinkWrap.create(ZipImporter.class, "Belarquian.war.war").importFrom(new File("target/Belarquian.war"))
	            .as(WebArchive.class);
	}
	
//	@Inject 
//	private TodoService todoService;

	@Test
	public void test() {
//		todoService.addToDo(new ToDoItem(1L, "first item"));
	//	assertEquals(1, todoService.getTodos().size());
	}
}
