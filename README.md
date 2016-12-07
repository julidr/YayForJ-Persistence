<img src="readmeStyle/yayForJComplete.png" align="right" />
# YayForJ-Persistence [![yayForJicon](readmeStyle/yayForJMini.png)](https://github.com/julidr/YayForJ-Persistence)
>DAO generico en el cual solo se requiere implementar una clase abstracta.

![Works](readmeStyle/works.PNG)

## Requerimientos [![yayForJicon](readmeStyle/yayForJDependencyMini.png)](https://github.com/julidr/YayForJ-Persistence/blob/master/pom.xml)
Muchos de los requerimientos ya vienen incluidos en el POM del proyecto, asi que si pueden abrir el proyecto como un proyecto Maven lo más seguro es que les descargue de una vez las dependencias. En cualquier caso dejo el listado:

* Mysql-Connector-Java
  * Version: 5.1.6
  
## Contenido del Proyecto [![yayForJicon](readmeStyle/yayForJDependencyMini.png)](https://github.com/julidr/YayForJ-Persistence/tree/master/src/main/java/co/edu/usa/adf/YayForJ_Persistencel)
El contenido del proyecto es bastante simple, considerando que la mayoria de archivos son generados por eclipse y maven para que el proyecto funcione. Por lo que me limitare a explicar los archivos importantes del proyecto, tales como el Pom, los paquetes y las clases.

* Pom.xml
 * Si se encuentran familiarizados con Maven, saben que el pom es el documento en donde se establecen las dependencias del proyecto, el cual va y las busca en el repositorio local para no tener que estar agregando los .jar a cada proyecto. En caso de que la dependencia no se encuentre en el repositorio local, la descarga de internet.