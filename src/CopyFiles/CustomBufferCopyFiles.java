package CopyFiles;

import java.io.*;

public class CustomBufferCopyFiles {
    public static void copy(File input, File output)
    {
        try(FileInputStream fin=new FileInputStream(input);
            FileOutputStream fos=new FileOutputStream(output))
        {
            byte[] buffer = new byte[fin.available()];
            // считываем буфер
            fin.read(buffer, 0, buffer.length);
            // записываем из буфера в файл
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
