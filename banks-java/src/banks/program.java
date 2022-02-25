package banks;

import java.util.Scanner;
import banks.builder.clientBuilder;
import banks.exception.banksException;
import static java.lang.Double.parseDouble;

public class program {
    private static centralBank _centralBank;
    private static bank _bank;
    private static Scanner in;

    public program(centralBank centralBank, bank bank) {
        in = new Scanner(System.in);
        _centralBank = centralBank;
        _bank = bank;
    }

    public static void createClientConsole() {
        var clientBuilder = new clientBuilder();
        System.out.println("enter customer name");
        String firstName = in.nextLine();
        System.out.println("enter the client last name");
        String lastName = in.nextLine();
        System.out.println("enter your passport number");
        String passport = in.nextLine();
        System.out.println("enter address");
        String address = in.nextLine();
        System.out.println("enter phone number");
        String numberPhone = in.nextLine();
        clientBuilder
                .setName(lastName)
                .setFirstName(firstName)
                .setPassport(passport)
                .setAddress(address)
                .setNumberPhone(numberPhone);
        _bank.createClient(clientBuilder.build());
    }

    public static void createCreditScoreConsole() throws banksException {
        System.out.println("enter your account id");
        String account = in.nextLine();
        System.out.println("how much money do you want to throw in?");
        double money = parseDouble(in.nextLine());
        _bank.createCreditScore(account, money);
    }

    public static void putMoneyConsole() throws banksException {
        System.out.println("enter account number");
        String id = in.nextLine();
        System.out.println("how much money do you want to put in?");
        double money = parseDouble(in.nextLine());
        System.out.println("how much money do you want to withdraw?");
        String idBankAccount = in.nextLine();
        _bank.putMoney(id, money, idBankAccount);
    }

    public static void raiseMoneyConsole() throws banksException {
        System.out.println("enter account number");
        String id = in.nextLine();
        System.out.println("how much money do you want to withdraw?");
        double money = parseDouble(in.nextLine());
        System.out.println("how much money do you want to withdraw?");
        String idBankAccount = in.nextLine();
        _bank.raiseMoney(id, money);
    }

    public static void transactionConsole() {
        System.out.println("enter account number");
        String account = in.nextLine();
        System.out.println("Enter your account number");
        String id = in.nextLine();
        System.out.println("enter the account number to which you want to transfer money");
        String id1 = in.nextLine();
        System.out.println("enter the amount");
        double sum = parseDouble(in.nextLine());
        _bank.transaction(account, id, sum);
    }

    public static void createDebitScoreConsole() throws banksException {
        System.out.println("enter your account id");
        String account = in.nextLine();
        System.out.println("how much money do you want to throw in?");
        double money = parseDouble(in.nextLine());
        _bank.createDebitScore(account, money);
    }

    public static void cancelTransactionConsole() throws banksException {
        System.out.println("enter the id of the transaction");
        String id = in.nextLine();
        _bank.cancelTransaction(id);
    }

    public static void createDepositScoreConsole() throws banksException {
        System.out.println("enter your account id");
        String account = in.nextLine();
        System.out.println("how much money do you want to throw in?");
        double money = parseDouble(in.nextLine());
        _bank.createDepositScore(account, money);
    }

    public static void addNumberPhoneConsole() throws banksException {
        System.out.println("enter passport details");
        String passport = in.nextLine();
        System.out.println("enter phone number");
        String numberPhone = in.nextLine();
        _bank.addNumberPhone(passport, numberPhone);
    }

    public static void addAddressConsole() throws banksException {
        System.out.println("enter passport details");
        String passport = in.nextLine();
        System.out.println("enter address");
        String address = in.nextLine();
        _bank.addAddress(passport, address);
    }

    public void createCentralBank(String bankName, double percentDebitScore, double percentCreditScore, double percentDepositScore) {
        _centralBank = centralBank.GetInstance(bankName, percentDebitScore, percentCreditScore, percentDepositScore);
    }

    private static void main() throws banksException {
        while (true) {
            System.out.println("1 - register client");
            System.out.println("2 - create a credit account");
            System.out.println("3 - create a debit account");
            System.out.println("4 - create a deposit account");
            System.out.println("5 - withdraw money from the account");
            System.out.println("6 - put money into the account");
            System.out.println("7 - Transaction money to another account");
            System.out.println("8 - cancel Transaction");
            System.out.println("9 - add address");
            System.out.println("10 - Add number phone");
            System.out.println("11 - exit");
            String str = in.nextLine();
            int number = Integer.parseInt(str);
            switch (number) {
                case 1:
                    createClientConsole();
                    continue;
                case 2:
                    createCreditScoreConsole();
                    continue;
                case 3:
                    createDebitScoreConsole();
                    continue;
                case 4:
                    createDepositScoreConsole();
                    continue;
                case 5:
                    raiseMoneyConsole();
                    continue;
                case 6:
                    putMoneyConsole();
                    continue;
                case 7:
                    transactionConsole();
                    continue;
                case 8:
                    cancelTransactionConsole();
                    continue;
                case 9:
                    addAddressConsole();
                    continue;
                case 10:
                    addNumberPhoneConsole();
                    continue;
                case 11:
                    return;
                default:
                    System.out.println("wrong number entered");
                    continue;
            }
        }
    }
}
