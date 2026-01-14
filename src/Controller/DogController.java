/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Dog;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import Controller.Sort;
import Controller.Stack;

/**
 *
 * @author Shahil
 */
public class DogController {

    LinkedList<Dog> dogList = new LinkedList<Dog>();
    private boolean isSortedById = false;
    Stack stack1 = new Stack(15);

    public void loadInitialData() {
        dogList.add(new Dog(1, "Buddy", "Golden Retriever", "Unadopted", 3, "Male", 30.5f, "Golden", ""));
        dogList.add(new Dog(2, "Bella", "Labrador", "Adopted", 2, "Female", 25.0f, "Black", ""));
        dogList.add(new Dog(3, "Charlie", "Beagle", "Unadopted", 4, "Male", 20.0f, "Brown", ""));
        dogList.add(new Dog(4, "Max", "Bulldog", "Unadopted", 5, "Male", 35.0f, "Brindle", ""));
        dogList.add(new Dog(5, "Daisy", "Poodle", "Unadopted", 2, "Female", 22.0f, "White", ""));

    }

    /**
     * Adds "Dog" object to the linkedList
     *
     * @param dogObject object of dog with its data to add to the linkedList
     * @return true if dog is added, else returns false
     * @throws
     */
    public boolean addDog(Dog dogObject) {
        for (Dog dog : dogList) {
            if (dogObject.getId() == dog.getId()) {
                return false;
            }
        }
        dogList.add(dogObject);
        return true;
    }

    /**
     * Deletes existing "Dog" object from the linkedList
     *
     * @param dogId id of the dog to delete
     * @return true if successfully deleted dog, else returns false
     * @throws
     */
    public boolean deleteDog(int dogId) {
        for (int i = 0; i < dogList.size(); i++) {
            if (dogList.get(i).getId() == dogId) {
                stack1.push(dogList.remove(i));
                return true;
            }
        }
        return false;
    }
    
    public boolean undoLastDeletion() {
        Dog lastDeleted = stack1.pop(); // Get most recent deletion
        if (lastDeleted != null) {
            dogList.add(lastDeleted); // Add back to main list
            return true;
        }
        return false;
    }

    /**
     * Updates details of existing "Dog" object in linkedList
     *
     * @param dogId id of the dog to update
     * @param updatedDog object of dog with updated data
     * @return true if successfully updated dog's details, else returns false
     * @throws
     */
    public boolean updateDog(int dogId, Dog updatedDog) {
        for (int i = 0; i < dogList.size(); i++) {
            if (dogList.get(i).getId() == dogId) {
                dogList.set(i, updatedDog);
                return true; //if successfully updated dog's details
            }
        }
        return false; //if dogId not found and update fails
    }

    /**
     * Views details of existing "Dog" object from linkedList
     *
     * @param dogId id of the dog to view
     * @return Dog object if found, else returns null
     * @throws
     */
    public Dog viewDog(int dogId) {
        for (Dog dog : dogList) {
            if (dog.getId() == dogId) {
                return dog;
            }
        }
        return null;
    }

    public void loadDataToTable(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Dog dog : dogList) {
            tableModel.addRow(new Object[]{
                dog.getId(),
                dog.getName(),
                dog.getBreed(),
                dog.getAge(),
                dog.getGender(),
                dog.getWeight(),
                dog.getColor(),
                dog.getAdoptionStatus(),});
        }
        table.repaint();
    }

    public void loadDataToTable(JTable table, LinkedList<Dog> SearchedDogList) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Dog dog : SearchedDogList) {
            tableModel.addRow(new Object[]{
                dog.getId(),
                dog.getName(),
                dog.getBreed(),
                dog.getAge(),
                dog.getGender(),
                dog.getWeight(),
                dog.getColor(),
                dog.getAdoptionStatus(),});
        }
        table.repaint();
    }

    public void loadDeletionHistoryToTable(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        if (stack1 != null && !stack1.isEmpty()) {
            Dog[] deletedDogs = stack1.getAllHistory();

            // Show most recent first with serial numbers
            for (int i = deletedDogs.length - 1; i >= 0; i--) {
                Dog dog = deletedDogs[i];
                tableModel.addRow(new Object[]{
                    dog.getId(),
                    dog.getName(),
                    dog.getBreed(),
                    dog.getAge(),
                    dog.getGender(),
                    dog.getWeight(),
                    dog.getColor(),
                    "Deleted"
                });
            }
        }
        table.repaint();
    }

    /**
     * Validates details "Dog" object Checks if the dogId exists in the
     * linkedList.
     *
     * @param dogId id of the dog to validate
     * @return true if exists is successful, false if it doesnt exist.
     * @throws
     */
    public boolean dogExists(int dogId) {
        for (Dog dog : dogList) {
            if (dogId == dog.getId()) {
                return true;
            }
        }
        return false;
    }

    public void sortByAge() {
        Sort.insertionSortByAge(dogList);
        isSortedById = false;
    }

    public void sortByName() {
        Sort.selectionSortByName(dogList);
        isSortedById = false;
    }

    public void sortByWeight() {
        Sort.mergeSortByWeight(dogList);
        isSortedById = false;
    }

    public void sortById() {
        Sort.bubbleSortById(dogList);
        isSortedById = true;
    }

    public LinkedList<Dog> searchDogsByName(String name) {
        LinkedList<Dog> results = new LinkedList<>();
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                results.add(dog);
            }
        }
        return results;
    }

    public LinkedList<Dog> searchDogById(String id) {
        LinkedList<Dog> results = new LinkedList<>();

        try {
            // Ensure list is sorted for binary search
            if (!isSortedById) {
                sortById(); // Sort first
            }

            Dog dog = Search.binarySearchById(dogList, id);
            if (dog != null) {
                results.add(dog);
            }
        } catch (NumberFormatException e) {
            // Invalid ID format - return empty list
        }

        return results;
    }

    public int getTotalDogs() {
        return dogList.size();
    }
    
    public void clearHistory(){
        stack1.clearHistory();
    }
}
