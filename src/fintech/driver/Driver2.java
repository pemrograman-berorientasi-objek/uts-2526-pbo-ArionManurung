package academic.driver;

/**
 * @author 12S24014 Arion Manurung
 */

import academic.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Account> accountList = new ArrayList<>();
        ArrayList<Transaction> transactionList = new ArrayList<>();

        int trxIdCounter = 0;

        while (true) {
            String line = input.nextLine();

            if (line.equals("---")) {
                break;
            }

            String[] data = line.split("#");
            String cmd = data[0];

            if (cmd.equals("create-account")) {
                String accName = data[1];
                String accUsername = data[2];

                Account account = new Account(accName, accUsername);
                accountList.add(account);
            }

            else if (cmd.equals("deposit")) {
                String user = data[1];
                double nominal = Double.parseDouble(data[2]);
                String time = data[3];
                String note = data[4];

                for (Account account : accountList) {
                    if (account.getUsername().equals(user)) {
                        account.deposit(nominal);

                        trxIdCounter++;
                        Transaction depositTrx = new DepositTransaction(
                            trxIdCounter, user, nominal, time, note
                        );
                        transactionList.add(depositTrx);
                        break;
                    }
                }
            }

            else if (cmd.equals("withdraw")) {
                String user = data[1];
                double nominal = Double.parseDouble(data[2]);
                String time = data[3];
                String note = data[4];

                for (Account account : accountList) {
                    if (account.getUsername().equals(user)) {

                        boolean isSuccess = account.withdraw(nominal);

                        if (isSuccess) {
                            trxIdCounter++;
                            Transaction withdrawTrx = new WithdrawTransaction(
                                trxIdCounter, user, nominal, time, note
                            );
                            transactionList.add(withdrawTrx);
                        }

                        break;
                    }
                }
            }
        }

        for (Account account : accountList) {
            System.out.println(
                account.getUsername() + "|" +
                account.getName() + "|" +
                account.getBalance()
            );
        }

        input.close();
    }
}