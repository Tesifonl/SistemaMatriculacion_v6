# Tarea: Sistema de Matriculación
## Profesor: Andrés Rubio del Río
## Alumno: Tesifón Linares Bonilla_v1

En este segundo spring de la tarea consistente en modelar la gestión del sistema de matriculación del instituto IES Al-Ándalus,  se va a continuar usando la implementación basada en arrays para la gestión de las colecciones. Pero añadiremos el patrón MVC, haciendo una primera aproximación al mismo que poco a poco iremos mejorando cuando se vayan adquiriendo los conocimientos necesarios. Por tal motivo, haremos una distinción entre la vista, encargada de interaccionar con el usuario, el modelo, encargado de gestionar los datos y de interactuar con las colecciones y que dividiremos entre clases de dominio y clases de negocio. Por último, pero no menos importante, el controlador que será el encargado de dirigir toda esta orquesta.
Debes tener en cuenta el problema existente con las referencias, por lo que, al igual que en el primer spring, deberás devolver referencias a objetos nuevos en los métodos de acceso. También deberás crear nuevas referencias a nuevos objetos cuando los vayamos a asignar a atributos. Además, en los métodos de las clases dominio deberás devolver una copia profunda de los elementos de la colección en dicho método de acceso.


Para ello te muestro un diagrama de clases para el mismo y poco a poco te iré explicando los diferentes pasos a realizar:

![](src/main/resources/SistemaMatriculacion_v1.png)



###Primeros Pasos

1.	Realiza un fork del repositorio de tu tarea anterior en otro nuevo llamado SistemaMatriculacion_v1. Dicho repositorio lo clonarás localmente y realizarás las diferentes modificaciones que se piden en esta tarea.
Modelo

###Modelo

1.	Crea la clase Modelo en el paquete indicado en el diagrama. Esta clase gestionará todo el modelo de datos de nuestra aplicación. Será la encargada de comunicarse con las tres clases que hacen referencia a las colecciones de datos (alumnos, asignaturas, ciclos formativos y matrículas).
2.	Crea el método comenzar que creará la instancia de las clases de negocio.
3.	Crea el método terminar que simplemente mostrará un mensaje informativo indicando que el modelo ha terminado.
4.	Crea los diferentes métodos insertar (para Alumno, Asignatura, Ciclo Formativo y Matricula).
5.	Crea los diferentes métodos buscar, cada uno de los cuales devolverá una nueva instancia del elemento encontrado si éste existe.
6.	Crea los diferentes métodos borrar (para Alumno, Asignatura, Ciclo Formativo y Matricula).
7.	Crea los diferentes métodos get definidos en el diagrama de clases, que deben devolver una lista de los diferentes elementos de la aplicación (Alumnos, Asignaturas, Ciclos Formativos y Matrículas).
8.	Realiza un commit con la nueva clase creada.
Controlador

###Controlador

1.	Crea la clase Controlador en el paquete indicado en el diagrama. Esta clase será la encargada de hacer de intermediario entre la vista y el modelo.
2.	Crea los atributos adecuados.
3.	Crea el constructor con parámetros que comprobará que no son nulos y los asignará a los atributos. Además, recuerda llamar al método setControlador de la vista con una instancia suya.
4.	Crea los métodos comenzar y terminar, que llamarán a los correspondientes métodos en el modelo y en la vista.
5.	Crea los demás métodos que realizarán operaciones de insertar, buscar, borrar y listar. Éstos simplemente harán una llamada al correspondiente método del modelo.
6.	Realiza un commit con la nueva clase creada.
Consola

####Consola

1.	Modifica los métodos mostrarCiclosFormativos y mostrarAsignaturas. A partir de ahora tendrán como parámetro de entrada un array de elementos en vez de la clase de negocio.
2.	Crea el método elegirAsignaturasMatricula para obtener el array de asignaturas que se asignarán en una matrícula.
3.	Modifica el método leerMatricula para que acepte como parámetros un objeto Alumno y un array de Asignaturas previamente elegidas.


####Vista

1.	Crea la clase Vista en el paquete indicado en el diagrama.
2.	Crea los diferentes atributos que se indican en el diagrama de clases con su visibilidad adecuada.
3.	Excepto el método main, mueve todos los métodos existentes en la clase MainApp del spring anterior a la clase Vista.
4.	Crea el método setControlador que asignará el controlador pasado al atributo si éste no es nulo.
5.	Crea el método comenzar que mostrará el menú, leerá una opción de consola y la ejecutará. Repetirá este proceso mientras la opción elegida no sea la correspondiente a salir. Utilizará los correspondientes métodos de la clase Consola.
6.	Crea el método terminar que simplemente mostrará un mensaje de despedida por consola.
7.	Modifica los métodos de insertar, buscar, borrar y listar. Recuerda que debes llamar a los correspondientes métodos del Controlador para realizar las operaciones.
1.	Modifica el método insertarMatricula para que utilice los métodos leerAlumno, elegirAsignaturasMatricula y leerMatricula de la clase Consola.
8.	Realiza un commit con la nueva clase creada.

####MainApp

1.	Modifica la clase MainApp con un único método main que será el método de entrada a nuestra aplicación. Este método simplemente creará una vista, un modelo y un controlador, pasándoles las instancias antes creadas. Luego simplemente invocará al método comenzar del controlador.
2.	Realiza las pruebas que estimes oportunas y cuando consideres que todo es correcto, realiza el último commit y seguidamente realiza el push a tu repositorio remoto.

####Se valorará:

•	La indentación debe ser correcta en cada uno de los apartados.
•	El nombre de las variables debe ser adecuado.
•	Toda la implementación realizada deberá ajustarse a lo indicado en este apartado y a lo mostrado en el diagrama de clase. En caso contrario, lo que se use que no esté indicado en el diagrama de clase no será considerado y por tanto, la parte correspondiente no será evaluada.
•	Se debe utilizar la clase Entrada para realizar la entrada por teclado.
•	El programa debe pasar todas las pruebas que van en el esqueleto del proyecto y toda entrada del programa será validada, para evitar que el programa termine abruptamente debido a una excepción.
•	La corrección ortográfica tanto en los comentarios como en los mensajes que se muestren al usuario.
•	Para calificar cada uno de los criterios de evaluación asociados a la tarea será imprescindible que el código compile correctamente y se pueda ejecutar. En caso contrario, los criterios de evaluación serán calificados con un 0.

