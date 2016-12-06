package co.edu.usa.adf.YayForJ_Persistence.logic;

public class AnnotationController {

	private AnnotationReader annotationReader;
	
	public AnnotationController(Class<?> clase) throws AnnotationException {
		annotationReader= new AnnotationReader();
		readAnnotations(clase);
	}
	
	private void readAnnotations(Class<?> clase) throws AnnotationException{
		annotationReader.classAnnotationReader(clase);
		if(annotationReader.getIsEntity()==true){
			if(annotationReader.getHasId()==true){
				System.out.println("Class has been readed without problems");
			}
		}
	}
	
	public String getTableName(){
		return annotationReader.getTableName();
	}
	
	public String getFieldIdName(){
		return annotationReader.getFieldIdName();
	}

}
