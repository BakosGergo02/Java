public class Processor{

    public String adatok;
    public double frequency;
    public int coreCount;
    
    
    public Processor() {
    }
    
    public Processor(String adatok) {
        this.adatok = adatok;
        
        String[] split = adatok.split(" ");
        this.frequency = Double.parseDouble(split[0]);
        this.coreCount = Integer.parseInt(split[1]);
    }
    
    public double getFrequency() {
        return frequency;
    }
    public int getCoreCount() {
        return coreCount;
    }
    public String getFrequencyInHertz() {
        double FrequencyInHertz = frequency * 1000000000.0;
        String res = String.format("%.1f", FrequencyInHertz);

        return res;
    }
    public double getPerformanceRating() {
        return frequency*coreCount;
    }
    public boolean isHighPerformance() {
        if (coreCount >= 6){
            return true;
        }
        else
        return false;
    }
    public int compare(Processor other) {
        if (this.getPerformanceRating() > other.getPerformanceRating()) {
            return 1;
        } else if (this.getPerformanceRating() < other.getPerformanceRating()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Processor(" + frequency + "GHz, " + coreCount + " cores)";
    }


    
}