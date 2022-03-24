package com.example.homeassignment_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import domain.*;
import controller.*;
import repo.*;
import java.util.*;
import java.io.*;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();


        /*PatientRepositoryFile f1 = new PatientRepositoryFile("C:\\Users\\ami24\\IdeaProjects\\HomeAssignment_FX\\src\\writeFile_patient.txt");
        f1.add(new Patient(1, "Popescu", "373", "pain", 20));
        f1.add(new Patient(2, "Ionas", "484", "teeth", 23));
        f1.add(new Patient(3, "Caras", "595", "braces", 54));
        System.out.println("Check 'writeFile_patient' \n");

        Patient p4 = new Patient(4, "Grigore", "262", "braces", 18);
        Patient p5 = new Patient(5, "Ioan", "151", "whitening", 25);
        f1.add(p4);
        f1.add(p5);

        f1.delete(p4);
//		f1.update(p5, 4);


        AppointmentRepositoryFile f2 = new AppointmentRepositoryFile("C:\\Users\\ami24\\IdeaProjects\\HomeAssignment_FX\\src\\writeFile_app.txt", f1);
        f2.add(new Appointment(1, f1.findById(1), "24.12", 100));
        f2.add(new Appointment(2, f1.findById(3), "05.01", 120));
        f2.add(new Appointment(3, f1.findById(2), "26.12", 85));
        System.out.println("Check 'writeFile_app' \n");


        PatientRepositorySerialization f1S = new PatientRepositorySerialization("C:\\Users\\ami24\\eclipse-workspace\\Lab2_hw_1\\src\\patientSerialization");
        f1S.add(p4);
        f1S.add(p5);

        AppointmentRepositorySerialization f2S = new AppointmentRepositorySerialization("C:\\Users\\ami24\\eclipse-workspace\\Lab2_hw_1\\src\\appointmentSerialization", f1S);
        f2S.add(new Appointment(1, f1S.findById(4), "24.10", 50));
        f2S.add(new Appointment(2, f1S.findById(5), "17.18", 75));*/


        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\ami24\\IdeaProjects\\HomeAssignment_FX\\src\\DentistApplication.properties"));
            String patientFileName = properties.getProperty("PatientFile");
            if (patientFileName == null) {
                patientFileName = "C:\\Users\\ami24\\IdeaProjects\\HomeAssignment_FX\\src\\writeFile_patient.txt";
                System.err.println("Patients file not found. Using default " + patientFileName);
            }
            PatientRepositoryFile pRepoF = new PatientRepositoryFile(patientFileName);
            /*String appointmentFileName = properties.getProperty("AppointmentFile");
            if (appointmentFileName == null) {
                appointmentFileName = "C:\\Users\\ami24\\IdeaProjects\\HomeAssignment_FX\\src\\writeFile_app.txt";
                System.err.println("Appointments file not found. Using default " + appointmentFileName);
            }
            PatientRepositoryFile pRepoF = new PatientRepositoryFile(patientFileName);
            AppointmentRepositoryFile appRepoF = new AppointmentRepositoryFile(appointmentFileName, pRepoF);
            System.err.println("gudd");*/
        }
        catch (IOException ex) {
            System.err.println("Error reading the configuration file" + ex);
        }

    }
}