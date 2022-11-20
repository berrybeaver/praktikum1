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

        privateBank.createAccount("Salve", List.of(
                new Payment("12.03.2008", 300, "Payment"),
                new Payment("23.09.1897", -2500, "Payment", 0.8, 0.5),
                new Transfer("03.03.2000", 80, "Transfer", "Salve", "Mango")
        ));
        privateBank.createAccount("Mango", List.of(
                new Payment("22.06.1998", 400, "Payment", 0., 0.),
                new Transfer("03.03.2000", 80, "Transfer", "Salve", "Mango"),
                new Payment("05.08.2022", -100, "Payment", 0., 0.),
                new Transfer("15.04.1990", 200, "Transfer", "Mango", "Vaio"),
                new Transfer("30.07.2020", 2000, "Transfer", "Mango", "Booth"),
                new Payment("19.01.2011", -800, "Payment", 0.9, 0.25)
        ));

        System.out.println(privateBank);
        //create account fail
        try {
            privateBank.createAccount("Mango");
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }
        //create account with transaction fail
        try {
            privateBank.createAccount("Salve", List.of(
                    new Transfer("03.03.2001", 80, "Transfer", "Salve", "Mango")
            ));
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }
        //create account with attribute fail
        try{
            privateBank.createAccount("fire", List.of(new Payment("03.03.2001", 80, "Payment", 1, 0.5)
            ));
        }catch (AccountAlreadyExistsException | TransactionAttributeException e){
            System.out.println(e);
        }
        //create account success
        try {
            privateBank.createAccount("fire");
        } catch (AccountAlreadyExistsException e) {
            System.out.println(e);
        }

        System.out.println(privateBank);
        //add payment failed
        try {
            privateBank.addTransaction("Vaio", new Payment("11.11.2000", 900, "Payment"));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }
        //add Transfer "transaction already exist" failed
        try {
            privateBank.addTransaction("Salve", new Transfer("03.03.2000", 100, "Transfer", "Salve", "Mango"));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }
        //add payment success
        try {
            privateBank.addTransaction("fire", new Payment("22.03.2003", 100, "Payment", 0.9, 0.75));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }
        //add Transfer success
        try {
            privateBank.addTransaction("fire", new Transfer("19.04.2023", 4000, "Transfer", "fire", "Mango"));
        } catch (AccountDoesNotExistException | TransactionAlreadyExistException e) {
            System.out.println(e);
        }

        System.out.println(privateBank);
        //removing transaction success
        try {
            privateBank.removeTransaction("fire", new Transfer("19.04.2023", 4000, "Transfer", "fire", "Mango"));
        } catch (TransactionDoesNotExistException e) {
            System.out.println(e);
        }
        //removing transaction failed
        try {
            privateBank.removeTransaction("Mango", new Transfer("10.04.1990", 200, "Transfer", "Mango", "Vaio"));
        } catch (TransactionDoesNotExistException e) {
            System.out.println(e);
        }

        System.out.println(privateBank);
        //sorting in ascending order
        privateBank.getTransactionsSorted("Mango", true);
        //sorting in descending order
        privateBank.getTransactionsSorted("Mango", false);
        //sorting von Type positive
        privateBank.getTransactionsByType("Mango", true);
        //sorting von Type negative
        privateBank.getTransactionsByType("Mango", false);

        privateBank.getTransactions("Mango");
        //get account balance
        privateBank.getAccountBalance("Mango");

        //contain Transaction prüfen
        privateBank.containsTransaction("Salve", new Transfer("28.02.1908", 1200, "Transfer", "Salve", "Mango"));
        privateBank.containsTransaction("Salve", new Transfer("03.03.2000", 100, "Transfer", "Salve", "Mango"));
        privateBank.containsTransaction("fire", new Payment("22.03.2003", 100, "Payment", 0.9, 0.75));


        PrivateBank privateBank2 = new PrivateBank("Bank2", 0.25, 0.3);
        privateBank2.createAccount("Salve", List.of(
                new Payment("12.03.2008", 320, "Payment"),
                new Payment("23.09.1897", -2500, "Payment", 0.8, 0.5),
                new OutgoingTransfer("03.03.2000", 80, "OutgoingTransfer", "Salve", "Mango")
        ));
        privateBank2.createAccount("Mango", List.of(
                new Payment("22.06.1998", 450, "Payment", 0., 0.),
                new IncomingTransfer("03.03.2000", 80, "IncomingTransfer", "Salve", "Mango"),
                new Payment("05.08.2022", -120, "Payment", 0., 0.),
                new OutgoingTransfer("15.04.1990", 200, "OutgoingTransfer", "Mango", "Vaio"),
                new OutgoingTransfer("30.07.2020", 1900, "OutgoingTransfer", "Mango", "Booth"),
                new Payment("19.01.2011", -800, "Payment", 0.9, 0.25)
        ));

        privateBank2.getTransactions("Salve");
        privateBank2.getAccountBalance("Salve");
        privateBank.getTransactions("Salve");
        privateBank.getAccountBalance("Salve");

        System.out.println("Testing equals() method:\nBank1 vs Bank2 expected <false> => " + privateBank.equals(privateBank2));

    }
}