package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML.*;


public class Matriculas implements IMatriculas{


	private ArrayList<Matricula> coleccionMatriculas;
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Matriculas () {

		coleccionMatriculas=new ArrayList<Matricula> ();


	}

	public ArrayList<Matricula> get() throws OperationNotSupportedException {
		ArrayList<Matricula> copia=copiaProfundaMatriculas();
		return copia;
	}

	private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {

		ArrayList<Matricula>copiaMatricula=new ArrayList<Matricula>();

		for(Matricula matricula: coleccionMatriculas) {
			copiaMatricula.add(matricula);
		}
		return copiaMatricula;
	}


	public int getTamano() {
		int tamano=0;

		for (int i=0;i<coleccionMatriculas.size();i++) {
			if(coleccionMatriculas.get(i)!=null) {tamano++;}
		}

		return tamano;
	}



	public void insertar(Matricula matricula) throws OperationNotSupportedException {

		if(matricula!=null) {
			if(coleccionMatriculas.contains(matricula)) {
				throw new OperationNotSupportedException("ERROR: Ya existe una matricula con ese codigo.");
			}else {
				coleccionMatriculas.add(matricula);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
		}
	}


	public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean noEncontrado=false;
		Matricula matriculaArrayCreado=null;

		if(matricula!=null) {

			if(coleccionMatriculas.size()>0) {

				for (Matricula matriculaArray:coleccionMatriculas) {

					if(matriculaArray.equals(matricula)) {
						encontrado=true;
						matriculaArrayCreado=matriculaArray;
					}else {
						noEncontrado=true;
					}
				}
				if (encontrado!=true && noEncontrado==true) {
					return null;
				}else {
					return matriculaArrayCreado;
				}
			}else {
				throw new NullPointerException("No hay matriculas incluidas en la coleccion");
			}
		}
		else {
			throw new NullPointerException("matricula recibido nulo");
		}
	}



	public void borrar(Matricula matricula) throws OperationNotSupportedException {
		if(matricula!=null) {
			if (coleccionMatriculas.contains(matricula)) {
				//coleccionMatriculas.remove(matricula);

				for (int i=0;i<coleccionMatriculas.size();i++) {
					if(coleccionMatriculas.get(i).equals(matricula)) {
						Matricula matriculaElegida=coleccionMatriculas.get(i);
						matriculaElegida.setFechaAnulacion(LocalDate.now());;
					}
				}
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
		}
	}



	public ArrayList<Matricula> get (Alumno alumno) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		ArrayList<Matricula> nuevaColeccion=new ArrayList<Matricula>();


		if (alumno!=null) {
			for (Matricula matricula:coleccionMatriculas) {
				if (matricula.getAlumno().equals(alumno)) {
					encontrado=true;
					nuevaColeccion.add(matricula);

				}
				else {
					otro=false;
				}
			}

			if (encontrado==true) {
				return nuevaColeccion;
			}
			else {
				return null;
			}
		}
		else {
			throw new NullPointerException("ERROR: Se ha recibido un alumno nulo");
		}
	}

	public ArrayList<Matricula> get (String cursoAcademico){
		boolean encontrado=false;
		boolean otro=false;
		ArrayList<Matricula> nuevaColeccion=new ArrayList<Matricula>();


		if (cursoAcademico!=null) {

			for (Matricula matricula:coleccionMatriculas) {
				if (matricula.getCursoAcademico().equals(cursoAcademico)) {
					encontrado=true;
					nuevaColeccion.add(matricula);

				}
				else {
					otro=false;
				}
			}

			if (encontrado==true) {
				return nuevaColeccion;
			}
			else {
				return null;
			}
		}
		else {
			throw new NullPointerException("ERROR: Se ha recibido un curso academico nulo");
		}
	}

	public ArrayList<Matricula> get (CicloFormativo cicloFormativo) throws OperationNotSupportedException{
		boolean encontrado=false;
		boolean otro=false;
		ArrayList<Matricula> nuevaColeccion=new ArrayList<Matricula>();



		if (cicloFormativo!=null) {
			for (Matricula matricula:coleccionMatriculas) {

				ArrayList<Asignatura> nuevaColeccionAsignatura=matricula.getColeccionAsignaturas();

				for (Asignatura asignatura: nuevaColeccionAsignatura)
					if (asignatura.getCicloFormativo().equals(cicloFormativo))
					{encontrado=true;
						nuevaColeccion.add(matricula);
					}
					else {
						otro=false;
					}


			}
			if (encontrado==true) {
				return nuevaColeccion;
			}
			else {
				return null;
			}

		}
		else {
			throw new NullPointerException("ERROR: Se ha recibido un ciclo formativo nulo");
		}

	}

