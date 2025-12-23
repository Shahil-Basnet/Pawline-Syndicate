/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Dog;
import java.util.LinkedList;

/**
 *
 * @author Shahil
 */
public class DogController {
    /**
     * Adds "Dog" object to the linkedList 
     * 
     * @param dogList linkedList of DogModel
     * @param dogObject object of dog with its data to add to the linkedList
     * @return true if dog is added, else returns false
     * @throws
     */
    public boolean addDog(LinkedList<Dog> dogList, Dog dogObject){
        
        return true;
    }
    
    /**
     * Deletes existing "Dog" object from the linkedList 
     * 
     * @param dogList linkedList of DogModel
     * @return true if successfully deleted dog, else returns false
     * @throws
     */
    public boolean deleteDog(LinkedList<Dog> dogList){
        return false;
    }
    /**
     * Updates details of existing "Dog" object in linkedList
     * 
     * @param dogList arrayList of DogModel
     * @return true if successfully updated dog's details, else returns false
     * @throws
     */
    public boolean updateDog(LinkedList<Dog> dogList){
        
        return true;
    }
    /**
     * Validates details "Dog" object
     *  Checks if the dogId exists when adding, deleting, updating or viewing a dog
     * 
     * @param dogId
     * @param mode
     * @param dogList linkedList of DogModel
     * @return true if validation is successful, false if it fails
     * @throws
     */
    public boolean validateDog(int dogId, LinkedList<Dog> dogList, String mode){
        if(mode.equalsIgnoreCase("add")){
            for(Dog dog: dogList){
                if(dogId == dog.getId()){
                    return false;
                }
            }
            return true;
        }
        //if mode: update, delete
        else{
            for(Dog dog: dogList){
                if(dogId == dog.getId()){
                    return true;
                }
            }
            return false;
        }
    }
}
