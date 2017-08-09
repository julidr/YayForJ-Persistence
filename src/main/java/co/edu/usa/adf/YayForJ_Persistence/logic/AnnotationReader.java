package co.edu.usa.adf.YayForJ_Persistence.logic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import co.edu.usa.adf.YayForJ_Persistence.annotation.Column;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Entity;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Id;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Table;
import org.apache.log4j.Logger;

/**Clase encargada de leer las anotaciones Entity, Table e Id de una clase modelo.
 * @author Juliana Diaz
 * @version 1.0
 * */
public class AnnotationReader {
	
	private boolean isEntity;
	private String tableName;
	private boolean hasId;
	private String fieldIdName;
	private boolean isAutoincremental;
	private ArrayList<String> columnsName;
	private boolean hasAllColumns;
	private String columnIdName;
    final static Logger logger = Logger.getLogger(AnnotationReader.class);

	public AnnotationReader(){
        isEntity=false;
        tableName="";
        hasId = false;
        fieldIdName="";
        isAutoincremental = false;
        columnsName = new ArrayList<String>();
        hasAllColumns = true;
        columnIdName = "";
    }
	
	/**Metodo que lee las anotaciones de clase, tales como Entity y Table de una clase modelo.
	 * Leer un Class<?> y cambia las variables tableName e isEntity por sus valores correspondientes*/
	public void classAnnotationReader(Class<?> clase) throws AnnotationException{
		Annotation classAnnotation[]=clase.getAnnotations();
		for(int i=0; i<classAnnotation.length; i++){
			if(classAnnotation[i] instanceof Entity){
				isEntity=true;
				try {
                    if(classAnnotation[i+1] instanceof Table){
                        tableName=((Table)classAnnotation[i+1]).name();
                        fieldAnnotationReader(clase);
                    }
                    else{
                        logger.error("Class "+clase.getName()+" doesn't have a Table Annotation");
                        throw new AnnotationException("Class "+clase.getName()+" doesn't have a Table Annotation");
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    logger.error("Class "+clase.getName()+" doesn't have a Table Annotation");
				    throw new AnnotationException("Class "+clase.getName()+" doesn't have a Table Annotation");
                }
				break;
			}
			else{
                logger.error("Class "+clase.getName()+" doesn't have an Entity Annotation");
				throw new AnnotationException("Class "+clase.getName()+" doesn't have an Entity Annotation");
			}
		}
	}
	
	/**Metodo que lee las anotaciones de campo, tales como Id y Columns, de una clase modelo.
	 * Es invocado por el metodo classAnnotationReader, recibiendo el mismo Class<?>
	 * y cambia las variables hasId, fieldIdName, isAutoincremental y columnsName por sus respectivos valores.*/
	public void fieldAnnotationReader(Class<?> clase) throws AnnotationException{
		Field[] fields= clase.getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			Annotation fieldAnnotations[]= fields[i].getAnnotations();
			for(int j=0; j<fieldAnnotations.length; j++){
				if(fieldAnnotations[j] instanceof Id){
					hasId=true;
					fieldIdName= fields[i].getName();
                    isAutoincremental = ((Id)fieldAnnotations[j]).isAutoincremental();
                    columnIdName = ((Id)fieldAnnotations[j]).name();
                    columnsName.add(((Id)fieldAnnotations[j]).name());
				} else if(fieldAnnotations[j] instanceof Column){
                    columnsName.add(((Column)fieldAnnotations[j]).name());
                }
			}
		}
        if(columnsName.size()!=fields.length && hasId==true){
            hasAllColumns = false;
            logger.error("Some fields in your class "+clase.getName()+" don't have a Column annotation");
            throw new AnnotationException("Some fields in your class "+clase.getName()+" don't have a Column annotation");
        } else if(hasId==false){
            logger.error("Table doesn't have an Id");
            throw new AnnotationException("Table doesn't have an Id");
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

	/**retorna un booleano con el valor de si el id es o no autoincremental
     * @return true si el id es autoincremental*/
	public boolean getIsAutoincremental(){
	    return isAutoincremental;
    }

    /**retorna una lista con los nombres de las columnas en la base de datos
     * @return columnsName*/
    public ArrayList<String> getColumnsName() {
        return columnsName;
    }

    /**retorna un booleano indicando si la clase leida posee todas las anotaciones de Column en sus campos.
     * @return true si todos los campos tienen la anotación Column*/
    public boolean getHasAllColumns(){
        return hasAllColumns;
    }

    public String getColumnIdName() {
        return columnIdName;
    }
}
