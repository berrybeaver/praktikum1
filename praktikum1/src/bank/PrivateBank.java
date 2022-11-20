package bank;


import java.util.*;

public class PrivateBank implements Bank{
    private String name;
    private double incomingInterest;
    private double outgoingInterest;
    private Map<String, List<Transaction>> accountsToTransactions =  new HashMap<>();}

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

}
