package javacore.homework01.models;

import java.util.Random;

/**
 * Класс Notebook - основная модель для работы программы
 */

public class Notebook implements Comparable<Notebook> {
    int article;
    public int cost;
    public int ram;
    int ssd;

    public Notebook(int article) {
        Random rand = new Random();
        this.article = article;
        this.cost = rand.nextInt(10000, 20000);
        this.ram = rand.nextInt(4, 16);
        this.ssd = rand.nextInt(1, 5) * 500;
        ;
    }

    /**
     * Фунция вывода на экран
     */

    public void PrintInfo() {
        System.out.println(String.format("Ноутбук № %d : Стоимость %d, Оперативная память %d гб, Накопитель %d гб.",
                this.article, this.cost, this.ram, this.ssd));

    }

    /**
     * Фунция сравнения по введенным параметрам
     */

    @Override
    public int compareTo(Notebook o) {
        if (this.ssd > o.ssd)
            return -1;
        if (this.ssd < o.ssd)
            return 1;
        if ((this.ssd == o.ssd) & (this.cost > o.cost))
            return 1;
        if ((this.ssd == o.ssd) & (this.cost < o.cost))
            return -1;
        else
            return 0;
    }

}
