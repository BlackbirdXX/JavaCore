/**
 * Класс со списком работников и реализованным интерфейсом Iterable
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StaffIterator implements Iterable<Employee>{
    List<Employee> staffList = new ArrayList<>();

    @Override
    public Iterator<Employee> iterator() {

        return new Iterator<Employee>() {
            int current;
            @Override
            public boolean hasNext() {
                return current <= staffList.size();
            }

            @Override
            public Employee next() {
                return staffList.get(current);
            }
        };
    }

}
