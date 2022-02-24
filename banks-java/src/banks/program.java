package banks;

import java.util.Scanner;
import banks.builder.clientBuilder;
import banks.Exception.banksException;
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

    public static void CreateClientConsole() {
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
                .SetName(lastName)
                .SetFirstNAme(firstName)
                .SetPassport(passport)
                .SetAddress(address)
                .SetNumberPhone(numberPhone);
        _bank.CreateClient(clientBuilder.Build());
    }

    public static void CreateCreditScoreConsole() throws banksException {
        System.out.println("enter your account id");
        String account = in.nextLine();
        System.out.println("how much money do you want to throw in?");
        double money = parseDouble(in.nextLine());
        _bank.CreateCreditScore(account, money);
    }

    public static void PutMoneyConsole() throws banksException {
        System.out.println("enter account number");
        String id = in.nextLine();
        System.out.println("how much money do you want to put in?");
        double money = parseDouble(in.nextLine());
        System.out.println("how much money do you want to withdraw?");
        String idBankAccount = in.nextLine();
        _bank.PutMoney(id, money, idBankAccount);
    }

    public static void RaiseMoneyConsole() throws banksException {
        System.out.println("enter account number");
        String id = in.nextLine();
        System.out.println("how much money do you want to withdraw?");
        double money = parseDouble(in.nextLine());
        System.out.println("how much money do you want to withdraw?");
        String idBankAccount = in.nextLine();
        _bank.RaiseMoney(id, money);
    }

    public static void TransactionConsole() {
        System.out.println("enter account number");
        String account = in.nextLine();
        System.out.println("Enter your account number");
        String id = in.nextLine();
        System.out.println("enter the account number to which you want to transfer money");
        String id1 = in.nextLine();
        System.out.println("enter the amount");
        double sum = parseDouble(in.nextLine());
        _bank.Transaction(account, id, sum);
    }

    public static void CreateDebitScoreConsole() throws banksException {
        System.out.println("enter your account id");
        String account = in.nextLine();
        System.out.println("how much money do you want to throw in?");
        double money = parseDouble(in.nextLine());
        _bank.CreateDebitScore(account, money);
    }

    public static void CancelTransactionConsole() throws banksException {
        System.out.println("enter the id of the transaction");
        String id = in.nextLine();
        _bank.CancelTransaction(id);
    }

    public static void CreateDepositScoreConsole() throws banksException {
        System.out.println("enter your account id");
        String account = in.nextLine();
        System.out.println("how much money do you want to throw in?");
        double money = parseDouble(in.nextLine());
        _bank.CreateDepositScore(account, money);
    }

    public static void AddNumberPhoneConsole() throws banksException {
        System.out.println("enter passport details");
        String passport = in.nextLine();
        System.out.println("enter phone number");
        String numberPhone = in.nextLine();
        _bank.AddNumberPhone(passport, numberPhone);
    }

    public static void AddAddressConsole() throws banksException {
        System.out.println("enter passport details");
        String passport = in.nextLine();
        System.out.println("enter address");
        String address = in.nextLine();
        _bank.AddAddress(passport, address);
    }

    public void CreateCentralBank(String bankName, double percentDebitScore, double percentCreditScore, double percentDepositScore) {
        _centralBank = centralBank.GetInstance(bankName, percentDebitScore, percentCreditScore, percentDepositScore);
    }

    private static void Main() throws banksException {
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
                    CreateClientConsole();
                    continue;
                case 2:
                    CreateCreditScoreConsole();
                    continue;
                case 3:
                    CreateDebitScoreConsole();
                    continue;
                case 4:
                    CreateDepositScoreConsole();
                    continue;
                case 5:
                    RaiseMoneyConsole();
                    continue;
                case 6:
                    PutMoneyConsole();
                    continue;
                case 7:
                    TransactionConsole();
                    continue;
                case 8:
                    CancelTransactionConsole();
                    continue;
                case 9:
                    AddAddressConsole();
                    continue;
                case 10:
                    AddNumberPhoneConsole();
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
