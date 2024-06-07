import java.util.Scanner;
public class Taunt {

    public static void TauntFunc() {
            Scanner scanner = new Scanner(System.in);

            // Read the number of test cases
            System.out.println("Inserisci il numero di parole da decifrare:");
            int N = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            // Read the encrypted words
            System.out.println("Inserisci le parole da decifrare:");
            String[] encryptedWords = new String[N];
            for (int i = 0; i < N; i++) {
                encryptedWords[i] = scanner.next();
            }

            System.out.println("Inserisci le parole chiave:");
            // Read the keys
            String[] keys = new String[N];
            for (int i = 0; i < N; i++) {
                keys[i] = scanner.next();
            }

            // Decrypt each word and print the result
            for (int i = 0; i < N; i++) {
                String decryptedWord = Taunt.decrypt(encryptedWords[i], keys[i]);
                System.out.println(decryptedWord);
            }

            scanner.close();

    }
    // Method to decrypt a word using the given key
    private static String decrypt(String encryptedWord, String key) {
        StringBuilder decryptedWord = new StringBuilder();

        for (int i = 0; i < encryptedWord.length(); i++) {
            char encryptedChar = encryptedWord.charAt(i);
            char keyChar = key.charAt(i % key.length());

            // Determine the shift amount (keyChar - 'a')
            int shift = keyChar - 'a';

            // Decrypt the character
            char decryptedChar;
            if (Character.isUpperCase(encryptedChar)) {
                decryptedChar = (char) ('A' + (encryptedChar - 'A' - shift + 26) % 26);
            } else if (Character.isLowerCase(encryptedChar)) {
                decryptedChar = (char) ('a' + (encryptedChar - 'a' - shift + 26) % 26);
            } else {
                decryptedChar = encryptedChar; // Non-alphabetic characters are not decrypted
            }

            decryptedWord.append(decryptedChar);
        }

        return decryptedWord.toString();
    }

}
