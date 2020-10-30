package TextAnalyzation;
import Progress.prgEntity;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Регулярные выражения
// Задание на 5
// Сохранить лог на ЖД
// https://metanit.com/java/tutorial/6.9.php
// Все файловые диалоги JAVA с мопеда
// Добавить метод исправления(замена) символа с записью в файл
public class analyzer{
    private final String pattern = "[" +
            (char)12 + (char)13 + (char)14 + (char)32 + (char)160 +
            "\t\n\r\f'\u000B'\u001C\u001D\u001E\u001F]";

    private File inFile;
    private boolean singleton;
    public prgEntity prg;

    public analyzer(String path) throws FileNotFoundException {
        inFile = new File(path);
        if(!inFile.exists())
            throw new FileNotFoundException();
        allCharsCounter = inFile.length();
        prg = new prgEntity(allCharsCounter);
        singleton = false;
    }

    private long allCharsCounter;
    public long getAllCharsCounter()
    {
        return allCharsCounter;
    }

    private long charsCounter;
    public long getCharsCounter()
    {
        read();
        return charsCounter;
    }

    private long wordsCounter;
    public long getWordsCounter(){
        read();
        return wordsCounter;
    }

    private long linesCounter;
    public long getLinesCounter(){
        read();
        return linesCounter;
    }

    Map<Character, Long> dictionary;

    public String logger(boolean small, boolean clear){
        if(clear) singleton = true;
        read();

        String result = "В файле: " + inFile.getName() + "найденно\n";

        if(linesCounter<1){
            return result + "\tне найденно ни одного слова, символа или строки";
        }
        result += "\tстрок: " + linesCounter + "\n";

        if(wordsCounter < 1 && charsCounter < 1){
            return result + "\tне найденно ни одного слова или символа";
        }

        if(wordsCounter > 0){
            result += "\tслов: " + wordsCounter + "\n";
        }

        if(charsCounter>0) {
            result += "\tуникальных символов: " + dictionary.size() + "\n";
            result += "\tне пустых символов: " + charsCounter + "\n";
            result += "\tвсего символов: " + allCharsCounter + "\n";
        }
        else {
            small = true;
        }

        if(small) return result + "--------------------------------------";

        result += "\nСимвол            | Частота вхождения\n";
        result += "------------------|-------------------\n";

        TreeMap<Character, Long> sorted = new TreeMap<>(dictionary);

        for (Map.Entry<Character, Long> entry : sorted.entrySet()) {
            var ch = entry.getKey();
            String tempStr = ch.toString() + "                 ";
            if(ch == '\u0301') tempStr += " ";
            result += tempStr + "|";

            double d = (double)entry.getValue() / charsCounter;
            BigDecimal bd = new BigDecimal(d).setScale(15, RoundingMode.DOWN);
            tempStr = " " + bd.toString();
            result += tempStr + "\n";
        }

        return result + "--------------------------------------";
    }
    public String logger(){
        return logger(false, false);
    }
    public String logger(boolean small){
        return logger(small, false);
    }

    private void read()
    {
        if(singleton) return;

        try(BufferedReader br = new BufferedReader(new FileReader(inFile)))
        {
            linesCounter = 0;
            charsCounter = 0;
            wordsCounter = 0;
            dictionary = new HashMap<>();

           // System.out.println(prg.getValue());

            //чтение построчно
            String s, rep = "";
            while((s=br.readLine())!=null){
                linesCounter++;

                for(String word : s.split(pattern)) {
                    if (word.length() > 0 && Character.isLetter(word.charAt(0))) {
                        wordsCounter++;
                    }
                }

                for(Character ch : s.toCharArray()){
                    charsCounter++;
                    dictionary.put(ch,
                            dictionary.containsKey(ch) ? dictionary.get(ch) + 1 : 1);
                }
                prg.addValue((long)s.length() + 1);
                String res = prg.toString();
                if(!rep.equals(res)) {
                    System.out.println(prg.getValue() + "\t\t\t" + res);
                    //System.out.println("\t" + res);
                    rep = res;
                }
            }
            singleton = false;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}