/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modified Queue class that stores String activities
package Controller;

public class Queue {

    private String[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Add a string activity
    public void enqueue(String activity) {
        if (isFull()) {
            dequeue(); // Remove oldest
        }

        rear = (rear + 1) % capacity;
        queue[rear] = activity;
        size++;
    }

    // Remove oldest activity
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String activity = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;
        return activity;
    }

    // Get all activities (newest first)
    public String[] getAllActivities() {
        String[] activities = new String[size];

        if (size == 0) {
            return activities;
        }

        // Start from rear (newest) and go backwards
        for (int i = 0; i < size; i++) {
            int index = (rear - i + capacity) % capacity;
            activities[i] = queue[index];
        }

        return activities;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            queue[i] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
    }
}
