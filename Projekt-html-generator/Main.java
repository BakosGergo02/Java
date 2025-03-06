import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String root = "C:/Users/Dell/OneDrive/Dokumentumok/Projekt_images/images"; 

        // Először példányosítani kell a támogatott kiterjesztések Listájával a HTML generatort
        HTMLGenerator html = new HTMLGenerator(root, List.of("jpg", "png"));
        // HTML fileok generálása
        html.generateHtmls(root);
    }
}
