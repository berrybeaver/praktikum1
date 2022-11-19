package bank;


import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.*;

public class PrivateBank implements Bank{
    private String name;
    private double incomingInterest;
    private double outgoingInterest;
    private Map<String, List<Transaction>> accountsToTransactions =  new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }


    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }
    public double getIncomingInterest() {
        return incomingInterest;
    }


    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }
    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    /**
     * Constructor mit 3 Attribute
     */
    public PrivateBank(String newName, double newIncomingInterest, double newOutgoingInterest) {
        this.name = newName;
        this.incomingInterest = newIncomingInterest;
        this.outgoingInterest = newOutgoingInterest;

    }

    /**
     * Copy Constructor
     */
    public PrivateBank(PrivateBank newPrivateBank) {
        this(newPrivateBank.name, newPrivateBank.incomingInterest, newPrivateBank.outgoingInterest);
        this.accountsToTransactions = newPrivateBank.accountsToTransactions;
    }

    /**
     * @return contents of all attributes
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Set<String> setKey = accountsToTransactions.keySet();
        for (String key : setKey) {
            str.append(key).append(" => \n");
            List<Transaction> transactionsList = accountsToTransactions.get(key);
            for (Transaction transaction : transactionsList)
                str.append("\t\t").append(transaction);
        }
        return "Name: " + name + "\nIncoming Interest: " + incomingInterest + "\noutgoing Interest: " + outgoingInterest + "\n" + str;
    }
    public boolean equals(Object obj) {
        if (obj instanceof PrivateBank privateBank)
            return (name.equals(privateBank.name) && incomingInterest == privateBank.incomingInterest && outgoingInterest == privateBank.outgoingInterest && accountsToTransactions.equals(privateBank.accountsToTransactions));
        return false;
    }

    /**
     * Adds an account to the bank. If the account ALREADY EXISTS, an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account ALREADY EXISTS
     */
    @Override
    public void createAccount(String account) throws AccountAlreadyExistsException {
        System.out.println("Creating new account <" + account + "> to bank <" + name + ">");
        if (accountsToTransactions.containsKey(account))
            throw new AccountAlreadyExistsException("ACCOUNT <" + account +"> ALREADY EXISTS!\n");
        else {
            accountsToTransactions.put(account, List.of());
            System.out.println("=> SUCCESS!\n");
        }
    }
    /**
     * Adds an account (with all specified transactions) to the bank. If the account ALREADY EXISTS,
     * an exception is thrown.
     *
     * @param account      the account to be added
     * @param transactions
     * @throws AccountAlreadyExistsException if the account ALREADY EXISTS
     */
    @Override
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException {
        System.out.print("Creating new account <" + account + "> to bank <" + name + "> with transactions list: \n\t\t" + transactions.toString().replaceAll("[]]|[\\[]", "").replace("\n, ", "\n\t\t"));
        if ( (accountsToTransactions.containsKey(account)) || (accountsToTransactions.containsKey(account) && accountsToTransactions.containsValue(transactions)) )
            throw new AccountAlreadyExistsException("ACCOUNT <" + account + "> ALREADY EXISTS!\n");
        else {
            for (Transaction valueOfTransactions : transactions)
                if (valueOfTransactions instanceof Payment payment) {
                    payment.setIncomingInterest(PrivateBank.this.incomingInterest);
                    payment.setOutcomingInterest(PrivateBank.this.outgoingInterest);
                }
            accountsToTransactions.put(account, transactions);
            System.out.println("=> SUCCESS!\n");

        }
    }
    /**
     * Adds a transaction to an account. If the specified account does not exist, an exception is
     * thrown. If the transaction ALREADY EXISTS, an exception is thrown.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which is added to the account
     * @throws TransactionAlreadyExistException if the transaction ALREADY EXISTS
     */
    @Override
    public void addTransaction(String account, Transaction transaction)
            throws TransactionAlreadyExistException, AccountDoesNotExistException{
        System.out.println("Adding new transaction <" + transaction.toString().replace("\n", "") + "> to account <" + account + "> in bank <" + name + ">");
        if(!accountsToTransactions.containsKey(account)){
            throw new AccountDoesNotExistException("ACCOUNT <" + account + "> DOES NOT EXISTS!\n");
        }
        else {
            if(accountsToTransactions.get(account).contains(transaction)){
                throw new TransactionAlreadyExistException("THIS TRANSACTION ALREADY EXIST!\n");
            }
            else{
                if(transaction instanceof Payment payment){
                    payment.setIncomingInterest(PrivateBank.this.incomingInterest);
                    payment.setOutcomingInterest(PrivateBank.this.outgoingInterest);
                }
                List<Transaction> transactionList = new ArrayList<>(accountsToTransactions.get(account));
                transactionList.add(transaction);
                accountsToTransactions.put(account, transactionList);
                System.out.println("=> SUCCESS!\n");
            }
        }
    }
    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is added to the account
     * @throws TransactionDoesNotExistException if the transaction cannot be found
     */
    @Override
    public void removeTransaction(String account, Transaction transaction)
            throws AccountDoesNotExistException, TransactionDoesNotExistException{
        System.out.println("Removing transaction <" + transaction.toString().replace("\n", "") + "> from account <" + account + "> in bank <" + name + ">");
        if (transaction instanceof Payment payment) {
            payment.setIncomingInterest(PrivateBank.this.incomingInterest);
            payment.setOutcomingInterest(PrivateBank.this.outgoingInterest);
        }
        if(!accountsToTransactions.get(account).contains(transaction)){
           throw new TransactionDoesNotExistException("THIS TRANSACTION DOES NOT EXISTS!\n");
        }
        else{
            List<Transaction> transactionList = new ArrayList<>(accountsToTransactions.get(account));
            transactionList.remove(transaction);
            accountsToTransactions.put(account, transactionList);
            System.out.println("=> SUCCESS!\n");
        }
    }
    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction which is added to the account
     */
    @Override
    public boolean containsTransaction(String account, Transaction transaction){
        if (transaction instanceof Payment payment){
            payment.setIncomingInterest(PrivateBank.this.incomingInterest);
            payment.setOutcomingInterest(PrivateBank.this.outgoingInterest);
        }
        System.out.println("Checking account <" + account + "> contains the transaction <" + transaction.toString().replace("\n", "") + "> : " + accountsToTransactions.get(account).contains(transaction) + "\n");
        return accountsToTransactions.get(account).contains(transaction);
    }
    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    @Override
    public double getAccountBalance(String account){
        double balance =0;
        for (Transaction transaction : accountsToTransactions.get(account)){
            balance = balance + transaction.calculate();
        }
        System.out.println("Balance of account <" + account + "> in bank <" + name + "> : " + (double) Math.round(balance * 100)/100 + "\n");
        return 1;
    }

}
