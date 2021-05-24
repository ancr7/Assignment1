package Assignment2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Repo {

  FileOutputStream fos;
  FileInputStream fis;

  Repo() {
  }

  void write(ArrayList<StudentModel> studentList) throws Exception {
    fos = new FileOutputStream(new File("DB.txt"));
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(studentList);
    System.out.println("data written");
  }

  @SuppressWarnings("unchecked")
  ArrayList<StudentModel> read() throws Exception {
    fis = new FileInputStream(new File("DB.txt"));
    ObjectInputStream ois = new ObjectInputStream(fis);
    return (java.util.ArrayList<StudentModel>) ois.readObject();
  }

  protected void finalize() throws IOException {
    //resources to be close
    fos.close();
    fis.close();
  }
}
