package repo;

import domain.*;
import java.io.*;
import exceptions.RepositoryException;

public class AppointmentRepositoryFile extends AbstractRepository<Appointment, Integer>{
    private String fileName;
    private PatientRepositoryFile pRepo;

    public AppointmentRepositoryFile(String filename, PatientRepositoryFile pRepo) {
        this.fileName = filename;
        this.pRepo = pRepo;
        readFromFile();
    }

    private void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] elems = line.split(",");
                if (elems.length != 7) {
                    System.err.println("Number of attributes not valid" + line);
                    continue;
                }
                try {
                    int ID = Integer.parseInt(elems[0]);
                    int price = Integer.parseInt(elems[3]);
                    Appointment a = new Appointment(ID, pRepo.findById(Integer.parseInt(elems[1])), elems[2], price);
                    super.add(a);
                }
                catch(NumberFormatException n) {
                    System.err.println("ID not valid" + elems[0]);
                }
            }
        }
        catch(IOException e) {
            throw new RepositoryException("Reading error" + e);
        }
    }

    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(fileName)) {
            for(Appointment elems : findAll()) {
                String line = elems.getID() + "," + elems.getPatient().getID() + "," + elems.getDate() + "," + elems.getPrice();
                pw.println(line);
            }
        }
        catch(IOException e) {
            throw new RepositoryException("Writing error" + e);
        }
    }

    @Override
    public void add(Appointment a) {
        try {
            super.add(a);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Appointment wasn't added" + e + ": " + a);
        }
    }

    @Override
    public void delete(Appointment a) {
        try {
            super.delete(a);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Appointment wasn't deleted" + e + "; " + a);
        }
    }

    @Override
    public void update(Appointment a, Integer id) {
        try {
            super.update(a, id);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Appointment wasn't updated" + e + ": " + a);
        }
    }
}
