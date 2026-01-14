/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Shahil
 */
import Model.Dog;
import java.util.Arrays;

/**
 * Stack implementation for tracking Dog deletion/adoption history. Uses array
 * to store Dog objects with LIFO (Last-In-First-Out) operations.
 */
public class Stack {

    private Dog[] history;  // Array to store dogs
    private int top;        // Index of top element
    private int capacity;   // Maximum size

    /**
     * Constructor to initialize stack with given capacity
     *
     * @param capacity Maximum number of history records to store
     */
    public Stack(int capacity) {
        this.capacity = capacity;
        history = new Dog[capacity];
        top = -1;  // Stack is empty
    }

    /**
     * Add a dog to the stack
     *
     * @param dog Dog object to add to history
     * @return true if successful, false if stack is full
     */
    public boolean push(Dog dog) {
        if (isFull()) {
            // Remove oldest by shifting (circular buffer style)
            shiftAndPush(dog);
            return true;
        }
        history[++top] = dog;  // Increment top, then store
        return true;
    }

    /**
     * Remove and return the most recent dog from history
     *
     * @return Most recent Dog object, or null if stack is empty
     */
    public Dog pop() {
        if (isEmpty()) {
            return null;
        }
        Dog dog = history[top];
        history[top] = null;  // Clear reference
        top--;
        return dog;
    }

    /**
     * View most recent dog without removing
     *
     * @return Most recent Dog object, or null if empty
     */
    public Dog peek() {
        if (isEmpty()) {
            return null;
        }
        return history[top];
    }

    /**
     * Get all history records (for display)
     *
     * @return Array of Dog objects in history
     */
    public Dog[] getAllHistory() {
        if (isEmpty()) {
            return new Dog[0];
        }
        Dog[] result = new Dog[top + 1];
        for (int i = 0; i <= top; i++) {
            result[i] = history[i];
        }
        return result;
    }

    /**
     * Get history in reverse order (most recent first)
     *
     * @return Array of Dog objects, most recent first
     */
    public Dog[] getHistoryRecentFirst() {
        if (isEmpty()) {
            return new Dog[0];
        }
        Dog[] result = new Dog[top + 1];
        for (int i = top, j = 0; i >= 0; i--, j++) {
            result[j] = history[i];
        }
        return result;
    }

    /**
     * Clear all history
     */
    public void clearHistory() {
        Arrays.fill(history, null);
        top = -1;
    }

    /**
     * Check if stack is empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Check if stack is full
     *
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * Get current number of history records
     *
     * @return Count of records
     */
    public int getHistoryCount() {
        return top + 1;
    }

    /**
     * Get maximum capacity
     *
     * @return Maximum records that can be stored
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Helper method for circular buffer when stack is full Removes oldest and
     * adds new
     */
    private void shiftAndPush(Dog dog) {
        // Shift all elements left (remove oldest)
        for (int i = 0; i < top; i++) {
            history[i] = history[i + 1];
        }
        // Add new dog at top
        history[top] = dog;
    }

}
