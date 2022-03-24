package com.example.homeassignment_fx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import domain.*;
import controller.*;


import java.io.IOException;
import java.util.*;

public class HelloController {
    private PatientController pCtrl;
    private AppointmentController aCtrl;


    @FXML
    private TextField patientIDText;
    @FXML
    private TextField patientNameText;
    @FXML
    private TextField patientPhNumberText;
    @FXML
    private TextField patientPrbDescriptionText;
    @FXML
    private TextField patientAgeText;
    @FXML
    private TextField appIDText;
    @FXML
    private TextField appPatientIDText;
    @FXML
    private TextField appDateText;
    @FXML
    private TextField appPriceText;
    @FXML
    private TextField deletePatientIDText;
    @FXML
    private TextField deleteAppIDText;
    @FXML
    private TextField updatePIDText;
    @FXML
    private TextField updateAppIDText;
    @FXML
    private TextField findPatientIDText;
    @FXML
    private TextField findAppIDText;
    @FXML
    private TextField givenProblemText;
    @FXML
    private TextField givenPatientNameText;
    @FXML
    private TextField givenMonthText;
    @FXML
    private TextField givenPriceText;
    @FXML
    private TextArea displayPatients;
    @FXML
    private TextArea displayApps;
    @FXML
    private TextArea outputPText;
    @FXML
    private TextArea outputAText;

    public HelloController() {

        this.pCtrl = new PatientController();
        this.aCtrl = new AppointmentController();

        Patient p1 = new Patient(1, "Maria Pop", "0756321100", "front teeth", 16);
        Patient p2 = new Patient(2, "Ion Ion", "0745123744", "masele", 17);
        Patient p3 = new Patient(3, "Ion Pion", "0745123744", "aparat dentar", 22);
        Patient p4 = new Patient(4, "jianu jianu", "0743571536", "braces", 25);
        Appointment a1 = new Appointment(1, p2, "04.12", 89);
        Appointment a2 = new Appointment(2, p1, "05.06", 199);
        Appointment a3 = new Appointment(3, p4, "25.12", 56);
        Appointment a4 = new Appointment(4, p4, "05.01", 72);
        Appointment a5 = new Appointment(5, p3, "14.02", 93);

        pCtrl.add(p1);
        pCtrl.add(p2);
        pCtrl.add(p3);
        pCtrl.add(p4);
        aCtrl.add(a1);
        aCtrl.add(a2);
        aCtrl.add(a3);
        aCtrl.add(a4);
        aCtrl.add(a5);
    }

    @FXML
    public void addPatientButtonAction(ActionEvent event){
        try{
            int ID = Integer.parseInt(patientIDText.getText());
            String name = patientNameText.getText();
            String phNumber = patientPhNumberText.getText();
            String prbDescr = patientPrbDescriptionText.getText();
            int age = Integer.parseInt(patientAgeText.getText());

            Patient newPatient = new Patient(ID, name, phNumber, prbDescr, age);
            pCtrl.add(newPatient);
            outputPText.setText("The patient was added.");
        }
        catch(RuntimeException e){
            outputPText.setText("Adding the data has failed.");
        }
    }


    public void addAppointmentButtonAction(ActionEvent actionEvent){
        try{
            int ID = Integer.parseInt(appIDText.getText());
            String date = appDateText.getText();
            int price = Integer.parseInt(appPriceText.getText());

            Patient wanted = new Patient();
            int patientID = Integer.parseInt(appPatientIDText.getText());
            for (Patient p : pCtrl.ctrlFindAll()){
                if (p.getID() == patientID){
                    wanted = p;
                    break;
                }
            }

            Appointment newApp = new Appointment(ID, wanted, date, price);
            aCtrl.add(newApp);
            outputAText.setText("The appointment was added.");
        }
        catch(RuntimeException e){
            outputAText.setText("Adding the data has failed.");
        }
    }

    public void deletePatientButtonAction(ActionEvent actionEvent){
        try{
            int ID = Integer.parseInt(deletePatientIDText.getText());
            pCtrl.deleteByID(ID);
            outputPText.setText("The patient was deleted.");
        }
        catch(RuntimeException e){
            outputPText.setText("Deleting the data has failed.");
        }
    }

    public void deleteAppointmentButtonAction(ActionEvent actionEvent){
        try{
            int id = Integer.parseInt(deleteAppIDText.getText());
            aCtrl.deleteByID(id);
            outputAText.setText("The appointment was deleted.");
        }
        catch(RuntimeException e){
            outputAText.setText("Deleting the data has failed.");
        }
    }

    public void updatePatientButtonAction(ActionEvent actionEvent){
        try{
            int ID = Integer.parseInt(updatePIDText.getText());
            String name = patientNameText.getText();
            String phNumber = patientPhNumberText.getText();
            String prbDescription = patientPrbDescriptionText.getText();
            int age = Integer.parseInt(patientAgeText.getText());

            Patient updatedPatient = new Patient(ID, name, phNumber, prbDescription, age);
            pCtrl.update(updatedPatient,ID);
            outputPText.setText("The patient was updated.");
        }
        catch(RuntimeException e){
            outputPText.setText("Updating the data has failed.");
        }
    }

