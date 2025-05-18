package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;

import static org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML.*;

public class CiclosFormativos implements ICiclosFormativos {
	

	private static CiclosFormativos instancia;
	private  ArrayList <CicloFormativo> coleccionCiclosFormativos;

	
	public static CiclosFormativos getInstancia() {
		if (instancia==null)
			instancia=new CiclosFormativos();

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


	public CiclosFormativos () {

		if (coleccionCiclosFormativos==null){
			coleccionCiclosFormativos=new ArrayList<>();
		};

	}

	public static Element cicloFormativoToElement(Document doc, CicloFormativo ciclo) {
		Element cicloDOM = doc.createElement("CicloFormativo");
		cicloDOM.setAttribute("Codigo", String.valueOf(ciclo.getCodigo()));

		Element eNombre = doc.createElement("Nombre");
		eNombre.setTextContent(ciclo.getNombre());
		cicloDOM.appendChild(eNombre);

		Element eFamilia = doc.createElement("FamiliaProfesional");
		eFamilia.setTextContent(ciclo.getFamiliaProfesional());
		cicloDOM.appendChild(eFamilia);

		Element eHoras = doc.createElement("Horas");
		eHoras.setTextContent(String.valueOf(ciclo.getHoras()));
		cicloDOM.appendChild(eHoras);

		Grado grado = ciclo.getGrado();
		Element eGrado = doc.createElement("Grado");
		eGrado.setAttribute("Tipo", grado.getClass().getSimpleName()); // GradoD o GradoE

		Element eNombreGrado = doc.createElement("Nombre");
		eNombreGrado.setTextContent(grado.getNombre());
		eGrado.appendChild(eNombreGrado);

		Element eNumAnios = doc.createElement("NumAnios");
		eNumAnios.setTextContent(String.valueOf(grado.getNumAnios()));
		eGrado.appendChild(eNumAnios);

		if (grado instanceof GradoD) {
			GradoD g = (GradoD) grado;
			Element eModalidad = doc.createElement("Modalidad");
			eModalidad.setTextContent(g.getModalidad().name());
			eGrado.appendChild(eModalidad);
		} else if (grado instanceof GradoE) {
			GradoE g = (GradoE) grado;
			Element eNumEdiciones = doc.createElement("NumEdiciones");
			eNumEdiciones.setTextContent(String.valueOf(g.getNumEdiciones()));
			eGrado.appendChild(eNumEdiciones);
		}

		cicloDOM.appendChild(eGrado);

		return cicloDOM;
	}


	public static CicloFormativo elementToCicloFormativo(Element cicloDOM) {
		int codigo = Integer.parseInt(cicloDOM.getAttribute("Codigo"));
		String nombre = cicloDOM.getElementsByTagName("Nombre").item(0).getTextContent();
		String familia = cicloDOM.getElementsByTagName("FamiliaProfesional").item(0).getTextContent();
		int horas = Integer.parseInt(cicloDOM.getElementsByTagName("Horas").item(0).getTextContent());

		Element eGrado = (Element) cicloDOM.getElementsByTagName("Grado").item(0);
		String tipoGrado = eGrado.getAttribute("Tipo");

		String nombreGrado = eGrado.getElementsByTagName("Nombre").item(0).getTextContent();
		int numAnios = Integer.parseInt(eGrado.getElementsByTagName("NumAnios").item(0).getTextContent());

		Grado grado;

		switch (tipoGrado) {
			case "GradoD":
				Modalidad modalidad = Modalidad.valueOf(
						eGrado.getElementsByTagName("Modalidad").item(0).getTextContent()
				);
				grado = new GradoD(nombreGrado, numAnios, modalidad);
				break;
			case "GradoE":
				int numEdiciones = Integer.parseInt(
						eGrado.getElementsByTagName("NumEdiciones").item(0).getTextContent()
				);
				grado = new GradoE(nombreGrado, numAnios, numEdiciones);
				break;
			default:
				throw new IllegalArgumentException("Tipo de grado desconocido: " + tipoGrado);
		}

		return new CicloFormativo(codigo, familia, grado, nombre,horas);
	}




	public void leerXml(){

		//xmlToDom("datos/alumnos.xml");
		//Document doc=crearDomVacio("Alumnos");

		Document doc = xmlToDom("datos/ciclos.xml");

		if (doc==null)
		{
			System.out.println("No se ha podido leer el fichero ");
		}
		else
		{
			Element raizDOM = doc.getDocumentElement();

			//Recorremos la lista de nodos del DOM
			NodeList listaNodos=raizDOM.getElementsByTagName("CicloFormativo");

			if (listaNodos.getLength()>0) {
				System.out.println("Datos de las ciclos formativos:");
				System.out.println("=======================");

				for (int i=0; i<listaNodos.getLength();i++)
				{
					Node nodo=listaNodos.item(i);

					if(nodo.getNodeType() == Node.ELEMENT_NODE)
					{
						Element cicloDOM = (Element) nodo;
						CicloFormativo cicloFormativo=elementToCicloFormativo(cicloDOM);
						coleccionCiclosFormativos.add(cicloFormativo);
						System.out.println(cicloFormativo);
					}
				}

				System.out.println("Fichero de ciclos leido correctamente.");
			}
			else
				System.out.println("No hay datos de ciclos en el fichero xml proporcionado");

		}
	}


	public void escribirXml(){
		Document DOMCicloFormativo=crearDomVacio("Ciclos");
		Element raizDOM = DOMCicloFormativo.getDocumentElement();

		if(coleccionCiclosFormativos!=null){

			for(CicloFormativo cicloFormativo :coleccionCiclosFormativos){
				Element cicloDOM = cicloFormativoToElement(DOMCicloFormativo, cicloFormativo);
				raizDOM.appendChild(cicloDOM);
			}
		}

		//Convertimos nuestro arbol DOM en un fichero xml
		domToXml(DOMCicloFormativo, "datos/ciclos.xml");
	}

	public ArrayList<CicloFormativo> get() {
		ArrayList<CicloFormativo> copia=copiaProfundaCiclosFormativos();
		return copia;
	}

	private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {

		ArrayList<CicloFormativo>copiaCicloFormativo=new ArrayList<CicloFormativo>();

		for(CicloFormativo cicloFormativo: coleccionCiclosFormativos) {
			copiaCicloFormativo.add(cicloFormativo);
		}

		return copiaCicloFormativo;
	}

	public int getTamano() {
		int tamano=0;

		for (int i=0;i<coleccionCiclosFormativos.size();i++) {
			if(coleccionCiclosFormativos.get(i)!=null) {tamano++;}
		}

		return tamano;
	}




	public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {


		if (cicloFormativo!=null) {

			if(coleccionCiclosFormativos.contains(cicloFormativo)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese codigo.");
			}else {
				coleccionCiclosFormativos.add(cicloFormativo);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
		}
	}



	public CicloFormativo buscar(CicloFormativo cicloFormativo) {
		boolean encontrado=false;
		boolean noEncontrado=false;
		CicloFormativo cicloFormativoArrayCreado=null;

		if(cicloFormativo!=null) {

			if(coleccionCiclosFormativos.size()>0) {

				for (CicloFormativo cicloFormativoArray:coleccionCiclosFormativos) {

					if(cicloFormativoArray.equals(cicloFormativo)) {
						encontrado=true;
						cicloFormativoArrayCreado=cicloFormativoArray;
					}else {
						noEncontrado=true;
					}
				}
				if (encontrado!=true && noEncontrado==true) {
					return null;
				}else {
					return cicloFormativoArrayCreado;
				}
			}else {
				throw new NullPointerException("No hay ciclos formativos incluidos en la coleccion");
			}
		}
		else {
			throw new NullPointerException("ciclo formativo recibido nulo");
		}
	}


	public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		if(cicloFormativo!=null) {
			if (coleccionCiclosFormativos.contains(cicloFormativo)) {
				coleccionCiclosFormativos.remove(cicloFormativo);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
		}
	}


}
