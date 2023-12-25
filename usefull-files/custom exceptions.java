
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super();
    }
    public InsufficientFundsException(String message) {
        super(message);
    }
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

public void withdraw(int amount) {
    if (amount < 0) {
        throw new IllegalArgumentException("Illegal amount");
    }
    if (this.balance < amount) {
        throw new InsufficientFundsException();
    }
    this.balance -= amount;
}

//Custom exception with specified message & calculation
public class InsufficientFundsException extends RuntimeException {
    private final int balance;
    private final int amount;

    public InsufficientFundsException(int balance, int amount) {
        super(String.format("Insufficient Funds: cannot withdraw %d when the balance is only %d.", amount, balance));
        this.balance = balance;
        this.amount = amount;
    }
    public int getBalance() {
        return balance;
    }
    public int getAmount() {
        return amount;
    }
}

public void withdraw(int amount) {
    if (amount < 0) {
        throw new IllegalArgumentException("Illegal amount");
    }
    if (this.balance < amount) {
        throw new InsufficientFundsException(this.balance, amount);
    }
    this.balance -= amount;
}
```

