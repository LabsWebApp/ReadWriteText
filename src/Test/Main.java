package Test;
import Progress.prgEntity;
import TextAnalyzation.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        analyzer anal = new analyzer("C:/Users/vovik/OneDrive/Рабочий стол/Bibliya70.txt");

        System.out.println(anal.logger(true));
    }
}
