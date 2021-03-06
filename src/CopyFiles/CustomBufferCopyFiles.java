package CopyFiles;

import Progress.prgTree;

import java.io.*;

public class CustomBufferCopyFiles {
    public static void copy(String input, String output, int bufferLenght) throws Exception {
        if(bufferLenght<=0){
            throw new Exception("Буфер должен быть больше 0.");
        }
        prgTree prg = new prgTree();
        try(FileInputStream fin=new FileInputStream(input);
            FileOutputStream fos=new FileOutputStream(output, false))
        {
            int _aviable = fin.available();
            prg = new prgTree(_aviable);
            prg.startStopwatch();
            int current = 0;
            int end = 0;
            if(bufferLenght >= _aviable) {
                bufferLenght =_aviable;
            }
            else{
                end = _aviable % bufferLenght;
            }
            boolean flag = true;
            byte[] buffer = new byte[bufferLenght];
            while (flag) {
                if(current > _aviable - bufferLenght)
                {
                    if(end == 0) break;
                    bufferLenght = end;
                    flag = false;
                }
                // считываем буфер
                fin.read(buffer, 0, bufferLenght);
                //System.out.println(pos);
                // записываем из буфера в файл
                fos.write(buffer, 0, bufferLenght);
                //prg.addValue(bufferLenght);
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
