/**
 * Класс наследник для наемных работников
 */
public class Freelancer extends Employee{

    private int hourlyRate;

    private Freelancer(String name, int hourlyRate) {
        super(name);
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public Double getSalaryInfo() {
        return 20.8*8*hourlyRate;
    }

    public static Freelancer create(String name, int hourlyRate){
        if (name == null || name.length() < 3){
            throw new RuntimeException("Некорректное имя фрилансера.");
        }
        if (hourlyRate < 1) {
            throw new RuntimeException("Некорректная ставка");
        }
        return new Freelancer(name, hourlyRate);
    }
}
