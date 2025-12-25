/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Shahil
 */
public class Dog {
    private String name, breed, adoptionStatus, gender, color;
    
    //Basic details
    private int age, id;
    
    private float weight;
    
    //Dates
//    private String intakeDate;
    
    public Dog(int id, String name, String breed, String adoptionStatus, int age, String gender, float weight, String color) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.adoptionStatus = adoptionStatus;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
    }
    
    //getter methods
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public String getAdoptionStatus() {
        return adoptionStatus;
    }
    
    public String getColor() {
        return color;
    }
    
    public String getGender() {
        return gender;
    }
    
    public int getAge() {
        return age;
    }
    
//    public String getIntakeDate() {
//        return intakeDate;
//    }
    
    public float getWeight() {
        return weight;
    }
    
    //setter methods
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public void setIntakeDate(String intakeDate) {
//        this.intakeDate = intakeDate;
//    }
//    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}