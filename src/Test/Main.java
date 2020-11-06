package Test;
import CopyFiles.CustomBufferCopyFiles;
import Progress.prgEntity;
import TextAnalyzation.*;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
       // analyzer anal = new analyzer("C:/Users/vovik/OneDrive/Рабочий стол/Bibliya70.txt");

       // System.out.println(anal.logger(true)); 5.6
        String inputFile = "C:/Users/vovik/OneDrive/Рабочий стол/Bibliya3.txt",
                outputFile = "G:/file_copy.txt";
        (new File(outputFile)).delete();
        CustomBufferCopyFiles.copy(inputFile, outputFile, 1);
    }
}
