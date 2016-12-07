package co.edu.usa.adf.YayForJ_Persistence.logic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import co.edu.usa.adf.YayForJ_Persistence.annotation.Entity;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Id;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Table;

/**Clase encargada de leer las anotaciones Entity, Table e Id de una clase modelo.
 * @author Juliana Diaz
 * */
public class AnnotationReader {
	
	private boolean isEntity=false;
	private String tableName="";
	private boolean hasId=false;
	private String fieldIdName="";
	
	/**Metodo que lee las anotaciones de clase, tales como Entity y Table de una clase modelo.
	 * Leer un Class<?> y cambia las variables tableName e isEntity por sus valores correspondientes*/
	public void classAnnotationReader(Class<?> clase) throws AnnotationException{
		Annotation classAnnotation[]=clase.getAnnotations();
		for(int i=0; i<classAnnotation.length; i++){
			if(classAnnotation[i] instanceof Entity){
				isEntity=true;
				if(classAnnotation[i+1] instanceof Table){
					tableName=((Table)classAnnotation[i+1]).name();
					fieldAnnotationReader(clase);
				}
				else{
					System.out.println(clase.getName());
					throw new AnnotationException("Class doesn't have a Table Annotation");
				}
				break;
			}
			else{
				System.out.println(clase.getName());
				throw new AnnotationException("Class doesn't have an Entity Annotation");
			}
		}
	}
	
	/**Metodo que lee las anotaciones de campo, tales como Id, de una clase modelo.
	 * Es invocado por el metodo classAnnotationReader, recibiendo el mismo Class<?>
	 * y cambia las variables hasId y fieldIdName por sus respectivos valores.*/
	public void fieldAnnotationReader(Class<?> clase) throws AnnotationException{
		Field[] fields= clase.getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			Annotation fieldAnnotations[]= fields[i].getAnnotations();
			for(int j=0; j<fieldAnnotations.length; j++){
				if(fieldAnnotations[j] instanceof Id){
					hasId=true;
					fieldIdName= fields[i].getName();
				}
				else{
					throw new AnnotationException("Table doesn't have an Id");
				}
			}
		}
	}
	
	/**Retorna un String con el nombre de la tabla
	 * @return tableName*/
	public String getTableName(){
		return tableName;
	}
	
	/**retorna un booleano indicando si la clase leida poseia la anotacion Entity
	 * @return true si la clase tenia la anotacion entity, false si no*/
	public boolean getIsEntity(){
		return isEntity;
	}
	
	/**retorna un booleano indicando si la clase leida poseia la anotacion Id
	 * @return true si la clase tenia la anotacion Id, false si no*/
	public boolean getHasId(){
		return hasId;
	}
	
	/**Retorna un String con el nombre del campo que tiene el id
	 * @return fieldIdName*/
	public String getFieldIdName(){
		return fieldIdName;
	}
	

}
