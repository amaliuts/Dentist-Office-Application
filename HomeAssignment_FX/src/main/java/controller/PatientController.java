package controller;

import domain.Patient;
import repo.PatientRepository;
import repo.PatientRepositoryFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PatientController {
    private PatientRepository  repo;


    public PatientController(){
        this.repo = new PatientRepository();
    }

    public PatientController(PatientRepository pRepo, PatientRepositoryFile pfileRepo) {
        this.repo = pRepo;
    }

    public void add(Patient p) {
        repo.add(p);
    }

    public void delete(Patient p) {
        repo.delete(p);
    }

    public void deleteByID(int id) {
        Patient p = new Patient();
        p.setID(id);
        repo.delete(p);
    }

    public void update(Patient p, int id) {
        repo.update(p, p.getID());
    }

    public Patient search(int id) {
        return repo.findById(id);
    }

    public Iterable<Patient> ctrlFindAll(){
        return repo.findAll();
    }

    public Collection<Patient> ctrlGetAll(){
        return repo.getAll();
    }

    public List<String> getNameOfPatientsWithGivenProblem(String givenProblem){
        Collection<Patient> pCollection = ctrlGetAll();
        List<String> patientNameList = pCollection.stream().filter(patient -> patient.getProblemDescription() == givenProblem).map(patient -> patient.getName()).collect(Collectors.toList());
        return patientNameList;
    }

    public int getNumberofMinorPatients() {
        Collection<Patient> pCollection = ctrlGetAll();
        int cnt = 0;
        cnt = (int)pCollection.stream().filter(patient -> patient.getAge() < 18).count();
        return cnt;
    }

}
