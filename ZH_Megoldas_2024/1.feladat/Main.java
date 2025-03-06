public class Main {
    public static void main(String[] args) {
    
        Processor p0 = new Processor("3.5 4");
        Processor p1 = new Processor("2.8 8");
        Processor p2 = new Processor("4.0 6");

        System.out.println(p0); //Processor(3.5 GHz, 4 cores)
        System.out.println(p1.getFrequency()); //2.8 GHz
        System.out.println(p1.getFrequencyInHertz()); //2800000000.0
        System.out.println(p1.getCoreCount()); //8
        System.out.println(p2.getPerformanceRating()); //6.0
        System.out.println(p0.isHighPerformance()); //false
        System.out.println(p2.isHighPerformance()); //true
        System.out.println(p0.compare(p2)); //-1    
    }
}
