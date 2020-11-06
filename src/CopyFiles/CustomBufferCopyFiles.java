package CopyFiles;

import Progress.prgTree;

import java.io.*;

public class CustomBufferCopyFiles {

    public static void copy(String input, String output, int bufferLenght) throws Exception {
        prgTree prg = new prgTree();
        try(FileInputStream fin=new FileInputStream(input);
            FileOutputStream fos=new FileOutputStream(output, false))
        {
            int _aviable = fin.available();
            prg = new prgTree(_aviable);
            int current = 0;
            int end = 0;
            if(bufferLenght >= _aviable) {
                bufferLenght =_aviable;
            }
            else{
                end = _aviable % bufferLenght;
            }
            boolean flag = true;
            while (flag) {
                if(current > _aviable - bufferLenght)
                {
                    if(end == 0) break;
                    bufferLenght = end;
                    flag = false;
                }
                byte[] buffer = new byte[bufferLenght];
                // считываем буфер
                fin.read(buffer, 0, bufferLenght);
                //System.out.println(pos);
                // записываем из буфера в файл
                fos.write(buffer, 0, bufferLenght);
                prg.addValue(bufferLenght);
                if(flag) current += bufferLenght;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(prg.getCurrentPercent() + "%\t" + prg.getStopwatches());
        }
    }
}
