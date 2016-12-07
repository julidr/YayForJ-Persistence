package co.edu.usa.adf.YayForJ_Persistence.logic;

/**Clase encargada de administrar el momento en que se leen las anotaciones.
 * @author Juliana Diaz
 * */
public class AnnotationController {

	private AnnotationReader annotationReader;
	
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
				System.out.println("Class has been readed without problems");
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

}
