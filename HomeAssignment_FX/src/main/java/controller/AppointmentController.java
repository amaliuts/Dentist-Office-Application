package controller;

import domain.*;
import repo.AppointmentRepository;
import repo.AppointmentRepositoryFile;
import repo.PatientRepositoryFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentController {
    private AppointmentRepository repo;

    public AppointmentController() {this.repo = new AppointmentRepository();}

    public AppointmentController(AppointmentRepository aRepo) {this.repo = aRepo;}

    public void add(Appointment a) {
        repo.add(a);

    }

    public void delete(Appointment a) {
        repo.delete(a);

    }

    public void deleteByID(int id) {
        Appointment a = new Appointment();
        a.setID(id);
        repo.delete(a);

    }

    public void update(Appointment a, int id) {
        repo.update(a, a.getID());

    }

    public Appointment search(int id) {
        return repo.findById(id);
    }

    public Iterable<Appointment> ctrlFindAll(){
        return repo.findAll();
    }

    public Collection<Appointment> ctrlGetAll(){
        return repo.getAll();
    }

    public List<String> getAppointmentsOfGivenPatientName (String pName){
        Collection<Appointment> aCollection = ctrlGetAll();
        List<String> aList = aCollection.stream().filter(appointment -> appointment.getPatient().getName().contains(pName)).map(appointment -> appointment.getID()+ " - " + appointment.getDate()).collect(Collectors.toList());
        List<String> apps = new ArrayList<>();
        for (String a : aList) {
            apps.add(a);
        }
        return apps;
    }

    public List<Patient> getPatientsWithAppointmentsOnAGivenMonth (String month){
        Collection<Appointment> aCollection = ctrlGetAll();
        List<Patient> pList = aCollection.stream().filter(appointment -> appointment.getDate().contains(month)).map(appointment -> appointment.getPatient()).collect(Collectors.toList());
        return pList;
    }

    public List<String> getAppointmentsGreaterThanGivenPrice (int price){
        Collection<Appointment> aCollection = ctrlGetAll();
        List<String> aList = aCollection.stream().filter(appointment -> appointment.getPrice() > price).map(appointment -> appointment.getID() + " - " + appointment.getPrice()).collect(Collectors.toList());
        return aList;
    }
}
