package edu.csuf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

;

public class FileUtil {

    static Gson gson = new GsonBuilder()
            .setDateFormat("MM/dd/YYYY").create();

    static List<User> readFromFileSystem() {
//        Read String from file
        String json2 = "";
        try {
            FileReader fileReader = new FileReader("users.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                json2 += line;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading Users Data from file");
            return new ArrayList<>();
        }
        System.out.println(json2);
        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return gson.fromJson(json2, listType) != null ? gson.fromJson(json2, listType) : new ArrayList<>();
    }

    static void writeToFileSystem(List<User> users) {
        String json = gson.toJson(users);
        try {
            FileWriter fileWriter = new FileWriter("users.json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing Users Data to file");
        }
    }

    static void exportToCSV(List<PrintItem> printItems) {
        String csv = "Date,Transaction Type,Used Card,Amount,Billing Cycle \n";
        for (PrintItem printItem : printItems) {
            csv += printItem.getDate() + "," + printItem.getTransactionType() + "," + printItem.getPurchaseCard() + "," + printItem.getAmount() + "," + "\"" + printItem.getBillingCycle() + "\"" + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter("export.csv");
            fileWriter.write(csv);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing Users Data to CSV");
        }
    }

}