	@Override
	public void comenzar() throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		leerXml();
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		escribirXml();
	}

	public void leerXml() throws OperationNotSupportedException {

		//xmlToDom("datos/alumnos.xml");
		//Document doc=crearDomVacio("Alumnos");

		Document doc = xmlToDom("datos/matriculas.xml");

		if (doc==null)
		{
			System.out.println("No se ha podido leer el fichero matriculas ");
		}
		else
		{
			Element raizDOM = doc.getDocumentElement();

			//Recorremos la lista de nodos del DOM
			NodeList listaNodos=raizDOM.getElementsByTagName("Asignatura");

			if (listaNodos.getLength()>0) {
				System.out.println("Datos de la matricula:");
				System.out.println("=======================");

				for (int i=0; i<listaNodos.getLength();i++)
				{
					Node nodo=listaNodos.item(i);

					if(nodo.getNodeType() == Node.ELEMENT_NODE)
					{
						Element matriculaDOM = (Element) nodo;
						Matricula matricula=elementToMatricula(matriculaDOM);
						coleccionMatriculas.add(matricula);
						System.out.println(matricula);
					}
				}

				System.out.println("Fichero de matriculas leido correctamente.");
			}
			else
				System.out.println("No hay datos de matriculas en el fichero xml proporcionado");

		}
	}


	public void escribirXml(){
		Document DOMMatricula=crearDomVacio("Matriculas");
		Element raizDOM = DOMMatricula.getDocumentElement();

		if(coleccionMatriculas!=null){

			for(Matricula matricula :coleccionMatriculas){
				Element cicloDOM = matriculaToElement(DOMMatricula, matricula);
				raizDOM.appendChild(cicloDOM);
			}
		}

		//Convertimos nuestro arbol DOM en un fichero xml
		domToXml(DOMMatricula, "datos/matriculas.xml");
	}

	private static Element matriculaToElement(Document doc, Matricula matricula) {
		Element eMatricula = doc.createElement("Matricula");
		eMatricula.setAttribute("Id", String.valueOf(matricula.getIdMatricula()));
		eMatricula.setAttribute("Alumno", matricula.getAlumno().getDni());

		Element eCurso = doc.createElement("Curso");
		eCurso.setTextContent(matricula.getCursoAcademico());
		eMatricula.appendChild(eCurso);

		Element eFechaMatriculacion = doc.createElement("FechaMatriculacion");
		eFechaMatriculacion.setTextContent(matricula.getFechaMatriculacion().format(FORMATO_FECHA));
		eMatricula.appendChild(eFechaMatriculacion);

		if (matricula.getFechaAnulacion() != null) {
			Element eFechaAnulacion = doc.createElement("FechaAnulacion");
			eFechaAnulacion.setTextContent(matricula.getFechaAnulacion().format(FORMATO_FECHA));
			eMatricula.appendChild(eFechaAnulacion);
		}

		Element eAsignaturas = doc.createElement("Asignaturas");
		for (Asignatura a : matricula.getColeccionAsignaturas()) {
			Element eAsignatura = doc.createElement("Asignatura");
			eAsignatura.setAttribute("Codigo", String.valueOf(a.getCodigo()));
			eAsignaturas.appendChild(eAsignatura);
		}

		eMatricula.appendChild(eAsignaturas);

		return eMatricula;
	}

	private static Matricula elementToMatricula(Element eMatricula) throws OperationNotSupportedException {
		// Obtener listas desde clases externas
		ArrayList<Alumno> alumnos = new Alumnos().get();
		ArrayList<Asignatura> asignaturasDisponibles = new Asignaturas().get();

		int id = Integer.parseInt(eMatricula.getAttribute("Id"));
		String dniAlumno = eMatricula.getAttribute("Alumno");

		// Buscar el alumno por DNI
		Alumno alumno = null;
		for (Alumno a : alumnos) {
			if (a.getDni().equals(dniAlumno)) {
				alumno = a;
				break;
			}
		}
		if (alumno == null) {
			throw new IllegalArgumentException("Alumno con DNI " + dniAlumno + " no encontrado.");
		}

		String curso =eMatricula.getElementsByTagName("Curso").item(0).getTextContent();

		LocalDate fechaMatriculacion = LocalDate.parse(
				eMatricula.getElementsByTagName("FechaMatriculacion").item(0).getTextContent(),
				FORMATO_FECHA
		);

		// Fecha de anulación (opcional)
		LocalDate fechaAnulacion = null;
		NodeList fechaAnulacionNodes = eMatricula.getElementsByTagName("FechaAnulacion");
		if (fechaAnulacionNodes.getLength() > 0) {
			fechaAnulacion = LocalDate.parse(fechaAnulacionNodes.item(0).getTextContent(), FORMATO_FECHA);
		}

		// Buscar asignaturas por código
		ArrayList<Asignatura> asignaturasMatriculadas = new ArrayList<>();
		NodeList asignaturaNodes = ((Element) eMatricula.getElementsByTagName("Asignaturas").item(0)).getElementsByTagName("Asignatura");

		for (int i = 0; i < asignaturaNodes.getLength(); i++) {
			Element eAsignatura = (Element) asignaturaNodes.item(i);
			String codigo = eAsignatura.getAttribute("Codigo");

			for (Asignatura asignatura : asignaturasDisponibles) {
				if (asignatura.getCodigo().equals(codigo)) {
					asignaturasMatriculadas.add(asignatura);
					break;
				}
			}
		}

		return new Matricula(id,curso, fechaMatriculacion,alumno,asignaturasMatriculadas);
	}





}
