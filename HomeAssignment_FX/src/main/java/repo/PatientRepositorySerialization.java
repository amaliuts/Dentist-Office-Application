package repo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import exceptions.RepositoryException;
import domain.*;

public class PatientRepositorySerialization extends AbstractRepository<Patient, Integer>{
    private String fileName;

    public PatientRepositorySerialization(String filename) {
        this.fileName = filename;
        //readFromFile();
    }

    private void readFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            // elems = (Map<Integer, Patient>) in.readObject();
        }
        catch(Exception e ) {
            throw new RepositoryException("Reading error: " + e);
        }
    }

    public void writeToFile() {
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(fileName))){
            o.writeObject(repo);
        }
        catch(IOException e) {
            throw new RepositoryException("error: " + e);
        }
    }

    @Override
    public void add(Patient p) {
        try {
            super.add(p);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Object wasn't added " + e + " " + p);
        }
    }

    @Override
    public void delete(Patient p) {
        try {
            super.delete(p);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Object wasn't deleted " + e + " " + p);
        }
    }

    @Override
    public void update(Patient p, Integer id) {
        try {
            super.update(p, id);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Object wasn't updated " + e + " " + p);
        }
    }

}
