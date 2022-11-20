import java.util.Scanner;
import bank.*;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import bank.exceptions.TransactionAttributeException;

import java.util.List;


public class Main {
    public static void main(String[] args) throws AccountAlreadyExistsException, TransactionAlreadyExistException, AccountDoesNotExistException, TransactionDoesNotExistException, TransactionAttributeException {
        PrivateBank privateBank = new PrivateBank("Bank1", 0.25, 0.3);

        privateBank.createAccount("Molziles", List.of(
                new Payment("12.03.2008", 321, "Payment"),
                new Payment("23.09.1897", -2500, "Payment", 0.8, 0.5),
                new Transfer("03.03.2000", 80, "Transfer", "Molziles", "Elixir")
        ));
        privateBank.createAccount("Elixir", List.of(
                new Payment("22.06.1998", 435, "Payment", 0., 0.),
                new Transfer("03.03.2000", 80, "Transfer", "Molziles", "Elixir"),
                new Payment("05.08.2022", -118, "Payment", 0., 0.),
                new Transfer("15.04.1990", 185, "Transfer", "Elixir", "Vaio"),
                new Transfer("30.07.2020", 1890, "Transfer", "Elixir", "Booth"),
                new Payment("19.01.2011", -789, "Payment", 0.9, 0.25)
        ));

        System.out.println(privateBank);
        //create account fail
        try {
            privateBank.createAccount("Elixir");
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }
        //create account with transaction fail
        try {
            privateBank.createAccount("Molziles", List.of(
                    new Transfer("03.03.2001", 80, "Transfer", "Molziles", "Elixir")
            ));
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }
        //create account success
        try {
            privateBank.createAccount("Anand");
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }

        System.out.println(privateBank);
        //add payment failed
        try {
            privateBank.addTransaction("Vaio", new Payment("11.11.2000", 890, "Payment"));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }
        //add Transfer failed
        try {
            privateBank.addTransaction("Molziles", new Transfer("03.03.2000", 80, "Transfer", "Molziles", "Elixir"));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }
        //add payment success
        try {
            privateBank.addTransaction("Anand", new Payment("22.03.2003", 90, "Payment", 0.9, 0.75));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }
        //add Transfer success
        try {
            privateBank.addTransaction("Anand", new Transfer("19.04.2023", 3890, "Transfer", "Molziles", "Elixir"));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }

        System.out.println(privateBank);
        //removing transaction success
        try {
            privateBank.removeTransaction("Anand", new Transfer("19.04.2023", 3890, "Transfer", "Molziles", "Elixir"));
        } catch (TransactionDoesNotExistException e) {
            System.out.println(e);
        }
        //removing transaction failed
        try {
            privateBank.removeTransaction("Elixir", new Transfer("10.04.1990", 185, "Transfer", "Elixir", "Vaio"));
        } catch (TransactionDoesNotExistException e) {
            System.out.println(e);
        }

        System.out.println(privateBank);
        //sorting in ascending order
        privateBank.getTransactionsSorted("Elixir", true);
        //sorting in descending order
        privateBank.getTransactionsSorted("Elixir", false);
        //sorting von Type positive
        privateBank.getTransactionsByType("Elixir", true);
        //sorting von Type negative
        privateBank.getTransactionsByType("Elixir", false);

        privateBank.getTransactions("Elixir");
        //get account balance
        privateBank.getAccountBalance("Elixir");

        //contain Transaction prüfen
        privateBank.containsTransaction("Molziles", new Transfer("28.02.1908", 1095, "Transfer", "Molziles", "Elixir"));
        privateBank.containsTransaction("Molziles", new Transfer("03.03.2000", 80, "Transfer", "Molziles", "Elixir"));
        privateBank.containsTransaction("Anand", new Payment("22.03.2003", 90, "Payment", 0.9, 0.75));


        PrivateBank privateBank2 = new PrivateBank("Bank2", 0.25, 0.3);
        privateBank2.createAccount("Molziles", List.of(
                new Payment("12.03.2008", 321, "Payment"),
                new Payment("23.09.1897", -2500, "Payment", 0.8, 0.5),
                new OutgoingTransfer("03.03.2000", 80, "OutgoingTransfer", "Molziles", "Elixir")
        ));
        privateBank2.createAccount("Elixir", List.of(
                new Payment("22.06.1998", 435, "Payment", 0., 0.),
                new IncomingTransfer("03.03.2000", 80, "IncomingTransfer", "Molziles", "Elixir"),
                new Payment("05.08.2022", -118, "Payment", 0., 0.),
                new OutgoingTransfer("15.04.1990", 185, "OutgoingTransfer", "Elixir", "Vaio"),
                new OutgoingTransfer("30.07.2020", 1890, "OutgoingTransfer", "Elixir", "Booth"),
                new Payment("19.01.2011", -789, "Payment", 0.9, 0.25)
        ));

        privateBank2.getTransactions("Molziles");
        privateBank2.getAccountBalance("Molziles");
        privateBank.getTransactions("Molziles");
        privateBank.getAccountBalance("Molziles");

        System.out.println("Testing equals() method:\nBank1 vs Bank2 expected <false> => " + privateBank.equals(privateBank2));

    }
}