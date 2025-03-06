import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        
        String input = scanner.next("Adja meg a számokat: ");
        int[] szamok = new int[input.length()];

        int szam = 0;
        for (int i = 0; i < input.length();i++) {
            String subString = input.substring(i,i+1);

            szam += Integer.parseInt(subString);
            if (i % 4 == 0) {
                for (int j = 0; j < szamok.length; j++) {
                    szamok[j] = szam;
                    szam = 0;
                }
            }
        }

        for (int i : szamok) {
            System.out.print(i + " ");
        }

        scanner.close();
    }
}
