<img src="readmeStyle/yayForJComplete.png" align="right" />

# YayForJ-Persistence 

[![version](https://img.shields.io/badge/version-1.0.1-blue.svg)](https://github.com/julidr/YayForJ-Persistence)
[![msqlCon](https://img.shields.io/badge/Mysql--Connector--Java-5.1.6-yellowgreen.svg)](https://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.6)
[![log4j](https://img.shields.io/badge/log4j-1.2.17-yellowgreen.svg)](https://mvnrepository.com/artifact/log4j/log4j/1.2.17)
>DAO generico en el cual solo se requiere implementar una clase abstracta.

![Works](readmeStyle/works.PNG)

## Tabla de Contenidos
* [Requerimientos](#requerimientos)
* [Precauciones](#precauciones)
* [Funcionamiento](#funcionamiento)
* [Mejoras y Contacto](#mejoras-y-contacto)

## Requerimientos
Muchos de los requerimientos ya vienen incluidos en el POM del proyecto, asi que si pueden abrir el proyecto como un proyecto Maven lo más seguro es que les descargue de una vez las dependencias. En cualquier caso dejo el listado:

* Mysql-Connector-Java
  * Version: 5.1.6
* Log4j
    * Version: 1.2.17
* Junit
    * Version: 4.12

## Precauciones

* Solo Funciona con MySQL
    <i><br>El DAO generico fue pensando para que funcionara con MySQL.</i>
 
* Las anotaciones Entity, Table, ID y Column son Obligatorias.
    <i><br>Para que el programa logre reconocer cuales son las clases que representan el modelo, estas deben tener las anotaciones indicadas.</i>

* Para las inserciones en la base procuren no dejar atributos vacios excepto el id.
    <i><br>Es algo a mejorar a futuro, pero por ahora procuren no dejar el objeto a insertar con alguno de sus atributos vacios. El unico que puede y debe estar vacio es el id en caso de ser Autoincremental.</i>

* Las clases que reflejan el Modelo solo deben tener los campos que existen en la base de datos.

## Funcionamiento

Lo primero que se debe hacer para poder utilizar el proyecto de manera correcta, es crear los modelos que representan las entidades de la base de datos. Para este caso se creara la clase Trainer y haremos el ejemplo del funcionamiento con la base de datos [YayTest](https://github.com/julidr/YayForJ-Persistence/blob/master/yaytest.sql).

```sh
@Entity
@Table(name="trainers")
public class Trainer {

    @Id(isAutoincremental = true, name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "age")
    private int age;
    @Column(name = "birthday")
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
    <i><br>Esta anotación le indica al programa, que es la representante de una entidad en la base de datos. Si no se coloca se lanzara una excepción.</i>

* Table
    <i><br>Esta anotación le indica el nombre de la tabla en la base de datos. En este caso, pese a que la clase se llama Trainer, en la base de datos, la tabla va en plural y se llama trainers. Por lo que se indica el nombre de la tabla para buscar la referencia correcta. Si falta esta anotación el programa lanza una excepcion.</i>

* Id
    <i><br>Esta anotación le indica al programa quien es el id de la clase, ademas el nombre de la columna que lo contiene. Si falta esta anotacion el programa lanza una excepcion.</i>

* Column
    <i><br>Indica como se llama la columna en la base de datos. Debe agregarsele a todos los campos de la clase.</i>

Una vez el modelo esta hecho (y asumiendo que la base de datos tambien) se debe crear la instancia de la clase yayPersistence y mandar los datos necesarios de la base de datos, tales como la referencia, el usuario y contraseña, para establecer la conexión.

```sh
YayPersistence persistenceController= new YayPersistence("jdbc:mysql://localhost:3306/yaytest", "root", "");
```
Si todo se realizo de manera correcta, deberia salir un aviso de conexión exitosa en la consola. <br>
<img src="readmeStyle/yayConnect.PNG"/> <br>

Lo siguiente es extender la clase abstracta. Yo recomiendo que lo hagan en una clase donde tengan pensado manejar todos los asuntos del objeto del modelo y que no se limite solo a los metodos basicos que ya viene por defecto. Si no que puedan implementar sus propios metodos basados en InnerJoin y demas relaciones.

Para este ejemplo, cree una clase que se llama TrainerDAO y que extiende de DAOGenerator.

```sh
public class TrainerDAO extends DAOGenerator<Trainer>{

}
```
Ahora si instanciamos esta clase en nuestra aplicación podemos acceder a los 6 metodos que viene por default en el DAO generico.

```sh
TrainerDAO trainerDAO= new TrainerDAO();
```

* FindById
    <i><br>Recibe el id de un elemento en especifico de la base de datos y devuelve un objeto relacionado con la clase especificada.</i>
 ```sh
        Trainer trainer=trainerDAO.findById(1l, Trainer.class);
        System.out.println("nombre: " + trainer.getName());
        System.out.println("apellido: " + trainer.getLastname());
        System.out.println("edad: " + trainer.getAge());
        System.out.println("cumpleaños: " + trainer.getBirthday());
   ```
El resultado obtenido es efectivamente un objeto de la clase Trainer al cual se le puede acceder a los atributos sin problemas.
  <br>
  <img src="readmeStyle/yayFindById.PNG"/>

* FindAll
    <i><br>Devuelve un ArrayList de objetos segun la clase especificada.</i>
 
 ```sh
	ArrayList<Trainer> trainersList=trainerDAO.findAll(Trainer.class);
        System.out.println("Tamaño: " + trainersList.size());
        for(int i=0; i<trainersList.size(); i++){
        	System.out.println(trainersList.get(i));
        }
   ```
  El arrayList contiene todos los objetos de Trainer que se encontraron en la base de datos. Y gracias al toString que se implemento en la clase modelo, se pueden visualizar sin problemas. <br>
   <img src="readmeStyle/yayFindAll.PNG"/>
   
* Save
    <i><br>Permite insertar un objeto de una clase en especifico a la base de datos.</i>
 ```sh
      Trainer trainer2= new Trainer();
        trainer2.setName("Natalia");
        trainer2.setLastname("Diaz");
        trainer2.setAge(18);
        SimpleDateFormat formatoDelTexto= new SimpleDateFormat("yyyy-mm-dd");
        trainer2.setBirthday(formatoDelTexto.parse("1998-10-23"));
        trainerDAO.save(trainer2, Trainer.class);
```
  Si la inserción en la base de datos funciona sin problemas, se muestra la setencia SQL que se implemento para agregar el objeto a la base de datos. <br>
  <img src="readmeStyle/yaySave.PNG"/> <br>
  Y se puede visualizar la inserción en la base de datos directamente. <br>
  <img src="readmeStyle/dataBaseAll.PNG"/>
  
* Update
    <i><br>Permite actualizar un objeto de una clase en especifico a la base de datos.</i>
 ```sh
	trainer.setName("Juli");
        trainerDAO.update(trainer, Trainer.class);
```
  Si la actualizacion en la base de datos funciona sin problemas, se muestra la setencia SQL que se implemento para actualizar el objeto a la base de datos. <br>
  <img src="readmeStyle/yayUpdate.PNG"/> <br>
  Y se puede visualizar la actualización en la base de datos directamente. <br>
  <img src="readmeStyle/dataBaseUpdate.PNG"/>
  
* Delete
    <i><br>Permite eliminar un objeto de una clase en especifico de la base de datos.</i>
 ```sh
	trainerDAO.delete(trainer, Trainer.class);
```
  Si la eliminación en la base de datos funciona sin problemas, se muestra la setencia SQL que se implemento para eliminar el objeto de la base de datos. <br>
  <img src="readmeStyle/dataBaseDelete.PNG"/> <br>
  Y se puede visualizar la Eliminación en la base de datos directamente (Le volvi a cambiar el nombre a Juliana). <br>
  <img src="readmeStyle/dataBaseAll.PNG"/>
  
* FindByX
    <i><br>Devuelve un ArrayList de objetos segun la clase, columna y valor especificados.</i>
 ```sh
	ArrayList<Trainer> trainersList2=trainerDAO.findByX(Trainer.class, "name", "Juliana");
        System.out.println("Tamaño: " + trainersList2.size());
        for(int i=0; i<trainersList2.size(); i++){
        	System.out.println(trainersList2.get(i));
        }
```
  El arrayList contiene todos los objetos de Trainer que se encontraron en la base de datos con la referencia en columna y valor dichos. <br>
   <img src="readmeStyle/yayFindByX.PNG"/>
 
## Mejoras y Contacto
Hay muchas cosas por mejorar para este componente. YayForJ Persistence es solo una parte de un gran framework que se realizo con la ayuda de dos amigos más. Sin embargo, si quieren realizar cualquier tipo de mejora a este componente bien pueden hacerlo. <br><br>
Mi email de contacto es juli.milena@hotmail.com por si quieren discutir cualquier cosa del proyecto. <br><br>
En un futuro subire tanto el modulo, como el Framework completo a Maven Central para que puedan descargarlo como una libreria más.