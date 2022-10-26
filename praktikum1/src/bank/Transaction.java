package bank;

public class Transaction {
    private String date;

    private String description;

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


    public Transaction(){
    }
    public Transaction(String date, String description){
        this.date = date;
        this.description=description;
    }
}
