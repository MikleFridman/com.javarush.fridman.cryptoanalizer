import java.util.HashMap;

public class Alphabet {
    public static final char[] SYMBOLS = {'а','б','в','г','д','е','ж','з','и','к','л',
                                          'м','н','о','п','р','с','т','у','ф','х','ц',
                                          'ч','ш','щ','й','ь','ъ','э','ю','я','ё','ы',
                                          ' ','.',',',':',';','?','!','-','(',')','…'};

    public HashMap<Character, Character> getAlphabet(int offset) {
        HashMap<Character, Character> alphabet = new HashMap<>(SYMBOLS.length);
        offset = offset % SYMBOLS.length;

        for (int i = 0; i < SYMBOLS.length; i++) {
            int pos = i + offset;
            if (pos < 0) {
                pos = pos + SYMBOLS.length;
            }
            if (pos >= SYMBOLS.length) {
                pos = pos - SYMBOLS.length;
            }
            alphabet.put(SYMBOLS[i], SYMBOLS[pos]);
        }
        return alphabet;
    }

}
