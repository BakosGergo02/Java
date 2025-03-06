public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("hibás paraméterezés!");
            System.exit(1);
        }

        String input = args[0].substring(2);
        
        Endianness endianness = new Endianness();

        endianness.input = input;

        endianness.reverseEndianness(input);

    }
}
