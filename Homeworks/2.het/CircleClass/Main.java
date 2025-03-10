import java.util.Scanner;

class Circle
{
    public double radius;
    public Circle(double r)
    {
        this.radius = r;
        }
        public double kerulet()
        {
            return 2 * this.radius * Math.PI;
        }
        public double terulet()
        {
            return this.radius * this.radius * Math.PI;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        System.out.printf("Adja meg a kör sugarát: ");
        Scanner input = new Scanner(System.in);
        double r = input.nextDouble();

        Circle circle = new Circle(r);
        double k = circle.kerulet();
        double t = circle.terulet();
        System.out.printf("kerulet: %.2f cm\nTerulet: %.2f cm2\n", k, t);
        input.close();
    }
}
