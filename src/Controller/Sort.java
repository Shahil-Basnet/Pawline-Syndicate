/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Dog;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Shahil
 */
public class Sort extends DogController{

    public static void selectionSortByName(LinkedList<Dog> dogs) {
        for (int i = 0; i < dogs.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < dogs.size(); j++) {
                if (dogs.get(j).getName().compareToIgnoreCase(dogs.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Dog temp = dogs.get(i);
                dogs.set(i, dogs.get(minIndex));
                dogs.set(minIndex, temp);
            }
        }
    }

    public static void insertionSortByAge(LinkedList<Dog> dogs) {
        for (int i = 1; i < dogs.size(); i++) {
            Dog key = dogs.get(i);
            int j = i - 1;

            while (j >= 0 && dogs.get(j).getAge() > key.getAge()) {
                dogs.set(j + 1, dogs.get(j));
                j--;
            }
            dogs.set(j + 1, key);
        }
    }

    public static void mergeSortByWeight(LinkedList<Dog> dogs) {
        if (dogs.size() <= 1) {
            return;
        }

        // Split the kist of dog
        LinkedList<Dog> left = new LinkedList<>();
        LinkedList<Dog> right = new LinkedList<>();
        int mid = dogs.size() / 2;

        Iterator<Dog> it = dogs.iterator();

        // Fill left half
        for (int i = 0; i < mid; i++) {
            left.add(it.next());
        }

        // Add rest to right
        while (it.hasNext()) {
            right.add(it.next());
        }

        // Recursively sort
        mergeSortByWeight(left);
        mergeSortByWeight(right);

        // Clear original and merge
        dogs.clear();
        mergeLists(dogs, left, right);
    }

    private static void mergeLists(LinkedList<Dog> result, LinkedList<Dog> left, LinkedList<Dog> right) {
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.getFirst().getWeight() <= right.getFirst().getWeight()) {
                result.add(left.removeFirst());
            } else {
                result.add(right.removeFirst());
            }
        }

        result.addAll(left);
        result.addAll(right);
    }

    public static void bubbleSortById(LinkedList<Dog> dogs) {
        int swaps = 0;
        int listSize = dogs.size();
        boolean swapped;

        for (int i = 0; i < listSize - 1; i++) {
            swapped = false;
            for (int j = 0; j < listSize - i - 1; j++) {
                if (dogs.get(j).getId() > dogs.get(j + 1).getId()) {
                    Dog temp = dogs.get(j);
                    dogs.set(j, dogs.get(j + 1));
                    dogs.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
