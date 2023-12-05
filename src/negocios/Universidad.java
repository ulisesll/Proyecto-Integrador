package negocios;
import datos.Estudiante;
import datos.Lista;
import datos.PosicionIlegalException;
import datos.Prestamo;
import datos.Recurso;
import datos.Fecha;

public class Universidad {
	private Lista<Estudiante> estudiantes;
	private Lista<Recurso> recursos;
	private Lista<Prestamo> prestamos;
	public Universidad() {
		estudiantes = new Lista<Estudiante>();
		recursos = new Lista<Recurso>();
		prestamos = new Lista<Prestamo>();
		recursos.agregar(new Recurso(10,"Guitarra",true));
		recursos.agregar(new Recurso(20,"Violin",true));
		recursos.agregar(new Recurso(30,"Trompeta",true));
		recursos.agregar(new Recurso(40,"Bat de beisbol",true));
		recursos.agregar(new Recurso(50,"balón de futbol",true));
		recursos.agregar(new Recurso(80,"Tapete Yoga",true));
		recursos.agregar(new Recurso(90,"Pesitas de 500 grs",true));
		recursos.agregar(new Recurso(110,"Flauta",true));
		recursos.agregar(new Recurso(120,"Saxofon",true));
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
		
		
	}
	public boolean agregarEstudiante(int codigo, String nombre,
			String email,Fecha fechaNac, String sexo, String programa)
					throws PosicionIlegalException{
		//Estudiante est = buscarEstudiante(codigo);
		//Modifique la siguiente línes para que busque al estudiante
		//utilizando el método buscarEstudiante
		Estudiante est = null; //deberá sustituir el null por la accion correcta
		if (est == null) {
			estudiantes.agregar(new Estudiante(codigo, nombre,
					email, fechaNac, sexo, programa));
			return true;
		}
		return false;
			
	}
	public Estudiante buscarEstudiante(int codigo) throws PosicionIlegalException{
		for (int i=0;i<estudiantes.getTamanio();i++) {
			Estudiante est = estudiantes.getValor(i);
			if (est.getCodigo() == codigo) 
				return est;	
		}
		return null;
	}
	public boolean agregarRecurso(int id, String nombre) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if(rec == null) {
			recursos.agregar(new Recurso(id, nombre, true));
			return true;
		}
		return false;
	}
	public Recurso buscarRecurso(int id) throws PosicionIlegalException{
		for(int i=0; i<recursos.getTamanio();i++) {
			Recurso rec = recursos.getValor(i);
			if(rec.getId()==id) {
				return rec;
			}
		}
		return null;
	}
	public boolean prestarRecursos(int codigo, int id) throws PosicionIlegalException{
		Estudiante est = buscarEstudiante(codigo);
		String nombre = buscarNombreEstudiante(codigo);
		System.out.print(nombre+" ");
		Recurso rec = buscarRecurso(id);
		nombre = buscarNombreRecurso(id);
		System.out.print(nombre+" ");
		if(est ==null || rec==null) {
			System.out.println("Estudiante o recurso no existen");
			return false;
		}
		if(!rec.isDisponible()) {
			System.out.println("El recurso "+rec +" ya esta prestado "+
		      "a otro estudiante");
			return false;
		}
		Prestamo pres=new Prestamo(rec, est);
		pres.setFechaPrestamo(new Fecha());
		rec.setDisponible(false);
		prestamos.agregar(pres);
		System.out.println("El recurso "+rec+ "fue prestado al estudiante "+est);
		return true;
	}
	public Estudiante consultarEstudianteTieneRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if(rec==null) {
			System.out.println("Recurso no existen");
			return null;
		}
		if(rec.isDisponible()) {
			System.out.println("El recurso "+rec+" esta disponible");
			return null;
		}
		for(int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres = prestamos.getValor(i);
			if(rec.getId()==id && !rec.isDisponible()) {
				System.out.println("El recurso "+rec+" esta prestado al estudiante "+
			           pres.getEstudiante() );
				return pres.getEstudiante();
			}
		}
		return null;
	}
	public Lista<Recurso> consultarRecursosDeUnEstudiante(int codigo) throws
	   PosicionIlegalException {
		Estudiante est = buscarEstudiante(codigo);
		if(est == null) {
			System.out.println("Estudiante no existe");
			return null;
		}
		Lista<Recurso> recursosprestados = new Lista<Recurso>();
		//Modifique el cabecero del siguiente for (linea 132), para que muestre todos los 
		//elementos de la lista ya que hay un error
		for(int i=1;i<prestamos.getTamanio();i++) {
			Prestamo pres = prestamos.getValor(i);
			if (pres.getEstudiante().getCodigo() ==codigo && 
					!pres.getRecurso().isDisponible()) {
				recursosprestados.agregar(pres.getRecurso());
			}
		}
		return recursosprestados;
		
	}
	
	public boolean devolverRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if (rec==null){
			System.out.println("Recurso no existe");
			return false;
		}
		if(rec.isDisponible()) {
			System.out.println("El recurso "+rec+" no se puede devolver "+
		   " porque esta disponible");
			return false;
		}
		for (int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres=prestamos.getValor(i);
			if(pres.getRecurso().getId()==id &&
					pres.getFechaDevolucion()==null) {
				pres.setFechaDevolucion(new Fecha("31/12/2000"));
				rec.setDisponible(true);
				System.out.println("Se ha devuelto el recurso "+
				   rec+ "por parte del estudiantes "+
						pres.getEstudiante()+ "satisfactoriamente");
				return true;
			}
		}
		return false;
	}
	public boolean eliminarRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if(rec ==null) {
			System.out.println("Recursos no existen");
			return false;
		}
		//Borrar el recurso de los prestamos
		for(int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres = prestamos.getValor(i);
			if(pres.getRecurso().getId()==id) {
				prestamos.remover(i);
			}
		}
		//Borrar el recurso de los recursos
		for(int i=0;i<recursos.getTamanio();i++) {
			if(recursos.getValor(i).getId() ==id) {
				recursos.remover(i);
			}
		}
		System.out.println("El recurso "+rec+" fue eliminado satisfactoriamente");
		return true;
	}
	public boolean eliminarEstudiante(int codigo) throws PosicionIlegalException{
		Estudiante est = buscarEstudiante(codigo);
		if (est==null) {
			System.out.println("Estudiante no existe");
			return false;
		}
		//Borrar el estudiante de los prestamos
		
		for (int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres= prestamos.getValor(i);
			if(pres.getEstudiante().getCodigo() == codigo) {
				prestamos.remover(i);
			}
			
			
		}
		
		
		//Borrar el estudiantes de estudiantes
		for(int i=0; i<estudiantes.getTamanio();i++) {
			if(estudiantes.getValor(i).getCodigo() == codigo) {
				estudiantes.remover(i);
			}
		}
		System.out.println("El estudiante "+est+ " fue eliminado"
				+ " satisfactoriamente");
		return true;
	}
	public String buscarNombreRecurso(int id) throws PosicionIlegalException{
		for(int i=0; i<recursos.getTamanio();i++) {
			Recurso rec = recursos.getValor(i);
			if(rec.getId()==id) {
				return rec.getNombre();
			}
		}
		return null;
	}
	public String buscarNombreEstudiante(int codigo) throws PosicionIlegalException{
		for(int i=0; i<estudiantes.getTamanio();i++) {
			Estudiante est = estudiantes.getValor(i);
			if(est.getCodigo()==codigo) {
				return est.getNombre();
			}
		}
		return null;
	}
	
	public Lista<Recurso> mostrarRecursos() throws PosicionIlegalException{
		for (int i=0;i<recursos.getTamanio();i++) {
			System.out.println("Id: "+recursos.getValor(i).getId()+
					      "  Descripcion: "+recursos.getValor(i).getNombre() );
					     
		}
		return recursos;
	}
	
	public Lista<Estudiante> mostrarEstudiantes() throws PosicionIlegalException {
		for (int i=0;i<estudiantes.getTamanio();i++) {
			System.out.println("Código: "+estudiantes.getValor(i).getCodigo()+
					      "  Nombre: "+estudiantes.getValor(i).getNombre() );
		}		
		return estudiantes;
	}
	/**
	 * 
	 * @return una lista de los estudiantes que cumplan con la condición de tener mas
	 *       de tres préstamos
	 * @throws PosicionIlegalException
	 */
	
	public Lista<Estudiante> mostrarEstudiantesMasDeTres() throws PosicionIlegalException {
		 Lista<Estudiante> prestamosMasDeTres=new Lista<Estudiante>();
		
		
		
		return prestamosMasDeTres;
	}
	
}
		
	
	





