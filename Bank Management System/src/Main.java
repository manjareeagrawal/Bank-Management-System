import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Exception
{
    public static void main(String[] args)
    {
        ArrayList<User> arrayList=new ArrayList<>();
        arrayList.add(new User("Manjaree",101,10000,111,true));
        arrayList.add(new User("Sakshi",102,20000,111,true));
        arrayList.add(new User("Bhavya",103,40000,111,true));
        arrayList.add(new User("Jasneet",104,30000,111,false));

        while (true) {
            System.out.println("Welcome to ICICI bank... ");
            System.out.println("Please select the integer to get the following service:");
            System.out.println("1. To create Account in our bank.");
            System.out.println("2. Add balance.");
            System.out.println("3. Debit money from my account.");
            System.out.println("4. Send money to other.");
            System.out.println("5. This is for ADMIN - See all bank account details.");

            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            switch (a) {
                case 1:
                    System.out.println("Enter your name:");
                    String name = scanner.next();
                    System.out.println("Congratulations " + name + ". We are ahead of one step to create an account.");
                    System.out.println("Please enter 3 digit pin for your bank account.");
                    int pin = scanner.nextInt();
                    User u1 = new User(name, arrayList.get(arrayList.size() - 1).getAccount_number() + 1, 0, pin, true);
                    arrayList.add(u1);
                    System.out.println("Account created successfully... ");
                    System.out.println("These are the details:");
                    System.out.println("Account Name: " + u1.getName());
                    System.out.println("Account Number: " + u1.getAccount_number());
                    System.out.println("Account Balance: " + u1.getBalance());
                    System.out.println("Account Name: " + u1.getName());
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
                        e.printStackTrace(); }
                    break;

                case 5:
                    System.out.println("Account Details:");
                    for(int i = 0; i < arrayList.size(); i++) {
                        System.out.println(arrayList.get(i).toString());
                    }
                default:
                    break;


            }
        }
    }
}