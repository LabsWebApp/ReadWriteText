package Test;
import TextAnalyzation.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        analyzer anal = new analyzer("C:\\Users\\iskop\\OneDrive\\Рабочий стол\\file.txt");

        System.out.println(anal.logger());
    }
}
