import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        HashMap<Integer, String> mainMenu = Menu.getMenu();
        var menu = mainMenu.entrySet();
        for (Map.Entry<Integer, String> menuPoint : menu) {
            System.out.println(menuPoint);
        }
        System.out.println();
        System.out.println("Выберите пункт меню (0-3):");

        while (console.hasNext()) {
            int input;
            try {
                input = Integer.parseInt(console.nextLine());
            } catch (NumberFormatException e) {
                input = Byte.MAX_VALUE;
            }
            System.out.println();
            switch (input) {
                case 0 -> {
                    System.out.println("До свидания. Мы уже скучаем.");
                    System.exit(0);
                }
                case 1 -> callEncrypt(1);
                case 2 -> callEncrypt(2);
                case 3 -> callEncrypt(3);
                default -> System.out.println("Ошибочный ввод. Повторите еще раз, пожалуйста");
            }
        }
    }

    private static void callEncrypt(int mode) throws IOException {
        Scanner console = new Scanner(System.in);
        Alphabet abc = new Alphabet();
        String pathSource = getPath("Введите путь к файлу источнику:", false);

        if (mode == 3) {
            String dir = Path.of(pathSource).getParent().toString();
            Path pathDir = Paths.get(dir,"BruteForce");
            if (!Files.exists(pathDir)) {
                Files.createDirectories(pathDir);
            }
            for (int i = 0; i < Alphabet.SYMBOLS.length; i++) {
                String fileName = "text_" + i + ".txt";
                String pathTarget = Paths.get(pathDir.toString(),fileName).toString();
                Cipher cipher = new Cipher(abc.getAlphabet(i));
                cipher.encrypt(pathSource, pathTarget);
            }
        } else {
            String pathTarget = getPath("Введите путь к файлу приемнику:", true);
            System.out.println("Введите ключ шифрования:");
            int key = console.nextInt();
            if (mode == 2) {
                key = key * -1;
            }
            Cipher cipher = new Cipher(abc.getAlphabet(key));
            cipher.encrypt(pathSource, pathTarget);
        }
        System.exit(0);
    }

    private static String getPath(String msg, boolean noControlPath) {
        Scanner console = new Scanner(System.in);
        System.out.println(msg);
        String path = "";

        while (console.hasNext()) {
            path = console.nextLine();
            if (Files.exists(Path.of(path)) || noControlPath) {
                break;
            } else {
                System.out.println("Файл или директория по указанному пути не существует");
            }
        }
        return path;
    }
}

