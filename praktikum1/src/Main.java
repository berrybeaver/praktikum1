import java.util.Scanner;
import bank.Payment;
import bank.Transfer;

public class Main {
    public static void main(String[] args) {
        /*default konstruktor*/
        /*
        Payment myPayment = new Payment();
        myPayment.printObject();
        Transfer myTransfer = new Transfer();
        myTransfer.printObject();
        */


        String date= "06.10.2002";
        String desc= "Einzahlung auf ATM";
        double amount = 200;
        String desc1= "miete";
        String sender= "Mark";
        String recipient= "Frank";

        Payment myPayment1 = new Payment(date, amount, desc );
        Transfer myTransfer1 = new Transfer(date, amount, desc, sender, recipient);
        myPayment1.setIncomingInterest(1.1);
        myTransfer1.setAmount(-100);
        myPayment1.printObject();
        myTransfer1.printObject();




        /*copy konstruktor*/
        /*
        Payment copyPayment = new Payment(myPayment1);
        copyPayment.printObject();
        Transfer copyTransfer = new Transfer(myTransfer1);
        copyTransfer.printObject();
        */
/*
        Scanner myObj = new Scanner(System.in);
        System.out.print("Transfer(1)/Payment(2): ");
        int Funktion = myObj.nextInt();
        myObj.nextLine();
        if(Funktion == 1){
            Transfer elTransfer = new Transfer();

            System.out.print("select Date(DD.MM.YYYY): ");
            String Date = myObj.nextLine();
            elTransfer.setDate(Date);

            System.out.print("set Amount: ");
            double Amount = myObj.nextDouble();
            elTransfer.setAmount(Amount);
            myObj.nextLine();

            System.out.print("description: ");
            String Desc = myObj.nextLine();
            elTransfer.setDescription(Desc);

            System.out.print("sender's name: ");
            String Sender = myObj.nextLine();
            elTransfer.setSender(Sender);

            System.out.print("recipient's name: ");
            String Recipient = myObj.nextLine();
            elTransfer.setRecipient(Recipient);

            elTransfer.printObject();
        } else if (Funktion == 2) {
            Payment elPayment = new Payment();

            System.out.print("select Date(DD.MM.YYYY): ");
            String Date = myObj.nextLine();
            elPayment.setDate(Date);

            System.out.print("set Amount: ");
            double Amount = myObj.nextDouble();
            elPayment.setAmount(Amount);
            myObj.nextLine();

            System.out.print("description: ");
            String Desc = myObj.nextLine();
            elPayment.setDescription(Desc);

            System.out.print("set incoming Interest: ");
            double incInt = myObj.nextDouble();
            elPayment.setIncomingInterest(incInt);
            myObj.nextLine();

            System.out.print("set incoming Interest: ");
            double outInt = myObj.nextDouble();
            elPayment.setOutcomingInterest(outInt);
            myObj.nextLine();

            elPayment.printObject();
        }
        */
    }
}