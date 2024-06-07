import java.util.Scanner;

public class Menu {

    final private static String CORNICE = "--------------------------------";
    final private static String VOCE_USCITA = "6\tPassa al prossimo turno";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

    private String titolo;
    private String[] voci;

    public Menu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = new String[voci.length + 1];
        System.arraycopy(voci, 0, this.voci, 0, voci.length);
        this.voci[voci.length] = "Passa al prossimo turno"; // Aggiungi la voce di uscita
    }

    public int scegli() {
        stampaMenu();
        return leggiIntero(RICHIESTA_INSERIMENTO, 1, voci.length);
    }

    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i = 0; i < voci.length; i++) {
            System.out.println((i + 1) + "\t" + voci[i]);
        }
        System.out.println();
    }

    public static int leggiIntero(String messaggio, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        do {
            System.out.print(messaggio);
            while (!scanner.hasNextInt()) {
                System.out.print("Inserisci un numero valido: ");
                scanner.next();
            }
            numero = scanner.nextInt();
        } while (numero < min || numero > max);
        return numero;
    }
}
