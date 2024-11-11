import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Cipher {

    private HashMap<Character, Character> alphabet;
    private Set<Character> controlSet;

    Cipher(HashMap<Character, Character> abc) {
        this.alphabet = abc;
        this.controlSet = alphabet.keySet();
    }

    public void encrypt(String pathSource, String pathTarget) {
        try (FileReader fileReader = new FileReader(pathSource);
            FileWriter fileWriter = new FileWriter(pathTarget)) {
            BufferedReader bufferReader = new BufferedReader(fileReader);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            while (bufferReader.ready()) {
                String str = convertString(bufferReader.readLine());
                bufferWriter.write(str);
                Thread.sleep(100);
                System.out.print(".");
                bufferWriter.newLine();
            }
            bufferReader.close();
            bufferWriter.close();
            System.out.println();
            System.out.println("Процесс обработки файла " + pathTarget + " завершен");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertString(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char symbol = charArray[i];
            boolean upperCase = false;
            if (Character.isUpperCase(symbol)) {
                symbol = Character.toLowerCase(symbol);
                upperCase = true;
            }
            if (controlSet.contains(symbol)) {
                char convertedChar = alphabet.get(symbol);
                if (upperCase) {
                    convertedChar = Character.toUpperCase(convertedChar);
                }
                charArray[i] = convertedChar;
            }
        }
        return String.valueOf(charArray);
    }

}
