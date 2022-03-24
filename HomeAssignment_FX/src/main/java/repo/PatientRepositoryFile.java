package repo;

import domain.*;
import java.io.*;
import exceptions.RepositoryException;

public class PatientRepositoryFile extends PatientRepository{
    private String fileName;

    public PatientRepositoryFile(String filename) {
        fileName = filename;
        //readFromFile();
    }

    private void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] elems = line.split(",");
                if (elems.length > 5) {
                    System.err.println("Number of attributes not valid" + line);
                    continue;
                }
                try {
                    int ID = Integer.parseInt(elems[0]);
                    int age = Integer.parseInt(elems[4]);
                    Patient p = new Patient(ID, elems[1], elems[2], elems[3], age);
                    super.add(p);
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
        try (PrintWriter pw = new PrintWriter(fileName)) {
            for (Patient p : findAll()) {
                String line = p.getID() + "," + p.getName() + "," + p.getPhoneNumber() + "," + p.getProblemDescription() + "," + p.getAge();
                pw.println(line);
            }
        }
        catch(IOException e) {
            throw new RepositoryException("Writing error" + e);
        }
    }


    @Override
    public void add(Patient p) {
        try {
            super.add(p);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Patient wasn't added" + e + ": " + p);
        }
    }

    @Override
    public void delete(Patient p) {
        try {
            super.delete(p);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Patient wasn't deleted" + e + ": " + p);
        }
    }

    @Override
    public void update(Patient p, Integer id) {
        try {
            super.update(p, id);
            writeToFile();
        }
        catch(RuntimeException e) {
            throw new RepositoryException("Patient wasn't updated" + e + ": " + p);
        }
    }
}
