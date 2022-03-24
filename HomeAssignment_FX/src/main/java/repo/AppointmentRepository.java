package repo;

import domain.*;

public class AppointmentRepository extends AbstractRepository<Appointment, Integer>{
    public AppointmentRepository() {};


    public AppointmentRepository greaterThan(int newPrice) {
        AppointmentRepository filteredApps = new AppointmentRepository();
        for (Appointment a : this.findAll()) {
            if (a.getPrice() > newPrice)
                filteredApps.add(a);
        }
        return filteredApps;
    }

    public AppointmentRepository byMonth(String givenMonth) {
        AppointmentRepository filteredApps = new AppointmentRepository();
        for (Appointment a : this.findAll()) {
            if (a.getDate().contains(givenMonth))
                filteredApps.add(a);
        }
        return filteredApps;
    }

    public AppointmentRepository byPatient(String pName) {
        AppointmentRepository filteredApps = new AppointmentRepository();
        for (Appointment a : this.findAll()) {
            if (a.getPatient().getName().contains(pName))
                filteredApps.add(a);
        }
        return filteredApps;
    }
}
