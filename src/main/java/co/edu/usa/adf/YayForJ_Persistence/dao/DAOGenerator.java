package co.edu.usa.adf.YayForJ_Persistence.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import co.edu.usa.adf.YayForJ_Persistence.logic.AnnotationException;
import co.edu.usa.adf.YayForJ_Persistence.logic.ClassBuilder;
import co.edu.usa.adf.YayForJ_Persistence.logic.YayPersistence;

/**Clase que se encarga de construir los metodos basicos de un DAO con la informacion obtenida del
 * Class<?> que se esta manejando.
 * @author Juliana Diaz
 * @version 1.0
 * */
public abstract class DAOGenerator<T> {
	
	private ClassBuilder classBuilder= new ClassBuilder();
	
	/**Metodo que permite buscar un Objeto en la base de datos en base al Id.
	 * Recibe un id que representa el objeto y un Class<?>. Devuelve el objeto correspondiente
	 * al id.
	 * @return T*/
	@SuppressWarnings("unchecked")
	public T findById(Long id, Class<?> clase) throws AnnotationException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException{
		classBuilder.loadClass(clase);
		Connection connection= YayPersistence.getConnection();
		T object=null;
		try {
			Statement statement= connection.createStatement();
			String sql= "select * from "+ classBuilder.getTableName() 
							+ " where id='"+id+"';";
			ResultSet result= statement.executeQuery(sql);
			object= (T) classBuilder.createObject(clase, result);
		} catch (SQLException e) {
			System.out.println("ups! something happened");
			e.printStackTrace();
		}
		return object;
	}
	
	/**Metodo que devuelve toda la lista de objetos que esta relacionada a una tabla. Recibe un 
	 * Class<?> y retorna un ArrayList con los objetos correspondientes.
	 * @return ArrayList<T>
	 * **/
	@SuppressWarnings("unchecked")
	public ArrayList<T> findAll(Class<?> clase) throws AnnotationException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException{
		ArrayList<T> findAllList= new ArrayList<T>();
		classBuilder.loadClass(clase);
		Connection connection= YayPersistence.getConnection();
		try {
			Statement statement= connection.createStatement();
			String sql= "select * from "+ classBuilder.getTableName();
			ResultSet result= statement.executeQuery(sql);
			findAllList= (ArrayList<T>) classBuilder.findAllObjects(clase, result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findAllList;
	}
	
	/**Metodo que guarda en la base de datos un objeto en especifico. Recibe el objeto y un 
	 * Class<?>*/
	public void save(T t, Class<?> clase) throws AnnotationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		classBuilder.loadClass(clase);
		Connection connection= YayPersistence.getConnection();
		try {
			Statement statement= connection.createStatement();
			Object object= t;
			String sql= classBuilder.createInsert(clase, object);
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Metodo que actualiza en la base de datos un objeto en especifico. Recibe el objeto y un 
	 * Class<?>*/
	public void update(T t, Class<?> clase) throws AnnotationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		classBuilder.loadClass(clase);
		Connection connection= YayPersistence.getConnection();
		Object object= t;
		try {
			Statement statement= connection.createStatement();
			String sql= classBuilder.createUpdate(clase, object);
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Metodo que elimina de la base de datos un objeto en especifico. Recibe el objeto y un 
	 * Class<?>*/
	public void delete(T t, Class<?> clase) throws AnnotationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		classBuilder.loadClass(clase);
		Connection connection= YayPersistence.getConnection();
		Object object= t;
		try {
			Statement statement= connection.createStatement();
			String sql= classBuilder.createDelete(clase, object);
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Metodo busca uno o varios objetos en una base de datos, en base a una columna en 
	 * especifico y el valor dado. Recibe un Class<?>, el nombre de la columna y un valor de
	 * busqueda. Retorna una lista con todos los resultados.
	 * @return ArrayLis<T>*/
	@SuppressWarnings("unchecked")
	public ArrayList<T> findByX(Class<?> clase, String column, String value) throws AnnotationException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException{
		classBuilder.loadClass(clase);
		ArrayList<T> findAllList= new ArrayList<T>();
		Connection connection= YayPersistence.getConnection();
		try {
			Statement statement= connection.createStatement();
			String sql= "select * from "+ classBuilder.getTableName() + 
					" where " + column +"='"+value+"'";
			System.out.println(sql);
			ResultSet result= statement.executeQuery(sql);
			findAllList= (ArrayList<T>) classBuilder.findAllObjects(clase, result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findAllList;
	}

}
