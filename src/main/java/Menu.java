import java.util.HashMap;

public class Menu {
    private static HashMap<Integer, String> menu = new HashMap<>();

    public static HashMap<Integer, String> getMenu() {
        menu.put(1, "Зашифровать файл");
        menu.put(2, "Дешифровать файл");
        menu.put(3, "Brute force");
        menu.put(0, "Выход");
        return menu;
    }
}
