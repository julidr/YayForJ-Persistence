package co.edu.usa.adf.YayForJ_Persistence;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import co.edu.usa.adf.YayForJ_Persistence.dao.PokemonDAO;
import co.edu.usa.adf.YayForJ_Persistence.dao.TrainerDAO;
import co.edu.usa.adf.YayForJ_Persistence.logic.AnnotationException;
import co.edu.usa.adf.YayForJ_Persistence.logic.YayPersistence;
import co.edu.usa.adf.YayForJ_Persistence.model.Pokemon;
import co.edu.usa.adf.YayForJ_Persistence.model.Trainer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, AnnotationException, ParseException
    {
    	YayPersistence persistenceController= new YayPersistence("jdbc:mysql://localhost:3306/yaytest", "root", "");
        TrainerDAO trainerDAO= new TrainerDAO();
        PokemonDAO pokemonDAO= new PokemonDAO();
        
        System.out.println("\nFind By Id");
        Trainer trainer=trainerDAO.findById(1l, Trainer.class);
        System.out.println("nombre: " + trainer.getName());
        System.out.println("apellido: " + trainer.getLastname());
        System.out.println("edad: " + trainer.getAge());
        System.out.println("cumpleaños: " + trainer.getBirthday());
        
       System.out.println("\nFind All Trainers");
        ArrayList<Trainer> trainersList=trainerDAO.findAll(Trainer.class);
        System.out.println("Tamaño: " + trainersList.size());
        for(int i=0; i<trainersList.size(); i++){
        	System.out.println(trainersList.get(i));
        }
        
        System.out.println("\nSave");
        Trainer trainer2= new Trainer();
        trainer2.setName("Andres");
        trainer2.setLastname("Gomez");
        trainer2.setAge(24);
        SimpleDateFormat formatoDelTexto= new SimpleDateFormat("yyyy-mm-dd");
        trainer2.setBirthday(formatoDelTexto.parse("1992-08-06"));
        //trainerDAO.save(trainer2, Trainer.class);
        
        System.out.println("\nUpdate");
        trainer.setName("Juli");
        //trainerDAO.update(trainer, Trainer.class);
        
        System.out.println("\nDelete");
        //trainerDAO.delete(trainer, Trainer.class);
        
        System.out.println("\nFind by X");
        ArrayList<Trainer> trainersList2=trainerDAO.findByX(Trainer.class, "name", "Juliana");
        System.out.println("Tamaño: " + trainersList2.size());
        for(int i=0; i<trainersList2.size(); i++){
        	System.out.println(trainersList2.get(i));
        }
        
        System.out.println("\nFind By Id");
        Pokemon pokemon= pokemonDAO.findById(1l, Pokemon.class);
        System.out.println("nombre: " + pokemon.getName());
        System.out.println("primer tipo: " + pokemon.getFirstType());
        System.out.println("segundo tipo: " + pokemon.getSecondType());
        System.out.println("genero: " + pokemon.getSex());
        System.out.println("trainer_id: " + pokemon.getTrainer_id());
        
        System.out.println("\nFind All ");
        ArrayList<Pokemon> pokemonList=pokemonDAO.findAll(Pokemon.class);
        System.out.println("Tamaño: " + pokemonList.size());
        for(int i=0; i<pokemonList.size(); i++){
        	System.out.println(pokemonList.get(i));
        }
        
        System.out.println("\nSave");
        Pokemon pokemon2= new Pokemon();
        pokemon2.setName("Chimchar");
        pokemon2.setFirstType("fire");
        pokemon2.setSecondType("fire");
        pokemon2.setSex("male");
        pokemon2.setTrainer_id(1);
       // pokemonDAO.save(pokemon2, Pokemon.class);
        
        System.out.println("\nUpdate");
        System.out.println(pokemon.getId());
        pokemon.setSecondType("fire");
        //pokemonDAO.update(pokemon, Pokemon.class);
        
        System.out.println("\nDelete");
        //pokemonDAO.delete(pokemon, Pokemon.class);
        
        System.out.println("\nFind by X");
        ArrayList<Pokemon> pokemonList2=pokemonDAO.findByX(Pokemon.class, "name", "Chimchar");
        System.out.println("Tamaño: " + pokemonList2.size());
        for(int i=0; i<pokemonList2.size(); i++){
        	System.out.println(pokemonList2.get(i));
        }
        
        persistenceController.closeConnection();
    }
}
