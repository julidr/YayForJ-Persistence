package co.edu.usa.adf.YayForJ_Persistence.logic;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**Clase encargada de administrar el momento en que se leen las anotaciones.
 * @author Juliana Diaz
 * @version 1.0
 * */
public class AnnotationController {

	private AnnotationReader annotationReader;
    final static Logger logger = Logger.getLogger(AnnotationController.class);
	
	public AnnotationController(Class<?> clase) throws AnnotationException {
		annotationReader= new AnnotationReader();
		readAnnotations(clase);
	}
	
	/**Metodo que empieza con la lecturas de anotaciones de la clase. Recibe un Class<?>
	 * y se encarga de verificar que el proceso de lectura de anotaciones se haga correctamente.*/
	private void readAnnotations(Class<?> clase) throws AnnotationException{
		annotationReader.classAnnotationReader(clase);
		if(annotationReader.getIsEntity()==true){
			if(annotationReader.getHasId()==true){
			    if(annotationReader.getHasAllColumns()==true){
			        logger.info("Class has been readed without problems");
                }
			}
		}
	}
	
	/**Retorna un String con el nombre de la tabla
	 * @return tableName*/
	public String getTableName(){
		return annotationReader.getTableName();
	}
	
	
	/**Retorna un String con el nombre del campo que tiene el id
	 * @return fieldIdName*/
	public String getFieldIdName(){
		return annotationReader.getFieldIdName();
	}

    /**retorna un booleano con el valor de si el id es o no autoincremental
     * @return true si el id es autoincremental*/
	public boolean getIsAutoincremenatal(){
	    return annotationReader.getIsAutoincremental();
    }

    /**retorna una lista con los nombres de las columnas en la base de datos
     * @return columnsName*/
    public ArrayList<String> getColumnsName(){
	    return annotationReader.getColumnsName();
    }

}
