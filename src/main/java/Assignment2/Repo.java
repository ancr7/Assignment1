package Assignment2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Repo {

  FileOutputStream fos;
  FileInputStream fis;

  Repo() throws Exception {
    fos = new FileOutputStream(new File("DB.txt"));
    fis = new FileInputStream(new File("DB.txt"));
  }

  void write(ArrayList<StudentModel> studentList) throws Exception {
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(studentList);
  }

  @SuppressWarnings("unchecked")
  void read(ArrayList<StudentModel> studentList) throws Exception {
    ObjectInputStream ois = new ObjectInputStream(fis);
    studentList = (java.util.ArrayList<StudentModel>) ois.readObject();
  }
}
