import java.util.Scanner;

class BankException extends Exception {
    public BankException(String msg) {
        super(msg);
    }
}

class Account {
    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public Integer getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    // método de saque com validações
    public void withdraw(double amount) throws BankException {
        if (amount > withdrawLimit) {
            throw new BankException("Erro: O valor excede o limite de saque.");
        }
        if (amount > balance) {
            throw new BankException("Erro: Saldo insuficiente.");
        }
        balance -= amount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Informe os dados da conta:");
            System.out.print("Número: ");
            int number = sc.nextInt();
            sc.nextLine();

            System.out.print("Titular: ");
            String holder = sc.nextLine();

            System.out.print("Saldo inicial: ");
            double balance = sc.nextDouble();

            System.out.print("Limite de saque: ");
            double withdrawLimit = sc.nextDouble();

            Account acc = new Account(number, holder, balance, withdrawLimit);

            System.out.print("\nInforme um valor para saque: ");
            double amount = sc.nextDouble();

            acc.withdraw(amount);

            System.out.printf("Novo saldo: %.2f%n", acc.getBalance());
        }
        catch (BankException e) {
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        sc.close();
    }
}
