/*
Создать программу управления банковским счетом (Account).
Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств.
При этом она должна обрабатывать следующие исключительные ситуации:

Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
Попытка внести депозит с отрицательной суммой должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
Попытка снять средства, сумма которых превышает текущий баланс, должна вызывать исключение InsufficientFundsException с сообщением о недостаточных средствах и текущим балансом.

Продемонстрируйте работу вашего приложения:
Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.

2*.
Создать несколько типов счетов, унаследованных от Account, например: CreditAcciunt, DebitAccount.
Создать класс (Transaction), позволяющий проводить транзакции между счетами (переводить сумму с одного счета на другой)

Класс Transaction должен возбуждать исключение в случае неудачной попытки перевести деньги с одного счета на другой.

Продемонстрируйте работу вашего приложения:
Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.

Достаточно выпонить только первую задачу, вторая задача является дополнительной.
 */

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client1 = null;
        while (true) {
            try {
                client1 = CreateClient(scanner);
                break;
            } catch (NegativeInputException e) {
                scanner.nextLine();
                e.printStackTrace();
            }
        }
        System.out.println(client1.toString());

        try {
            client1.UpBalance(scanner);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        System.out.println(client1.toString());

        try {
            client1.CashWithdrawal(scanner);
        } catch (BallanceException e) {
            e.printStackTrace();
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        System.out.println(client1.toString());

        Client client2 = new Client("Благотворительность", 0);
        System.out.println(client2.toString());
        while (true){
            try {
                Translation.Transfer(client1, client2, scanner);
                break;
            } catch (RecipientException e) {
                e.printStackTrace();
            } catch (NegativeInputException e) {
                e.printStackTrace();
            } catch (BallanceException e) {
                e.printStackTrace();
            }
        }

        System.out.println(client1.toString());
        System.out.println(client2.toString());

        scanner.close();
    }

    public static Client CreateClient(Scanner scanner) throws NegativeInputException {
        String name;
        int balance;
        System.out.println("Введите имя : ");
        name = scanner.nextLine();
        System.out.println("Положите денежжные средства, для активации счета : ");
        balance = scanner.nextInt();
        scanner.nextLine();
        if (balance < 0){
            throw new NegativeInputException("Вы ввели неверное значение " + balance);
        }
        Client client = new Client(name, balance);
        return client;
    }




}
