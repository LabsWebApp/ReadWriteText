package Progress;

public class prgEntity {
    private long value;
    private long maximum;
    private long minimum;

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

    private double percent;

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
        validation(value);
        this.value = value;
    }

    public void addValue(long add) throws Exception {
        long res = value + add;
        validation(res);
        value = res;
    }

    public long getValue(){return value;}

    public double getCurrentPercent()
    {
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
}