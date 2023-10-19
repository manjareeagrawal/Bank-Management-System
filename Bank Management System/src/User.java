public class User
{
    private String name;
    private int account_number;
    private int balance;
    private int pin;
    private boolean status;
    private String account_type;

    public User()
    {

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", account_number=" + account_number +
                ", balance=" + balance +
                ", pin=" + pin +
                ", status=" + status +
                '}';
    }

    public User(String name, int account_number, int balance, int pin, boolean status, String account_type) {
        this.name = name;
        this.account_number = account_number;
        this.balance = balance;
        this.pin = pin;
        this.status = status;
        this.account_type=account_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
