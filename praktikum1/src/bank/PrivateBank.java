package bank;

import bank.exceptions.*;
import java.util.*;

public abstract class PrivateBank implements Bank{
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
     * Adds an account (with specified transactions) to the bank.
     * Important: duplicate transactions must not be added to the account!
     *
     * @param account      the account to be added
     * @param transactions a list of already existing transactions which should be added to the newly created account
     * @throws AccountAlreadyExistsException    if the account already exists
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws TransactionAttributeException    if the validation check for certain attributes fail
     */
    public void createAccount(String account, List<Transaction> transactions)
            throws AccountAlreadyExistsException, TransactionAlreadyExistException, TransactionAttributeException {
        if((accountsToTransactions.containsKey(account)) || (accountsToTransactions.containsKey(account) && accountsToTransactions.containsValue(transactions)))
        {
            throw new AccountAlreadyExistsException("ACCOUNT <" + account +"> ALREADY EXISTS!\n");
        }
        else {
            accountsToTransactions.put(account, transactions);
            System.out.println("=> SUCCESS!\n");
        }
    }

    /**
     * Adds a transaction to an already existing account.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which should be added to the specified account
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws AccountDoesNotExistException     if the specified account does not exist
     * @throws TransactionAttributeException    if the validation check for certain attributes fail
     */
    public void addTransaction(String account, Transaction transaction)
            throws TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException {
        if (!accountsToTransactions.containsKey(account)) {
            throw new AccountDoesNotExistException("ACCOUNT <" + account + "> DOES NOT EXISTS!\n");
        }
        else if(accountsToTransactions.get(account).contains(transaction)){
            throw new TransactionAlreadyExistException("THIS TRANSACTION ALREADY EXISTS!\n");
        }
        else {
            List<Transaction> transactionsList = new ArrayList<>(accountsToTransactions.get(account));
            transactionsList.add(transaction);
            accountsToTransactions.put(account, transactionsList);
            System.out.println("=> SUCCESS!\n");
        }
    }

}
