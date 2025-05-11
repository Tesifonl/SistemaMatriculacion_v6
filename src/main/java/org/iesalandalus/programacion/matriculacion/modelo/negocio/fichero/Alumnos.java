package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML.*;

public class Alumnos implements IAlumnos{


	private ArrayList <Alumno> coleccionAlumnos;
	private static Alumnos instancia;
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");


	
	private static Alumnos getInstancia() {
		if (instancia==null)
			instancia=new Alumnos();

		return instancia;
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



	private static Element alumnoToElement(Document DOMAlumnos, Alumno alumno)
	{
		Element alumnoDOM = DOMAlumnos.createElement("Empleado");
		alumnoDOM.setAttribute("dni", String.valueOf(alumno.getDni()));

		Element eNombre = DOMAlumnos.createElement("nombre");
		eNombre.setTextContent(alumno.getNombre());
		alumnoDOM.appendChild(eNombre);

		Element eTelefono = DOMAlumnos.createElement("telefono");
		eTelefono.setTextContent(alumno.getTelefono());
		alumnoDOM.appendChild(eTelefono);

		Element eCorreo = DOMAlumnos.createElement("correo");
		eCorreo.setTextContent(alumno.getCorreo());
		alumnoDOM.appendChild(eCorreo);

		Element eFechaNacimiento = DOMAlumnos.createElement("fechaNacimiento");
		eFechaNacimiento.setTextContent(alumno.getFechaNacimiento().format(FORMATO_FECHA));
		alumnoDOM.appendChild(eFechaNacimiento);

		return alumnoDOM;
	}



	private static Alumno elementToAlumno(Element alumnoDOM)
    {
        String aDni = alumnoDOM.getAttribute("dni");

        Element eNombre = (Element) alumnoDOM.getElementsByTagName("nombre").item(0);
        Element eTelefono = (Element) alumnoDOM.getElementsByTagName("telefono").item(0);
		Element eCorreo = (Element) alumnoDOM.getElementsByTagName("correo").item(0);
		Element eFechaNacimiento = (Element) alumnoDOM.getElementsByTagName("fechaNacimiento").item(0);

        String aNombre= eNombre.getTextContent();
		String aTelefono= eTelefono.getTextContent();
		String aCorreo= eCorreo.getTextContent();
		String aFechaNacimiento= eFechaNacimiento.getTextContent();

        return new Alumno(aDni, aNombre, aTelefono, aCorreo,LocalDate.parse(aFechaNacimiento,FORMATO_FECHA));
    }



	public void leerXml(){

		xmlToDom("datos/alumnos.xml");
		Document doc=crearDomVacio("Alumnos");

		if (doc==null)
		{
			System.out.println("No se ha podido leer el fichero ");
		}
		else
		{
			Element raizDOM = doc.getDocumentElement();

			//Recorremos la lista de nodos del DOM
			NodeList listaNodos=raizDOM.getElementsByTagName("alumno");

			if (listaNodos.getLength()>0) {
				System.out.println("Datos de los alumnos:");
				System.out.println("=======================");

				for (int i=0; i<listaNodos.getLength();i++)
				{
					Node nodo=listaNodos.item(i);

					if(nodo.getNodeType() == Node.ELEMENT_NODE)
					{
						Element alumnoDOM = (Element) nodo;
						Alumno alumno=elementToAlumno(alumnoDOM);
						coleccionAlumnos.add(alumno);
						System.out.println(alumno);
					}
				}

				System.out.println("Fichero de alumnos leido correctamente.");
			}
			else
				System.out.println("No hay datos de alumnos en el fichero xml proporcionado");

		}
	}


	public void escribirXml(){
		Document DOMAlumnos=crearDomVacio("Alumnos");
		Element raizDOM = DOMAlumnos.getDocumentElement();

		if(coleccionAlumnos!=null){

			for(Alumno alumno :coleccionAlumnos){
				Element alumnoDOM = alumnoToElement(DOMAlumnos, alumno);
				raizDOM.appendChild(alumnoDOM);
			}
		}

		//Convertimos nuestro arbol DOM en un fichero xml
		domToXml(DOMAlumnos, "datos/alumnos.xml");
	}



	public Alumnos () {

		coleccionAlumnos=new ArrayList <Alumno>();

	}

	public ArrayList<Alumno> get() {
		ArrayList<Alumno> copia=copiaProfundaAlumnos();
		return copia;
	}


	private ArrayList<Alumno> copiaProfundaAlumnos() {

		ArrayList<Alumno>copiaAlumnos=new ArrayList<Alumno>();

		for(Alumno alumno:coleccionAlumnos) {
			copiaAlumnos.add(alumno);
		}
		return copiaAlumnos;
	}


	public int getTamano() {
		int tamano=0;

		for (int i=0; i<coleccionAlumnos.size();i++) {
			if(coleccionAlumnos.get(i)!=null) {tamano++;}
		}

		return tamano;
	}



	public void insertar(Alumno alumno) throws OperationNotSupportedException {

		if(alumno!=null) {
			if(coleccionAlumnos.contains(alumno)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
			}else {
				coleccionAlumnos.add(alumno);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
	}


	public Alumno buscar(Alumno alumno) {

		boolean encontrado=false;
		boolean noEncontrado=false;
		Alumno AlumnoArrayCreado=null;

		if(alumno!=null) {

			if (coleccionAlumnos.size()>0) {

				for (Alumno alumnoArray:coleccionAlumnos) {

					if(alumnoArray.equals(alumno)) {
						encontrado=true;
						AlumnoArrayCreado=alumnoArray;
					}else {
						noEncontrado=true;
					}
				}
				if (encontrado!=true && noEncontrado==true) {
					return null;
				}else {
					return AlumnoArrayCreado;
				}
			}
			else {
				throw new NullPointerException("No hay alumnos incluidos en la coleccion");
			}
		}
		else {
			throw new NullPointerException("alumno recibido nulo");
		}
	}


	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if(alumno!=null) {
			if (coleccionAlumnos.contains(alumno)) {
				coleccionAlumnos.remove(alumno);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
		}
	}



}