    public void updateAppointmentButtonAction(ActionEvent actionEvent){
        try{
            int ID = Integer.parseInt(updateAppIDText.getText());
            String date = appDateText.getText();
            int price = Integer.parseInt(appPriceText.getText());

            Patient wanted = new Patient();
            int patientID = Integer.parseInt(appPatientIDText.getText());
            for (Patient p : pCtrl.ctrlFindAll()){
                if (p.getID() == patientID){
                    wanted = p;
                    break;
                }
            }

            Appointment updatedApp = new Appointment(ID, wanted, date, price);
            aCtrl.update(updatedApp, ID);
            outputAText.setText("The appointment was updated.");
        }
        catch(RuntimeException e){
            outputAText.setText("Updating the data has failed.");
        }
    }

    public void findPatientByIDButtonAction(ActionEvent actionEvent){
        try{
            int ID = Integer.parseInt(findPatientIDText.getText());
            Patient p = new Patient();
            p = pCtrl.search(ID);
            String foundP = p.toString();
            outputPText.setText(foundP);
        }
        catch(RuntimeException e){
            outputPText.setText("Invalid patient ID");
        }
    }

    public void findAppointmentByIDButtonAction(ActionEvent actionEvent){
        try{
            int ID = Integer.parseInt(findAppIDText.getText());
            Appointment a = new Appointment();
            a = aCtrl.search(ID);
            String foundApp = a.toString();
            outputAText.setText(foundApp);
        }
        catch(RuntimeException e){
            outputAText.setText("Invalid appointment ID");
        }
    }

    // patient reports

    public void getNameOfPatientsWithGivenProblemButtonAction(ActionEvent actionEvent){
        String givenProblem = givenProblemText.getText();
        List<String> patientNameList = new ArrayList<>();
        patientNameList = pCtrl.getNameOfPatientsWithGivenProblem(givenProblem);
        String result = patientNameList.toString();
        outputPText.setText(result);
    }

    public void getNumberofMinorPatientsButtonAction(ActionEvent actionEvent){
        int result = pCtrl.getNumberofMinorPatients();
        outputPText.setText(String.valueOf(result));
    }

    // appointment reports

    public void getAppointmentsOfGivenPatientName(ActionEvent actionEvent){
        String patient = givenPatientNameText.getText();
        List<String> aList = new ArrayList<>();
        aList = aCtrl.getAppointmentsOfGivenPatientName(patient);
        String result = aList.toString();
        outputAText.setText(result);

        /*
        List<String> stringsListApps = new ArrayList<String>(wantedApps.size());
        for (Appointment a : wantedApps){
            stringsListApps.add(a.toString());
         }
         outputAText.setText(String.valueof(stringsListApps));
         */
    }

    public void getPatientsWithAppointmentsOnAGivenMonthButtonAction(ActionEvent actionEvent){
        String month = givenMonthText.getText();
        List<Patient> pList = new ArrayList<>();
        pList = aCtrl.getPatientsWithAppointmentsOnAGivenMonth(month);
        String result = pList.toString();
        outputAText.setText(result);
    }

    public void getAppointmentsGreaterThanGivenPriceButtonAction(ActionEvent actionEvent){
        int price = Integer.parseInt(givenPriceText.getText());
        List<String> aList = new ArrayList<>();
        aList = aCtrl.getAppointmentsGreaterThanGivenPrice(price);
        String result = aList.toString();
        outputAText.setText(result);
    }

    public void displayPatientsButtonAction(ActionEvent actionEvent){
        String out = pCtrl.ctrlFindAll().toString();
        displayPatients.setText(out);
    }

    public void displayAppointmentsButtonAction(ActionEvent actionEvent){
        String out = aCtrl.ctrlFindAll().toString();
        displayApps.setText(out);
    }

    public void restartPatients(ActionEvent actionEvent){
        patientIDText.setText("");
        patientNameText.setText("");
        patientPhNumberText.setText("");
        patientPrbDescriptionText.setText("");
        patientAgeText.setText("");
        deletePatientIDText.setText("");
        updatePIDText.setText("");
        findPatientIDText.setText("");
        givenProblemText.setText("");
        displayPatients.setText("");
        outputPText.setText("");
    }

    public void restartAppointments(ActionEvent actionEvent){
        appIDText.setText("");
        appPatientIDText.setText("");
        appDateText.setText("");
        appPriceText.setText("");
        deleteAppIDText.setText("");
        updateAppIDText.setText("");
        findAppIDText.setText("");
        givenPatientNameText.setText("");
        givenMonthText.setText("");
        givenPriceText.setText("");
        displayApps.setText("");
        outputAText.setText("");
    }
}