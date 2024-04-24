package javacore.homework01.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javacore.homework01.services.Comparators;
import javacore.homework01.models.Notebook;

/**
 * Основной класс приложения.
 */

public class Price {


    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        List<Notebook> notebooks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            notebooks.add(new Notebook(i));
        }
        for (Notebook n : notebooks) {
            n.PrintInfo();
        }
        int val;
        Scanner nameScanner = new Scanner(System.in);
        System.out.print ("Выберите параметры сортировки : \n1. Сортировка по возрастанию цены.\n2. Сортировка по убыванию цены.\n3. Выгодное предложение по оперативной памяти.\n4. Выгодное предложение по накопителю.\n");
        val = nameScanner.nextInt();
        nameScanner.close();

        switch (val) {
            case 1:
                System.out.println("Сортировка по возрастанию цены");
                notebooks.sort(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
                for (Notebook n : notebooks) {
                    n.PrintInfo();
                }
                break;
            case 2:
                System.out.println("Сортировка по убыванию цены");
                notebooks.sort(((o1, o2) -> Integer.compare(o2.cost, o1.cost)));
                for (Notebook n : notebooks) {
                    n.PrintInfo();
                }
                break;
            case 3:
                System.out.println("Выгодное предложение по оперативной памяти");
                notebooks.sort(new Comparators());
                for (Notebook n : notebooks) {
                    n.PrintInfo();
                }
                break;
            case 4:
            System.out.println("Выгодное предложение по накопителю");
            Collections.sort(notebooks);
                for (Notebook n : notebooks) {
                    n.PrintInfo();
                }
                break;
        }
    }
}
