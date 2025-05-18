package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

import static org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.CiclosFormativos.cicloFormativoToElement;
import static org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML.*;

public class Asignaturas implements IAsignaturas {

	private static Asignaturas instancia;
	private ArrayList<Asignatura> coleccionAsignaturas;

	private static Asignaturas getInstancia() {
		if (instancia==null)
			instancia=new Asignaturas();

		return instancia;
	}

	public Asignaturas () {

		coleccionAsignaturas=new ArrayList<Asignatura>();

	}

	public ArrayList<Asignatura> get() {
		ArrayList<Asignatura> copia=copiaProfundaAsignaturas();
		return copia;
	}

	private ArrayList<Asignatura> copiaProfundaAsignaturas() {

		ArrayList<Asignatura>copiaAsignatura=new ArrayList<Asignatura>();

		for(Asignatura asignatura: coleccionAsignaturas){
			copiaAsignatura.add(asignatura);
		}

		return copiaAsignatura;
	}

	public int getTamano() {
		int tamano=0;

		for (int i=0;i<coleccionAsignaturas.size();i++) {
			if(coleccionAsignaturas.get(i)!=null) {tamano++;}
		}

		return tamano;
	}



	public void insertar(Asignatura asignatura) throws OperationNotSupportedException {

		if(asignatura!=null) {
			if(coleccionAsignaturas.contains(asignatura)) {
				throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese codigo.");
			}else {
				coleccionAsignaturas.add(asignatura);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
		}
	}

	public Asignatura buscar(Asignatura asignatura) {
		boolean encontrado=false;
		boolean noEncontrado=false;
		Asignatura asignaturaArrayCreado=null;

		if(asignatura!=null) {

			if(coleccionAsignaturas.size()>0) {

				for (Asignatura asignaturaArray:coleccionAsignaturas) {

					if(asignaturaArray.equals(asignatura)) {
						encontrado=true;
						asignaturaArrayCreado=asignaturaArray;
					}else {
						noEncontrado=true;
					}
				}
				if (encontrado!=true && noEncontrado==true) {
					return null;
				}else {
					return asignaturaArrayCreado;
				}
			}else {
				throw new NullPointerException("No hay asignaturas incluidas en la coleccion");
			}

		}
		else {
			throw new NullPointerException("asignatura recibido nulo");
		}
	}



	public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
		if(asignatura!=null) {
			if (coleccionAsignaturas.contains(asignatura)) {
				coleccionAsignaturas.remove(asignatura);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
		}
	}

	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		leerXml();
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		escribirXml();
	}

	public void leerXml(){

		//xmlToDom("datos/alumnos.xml");
		//Document doc=crearDomVacio("Alumnos");

		Document doc = xmlToDom("datos/asignaturas.xml");

		if (doc==null)
		{
			System.out.println("No se ha podido leer el fichero ");
		}
		else
		{
			Element raizDOM = doc.getDocumentElement();

			//Recorremos la lista de nodos del DOM
			NodeList listaNodos=raizDOM.getElementsByTagName("Asignatura");

			if (listaNodos.getLength()>0) {
				System.out.println("Datos de la asignatura:");
				System.out.println("=======================");

				for (int i=0; i<listaNodos.getLength();i++)
				{
					Node nodo=listaNodos.item(i);

					if(nodo.getNodeType() == Node.ELEMENT_NODE)
					{
						Element asignaturaDOM = (Element) nodo;
						Asignatura asignatura=elementToAsignatura(asignaturaDOM);
						coleccionAsignaturas.add(asignatura);
						System.out.println(asignatura);
					}
				}

				System.out.println("Fichero de asignaturas leido correctamente.");
			}
			else
				System.out.println("No hay datos de asignaturas en el fichero xml proporcionado");

		}
	}


	public void escribirXml(){
		Document DOMAsignatura=crearDomVacio("Asignaturas");
		Element raizDOM = DOMAsignatura.getDocumentElement();

		if(coleccionAsignaturas!=null){

			for(Asignatura asignatura :coleccionAsignaturas){
				Element cicloDOM = asignaturaToElement(DOMAsignatura, asignatura);
				raizDOM.appendChild(cicloDOM);
			}
		}

		//Convertimos nuestro arbol DOM en un fichero xml
		domToXml(DOMAsignatura, "datos/asignaturas.xml");
	}


	private static Element asignaturaToElement(Document doc, Asignatura asignatura) {
		Element asignaturaDOM = doc.createElement("Asignatura");
		asignaturaDOM.setAttribute("Codigo", String.valueOf(asignatura.getCodigo()));

		Element eNombre = doc.createElement("Nombre");
		eNombre.setTextContent(asignatura.getNombre());
		asignaturaDOM.appendChild(eNombre);

		Element eCurso = doc.createElement("Curso");
		eCurso.setTextContent(asignatura.getCurso().name()); // Enum
		asignaturaDOM.appendChild(eCurso);

		Element eEspecialidad = doc.createElement("EspecialidadProfesorado");
		eEspecialidad.setTextContent(asignatura.getEspecialidadProfesorado().name()); // Enum
		asignaturaDOM.appendChild(eEspecialidad);


		Element eCiclo = cicloFormativoToElement(doc, asignatura.getCicloFormativo());
		asignaturaDOM.appendChild(eCiclo);

		Element eHoras = doc.createElement("Horas");

		Element eAnuales = doc.createElement("Anuales");
		eAnuales.setTextContent(String.valueOf(asignatura.getHorasAnuales()));
		eHoras.appendChild(eAnuales);

		Element eDesdoble = doc.createElement("Desdoble");
		eDesdoble.setTextContent(String.valueOf(asignatura.getHorasDesdoble()));
		eHoras.appendChild(eDesdoble);

		asignaturaDOM.appendChild(eHoras);

		return asignaturaDOM;
	}

	private static Asignatura elementToAsignatura(Element asignaturaDOM) {



		String codigo = asignaturaDOM.getAttribute("Codigo");

		String nombre = asignaturaDOM.getElementsByTagName("Nombre").item(0).getTextContent();
		Curso curso = Curso.valueOf(asignaturaDOM.getElementsByTagName("Curso").item(0).getTextContent());
		EspecialidadProfesorado especialidadProfesorado = EspecialidadProfesorado.valueOf(asignaturaDOM.getElementsByTagName("EspecialidadProfesorado").item(0).getTextContent());

		// Recuperar el nodo <CicloFormativo> dentro de la asignatura
		Element eCiclo = (Element) asignaturaDOM.getElementsByTagName("CicloFormativo").item(0);
		int codigoCiclo=Integer.parseInt(eCiclo.getTextContent());
		Grado gradoFicticio=new GradoE("DW",1,1);
		CicloFormativo cicloFormativoFicticio =new CicloFormativo(codigoCiclo,"Semipresencial",gradoFicticio,"DAW",100);


		CiclosFormativos ciclosFormativos1=CiclosFormativos.getInstancia();
		ArrayList <CicloFormativo> copiaArray=ciclosFormativos1.get();


		CicloFormativo ciclosFormativosLocalizado=ciclosFormativos1.buscar(cicloFormativoFicticio);



		Element eHoras = (Element) asignaturaDOM.getElementsByTagName("Horas").item(0);
		int horasAnuales = Integer.parseInt(eHoras.getElementsByTagName("Anuales").item(0).getTextContent());
		int horasDesdoble = Integer.parseInt(eHoras.getElementsByTagName("Desdoble").item(0).getTextContent());

		return new Asignatura(codigo, nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado, ciclosFormativosLocalizado);
	}


}
