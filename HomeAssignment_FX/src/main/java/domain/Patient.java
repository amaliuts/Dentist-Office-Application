package domain;

import java.io.Serializable;

public class Patient implements Identifiable<Integer>, Serializable{
    private int ID;
    private String name;
    private String phoneNumber;
    private String problemDescription;
    private int age;


    public Patient() {
        this.ID = 0;
        this.name = "";
        this.phoneNumber = "";
        this.problemDescription = "";
        this.age = 0;
    }

    public Patient(int ID, String name, String phNumber, String prbDescription, int age) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phNumber;
        this.problemDescription = prbDescription;
        this.age = age;
    }

    @Override
    public String toString() {
        String s = "--Patient ID ";
        s = s + this.ID + ", ";
        s = s + this.name + ", ";
        s = s + this.phoneNumber + ", ";
        s = s + this.problemDescription + ", ";
        s = s + this.age + ".--\n";
        return s;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer id) {
        ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phNumber) {
        this.phoneNumber = phNumber;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String prbDescr) {
        this.problemDescription = prbDescr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        this.age = age;
    }

}
