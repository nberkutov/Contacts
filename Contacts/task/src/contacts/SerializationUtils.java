package contacts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializationUtils {
    public static void serialize(Object object, String file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close();
    }

    public static Object deserialize(String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
