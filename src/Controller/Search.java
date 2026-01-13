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
public class Search {

    /**
     * Search for dog by name
     * 
     * @param dogs LinkedList of dogs to search
     * @param targetName Name to search for
     * @return Dog if found, null otherwise
     */
    public static Dog linearSearchByName(LinkedList<Dog> dogs, String targetName) {
        for (Dog dog : dogs) {
            if (dog.getName().equalsIgnoreCase(targetName)) {
                return dog;
            }
        }
        return null;
    }

    /**
     * Search for dog by ID 
     * 
     * search
     * @param dogs LinkedList of dogs (should be sorted by ID)
     * @param targetId ID to search for
     * @return Dog if found, null otherwise
     */
    public static Dog binarySearchById(LinkedList<Dog> dogs, int targetId) {
        int left = 0;
        int right = dogs.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Get middle element (O(n) for LinkedList but okay for small data)
            Dog midDog = dogs.get(mid);
            int midId = midDog.getId();

            if (midId == targetId) {
                return midDog;  // Found!
            }

            if (midId < targetId) {
                left = mid + 1;  // Search right half
            } else {
                right = mid - 1;  // Search left half
            }
        }

        return null;  // Not found
    }

}
