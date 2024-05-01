/**
 * Класс наследник для постоянных работников
 */
public class Worker extends Employee{

    private double monthlyRate;

    private Worker(String name, double monthlyRate) {
        super(name);
        this.monthlyRate = monthlyRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(int monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    @Override
    public Double getSalaryInfo() {
        return monthlyRate;
    }

    public static Worker create(String name, int monthlyRate){
        if (name == null || name.length() < 3){
            throw new RuntimeException("Некорректное имя рабочего.");
        }
        if (monthlyRate < 1) {
            throw new RuntimeException("Некорректная ставка");
        }
        return new Worker(name, monthlyRate);
    }
}
