public class IncorrectPinException extends RuntimeException
{

    public IncorrectPinException(String incorrectPin) {
        System.out.println(incorrectPin);
    }
}
