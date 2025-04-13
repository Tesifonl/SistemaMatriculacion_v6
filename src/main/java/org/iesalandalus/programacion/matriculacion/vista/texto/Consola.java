package org.iesalandalus.programacion.matriculacion.vista.texto;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Grado;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoD;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Modalidad;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.TiposGrado;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private Consola() {
		
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
			System.out.println("Introduce el nombre del alumno: ");
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

		Grado gradoElegido=null;
		System.out.println("Introduce un nombre para el grado");
		String nombre=Entrada.cadena();
		TiposGrado nuevoGrado=leerTipoGrado();
		System.out.println("Introduce un numero de años.Para grados D solo puede ser 2 o 3 años. Para grados E solo 1 año ");
		int numAnios=Entrada.entero();
		
		if (nuevoGrado.equals(TiposGrado.GRADOD)) {
			gradoElegido=new GradoD(nombre,numAnios,leerModalidad());
		}else {
			System.out.println("Introduce un numero de ediciones para el grado");
			int numEdiciones=Entrada.entero();
			gradoElegido=new GradoE(nombre,1,numEdiciones);
		}

		
		return gradoElegido;
		
	}
	
	public static TiposGrado leerTipoGrado() {
		int eleccion=0;
		TiposGrado tiposGradoElegido=null;
		
		System.out.println("Introduce una numero para elegir el tipo de grado que deseas cursar: ");
		
		for (int i = 0; i < TiposGrado.values().length; i++) {
			System.out.print("Opcion "+(i+1)+"=  "+TiposGrado.values()[i]+"    ");
		}
		System.out.println();
		
		eleccion = Entrada.entero();
	
		switch (eleccion)
		{
			case 1:
				tiposGradoElegido=TiposGrado.GRADOD;
				break;
			case 2:
				tiposGradoElegido=TiposGrado.GRADOE;
				break;
			case 3:
				tiposGradoElegido=TiposGrado.GRADOD;
				break;

		}
		
		return tiposGradoElegido;
		
	}
	
	public static Modalidad leerModalidad() {
		int eleccion=0;
		Modalidad modalidadElegido=null;
		
		System.out.println("Introduce una numero para elegir la modalidad que deseas cursar: ");
		
		for (int i = 0; i < Modalidad.values().length; i++) {
			System.out.print("Opcion "+(i+1)+"=  "+Modalidad.values()[i]+"    ");
		}
		System.out.println();
		
		eleccion = Entrada.entero();
	
		switch (eleccion)
		{
			case 1:
				modalidadElegido=Modalidad.SEMIPRESENCIAL;
				break;
			case 2:
				modalidadElegido=Modalidad.PRESENCIAL;
				break;
			case 3:
				modalidadElegido=Modalidad.SEMIPRESENCIAL;
				break;

		}
		
		return modalidadElegido;
	}
	
	public static CicloFormativo leerCicloFormativo() {
		
		try {
			System.out.println("Introduce un numero de 4 digitos como codigo del ciclo: ");
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
	
	
	public static void mostrarCiclosFormativos (List<CicloFormativo> ciclosFormativos) {
		boolean encontrado=false;
		
		if (ciclosFormativos!=null) {
		
			for(int i=0;i<ciclosFormativos.size();i++) {
				if(ciclosFormativos.get(i)!=null) {
					ciclosFormativos.get(i).toString();
				}else {
					encontrado=true;
				}
			}
		}else {
			throw new NullPointerException("No se ha recibido la coleccion de ciclos formativos");
		}
	}
	
	public static CicloFormativo getCicloFormativoPorCodigo() {
		
		try {
			System.out.println("Introduce el codigo del ciclo formativo que debe ser 4 caracteres numericos: ");
			int codigo=Entrada.entero();
			Grado grado=new GradoE("DW",1,1);

			CicloFormativo cicloFormativo =new CicloFormativo(codigo,"Semipresencial",grado,"DAW",100);
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
		
		System.out.println("Introduce un numero para elegir entre las siguientes opciones ");
		
		for (int i = 0; i < Curso.values().length; i++) {
			System.out.print("Opcion "+(i+1)+"=  "+Curso.values()[i] + "    ");
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
		
		System.out.println("Introduce una numero para elegir la especiliadad del profesorado ");
		
		for (int i = 0; i < EspecialidadProfesorado.values().length; i++) {
			System.out.print("Opcion "+(i+1)+"=  "+EspecialidadProfesorado.values()[i] + "   ");
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
			System.out.println("Introduce un codigo entero de 4 digitos para la asignatura: ");
			String codigo=Entrada.cadena();
			System.out.println("Introduce un nombre para la asignaturas ");
			String nombre=Entrada.cadena();
			System.out.println("Introduce un numero de horas anuales ");
			int horasAnuales=Entrada.entero();
			Curso curso=leerCurso();
			System.out.println("Introduce un numero de horas de desdoble entre 0 y 6 horas: ");
			int horasDesdoble=Entrada.entero();
			EspecialidadProfesorado especialidadProfesorado=leerEspecialidadProfesorado();
			CicloFormativo cicloformativo=getCicloFormativoPorCodigo();
			
			

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
			System.out.println("Introduce un codigo de asignatura que debe ser cuatro caracteres numericos: ");
			int codigo1=Entrada.entero();
			String codigo2=String.valueOf(codigo1);
			Grado grado=new GradoE("DW",1,1);
			CicloFormativo cicloFormativo =new CicloFormativo(9999,"Semipresencial",grado,"DAW",100);
			
			Asignatura asignatura =new Asignatura(codigo2,"Programacion",100,Curso.PRIMERO,6,EspecialidadProfesorado.INFORMATICA,cicloFormativo);
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
	
	public static void mostrarAsignaturas (ArrayList<Asignatura> asignaturas) {
		boolean encontrado=false;
		
		if (asignaturas!=null) {
		
			for(int i=0;i<asignaturas.size();i++) {
				if(asignaturas.get(i)!=null) {
					System.out.println(asignaturas.get(i).toString());
				}else {
					encontrado=true;
				}
			}
		}else {
			throw new NullPointerException("No se ha recibido la coleccion de asignaturas");
		}
	}
	

	
	public static boolean asignaturaYaMatriculada(ArrayList<Asignatura> asignaturasMatricula,Asignatura asignatura) {
		boolean insertada=false;
		boolean otro=false;
		
		for(int i=0;i<asignaturasMatricula.size();i++) {
			if(asignaturasMatricula.get(i)!=null && asignaturasMatricula.get(i).equals(asignatura)) {
				insertada=true;
			}
			else {
				otro=false;
			}
		}
		
		if (insertada==true) {
			return true;
		}
		else {return false;
		}
	}
	
	
	
	public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> asignaturas) throws OperationNotSupportedException {
		
		try {
			System.out.println("Introduce un id de matricula que debe ser un numero mayor que cero: ");
			int idMatricula=Entrada.entero();
			System.out.println("Introduce el cursoE que debe tener el formato dd-dd, por ejemplo, 23-24 ");
			String curso=Entrada.cadena();
			System.out.println("Introduce una fecha de matriculacion ");
			LocalDate fechaMatriculacion=leerFecha(Entrada.cadena());
			Alumno alumnoIntroducido=alumno;
			ArrayList<Asignatura> asignaturasIntroducidas=asignaturas;
			

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
		
	
	public static ArrayList<Asignatura> elegirAsignaturasMatricula() {
			System.out.println("Indica el numero de asignaturas que vas a introducir, debe ser mayor que 0");
			int numeroAsignaturas=Entrada.entero();
			ArrayList<Asignatura> coleccionAsignaturas=new ArrayList<Asignatura>();
			for (int i=0;i<numeroAsignaturas;i++) {
				coleccionAsignaturas.add(Consola.getAsignaturaPorCodigo());
			}
			return coleccionAsignaturas;
		}
	
	public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
		
		try {
			System.out.println("Introduce un id de matricula: ");
			int idMatricula=Entrada.entero();
			System.out.println(idMatricula);
			Grado grado=new GradoE("DW",1,1);
			Alumno alumno=new Alumno( "Tesi", "11111111H", "Tesi@gmail.com", "999999999", LocalDate.of(1979, 1, 8));
			CicloFormativo cicloFormativo =new CicloFormativo(1111,"Semipresencial",grado,"DAW",100);
			ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
			asignaturas.add(new Asignatura("2222","Programacion",100,Curso.PRIMERO,6,EspecialidadProfesorado.INFORMATICA,cicloFormativo));
			

			Matricula matricula =new Matricula(idMatricula,"23-24",LocalDate.now(),alumno,asignaturas);
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
