package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.FuenteDatosMySQL;

public enum FactoriaFuenteDatos {
	
	MEMORIA {
		@Override
		IFuenteDatos crear() {
			// TODO Auto-generated method stub
			return new FuenteDatosMemoria();
		}
	},
	MYSQL {
		@Override
		IFuenteDatos crear() {
			// TODO Auto-generated method stub
			return new FuenteDatosMySQL ();
		}
	};
	
	abstract IFuenteDatos crear();
	
	// Con esto obligo a que los enumerados tengan un metodo asociado.

}
