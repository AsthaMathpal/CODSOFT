import java.util.Scanner;
import java.net.URL;
import java.net.HttpURLConnection;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner Myobj = new Scanner(System.in);

        System.out.println("=======================================");
        System.out.println("       Welcome to Currency Converter       ");
        System.out.println("=======================================");

        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Convert Currency");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            while (!Myobj.hasNextInt()) {
                System.out.print("Invalid input! Enter a number: ");
                Myobj.next(); // discard invalid input
            }
            choice = Myobj.nextInt();

            switch (choice) {
                case 1:
                    // --- Base Currency Input ---
                    System.out.print("Enter base currency (USD, INR, EUR etc.): ");
                    String base = Myobj.next().toUpperCase();

                    // --- Target Currency Input ---
                    System.out.print("Enter target currency (USD, INR, EUR etc.): ");
                    String target = Myobj.next().toUpperCase();

                    // --- Amount Input ---
                    double amount = 0;
                    while (true) {
                        System.out.print("Enter amount to convert: ");
                        if (Myobj.hasNextDouble()) {
                            amount = Myobj.nextDouble();
                            if (amount > 0) break;
                            else System.out.println("Amount must be greater than 0!");
                        } else {
                            System.out.println("Invalid input! Enter a number.");
                            Myobj.next(); // discard invalid input
                        }
                    }

                    // --- Conversion Logic ---
                    try {
                        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + base;
                        URL url = new URL(apiUrl);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");

                        java.util.Scanner urlScanner = new java.util.Scanner(url.openStream());
                        StringBuilder result = new StringBuilder();
                        while (urlScanner.hasNext()) {
                            result.append(urlScanner.nextLine());
                        }
                        urlScanner.close();

                        String json = result.toString();

                        // --- Extract target rate manually ---
                        String search = "\"" + target + "\":";
                        int index = json.indexOf(search);
                        if (index == -1) {
                            System.out.println("Currency not found! Check the codes.");
                            break;
                        }

                        int start = index + search.length();
                        int end = json.indexOf(",", start);
                        if (end == -1) end = json.indexOf("}", start);

                        double rate = Double.parseDouble(json.substring(start, end).trim());
                        double convertedAmount = amount * rate;

                        // --- Display Result ---
                        System.out.println("---------------------------------------");
                        System.out.printf("Conversion Result: %.2f %s = %.2f %s\n", amount, base, convertedAmount, target);
                        System.out.println("---------------------------------------");

                    } catch (Exception e) {
                        System.out.println("Error fetching data! Check internet or currency code.");
                    }
                    break;

                case 2:
                    continueProgram = false;
                    System.out.println("Thank you for using Currency Converter!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        Myobj.close();
    }
}
