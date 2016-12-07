<img src="readmeStyle/yayForJComplete.png" align="right" />
# YayForJ-Persistence [![yayForJicon](readmeStyle/yayForJMini.png)](https://github.com/julidr/YayForJ-Persistence)
>DAO generico en el cual solo se requiere implementar una clase abstracta.

![Works](readmeStyle/works.PNG)

## Requerimientos [![yayForJicon2](readmeStyle/yayForJDependencyMini.png)](https://github.com/julidr/YayForJ-Persistence/blob/master/pom.xml)
Muchos de los requerimientos ya vienen incluidos en el POM del proyecto, asi que si pueden abrir el proyecto como un proyecto Maven lo más seguro es que les descargue de una vez las dependencias. En cualquier caso dejo el listado:

* Mysql-Connector-Java
  * Version: 5.1.6

## Funcionamiento [![yayForJicon4](readmeStyle/yayForJCodeMini.png)](https://github.com/julidr/YayForJ-Persistence/blob/master/yaytest.sql)

Lo primero que se debe hacer para poder utilizar el proyecto de manera correcta, es crear los modelos que representan las entidades de la base de datos. Para este caso se creara la clase Trainer.

```sh
@Entity
@Table(name="trainers")
public class Trainer {
	
	@Id private int id;
	private String name;
	private String lastname;
	private int age;
	private Date birthday;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", lastname=" + lastname + ", age=" + age + ", birthday="
				+ birthday + "]";
	}
}
```
Lo destacable de esta clase son sus anotaciones:

* Entity
 * Esta anotación le indica al programa, que es la representante de una entidad en la base de datos. Si no se coloca se lanzara una excepción.

* Table
 * Esta anotación le indica el nombre de la tabla en la base de datos. En este caso, pese a que la clase se llama Trainer, en la base de datos, la tabla va en plurar y se llama trainers. Por lo que se indica el nombre de la tabla para buscar la referencia correcta. Si falta esta anotación el programa lanza una excepcion.

* Id
 * Esta anotación le indica al programa quien es el id de la clase. Si falta esta anotacion el programa lanza una excepcion.
 
Lo primero que se debe hacer para poder utilizar el proyecto es crear una instancia de YayPersistence en donde se ingresan los parametros de la base de datos.

En este caso hare el ejemplo del funcionamiento con la base de datos [YayTest](https://github.com/julidr/YayForJ-Persistence/blob/master/yaytest.sql).

```sh
YayPersistence persistenceController= new YayPersistence("jdbc:mysql://localhost:3306/yaytest", "root", "");
```

## Contenido del Proyecto [![yayForJicon](readmeStyle/yayForJDependencyMini.png)](https://github.com/julidr/YayForJ-Persistence/tree/master/src/main/java/co/edu/usa/adf/YayForJ_Persistencel)
El contenido del proyecto es bastante simple, considerando que la mayoria de archivos son generados por eclipse y maven para que el proyecto funcione. Por lo que me limitare a explicar los archivos importantes del proyecto, tales como el Pom, los paquetes y algunas clases.

* Pom.xml
 * Si se encuentran familiarizados con Maven, saben que el pom es el documento en donde se establecen las dependencias del proyecto, el cual va y las busca en el repositorio local para no tener que estar agregando los .jar a cada proyecto. En caso de que la dependencia no se encuentre en el repositorio local, la descarga de internet.
 
* Annotation
 * Existe un paquete dentro del proyecto que posee todas las anotaciones hasta el momento. Tales como Entity, Table y Id. <br>
    <img src="readmeStyle/annotation.PNG" align="center" />
    
* Logic
 * El paquete logic trae las clases importantes del proyecto. Posee un AnnotationReader que es la clase encargada de leer las anotaciones de una clase. Tambien incluye el ClassBuilder que es el encargado de construir los objetos dependiendo del tipo de clase que se esta manejando y el YayPersistence que es la encargada de hacer la conexión con la base de datos. <br>
 <img src="readmeStyle/logic.PNG" align="center" />
 
* Dao
 * Es el paquete que contiene el DAO generico. El cual es una simple clase abstracta que permite la implementación de metodos tales con FindById, FindAll, Save, Update, Delete y FindByX. <br>
 <img src="readmeStyle/dao.PNG" align="center" />