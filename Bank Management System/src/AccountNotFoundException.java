public class AccountNotFoundException extends RuntimeException
{
    public AccountNotFoundException(String accountNotFount) {
        System.out.println(accountNotFount);
    }

}
