package co.edu.usa.adf.YayForJ_Persistence.logic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import co.edu.usa.adf.YayForJ_Persistence.annotation.Entity;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Id;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Table;

public class AnnotationReader {
	
	private boolean isEntity=false;
	private String tableName="";
	private boolean hasId=false;
	private String fieldIdName="";
	
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
	
	public String getTableName(){
		return tableName;
	}
	
	public boolean getIsEntity(){
		return isEntity;
	}
	
	public boolean getHasId(){
		return hasId;
	}
	
	public String getFieldIdName(){
		return fieldIdName;
	}
	

}
