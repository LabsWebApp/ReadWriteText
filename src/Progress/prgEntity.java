package Progress;

public class prgEntity {
    private long value;
    private long maximum;
    private long minimum;

    public prgEntity(long minimum, long maximum){
        value = this.minimum = minimum;
        this.maximum = maximum;
    }

    public prgEntity(long maximum){
        this(0, maximum);
    }

    public prgEntity(){
        this(0, 0);
    }

    private final double percent = ((double)maximum - minimum)/100;

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

    public double getCurrentPercent()
    {
        return (value - minimum)/percent;
    }
}