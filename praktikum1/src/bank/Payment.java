package bank;

public class Payment {
    protected String date;
    protected double amount;
    protected String description;
    private double incomingInterest=0;
    private double outcomingInterest=0;

    /* Setter und Getter*/
    public void setDate(String date){
        this.date = date;
    }
    public String get_date(){
       return date;
    }

    public void setAmount(double amount){
        this.amount=amount;
    }
    public double getAmount(){
        return amount;
    }

    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
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
        setDate("01.01.0000");
        setAmount(0);
        setDescription("");
    }
    public Payment(String date, double amount, String description){
        setDate(date);
        setAmount(amount);
        setDescription(description);

    }
    public Payment(String date, double amount, String description, double incomingInterest, double outcomingInterest){
        this(date, amount, description);
        setIncomingInterest(incomingInterest);
        setOutcomingInterest(outcomingInterest);
    }
    /*Copy Konstruktor*/
    public Payment(Payment Payment){
        this(Payment.date, Payment.amount, Payment.description, Payment.incomingInterest, Payment.outcomingInterest );
    }

    public void printObject(){
        System.out.println("-----Payment-----");
        System.out.println("date: " + date);
        /*sodass wenn amount - ist, wird ein - Zeichnung vor dem Amount geschriebt*/
        if (amount < 0) {
            System.out.println(amount + "$");
        } else {
            System.out.println("+" + amount + "$");
        }
        System.out.println(description);
        System.out.println("Incoming Interest: " + incomingInterest);
        System.out.println("Outcoming Interest: " + outcomingInterest);
        System.out.println("--------------------------------------------");
    }
}
