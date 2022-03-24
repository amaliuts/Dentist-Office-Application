package repo;

import domain.*;

public class PatientRepository extends AbstractRepository<Patient, Integer>{
    public PatientRepository() {};

    public PatientRepository overAge(int givenAge) {
        PatientRepository filteredPatients = new PatientRepository();
        for (Patient p : this.findAll()) {
            if (p.getAge() > givenAge)
                filteredPatients.add(p);
        }
        return filteredPatients;
    }

    public PatientRepository underAge(int givenAge) {
        PatientRepository filteredPatients = new PatientRepository();
        for (Patient p : this.findAll()) {
            if (p.getAge() < givenAge)
                filteredPatients.add(p);
        }
        return filteredPatients;
    }

    public PatientRepository filterByProblem(String prb) {
        PatientRepository filteredPatients = new PatientRepository();
        for (Patient p : this.findAll()) {
            if (p.getProblemDescription() == prb)
                filteredPatients.add(p);
        }
        return filteredPatients;
    }
}
