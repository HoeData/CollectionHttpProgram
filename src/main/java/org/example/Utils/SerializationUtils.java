package org.example.Utils;

import java.io.*;

public class SerializationUtils {
    public static void SerializationToBinaryFile(String FileName, Object objectsToSerialize) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileName));
            Throwable var3 = null;

            try {
                oos.writeObject(objectsToSerialize);
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (oos != null) {
                    if (var3 != null) {
                        try {
                            oos.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        oos.close();
                    }
                }

            }
        } catch (IOException var15) {
            var15.printStackTrace();
        }

    }

    public static Object BinaryFileToUnSerialization(String FileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileName));
            Throwable var2 = null;

            Object var3;
            try {
                var3 = ois.readObject();
            } catch (Throwable var13) {
                var2 = var13;
                throw var13;
            } finally {
                if (ois != null) {
                    if (var2 != null) {
                        try {
                            ois.close();
                        } catch (Throwable var12) {
                            var2.addSuppressed(var12);
                        }
                    } else {
                        ois.close();
                    }
                }

            }

            return var3;
        } catch (ClassNotFoundException | IOException var15) {
            var15.printStackTrace();
            return null;
        }
    }
}
