import java.util.Objects;
import java.util.Scanner;

public class Translation {
    public static void Transfer(Client sender, Client recipient, Scanner scanner) throws RecipientException, NegativeInputException, BallanceException {

        int money;
        System.out.println("Введите имя получателя перевода : ");
        String recipientName = scanner.nextLine();
        System.out.println("Введите сумму перевода : ");
        if (!Objects.equals(recipientName, recipient.name)){
            throw new RecipientException("Клиента с таким именем в банке не зарегистрировано");
        }
        money = scanner.nextInt();
        if (money < 0){
            throw new NegativeInputException("Введена неверная сумма перевода.");
        }
        if (sender.balance < money){
            throw new BallanceException("Не достаточно средств на счету. В наличии : " + sender.balance +", а вы пытаетесь перевести : " + money);
        }
        sender.balance -= money;
        recipient.balance +=money;
    }
}
