
import java.util.Collections;

public class Program {

    public static void main(String[] args) {

        StaffIterator staffItr = new StaffIterator();
        staffItr.staffList.add(Freelancer.create("Петр", 1000));
        staffItr.staffList.add(Worker.create("Елена", 95000));
        staffItr.staffList.add(Worker.create("Василий", 100000));
        staffItr.staffList.add(Freelancer.create("Анна", 1200));

        System.out.println("\nИсходный список сотрудников : ");
        for (Employee item : staffItr.staffList){
            System.out.printf("Сотрудник %s получает зарплату : %.1f в месяц.\n", item.getName(), item.getSalaryInfo());
        }
        System.out.println("\nСортировка по возрастанию зарплаты : ");
        Collections.sort(staffItr.staffList);
        for (Employee item : staffItr.staffList){
            System.out.printf("Сотрудник %s получает зарплату : %.1f в месяц.\n", item.getName(), item.getSalaryInfo());
        }

    }
}
