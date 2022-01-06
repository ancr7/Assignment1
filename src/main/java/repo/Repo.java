package repo;

import assignment2.StudentModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Repo {
  final String FILE_NAME = "DB.txt";
  private static Repo single_instance = null;

  private Repo() {}

  public static Repo getInstance() {
    if (single_instance == null) {
      try {
        single_instance = new Repo();
      } catch (Exception e) { e.printStackTrace(); }
    }
    return single_instance;
  }

  public void write(ArrayList<StudentModel> studentList) throws Exception {
    FileOutputStream fos = new FileOutputStream(FILE_NAME);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(studentList);
  }

  @SuppressWarnings("unchecked")
  public ArrayList<StudentModel> read() throws Exception {
    FileInputStream fis = new FileInputStream(FILE_NAME);
    ObjectInputStream ois = new ObjectInputStream(fis);
    return (ArrayList<StudentModel>)ois.readObject();
  }
}
