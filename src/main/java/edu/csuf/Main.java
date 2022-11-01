package edu.csuf;


import java.text.ParseException;
import java.util.*;

public class Main {
    static List<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static Date getDateInput() {
        Date dateTime = null;
        while (true) {
            try {
                dateTime = Utils.dateFormatter.parse(scanner.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Invalid Date, Please enter in MM/DD/YYYY format Example: 12/31/2021");
            }
        }
        return dateTime;
    }

    public static void main(String[] args) {
        System.out.println("---------Welcome to the Titan Payment System----------\n\n\n\n");

        try {
            users = FileUtil.readFromFileSystem();
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
            users = new ArrayList<>();
        }

        while (true) {
            int choice;
            System.out.println("Select Option from below:\n");
            System.out.println("1. Add New User");
            System.out.println("2. Delete  User");
            System.out.println("3. Show All Users");
            System.out.println("4. Login");
            System.out.println("5. Exit");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Name");
                    String username = scanner.nextLine();
                    System.out.println("Enter Full Name");
                    String fullName = scanner.nextLine();
                    System.out.println("Enter Phone Number");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter Password");
                    String password = scanner.nextLine();
                    System.out.println("Enter Country");
                    String country = scanner.nextLine();
                    System.out.println("Enter Address");
                    String address = scanner.nextLine();
                    User user = new User(username, fullName, phoneNumber, password, country, address);
                    users.add(user);
                }
                case 2 -> {
                    System.out.println("Enter Name");
                    String name = scanner.nextLine();
                    for (User user1 : users) {
                        if (user1.getName().equals(name)) {
                            users.remove(user1);
                            break;
                        }
                    }

                }
                case 3 -> {
                    System.out.println("List of Users:");
                    for (User user1 : users) {
                        System.out.println(user1);
                    }
                }
                case 4 -> {
                    System.out.println("Enter Name");
                    String name1 = scanner.nextLine();
                    System.out.println("Enter Password");
                    String password1 = scanner.nextLine();
                    for (User user1 : users) {
                        if (user1.getName().equals(name1) && user1.getPassword().equals(password1)) {
                            System.out.println("Login Successful");
                            WHILE:
                            while (true) {
                                int choice1;
                                System.out.println("Select Option");
                                System.out.println("1. Add New Purchase");
                                System.out.println("2. Upload a Payment");
                                System.out.println("3. Show All Purchases");
                                System.out.println("4. Show Minimum Transaction");
                                System.out.println("5. Show Maximum Transaction");
                                System.out.println("6. Show Amount Due");
                                System.out.println("7. Show Total Amount Paid");
                                System.out.println("8. Show Payment History");
                                System.out.println("9. Show All Purchases Between Dates");
                                System.out.println("10. Download PDF File");
                                System.out.println("11. Logout");
                                choice1 = Integer.parseInt(scanner.nextLine());
                                switch (choice1) {
                                    case 1 -> {
                                        System.out.println("Enter Date (MM/DD/YYYY)");
                                        Date dateTime = null;
                                        while (true) {
                                            try {
                                                dateTime = Utils.dateFormatter.parse(scanner.nextLine());
                                                break;
                                            } catch (ParseException e) {
                                                System.out.println("Invalid Date, Please enter in MM/DD/YYYY format Example: 12/31/2021");
                                            }
                                        }
                                        System.out.println("Select Purchase Card");
                                        System.out.println("1. Visa");
                                        System.out.println("2. AMEX");
                                        System.out.println("3. Discover");
                                        Card purchaseCard = Card.values()[Integer.parseInt(scanner.nextLine()) - 1];
                                        System.out.println("Enter Amount Paid");
                                        double amountPaid = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Is this Transaction Paid/Due? (y/n)");
                                        boolean isPaid = scanner.nextLine().equalsIgnoreCase("y");
                                        Purchase purchase = new Purchase(dateTime, purchaseCard, amountPaid, isPaid);
                                        user1.getPurchaseList().add(purchase);
                                    }
                                    case 2 -> {
                                        System.out.println("Making a Payment");
                                        System.out.println("Enter Payment Date (MM/DD/YYYY)");
                                        Date paymentDate = null;
                                        while (true) {
                                            try {
                                                paymentDate = Utils.dateFormatter.parse(scanner.nextLine());
                                                break;
                                            } catch (ParseException e) {
                                                System.out.println("Invalid Date, Please enter in MM/DD/YYYY format Example: 12/31/2021");
                                            }
                                        }
                                        System.out.println("Select Payment Card");
                                        System.out.println("1. Visa");
                                        System.out.println("2. AMEX");
                                        System.out.println("3. Discover");
                                        Card paymentCard = Card.values()[Integer.parseInt(scanner.nextLine()) - 1];
                                        System.out.println("Enter Amount Paid");
                                        double amountPaid1 = Double.parseDouble(scanner.nextLine());
                                        Payment payment = new Payment(paymentDate, paymentCard, amountPaid1);
                                        user1.getPaymentList().add(payment);
                                    }
                                    case 3 -> {
                                        System.out.println("List of Purchases:");
                                        System.out.println("Date\t\t\t\t\tCard\t\t\t\t\tAmount\t\t\t\t\tBilling Cycle\t\t\t\t\tPaid");
                                        for (Purchase purchase1 : user1.getPurchaseList()) {
                                            System.out.println(Utils.convertToLocalDateViaInstant(purchase1.getDate()).toString() + "\t\t\t\t\t" + purchase1.getPurchaseCard() + "\t\t\t\t\t" + purchase1.getAmount() + "\t\t\t\t\t" + purchase1.getBillingCycle() + "\t\t\t\t\t" + purchase1.getIsPaid());
                                        }
                                    }
                                    case 4 -> {
                                        System.out.println("Minimum Transaction: " + Collections.min(user1.getPurchaseList(), Comparator.comparing(Purchase::getAmount)));
                                    }
                                    case 5 -> {
                                        System.out.println("Maximum Transaction: " + Collections.max(user1.getPurchaseList(), Comparator.comparing(Purchase::getAmount)));
                                    }
                                    case 6 -> {
                                        System.out.println("Amount Due: " + (user1.getPurchaseList().stream().mapToDouble(Purchase::getAmount).sum() - user1.getPaymentList().stream().mapToDouble(Payment::getAmount).sum()));
                                    }
                                    case 7 -> {
                                        System.out.println("Total Amount Paid: " + user1.getPaymentList().stream().mapToDouble(Payment::getAmount).sum());
                                    }
                                    case 8 -> {
                                        System.out.println("Payment History:");
                                        System.out.println("Date\t\t\t\t\tCard\t\t\t\t\tAmount");
                                        for (Payment payment1 : user1.getPaymentList()) {
                                            System.out.println(Utils.convertToLocalDateViaInstant(payment1.getDate()).toString() + "\t\t\t\t\t" + payment1.getPaymentCard() + "\t\t\t\t\t" + payment1.getAmount());
                                        }
                                    }
                                    case 9 -> {
                                        System.out.println("Enter Start Date (MM/DD/YYYY)");
                                        Date startDate = getDateInput();
                                        System.out.println("Enter End Date (MM/DD/YYYY)");
                                        Date endDate = getDateInput();
                                        System.out.println("List of Purchases:");
                                        System.out.println("Date\t\t\t\t\t\tCard\t\tAmount\t\tBilling Cycle\t\tPaid");
                                        for (Purchase purchase1 : user1.getPurchaseList()) {
                                            if (purchase1.getDate().after(startDate) && purchase1.getDate().before(endDate)) {
                                                System.out.println(purchase1.getDate() + "\t\t" + purchase1.getPurchaseCard() + "\t\t" + purchase1.getAmount() + "\t\t" + purchase1.getBillingCycle() + "\t\t" + purchase1.getIsPaid());
                                            }
                                        }
                                    }
                                    case 10 -> {
                                        List<PrintItem> printItems = new ArrayList<>();
                                        printItems.addAll(user1.getPurchaseList());
                                        printItems.addAll(user1.getPaymentList());
                                        Collections.sort(printItems, Comparator.comparing(PrintItem::getDate));
                                        FileUtil.exportToCSV(printItems, user1.getName());
                                    }
                                    case 11 -> {
                                        break WHILE;
                                    }
                                    default -> {
                                        System.out.println("Invalid Choice");
                                    }
                                }
                            }
                        }
                    }
                }
                case 5 -> {
                    FileUtil.writeToFileSystem(users);
                    System.out.println("Exiting...");
                    System.exit(0);


                }
                default -> {
                    System.out.println("Invalid Input");
                }
            }
        }
    }


}