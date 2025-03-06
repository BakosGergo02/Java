import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = FileUtils.readLines("szavak.txt");
        for (int i = 0; i < lines.size()-1; i++) {
            if (lines.get(i).substring(lines.get(i).length() - 2).matches(lines.get(i + 1).substring(lines.get(i + 1).length() - 2))) {
                System.out.printf("%s - %s\n", lines.get(i), lines.get(i + 1));
                break;
            }
        }
    }

}
