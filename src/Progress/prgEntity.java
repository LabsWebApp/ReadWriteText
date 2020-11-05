package Progress;

public class prgEntity {
    private long value;
    public long getValue(){
        return value;
    }
    private long maximum;
    private long minimum;
    private long stopwatch = -1,  stopwatchFinish = -1 ;

    public prgEntity(long minimum, long maximum){
        value = this.minimum = minimum;
        this.maximum = maximum;
        percent = (maximum - minimum)/(double)100;
    }

    public prgEntity(long maximum){
        this(0, maximum);
    }

    public prgEntity(){
        this(0, 0);
    }

    private final double percent;

    private void validation(long value) throws Exception {
        if(value<minimum){
            throw new IllegalArgumentException("Значение стало меньше минимума");
        }
        if(value>maximum){
            throw new IllegalArgumentException("Значение стало больше максимума");
        }
    }
    private void validation() throws Exception {
        validation(this.value);
    }

    public void setValue(long value) throws Exception {
        if(value==minimum) stopwatch = System.nanoTime();
        validation(value);
        this.value = value;
        if(value==maximum) stopwatchFinish = System.nanoTime() - stopwatch;
    }
    public void setValue() throws Exception {
        setValue(maximum);
    }

    public void addValue(long add) throws Exception {
        if(value==minimum) stopwatch = System.nanoTime();
        long res = value + add;
        validation(res);
        value = res;
        if(value==maximum) stopwatchFinish = System.nanoTime() - stopwatch;
    }

    public double getCurrentPercent(){
        return (value - minimum)/percent;
    }

    @Override
    public String toString() {
        if(value==minimum)
            return "0";
        double res = getCurrentPercent();
        if(res<1)
            return "1";
        if(res>99 && res<100)
            return "99";
        return String.valueOf((int)Math.round(res));
    }

    public String getStopwatch(){
        if(stopwatch<0)
            return "Not started";
        if(stopwatchFinish<0)
            return String.valueOf(System.nanoTime() - stopwatch);
        return String.valueOf(stopwatchFinish);
    }

    public static void progressing(prgEntity prg) throws InterruptedException {
        while (prg.value < prg.maximum)
        {
            System.out.print("\b\b/");
            Thread.sleep(20);
            System.out.print("\b|");
            Thread.sleep(20);
            System.out.print("\b\\");
            Thread.sleep(20);
            System.out.print("\b——");
            Thread.sleep(20);
        }
    }
    public void progressing() throws InterruptedException {
        progressing(this);
    }
}