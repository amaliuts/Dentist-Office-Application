package repo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import exceptions.RepositoryException;
import domain.*;

public class AppointmentRepositorySerialization extends AbstractRepository<Appointment, Integer>{
    private String fileName;
    private PatientRepositorySerialization pRepo;

    public AppointmentRepositorySerialization(String filename, PatientRepositorySerialization prepo) {
        this.fileName = filename;
        this.pRepo = prepo;
        //readFromFile();
    }

    private void readFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            repo = (Map<Integer, Appointment>) in.readObject();
        }
        catch(IOException|ClassNotFoundException e) {
            throw new RepositoryException("Reading error: " + e);
        }
    }

    private void writeToFile() {
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(fileName))){
            o.writeObject(repo);
        }
        catch(IOException e) {
            throw new RepositoryException("error: " + e);
        }
    }

    @Override
    public void add(Appointment a) {
        try {
            super.add(a);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Object wasn't added " + e + " " + a);
        }
    }

    @Override
    public void delete(Appointment a) {
        try {
            super.delete(a);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Object wasn't deleted " + e + " " + a);
        }
    }

    @Override
    public void update(Appointment a, Integer id) {
        try {
            super.update(a, id);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Object wasn't updated " + e + " " + a);
        }
    }

}
