package bank;
/*Vererbung mit extends*/
public class Transfer extends Transaction implements CalculateBill{
    protected double amount;
    private String sender;
    private String recipient;

    /* Setter und Getter*/

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
        super(date, description);
        setAmount(amount);
    }
    public Transfer(String date, double amount, String description, String sender, String recipient){
        this(date, amount, description);
        setSender(sender);
        setRecipient(recipient);
    }
    /*Copy Konstruktor*/
    public Transfer(Transfer Transfer){
        this(Transfer.get_date(), Transfer.amount, Transfer.getDescription(), Transfer.sender, Transfer.recipient );
    }
    public void printObject(){
        System.out.println("-----Transfer-----");
        System.out.println("date: " + get_date());
        System.out.println(amount + "$");
        System.out.println(getDescription());
        System.out.println("Sender: " + sender);
        System.out.println("Recipient: " + recipient);
        System.out.println("--------------------------------------------");
    }

    @Override
    public double calculate() {
        return amount;
    }
}
