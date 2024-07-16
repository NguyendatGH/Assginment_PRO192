/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Book;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Utils {

    private static ArrayList<Book> bookList = new ArrayList<>();

    public static String getValue(String text) {
        System.out.print(text);
        Scanner input = new Scanner(System.in);
        String result = input.nextLine();
        return result;
    }

    public static int checkInt(String input) {
        int num = 0;
        while (true) {
            try {
                num = Integer.parseInt(getValue(input));
                if (num > 0) {
                    return num;
                }
                if (num == 0) {
                    System.out.println("must be larger than 0!");
                }
            } catch (Exception e) {
                System.err.println("Input invalid number");
            }
        }
    }

    public static double checkDouble(String input) {
        double num = 0;
        while (true) {
            try {
                num = Double.parseDouble(getValue(input));
                if (num > 0.0) {
                    return num;
                }
                if (num == 0) {
                    System.out.println("must be larger than 0!");
                }
            } catch (Exception e) {
                System.err.println("Input invalid number");
            }
        }
    }

    public static boolean validID(String id) throws Exception {
        if (!id.matches("B\\d+")) {
            System.out.println("Id should be format like: 'Bxxx'");
            return false;
        }
        return true;
    }

    public static boolean validDate(String Dob) {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        date.setLenient(false); // setLenient là phương thức dùng để kiểm tra ngày có định dạng đúng hay không
        try {
            date.parse(Dob);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return false;
        }
        return true;
    }

   
}
