package domain;

import java.io.Serializable;

public class Appointment implements Identifiable<Integer>, Serializable{
    private int ID;
    private Patient patient;
    private String date;
    private int price;


    public Appointment() {
        this.ID = 0;
        this.patient = null;
        this.date = "";
        this.price = 0;
    }

    public Appointment(int ID, Patient patient, String date, int price) {
        this.ID = ID;
        this.patient = patient;
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        String s = "Appointment with ID: ";
        s = s + this.ID + ", of ";
        s = s + this.patient;
        s = s + "Date: " + this.date;
        s = s + ", Price: " + this.price + ".\n";
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
