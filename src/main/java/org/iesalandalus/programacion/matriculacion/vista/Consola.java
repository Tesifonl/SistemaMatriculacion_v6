package org.iesalandalus.programacion.matriculacion.vista;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	public Consola() {
		
	}
	
	public static void mostrarMenu() {
	      System.out.println("--------------------------------------------------------------------------------------");
	      System.out.println("Opciones: ");
	      System.out.println("--------------------------------------------------------------------------------------");
	      System.out.println("");
	      for (int i = 0; i < Opcion.values().length; i++) {
	        	System.out.println(Opcion.values()[i].toString());
	       }
	}
	
	public static Opcion elegirOpcion() {
		int opciones=0;
		Opcion opcionElegida=Opcion.BUSCAR_ALUMNO;
		
		do {
			System.out.println("Introduzca la opcion a elegir");
			opciones=Entrada.entero();
		}while (opciones<0 || opciones>=Opcion.values().length);
		
		
		switch (opciones)
		
		{
			case 1:
				opcionElegida=Opcion.INSERTAR_ALUMNO;
				break;
			case 2:
				opcionElegida=Opcion.BUSCAR_ALUMNO;
				break;
			case 3:
				opcionElegida=Opcion.BORRAR_ALUMNO;
				break;
			case 4:
				opcionElegida=Opcion.MOSTRAR_ALUMNOS;
				break;
			case 5:
				opcionElegida=Opcion.INSERTAR_CICLO_FORMATIVO;
				break;
			case 6:
				opcionElegida=Opcion.BUSCAR_CICLO_FORMATIVO;
				break;
			case 7:
				opcionElegida=Opcion.BORRAR_CICLO_FORMATIVO;
				break;
			case 8:
				opcionElegida=Opcion.MOSTRAR_CICLOS_FORMATIVOS;
				break;
			case 9:
				opcionElegida=Opcion.INSERTAR_ASIGNATURA;
				break;
			case 10:
				opcionElegida=Opcion.BUSCAR_ASIGNATURA;
				break;
			case 11:
				opcionElegida=Opcion.BORRAR_ASIGNATURA;
				break;
			case 12:
				opcionElegida=Opcion.MOSTRAR_ASIGNATURAS;
				break;
			case 13:
				opcionElegida=Opcion.INSERTAR_MATRICULA;
				break;
			case 14:
				opcionElegida=Opcion.BUSCAR_MATRICULA;
				break;
			case 15:
				opcionElegida=Opcion.ANULAR_MATRICULA;
				break;
			case 16:
				opcionElegida=Opcion.MOSTRAR_MATRICULAS;
				break;
			case 17:
				opcionElegida=Opcion.MOSTRAR_MATRICULAS_ALUMNO;
				break;
			case 18:
				opcionElegida=Opcion.MOSTRAR_MATRICULAS_CICLO_FORMATIVO;
				break;
			case 19:
				opcionElegida=Opcion.MOSTRAR_MATRICULAS_CURSO_ACADEMICO;
				break;
			case 0:
				opcionElegida=Opcion.SALIR;
				break;				
		}
		
		return opcionElegida;	
	}
	
	
	public static Alumno leerAlumno() {
		
		
		try {
			System.out.println("Introduce un nombre: ");
			String nombre=Entrada.cadena();
			System.out.println("Introduce un dni: ");
			String dni=Entrada.cadena();
			System.out.println("Introduce un correo: ");
			String correo=Entrada.cadena();
			System.out.println("Introduce un telefono: ");
			String telefono=Entrada.cadena();
			System.out.println("Introduce una fecha nacimiento: ");
			LocalDate fechaNacimiento=leerFecha(Entrada.cadena());

			Alumno alumno =new Alumno( nombre, dni, correo, telefono, fechaNacimiento);
			return alumno;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	
	
	public static Alumno getAlumnoPorDni()  {
		try {
			System.out.println("Introduce un dni: ");
			String dni=Entrada.cadena();
			Alumno alumno=new Alumno( "Tesi", dni, "Tesi@gmail.com", "999999999", LocalDate.of(1979, 1, 8));
			return alumno;
			}
			catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	
	
	
	public static LocalDate leerFecha(String mensaje ) {
        String pattern = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        try {
           
            LocalDate fechaLocalDate = LocalDate.parse(mensaje, formatter);
            return fechaLocalDate;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
	}
	
	
	public static Grado leerGrado() {
		
		int eleccion=0;
		Grado gradoElegido=null;
		
		System.out.println("Introduce una numero para elegir el grado que deseas matricular: ");
		
		for (int i = 0; i < Opcion.values().length; i++) {
			System.out.print(Opcion.values()[i] + " ");
		}
		System.out.println();
		
		eleccion = Entrada.entero();
	
		switch (eleccion)
		{
			case 1:
				gradoElegido=Grado.GDCFGB;
				break;
			case 2:
				gradoElegido=Grado.GDCFGM;
				break;
			case 3:
				gradoElegido=Grado.GDCFGS;
				break;

		}
		
		return gradoElegido;
		
	}
	
	public static CicloFormativo leerCicloFormativo() {
		
		try {
			System.out.println("Introduce un codigo: ");
			int codigo=Entrada.entero();
			System.out.println("Introduce una familia profesional ");
			String familiaProfesional=Entrada.cadena();
			Grado grado=leerGrado();
			System.out.println("Introduce un nombre del ciclo: ");
			String nombre=Entrada.cadena();
			System.out.println("Introduce un numero de horas: ");
			int horas=Entrada.entero();
			

			CicloFormativo cicloFormativo =new CicloFormativo(codigo,familiaProfesional,grado,nombre,horas);
			return cicloFormativo;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	/*por el contexto entiendo que ciclos formativos no tiene que estar como variable de entrada*/
	
	public static void mostrarCiclosFormativos (CiclosFormativos ciclosFormativos) {
		
		if (ciclosFormativos!=null) {
			ciclosFormativos.toString();
		}else {
			throw new NullPointerException("No se ha recibido el cicloformativo");
		}
	}
	
	public static CicloFormativo getCicloFormativoPorCodigo() {
		
		try {
			System.out.println("Introduce un codigo: ");
			int codigo=Entrada.entero();
			

			CicloFormativo cicloFormativo =new CicloFormativo(codigo,"Semipresencial",Grado.GDCFGB,"DAW",100);
			return cicloFormativo;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	
	public static Curso leerCurso() {
		
		int eleccion=0;
		Curso cursoElegido=null;
		
		System.out.println("Introduce una numero para elegir el curso que deseas matricular: ");
		
		for (int i = 0; i < Opcion.values().length; i++) {
			System.out.print(Opcion.values()[i] + " ");
		}
		System.out.println();
		
		eleccion = Entrada.entero();
	
		switch (eleccion)
		{
			case 1:
				cursoElegido=Curso.PRIMERO;
				break;
			case 2:
				cursoElegido=Curso.SEGUNDO;
				break;

		}
		
		return cursoElegido;
	}
	
	public static EspecialidadProfesorado leerEspecialidadProfesorado () {
		
		int eleccion=0;
		EspecialidadProfesorado  especialidadElegida=null;
		
		System.out.println("Introduce una numero para elegir el grado que deseas matricular: ");
		
		for (int i = 0; i < Opcion.values().length; i++) {
			System.out.print(Opcion.values()[i] + " ");
		}
		System.out.println();
		
		eleccion = Entrada.entero();
	
		switch (eleccion)
		{
			case 1:
				especialidadElegida=EspecialidadProfesorado.INFORMATICA;
				break;
			case 2:
				especialidadElegida=EspecialidadProfesorado.SISTEMAS;
				break;
			case 3:
				especialidadElegida=EspecialidadProfesorado.FOL;
				break;

		}
		
		return especialidadElegida;
	}
	
	
	public static Asignatura leerAsignatura () {
		
		try {
			System.out.println("Introduce un codigo: ");
			String codigo=Entrada.cadena();
			System.out.println("Introduce un nombre ");
			String nombre=Entrada.cadena();
			System.out.println("Introduce un numero de horas anuales ");
			int horasAnuales=Entrada.entero();
			Curso curso=leerCurso();
			System.out.println("Introduce un numero de horas de desdoble: ");
			int horasDesdoble=Entrada.entero();
			EspecialidadProfesorado especialidadProfesorado=leerEspecialidadProfesorado();
			CicloFormativo cicloformativo=leerCicloFormativo();
			

			Asignatura asignatura =new Asignatura(codigo,nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado,cicloformativo);
			return asignatura;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	
	public static Asignatura getAsignaturaPorCodigo() {
		try {
			System.out.println("Introduce un codigo: ");
			String codigo=Entrada.cadena();
			
			Asignatura asignatura =new Asignatura(codigo,"Programacion",300,Curso.PRIMERO,100,EspecialidadProfesorado.INFORMATICA,getCicloFormativoPorCodigo());
			return asignatura;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	
	private static void mostrarAsignaturas (Asignaturas asignaturas) {
		if (asignaturas!=null) {
			asignaturas.toString();
		}else {
			throw new NullPointerException("No se ha recibido el cicloformativo");
		}
	}
	
	private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula,Asignatura asignatura) {
		boolean encontrada=false;
		boolean otro=false;
		
		for(int i=0;i<asignaturasMatricula.length-1;i++) {
			if(asignaturasMatricula [i]!=null && asignaturasMatricula[i].equals(asignatura)) {
				encontrada=true;
			}
			else {
				otro=false;
			}
		}
		
		if (encontrada==true) {
			return encontrada;
		}
		else {return otro;
		}
	}
	
	/*Dudas diagrama de clase, pone alumnos en vez de alumno*/
	
	public static Matricula leerMatricula(Alumno alumno, Asignatura [] asignaturas) throws OperationNotSupportedException {
		
		try {
			System.out.println("Introduce un id de matricula: ");
			int idMatricula=Entrada.entero();
			System.out.println("Introduce el curso: ");
			String curso=Entrada.cadena();
			LocalDate fechaMatriculacion=leerFecha(Entrada.cadena());
			Alumno alumnoIntroducido=alumno;
			Asignatura [] asignaturasIntroducidas=asignaturas;
			

			Matricula matricula =new Matricula(idMatricula,curso,fechaMatriculacion,alumnoIntroducido,asignaturasIntroducidas);
			return matricula;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
		
	}
	
	public static Matricula getMatriculaPorIdentificacion() throws OperationNotSupportedException {
		
		try {
			System.out.println("Introduce un id de matricula: ");
			int idMatricula=Entrada.entero();
			Asignatura[] asignaturas=new Asignatura[2];
			asignaturas[0]=getAsignaturaPorCodigo();
			

			Matricula matricula =new Matricula(idMatricula,"PRIMERO",LocalDate.now(),getAlumnoPorDni() ,asignaturas);
			return matricula;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return null;
			}
			catch(NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
			}
	}
	
	
}
