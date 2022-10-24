package bank;

public class Payment {
    private String date;
    private double amount;
    private String description;
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
        this.incomingInterest=incomingInterest;
    }
    public double getIncomingInterest(){
        return incomingInterest;
    }

    public void setOutcomingInterest(double outcomingInteret){
        this.outcomingInterest=outcomingInteret;
    }
    public double getOutcomingInterest(){
        return outcomingInterest;
    }

    /*Methoden und Konstruktoren*/
    public Payment(){
        date="01.01.0000";
        amount=0;
        description="";

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
        if((0 <= incomingInterest & incomingInterest<=1) & (0 <= outcomingInterest & outcomingInterest <= 1) ) {

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
        }
        else{
            System.out.println("Incoming/Outcoming Interest may not be lower than 0 or higher than 1," +
                    " please change the Amount");
        }
        System.out.println("--------------------------------------------");
    }
}
