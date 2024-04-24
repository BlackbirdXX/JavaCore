package javacore.homework01.services;

import javacore.homework01.models.Notebook;
import java.util.Comparator;

/**
 * Класс Comparator используется для сравнения моделей
 */


public class Comparators implements Comparator<Notebook> {
    @Override
    public int compare(Notebook o1, Notebook o2) {
        if (o1.ram > o2.ram)
            return -1;
        if (o1.ram < o2.ram)
            return 1;
        if ((o1.ram == o2.ram) & (o1.cost > o2.cost))
            return 1;
        if ((o1.ram == o2.ram) & (o1.cost < o2.cost))
            return -1;
        else
            return 0;
    }
}
