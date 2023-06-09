package Chapter_15.Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialization
 */
public class Serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException{ // For readObject()
        
        
        File filePath = new File("Chapter_15/Serialization/serial.out");
        Person Jay = new Person("Jay", "Professor");
        Person Wilson = new Person("Wilson", "Doctor");

        FileOutputStream fileOut = new FileOutputStream(filePath); 
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        try (fileOut; objOut){
            objOut.writeObject(Jay);
            objOut.writeObject(Wilson);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        FileInputStream fileInput = new FileInputStream(filePath); 
        ObjectInputStream objInput = new ObjectInputStream(fileInput);
        try (fileInput; objInput) {
                Person p1 = (Person)objInput.readObject(); // return type of readObject() is object
                Person p2 = (Person)objInput.readObject();
                System.out.println(p1);
                System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePath.delete();
    }
}