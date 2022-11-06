import java.util.Scanner;
import bank.Payment;
import bank.Transfer;

public class Main {
    public static void main(String[] args) {
        Payment ersteEinzahlung = new Payment("17.09.2009", 569.4912, "1. Einzahlung");
        Payment zweiteEinzahlung = new Payment("27.10.2017" ,1090, "2. Einzahlung", 0.5, 1);
        Payment auszahlung = new Payment("30.03.1980", -10983, "Auszahlung", 0.6, 0.789);
        Payment payment = new Payment(auszahlung);

        System.out.print("1.Einzahlung: \n" + ersteEinzahlung + "2. Einzahlung: \n" + zweiteEinzahlung +
                "Auszahlung: \n" + auszahlung + "Copy Konstruktor: \n" + payment);
        System.out.println( "1==2: "+ersteEinzahlung.equals(zweiteEinzahlung) +"\n2==1: "+ zweiteEinzahlung.equals(ersteEinzahlung) +"\n");

        Transfer firstTransfer = new Transfer("17.09.2009", 569.4912 , "1. Einzahlung");
        Transfer secondTransfer = new Transfer("23.08.2222", 200.5, "2. Transfer", "Tom", "Jonas");
        Transfer thirdTransfer = new Transfer("08.05.2000", 1248, "3. Transfer", "Violett", "Brause");
        Transfer transfer = new Transfer(secondTransfer);

        System.out.print("1. Transfer: \n" + firstTransfer + "2. Transfer: \n" + secondTransfer +
                "3. Transfer: \n" + thirdTransfer + "Copy Konstruktor: \n" + transfer);
        System.out.println("\npayment == firstTransfer: " + payment.equals(firstTransfer) + "\nfirstTransfer == secondTransfer: "
                + firstTransfer.equals(secondTransfer) + "\nsecondTransfer == thirdTransfer expected: " + secondTransfer.equals(thirdTransfer)
                + "\nsecondTransfer == transfer: " + secondTransfer.equals(transfer));
    }
}