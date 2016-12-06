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

public abstract class DAOGenerator<T> {
	
	private ClassBuilder classBuilder= new ClassBuilder();
	
	/***/
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
	
	public ArrayList<T> findAll(Class<?> clase) throws AnnotationException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException{
		ArrayList<T> findAllList= new ArrayList<T>();
		classBuilder.loadClass(clase);
		Connection connection= YayPersistence.getConnection();
		T object=null;
		try {
			Statement statement= connection.createStatement();
			String sql= "select * from "+ classBuilder.getTableName();
			ResultSet result= statement.executeQuery(sql);
			ResultSet temporal= result;
			findAllList= (ArrayList<T>) classBuilder.findAllObjects(clase, result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findAllList;
	}
	
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
