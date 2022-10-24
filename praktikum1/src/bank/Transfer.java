package bank;
/*Vererbung mit extends*/
public class Transfer {
    private String date;
    private double amount;

    private String description;
    private String sender;
    private String recipient;

    /* Setter und Getter*/
    public void setDate(String date){
        this.date = date;
    }
    public String get_date(){
        return date;
    }


    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    /*fuer Transfer, Amount kleiner als oder 0$ ist nicht erlaubt*/

    public void setAmount(double amount){

        if(amount<=0){
            System.out.println("Amount given may not be 0 or lower, please change the Amount");
        }
        else{
            this.amount = amount;
        }
    }
    public double getAmount(){
        return amount;
    }
    public void setSender(String sender){
        this.sender = sender;
    }
    public String getSender(){
        return sender;
    }
    public void setRecipient(String recipient){
        this.recipient = recipient;
    }
    public String getRecipient(){
        return recipient;
    }

    /*konstruktoren mit Nutzung von paymentskonstruktor*/
    public Transfer(){
        super();
    }
    public Transfer(String date, double amount, String description) {
        setDate(date);
        setAmount(amount);
        setDescription(description);
    }
    public Transfer(String date, double amount, String description, String sender, String recipient){
        this(date, amount, description);
        setSender(sender);
        setRecipient(recipient);
    }
    /*Copy Konstruktor*/
    public Transfer(Transfer Transfer){
        /*
        this.date = Transfer.get_date();
        this.amount = Transfer.getAmount();
        this.description = Transfer.getDescription();
        this.sender = Transfer.getSender();
        this.recipient = Transfer.getRecipient();
         */
        this(Transfer.date, Transfer.amount, Transfer.description, Transfer.sender, Transfer.recipient );

    }
    public void printObject(){
        System.out.println("-----Transfer-----");
        System.out.println("date: " + date);
        System.out.println(amount + "$");
        System.out.println(description);
        System.out.println("Sender: " + sender);
        System.out.println("Recipient: " + recipient);
        System.out.println("--------------------------------------------");
    }
}
