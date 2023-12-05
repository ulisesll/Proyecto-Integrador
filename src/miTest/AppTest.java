package miTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import datos.Estudiante;
import datos.Fecha;
import datos.Recurso;
import datos.Prestamo;

import datos.Lista;
import datos.Nodo;
import negocios.Universidad;

import datos.PosicionIlegalException;

class AppTest {
	private Lista<Estudiante> estudiantes;
	private Lista<Recurso> recursos;
	
	
	private Universidad universidad;
	private Estudiante estudiante;
	private Recurso recurso;
	
	@Test
	public void test_agregarEstudiante() throws PosicionIlegalException
	{
			universidad = new Universidad();
			
			
			boolean condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
					"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
			assertTrue(condicion,"El estudiante No debe existir en la Universidad");
			
			condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
					"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");
			assertFalse(condicion,"El estudiante debe existir en la Universidad");
				
	}
	@Test
	public void test_agregarRecurso() throws PosicionIlegalException{
		
		universidad = new Universidad();
		
		boolean condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarRecurso(50,"balón de futbol");
		assertFalse(condicion,"El Recurso No se pudo agregar");	
		
	}
	@Test
	public void test_buscarEstudiante() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiante = new Estudiante();
		
		boolean condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");      
		assertTrue(condicion,"El estudiante debe existir en la Universidad");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Filomena Juarez Lopez",
				"maria.jll@culiacan.tecnm.mx", new Fecha("01/01/1970"),"F","TICs");
		assertFalse(condicion,"El estudiante ya existe en la universidad y no puede agregarse de nuevo");
		
		estudiante = universidad.buscarEstudiante(20230001);
		assertEquals("Maria Lourdes Armenta Lindoro",estudiante.getNombre(),"El nombre no es el esperado");
		
	}
	@Test
	public void test_buscarRecurso() throws PosicionIlegalException{
		
		universidad = new Universidad();
		recurso = new Recurso();
		
		boolean condicion = universidad.agregarRecurso(10,"Guitarra eléctrica");
		assertFalse(condicion,"El recurso ya existe con otra descripcion");
		
		condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		recurso = universidad.buscarRecurso(70);
		assertEquals("Guitarra eléctrica",recurso.getNombre(),"El nombre es el esperado");	
		
	}
	@Test 
	public void test_prestarRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		recurso = new Recurso();
		estudiante = new Estudiante();
		
		
		boolean condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		
		estudiante = universidad.buscarEstudiante(20230001);
		
		recurso = universidad.buscarRecurso(70);
		assertTrue(recurso.isDisponible(),"El recurso debe estar disponible");
		
		assertTrue(universidad.prestarRecursos(20230001, 70), "El recurso fue prestado con exito");
		assertFalse(universidad.prestarRecursos(20230001, 70), "El recurso no puede ser prestado dos veces");
  		
        recurso = universidad.buscarRecurso(70);
		assertFalse(recurso.isDisponible(),"El recurso ya no debe estar disponible");
		
		
	}
	@Test
	public void test_consultarEstudianteTieneRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiante = new Estudiante();
		
		
		boolean condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		
		
		assertTrue(universidad.prestarRecursos(20230001, 70), "El recurso fue prestado con exito");
		
		estudiante=universidad.consultarEstudianteTieneRecurso(70);
		assertEquals("Maria Lourdes Armenta Lindoro",estudiante.getNombre(),"Fue prestado el recurso 70");
  		
		
	}
	@Test
	public void test_consultarRecursosDeUnEstudiante() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiante = new Estudiante();
		recursos = new Lista<Recurso>();
		
		
		boolean condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		
		
		assertTrue(universidad.prestarRecursos(20230001, 70), "El recurso fue prestado con exito");
		
		estudiante=universidad.consultarEstudianteTieneRecurso(70);
		assertEquals("Maria Lourdes Armenta Lindoro",estudiante.getNombre(),"Fue prestado el recurso 70");
		
		assertTrue(universidad.prestarRecursos(20230001, 10), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 20), "El recurso fue prestado con exito");
		
		recursos=universidad.consultarRecursosDeUnEstudiante(20230001);
		assertEquals(3,recursos.getTamanio(),"Tiene 3 recursos prestados");
		
	}
	@Test
	public void test_devolverRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		
		boolean condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		
		
		assertTrue(universidad.prestarRecursos(20230001, 70), "El recurso fue prestado con exito");
		
		assertTrue(universidad.devolverRecurso(70),"El recurso de devolvió con éxito");
		
		assertFalse(universidad.devolverRecurso(70),"El recurso no se pudo devolver");
		
		
	}
	@Test
	public void test_eliminarRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		recursos = new Lista<Recurso>();
		
		boolean condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		
		
		assertTrue(universidad.prestarRecursos(20230001, 70), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 10), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 20), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 30), "El recurso fue prestado con exito");
		
		assertFalse(universidad.eliminarRecurso(100),"No se puede eliminar un recurso que no existe");
		
		assertTrue(universidad.eliminarRecurso(40),"Se eliminó un recurso que no estaba prestado");
		
		assertNull(universidad.buscarRecurso(40),"El recurso ya no existe y regresa Nulo");
		
		assertTrue(universidad.eliminarRecurso(10),"El recurso se eliminó con exito");
		assertTrue(universidad.eliminarRecurso(30),"El recurso se eliminó con exito");
		recursos=universidad.consultarRecursosDeUnEstudiante(20230001);
		assertEquals(2,recursos.getTamanio(),"Le quedaron 2 recursos prestados");
		
		assertEquals("Guitarra eléctrica",recursos.getValor(0).getNombre(),"nombre del primer recurso de la lista");
		assertEquals("Violin",recursos.getValor(1).getNombre(),"nombre del segundo recurso de la lista");
			
	}
	@Test
	public void test_eliminarEstudiante() throws PosicionIlegalException{
		universidad = new Universidad();
		recursos = new Lista<Recurso>();
		estudiantes = new  Lista<Estudiante>();
		
		assertFalse(universidad.eliminarEstudiante(123),"El estudiante no existe por lo que no se elimina");
		
		boolean condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		assertTrue(universidad.eliminarEstudiante(20230001),"Se eliminó con éxito");
		
		condicion = universidad.agregarRecurso(70,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		
		recurso = universidad.buscarRecurso(70);
		assertTrue(recurso.isDisponible(),"El recurso debe estar disponible");
		
		assertTrue(universidad.prestarRecursos(20230001, 70), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 10), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 20), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20230001, 30), "El recurso fue prestado con exito");
		recursos = universidad.consultarRecursosDeUnEstudiante(20230001);
		assertEquals(4,recursos.getTamanio(),"Debe tener 4 recursos prestados");
		
		recurso = universidad.buscarRecurso(70);
		assertFalse(recurso.isDisponible(),"El recurso No debe estar disponible");
		
		assertTrue(universidad.prestarRecursos(20232001, 40), "El recurso fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20232001, 50), "El recurso fue prestado con exito");
		recursos = universidad.consultarRecursosDeUnEstudiante(20232001);
		assertEquals(2,recursos.getTamanio(),"Debe tener 2 recursos prestados");
		
		assertTrue(universidad.eliminarEstudiante(20230001),"Se elimino con exito un estudiante con prestamo");
		assertTrue(universidad.eliminarEstudiante(20232001),"Se elimino con exito un estudiante con prestamo");
		
		recursos = universidad.consultarRecursosDeUnEstudiante(20230001);
		assertNull(recursos,"Ya no deben existir prestamos");
		
		recursos = universidad.consultarRecursosDeUnEstudiante(20232001);
		assertNull(recursos,"Ya no deben existir prestamos");
		
		
		
	}
	@Test
	public void test_buscarNombreRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		
		String nombre = universidad.buscarNombreRecurso(50);
		assertEquals("balón de futbol",nombre,"El nombre es el esperado");
	
	}
	@Test
	public void test_buscarNombreEstudiante() throws PosicionIlegalException{
        universidad = new Universidad();
		
		String nombre = universidad.buscarNombreEstudiante(20231000);
		assertEquals("Ana Lopez",nombre,"El nombre es el esperado");
		
	}
	@Test
	public void test_mostrarRecursos() throws PosicionIlegalException{
		universidad = new Universidad();
		recursos = new Lista<Recurso>();
		recursos.agregar(new Recurso(10,"Guitarra",true));
		recursos.agregar(new Recurso(20,"Violin",true));
		recursos.agregar(new Recurso(30,"Trompeta",true));
		recursos.agregar(new Recurso(40,"Bat de beisbol",true));
		recursos.agregar(new Recurso(50,"balón de futbol",true));
		System.out.println("Tamaño Recursos:"+recursos.getTamanio());
		
		
	}
	@Test 
	public void test_mostrarEstudiantes() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiantes = new Lista<Estudiante>();
		estudiantes.agregar(new Estudiante(20231000,"Ana Lopez","email",
	            new Fecha("01/01/2000"),"Femenino","Sistemas"));
		estudiantes.agregar(new Estudiante(20231001,"Pedro Buelna","email",
		    new Fecha("24/12/1999"),"Masculino","Sistemas"));
		estudiantes.agregar(new Estudiante(20231002,"Cecilia Aragon","email",
		    new Fecha("06/12/1999"),"Femenimo","Sistemas"));
		estudiantes.agregar(new Estudiante(20232001,"Patricia Reyes","email",
		    new Fecha("30/01/2001"),"Femenimo","TICs"));
		estudiantes.agregar(new Estudiante(20232002,"Juan Sanchez","email",
		    new Fecha("30/05/2001"),"Masculino","TICs"));
		assertEquals(5,estudiantes.getTamanio(),"Deben existir 5 elementos de estudiantes");
		Estudiante est = estudiantes.getValor(0);
		
		
	}
	@Test
	public void test_listaMasTresPrestados() throws PosicionIlegalException{
		universidad = new Universidad();
		
		estudiantes = new Lista<Estudiante>();
		
		universidad.agregarRecurso(10,"Guitarra");
		universidad.agregarRecurso(20,"Violin");
		universidad.agregarRecurso(30,"Trompeta");
		universidad.agregarRecurso(40,"Bat de beisbol");
		universidad.agregarRecurso(50,"balón de futbol");
		universidad.agregarRecurso(80,"Tapete Yoga");
		universidad.agregarRecurso(90,"Pesitas de 500 grs");
		universidad.agregarRecurso(110,"Flauta");
		universidad.agregarRecurso(120,"Saxofon");
		universidad.agregarEstudiante(20231000,"Ana Lopez","email",
				            new Fecha("01/01/2000"),"Femenino","Sistemas");
		universidad.agregarEstudiante(20231001,"Pedro Buelna","email",
	            new Fecha("24/12/1999"),"Masculino","Sistemas");
		universidad.agregarEstudiante(20231002,"Cecilia Aragon","email",
	            new Fecha("06/12/1999"),"Femenimo","Sistemas");
		universidad.agregarEstudiante(20232001,"Patricia Reyes","email",
	            new Fecha("30/01/2001"),"Femenimo","TICs");
		universidad.agregarEstudiante(20232002,"Juan Sanchez","email",
	            new Fecha("30/05/2001"),"Masculino","TICs");
		universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
                "maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");
		
		
		universidad.prestarRecursos(20230001, 10);
		universidad.prestarRecursos(20230001, 20);
		universidad.prestarRecursos(20230001, 30);
		universidad.prestarRecursos(20230001, 40);
		
		universidad.prestarRecursos(20231000, 50);
		universidad.prestarRecursos(20231000, 80);
		universidad.prestarRecursos(20231000, 90);
		universidad.prestarRecursos(20231000, 110);
		
		universidad.prestarRecursos(20231002, 120);
		
		estudiantes = universidad.mostrarEstudiantesMasDeTres();
		
		assertEquals(2,estudiantes.getTamanio(),"Existen 2 estudiantes");
		
		
		
	}
	

}
