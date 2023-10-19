public class Transaction
{
    private int own_account_number;
    private String action;
    private int action_account_number;
    private String action_account_name;
    private int action_money;

    public Transaction(int own_account_number, String action, int action_account_number, String action_account_name, int action_money) {
        this.own_account_number = own_account_number;
        this.action = action;
        this.action_account_number = action_account_number;
        this.action_account_name = action_account_name;
        this.action_money = action_money;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "own_account_number=" + own_account_number +
                ", action='" + action + '\'' +
                ", action_account_number=" + action_account_number +
                ", action_account_name='" + action_account_name + '\'' +
                ", action_money=" + action_money +
                '}';
    }

    public int getOwn_account_number() {
        return own_account_number;
    }

    public void setOwn_account_number(int own_account_number) {
        this.own_account_number = own_account_number;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getAction_account_number() {
        return action_account_number;
    }

    public void setAction_account_number(int action_account_number) {
        this.action_account_number = action_account_number;
    }

    public String getAction_account_name() {
        return action_account_name;
    }

    public void setAction_account_name(String action_account_name) {
        this.action_account_name = action_account_name;
    }

    public int getAction_money() {
        return action_money;
    }

    public void setAction_money(int action_money) {
        this.action_money = action_money;
    }
}
