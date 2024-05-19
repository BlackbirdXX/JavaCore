

import java.util.Scanner;

public class Client {
    String name;
    int balance;


    public Client(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }



    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void UpBalance(Scanner scanner) throws NegativeInputException {

        int balance;
        System.out.println("Положите денежные средства на счет : ");
        balance = scanner.nextInt();
        scanner.nextLine();
        if (balance < 0){
            throw new NegativeInputException("Вы ввели неверное значение " + balance);
        }
        this.balance += balance;
    }
    public void CashWithdrawal(Scanner scanner) throws BallanceException, NegativeInputException {
        int balance;
        System.out.println("Сколько наличных вы хотите снять?  ");
        balance = scanner.nextInt();
        scanner.nextLine();
        if (balance < 0){
            throw new NegativeInputException("Вы ввели неверное значение " + balance);
        }
        if (balance > this.balance){
            throw new BallanceException("Вы пытаетесь снять " + balance +", а на счету " + this.balance);
        }
        this.balance -= balance;
    }

    @Override
    public String toString() {
        return "[" +
                "Имя: '" + name + '\'' +
                ", Баланс: " + balance +
                ']';
    }

}
