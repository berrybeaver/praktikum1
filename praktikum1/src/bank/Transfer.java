package bank;
/*Vererbung mit extends*/
public class Transfer extends Payment{
    private String date;
    private double amount;
    private String description;
    private String sender;
    private String recipient;

    /* Setter und Getter*/
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
        super(date, amount, description);
    }
    public Transfer(String date, double amount, String description, String sender, String recipient){
        super(date, amount, description);
        setSender(sender);
        setRecipient(recipient);
    }
    /*Copy Konstruktor*/
    public Transfer(Transfer Transfer){
        this.date = Transfer.get_date();
        this.amount = Transfer.getAmount();
        this.description = Transfer.getDescription();
        this.sender = Transfer.getSender();
        this.recipient = Transfer.getRecipient();
    }
    public void printObject(){
        /*fuer Transfer, Amount kleiner als oder 0$ ist nicht erlaubt*/
        System.out.println("-----Transfer-----");
        if(amount<0){
            System.out.println("Amount given may not be 0 or lower, please change the Amount");
        }
        else {
            System.out.println("date: " + date);
            System.out.println(amount + "$");
            System.out.println(description);
            System.out.println("Sender: " + sender);
            System.out.println("Recipient: " + recipient);
        }
        System.out.println("--------------------------------------------");
    }
}
