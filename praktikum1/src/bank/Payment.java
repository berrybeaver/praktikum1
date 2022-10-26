package bank;

public class Payment extends Transaction implements CalculateBill {

    protected double amount;
    private double incomingInterest=0;
    private double outcomingInterest=0;

    /* Setter und Getter*/

    public void setAmount(double amount){
        this.amount=amount;
    }
    public double getAmount(){
        return amount;
    }



    public void setIncomingInterest(double incomingInterest){
        if(0 <= incomingInterest & incomingInterest<=1) {
            this.incomingInterest = incomingInterest;
        }
        else{
            System.out.println("Incoming Interest may not be lower than 0 or higher than 1," +
                    " please change the Amount");
        }
    }
    public double getIncomingInterest(){
        return incomingInterest;
    }

    public void setOutcomingInterest(double outcomingInteret){
        if(0 <= outcomingInterest & outcomingInterest <= 1) {
            this.outcomingInterest = outcomingInteret;
        }
        else{
            System.out.println("Outcoming Interest may not be lower than 0 or higher than 1," +
                    " please change the Amount");
        }
    }
    public double getOutcomingInterest(){
        return outcomingInterest;
    }

    /*Methoden und Konstruktoren*/
    public Payment(){
        super();
    }
    public Payment(String date, double amount, String description){
        super(date, description);
        setAmount(amount);
    }
    public Payment(String date, double amount, String description, double incomingInterest, double outcomingInterest){
        this(date, amount, description);
        setIncomingInterest(incomingInterest);
        setOutcomingInterest(outcomingInterest);
    }
    /*Copy Konstruktor*/
    public Payment(Payment Payment){
        this(Payment.get_date(), Payment.amount, Payment.getDescription(), Payment.incomingInterest, Payment.outcomingInterest );
    }

    public void printObject(){
        System.out.println("-----Payment-----");
        System.out.println("date: " + get_date());
        /*sodass wenn amount - ist, wird ein - Zeichnung vor dem Amount geschriebt*/
        if (amount < 0) {
            System.out.println(amount + "$");
        } else {
            System.out.println("+" + amount + "$");
        }
        System.out.println(getDescription());
        System.out.println("Incoming Interest: " + incomingInterest);
        System.out.println("Outcoming Interest: " + outcomingInterest);
        System.out.println("--------------------------------------------");
    }

    @Override
    public double calculate() {
        if(amount > 0){
            amount = amount*(1-incomingInterest);
        }
        else {
            amount = amount*(1+outcomingInterest);
        }
        return amount;
    }
}
