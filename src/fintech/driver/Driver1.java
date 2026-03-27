package academic.driver;

/**
 * @author 12S24014 Arion Manurung
 */

import academic.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver1 {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<Account> listAccount = new ArrayList<>();

        while (true) {
            String data = inputScanner.nextLine();

            // kondisi berhenti
            if (data.equals("---")) {
                break;
            }

            String[] splitData = data.split("#");

            if (splitData[0].equals("create-account")) {
                String accName = splitData[1];
                String accUsername = splitData[2];

                Account newAccount = new Account(accName, accUsername);
                listAccount.add(newAccount);
            }
        }

        for (Account account : listAccount) {
            System.out.println(
                account.getUsername() + "|" +
                account.getName() + "|" +
                account.getBalance()
            );
        }

        inputScanner.close();
    }
}