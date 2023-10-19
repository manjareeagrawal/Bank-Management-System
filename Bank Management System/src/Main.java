import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Exception
{
    public static void main(String[] args)
    {
        ArrayList<User> arrayList=new ArrayList<>();
        arrayList.add(new User("Manjaree",101,10000,111,true,"Savings"));
        arrayList.add(new User("Sakshi",102,20000,111,true,"Checking"));
        arrayList.add(new User("Bhavya",103,40000,111,true,"Joint"));
        arrayList.add(new User("Jasneet",104,30000,111,false,"Savings"));

        ArrayList<Transaction> transactionsList =new ArrayList<>();

        while (true) {
            System.out.println("Welcome to ICICI bank... ");
            System.out.println("Please select the integer to get the following service:");
            System.out.println("1. To create Account in our bank.");
            System.out.println("2. Add balance.");
            System.out.println("3. Debit money from my account.");
            System.out.println("4. Send money to other.");
            System.out.println("5. This is for ADMIN - See all bank account details.");
            System.out.println("6. Display current account balance.");
            System.out.println("7. Display a user's transaction history.");

            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            switch (a) {
                case 1:
                    System.out.println("Which type of account do you want to open? Select accordingly.");
                    System.out.println("1. Savings account");
                    System.out.println("2. Cheking account");
                    System.out.println("3. Joint account");
                    int account_type=scanner.nextInt();
                    String account_ty="";
                    switch(account_type)
                    {
                        case 1:
                            account_ty="Savings";
                            break;
                        case 2:
                            account_ty="Checking";
                            break;
                        case 3:
                            account_ty="Joint";
                        default:
                            throw new UnauthorizedActionException("Invalid choice");

                    }
                    System.out.println("Enter your name:");
                    String name = scanner.next();
                    System.out.println("Congratulations " + name + ". We are ahead of one step to create an account.");
                    System.out.println("Please enter 3 digit pin for your bank account.");
                    int pin = scanner.nextInt();
                    User u1 = new User(name, arrayList.get(arrayList.size() - 1).getAccount_number() + 1, 0, pin, true,account_ty);
                    arrayList.add(u1);
                    System.out.println("Account created successfully... ");
                    System.out.println("These are the details:");
                    System.out.println("Account Name: " + u1.getName());
                    System.out.println("Account Number: " + u1.getAccount_number());
                    System.out.println("Account Balance: " + u1.getBalance());
                    System.out.println("Account Type: "+u1.getAccount_type());
                    break;

                case 2:
                    try {
                        System.out.print("Enter account number = ");
                        int acc_num = scanner.nextInt();
                        System.out.print("Enter Name =");
                        String acc_name = scanner.next();
                        System.out.print("Enter Ammount you want to add = ");
                        int ammount_add = scanner.nextInt();

                        List<User> checklist = arrayList.stream().filter(user -> user.getAccount_number() == acc_num && Objects.equals(user.getName(), acc_name)).collect(Collectors.toList());
                        if (!checklist.isEmpty()) {
                            if (!checklist.get(0).isStatus()) {
                                throw new UnauthorizedActionException("Your account is inactive. You cannot perform any action.");
                            }
                            if (ammount_add <= 0) {
                                throw new AddValidAmmountToYourAccount();
                            } else {
                                System.out.println("Enter your pin to proceed= ");
                                int pin_add=scanner.nextInt();
                                if(checklist.get(0).getPin()== pin_add) {
                                    int v=checklist.get(0).getBalance() + ammount_add;
                                    checklist.get(0).setBalance(v);

                                    transactionsList.add(new Transaction(acc_num,"Credit",acc_num,acc_name,ammount_add));

                                    System.out.println("Ammount is added to you account successfully.");
                                    System.out.println("Your account balance is= " + (v));
                                }
                                else {
                                    throw new IncorrectPinException("Incorrect Pin");
                                }
                            }
                        } else {
                            throw new AccountNotFoundException("Account not fount");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception:" + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter account number =");
                        int acc_num = scanner.nextInt();
                        System.out.print("Enter Name =");
                        String acc_name = scanner.next();
                        System.out.print("Enter Ammount you want to deposit =");
                        int ammount_deposit = scanner.nextInt();

                        List<User> checklist = arrayList.stream().filter(user -> user.getAccount_number() == acc_num && Objects.equals(user.getName(), acc_name)).collect(Collectors.toList());
                        if (!checklist.isEmpty()) {
                            if (!checklist.get(0).isStatus()) {
                                throw new UnauthorizedActionException("Your account is inactive. You cannot perform any action.");
                            }
                            if (ammount_deposit > checklist.get(0).getBalance()) {
                                throw new InsufficientFundsException("You have insufficient balance in your account.");
                            } else {
                                int val = checklist.get(0).getBalance() - ammount_deposit;
                                checklist.get(0).setBalance(val);
                                transactionsList.add(new Transaction(acc_num,"Debit",acc_num,acc_name,ammount_deposit));
                                System.out.println("Ammount is deposited successfully.");
                                System.out.println("Your account balance is= " + val);
                            }
                        } else {
                            throw new AccountNotFoundException("Account not fount");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception:" + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter your own account number =");
                        int acc_num_my = scanner.nextInt();
                        System.out.print("Enter your Name =");
                        String acc_name_my = scanner.next();
                        List<User> checkList_my = arrayList.stream().filter(user -> Objects.equals(user.getName(), acc_name_my) && user.getAccount_number() == acc_num_my).collect(Collectors.toList());
                        if (!checkList_my.isEmpty()) {
                            System.out.print("Enter the account number of that person=");
                            int acc_num = scanner.nextInt();
                            System.out.print("Enter name of that person=");
                            String acc_name = scanner.next();
                            List<User> checkList = arrayList.stream().filter(user -> Objects.equals(user.getName(), acc_name) && user.getAccount_number() == acc_num).collect(Collectors.toList());
                            if (!checkList.isEmpty()) {
                                System.out.print("Enter the ammount you want to send=");
                                int bal = scanner.nextInt();
                                if (bal >= 0 && bal <= checkList_my.get(0).getBalance()) {
                                    checkList_my.get(0).setBalance(checkList_my.get(0).getBalance() - bal);
                                    checkList.get(0).setBalance(checkList.get(0).getBalance() + bal);

                                    transactionsList.add(new Transaction(acc_num_my,"Debit",acc_num,acc_name,bal));
                                    transactionsList.add(new Transaction(acc_num,"Credit",acc_num_my,acc_name_my,bal));

                                    System.out.println("Money sent successfully.");
                                } else {
                                    throw new UnauthorizedActionException("Ammount cannot be Negative or zero");
                                }
                            } else {
                                throw new AccountNotFoundException("Account not found");
                            }
                        } else {
                            throw new AccountNotFoundException("Account not found");
                        }
                    }catch (Exception e){
                        e.getMessage(); }
                    break;

                case 5:
                    System.out.println("Account Details:");
                    for(int i = 0; i < arrayList.size(); i++) {
                        System.out.println(arrayList.get(i).toString());
                    }
                case 6:
                    try {
                        System.out.println("Enter your account number=");
                        int account_number = scanner.nextInt();
                        System.out.println("Enter your name=");
                        String account_name = scanner.next();
                        List<User> checkList = arrayList.stream().filter(user -> Objects.equals(user.getName(), account_name) && user.getAccount_number() == account_number).collect(Collectors.toList());
                        if (!checkList.isEmpty()) {
                            System.out.println("These are the details:");
                            System.out.println("Account Name: " + checkList.get(0).getName());
                            System.out.println("Account Number: " + checkList.get(0).getAccount_number());
                            System.out.println("Account Balance: " + checkList.get(0).getBalance());
                            System.out.println("Account Type: " + checkList.get(0).getAccount_type());
                        } else {
                            throw new AccountNotFoundException("No such account exist. Invalid entry.");
                        }
                    }catch (Exception e)
                    {
                        e.getMessage();
                    }
                    break;
                case 7:
                    try {
                        System.out.print("Enter account number =");
                        int acc_num = scanner.nextInt();
                        System.out.print("Enter Name =");
                        String acc_name = scanner.next();

                        List<User> checklist = arrayList.stream().filter(user -> user.getAccount_number() == acc_num && Objects.equals(user.getName(), acc_name)).collect(Collectors.toList());
                        if (!checklist.isEmpty()) {
                            System.out.println("Transaction history:");
                            List<Transaction> transactionDisplayList=transactionsList.stream().filter(transaction -> transaction.getOwn_account_number()==acc_num).collect(Collectors.toList());
                            if(!transactionDisplayList.isEmpty()) {
                                for (Transaction transaction : transactionDisplayList) {
                                    System.out.print("  From Account Number: " + transaction.getOwn_account_number());
                                    System.out.print("  To Account Number: " + transaction.getAction_account_number());
                                    System.out.print("  To Account Name: " + transaction.getAction_account_name());
                                    System.out.print("  Action: " + transaction.getAction());
                                    System.out.println("  Ammount transfer: " + transaction.getAction_money());
                                }
                            }
                            else {
                                throw new NoTransactionHistory("No transactions happened in this account.");
                            }
                        } else {
                            throw new AccountNotFoundException("Account not fount");
                        }
                    }catch (Exception e)
                    {
                        e.getMessage();
                    }
                    break;
                default:
                    break;


            }
        }
    }
}