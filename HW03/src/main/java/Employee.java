/**
 * Родительский класс для сотрудников. В нем реализован интерфейс Comparable для сортировки.
 */
public abstract class Employee implements Comparable<Employee>{
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Double getSalaryInfo();

    private void updateName(String name){
        if(name == null || name.length() < 3){
            throw new RuntimeException("Некоректное имя сотрудника. ");
        }
        this.name = name;
    }

    @Override
    public int compareTo(Employee e) {
        return this.getSalaryInfo().compareTo(e.getSalaryInfo());
    }
}
