package co.edu.usa.adf.YayForJ_Persistence.logic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ClassBuilder {
	
	private AnnotationController annotationController;
	private ArrayList<Object> allObjects;
	private Field[] fields;
	
	public void loadClass(Class<?> clase) throws AnnotationException{
		annotationController= new AnnotationController(clase);
		allObjects= new ArrayList<Object>();
	}
	
	public String getFieldIdName(){
		return annotationController.getFieldIdName();
	}
	
	public String getTableName(){
		return annotationController.getTableName();
	}
	
	public Object createObject(Class<?> clase, ResultSet result) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException, SQLException{
		Object object =clase.newInstance();
		fields= clase.getDeclaredFields();
		while(result.next()){
			for(int i=0; i<fields.length; i++){
				Class<?> type= fields[i].getType();
				Method method= clase.getMethod("set"+transformToUppercase(fields[i].getName()), type);
				implementsMethod(object, method, result.getString(fields[i].getName()), type.getName());
			}
		}
		return object;
	}
	
	public ArrayList<Object> findAllObjects(Class<?> clase, ResultSet result) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException, ParseException{
		allObjects= new ArrayList<Object>();
		Object object= null;
		fields= clase.getDeclaredFields();
		while(result.next()){
			object =clase.newInstance();
			for(int i=0; i<fields.length; i++){
				Class<?> type= fields[i].getType();
				Method method= clase.getMethod("set"+transformToUppercase(fields[i].getName()), type);
				implementsMethod(object, method, result.getString(fields[i].getName()), type.getName());
			}
			allObjects.add(object);
		}
		return allObjects;
	}
	
	public String createInsert(Class<?> clase, Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		fields= clase.getDeclaredFields();
		String get="get";
		String sql= "insert into " + getTableName() +"(";
		for(int j=0; j<fields.length; j++){
			if(!fields[j].getName().equals(getFieldIdName())){
				sql=sql+fields[j].getName()+",";
			}
		}
		sql=sql.substring(0, sql.length()-1)+")values(";
		for(int i=0; i<fields.length; i++){
			if(!fields[i].getName().equals(getFieldIdName())){
				if(fields[i].getType().getName().equals("java.lang.Boolean")){
					get="is";
				}
				Method method=clase.getMethod(get+transformToUppercase(fields[i].getName()));
				if(fields[i].getType().getName().equals("java.util.Date")){
					SimpleDateFormat formatoDelTexto= new SimpleDateFormat("yyyy-mm-dd");
					String fecha= formatoDelTexto.format(method.invoke(object));
					sql=sql+"'"+fecha+"',";
				}
				else{
					sql=sql+"'"+method.invoke(object)+"',";
				}
			}
		}
		sql=sql.substring(0, sql.length()-1)+");";
		return sql;
	}
	
	public String createUpdate(Class<?> clase, Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		fields= clase.getDeclaredFields();
		String get="get";
		String sql= "update " + getTableName() +" set ";
		for(int i=0; i<fields.length; i++){
			if(!fields[i].getName().equals(getFieldIdName())){
				sql=sql+fields[i].getName()+"='";
				if(fields[i].getType().getName().equals("java.lang.Boolean")){
					get="is";
				}
				Method method=clase.getMethod(get+transformToUppercase(fields[i].getName()));
				if(fields[i].getType().getName().equals("java.util.Date")){
					SimpleDateFormat formatoDelTexto= new SimpleDateFormat("yyyy-mm-dd");
					String fecha= formatoDelTexto.format(method.invoke(object));
					sql=sql+fecha+"',";
				}
				else{
					sql=sql+method.invoke(object)+"',";
				}
			}
		}
		Method method2= clase.getMethod(get+transformToUppercase(getFieldIdName()));
		sql=sql.substring(0, sql.length()-1)+" where " +getFieldIdName()+"='"+method2.invoke(object)+"'";
		return sql;
	}
	
	public String createDelete(Class<?> clase, Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String get="get";
		String sql= "delete from " + getTableName() + " where " + getFieldIdName();
		Method method= clase.getMethod(get+transformToUppercase(getFieldIdName()));
		sql=sql+"='"+method.invoke(object)+"';";
		return sql;
	}
	
	
	public void implementsMethod(Object nn,Method method,String attribute, String type) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
		if(type.equals("java.lang.String")){
			method.invoke(nn,attribute);
		}
		else if(type.equals("java.lang.Integer") || type.equals("int") ){
			int entero= Integer.parseInt(attribute);
			method.invoke(nn,entero);
		}
		else if(type.equals("java.lang.Character")|| type.equals("char")){
			char character= attribute.charAt(0);
			method.invoke(nn,character);
			
		}
		else if(type.equals("java.lang.Double") || type.equals("double")){
			double doble= Double.parseDouble(attribute);
			method.invoke(nn,doble);
		}
		else if(type.equals("java.lang.Long") || type.equals("long")){
			long largo= Long.parseLong(attribute);
			method.invoke(nn, largo);
		}
		else if(type.equals("java.lang.Float") || type.equals("float")){
			float flotante= Float.parseFloat(attribute);
			method.invoke(nn,flotante);
		}
		else if(type.equals("java.lang.Boolean")|| type.equals("boolean") ){
			boolean booleano= Boolean.parseBoolean(attribute);
			method.invoke(nn,booleano);
		}
		else if(type.equals("java.util.Date")){
			SimpleDateFormat formatoDelTexto= new SimpleDateFormat("yyyy-mm-dd");
			String fecha= attribute+"";
			method.invoke(nn,formatoDelTexto.parse(fecha));
		}
	}
	
	public String transformToUppercase(String attribute){
		String inicial= attribute.substring(0,1).toUpperCase();
		attribute=inicial+attribute.substring(1, attribute.length());
		return attribute;
	}

}
