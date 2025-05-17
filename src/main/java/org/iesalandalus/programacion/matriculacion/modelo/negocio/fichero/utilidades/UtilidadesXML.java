package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class UtilidadesXML {


    public static Document crearDomVacio(String etiquetaRaiz)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc = null ;
        try {
            db = dbf.newDocumentBuilder() ;
            doc = db.newDocument();
            Element elementoRaiz=doc.createElement(etiquetaRaiz);
            doc.appendChild(elementoRaiz);
            return doc;
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
        return doc ;
    }


    public static boolean domToXml (Document DOM, String rutaXml)
    {
        try
        {
            File f=new File(rutaXml);

            FileOutputStream fos =new FileOutputStream(f);

            //Creamos la fuente del fichero XML
            DOMSource source = new DOMSource(DOM);
            //Creamos el StreamResult, que intermediar� entre el transformador y el archivo de destino.
            StreamResult result = new StreamResult(new OutputStreamWriter(fos,"UTF-8"));

            // Creamos un objeto transformer que transformara el DOM en el fichero xml
            //Creamos una fabrica de transformadores de DOM
            TransformerFactory tFactory=TransformerFactory.newInstance();

            //Establecemos algunas opciones de salida, como por ejemplo, la codificaci�n de salida.
            tFactory.setAttribute("indent-number", Integer.valueOf(4));
            Transformer transformer = tFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //Transformamos el arbol DOM en un fichero xml
            transformer.transform(source, result);
            System.out.printf("Fichero %s escrito correctamente.%n", rutaXml);
            return true;
        }
        catch (TransformerException | FileNotFoundException | UnsupportedEncodingException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }



    public static Document xmlToDom(String rutaXml) {
        //Creamos DOM a partir de XML
        Document doc=null;
        try {

            File file = new File(rutaXml);
            if (!file.exists()) {
                System.out.println(" El archivo no existe en la ruta: " + file.getAbsolutePath());
                return null;
            }
            // Creamos una nueva instancia de un fabrica de constructores de documentos.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // A partir de la instancia anterior, fabricamos un constructor de documentos, que procesara el XML.
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Procesamos el documento (almacenado en un archivo) y lo convertimos en un arbol DOM.
            doc=db.parse(file);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return doc;
    }

}
