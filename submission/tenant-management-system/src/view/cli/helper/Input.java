package view.cli.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Input {
    static private Scanner scanner = new Scanner(System.in);
    static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static void prefixInputLabel(){
        System.out.print(">>> ");
    }
    /**
     * takes input from the user
     * @param label description of the user input
     * @return valid integer value
     */
    public static int getInteger(String label){
        int input;
        while (true){
            try{
                System.out.println(label);
                prefixInputLabel();
                input = Integer.parseInt(scanner.nextLine());
                return input;
            }
            catch (Exception e){
                System.out.println("Please enter digit as a input for given field.");
            }
        }
    }

    /**
     *
     * @param label provide the description of input
     * @param min provide min value for given input
     * @param max provide max value for given input
     * @return valid int value
     */
    public static int getIntegerInRange(String label,int min,int max){
        int input;
        while (true){
            try{
                System.out.println(label);
                prefixInputLabel();
                input = Integer.parseInt(scanner.nextLine());
                if(input >= min && input <= max){
                    return input;
                }
                else {
                    System.out.println(String.format("Please enter the value between %d and %d.",min,max));
                }
            }
            catch (Exception e){
                System.out.println("Please enter digit as a input for given field.");
            }
        }
    }

    /**
     * takes input from the user
     * @param label description of the user input
     * @return valid decimal value
     */
    public static double getDouble(String label){
        double input;
        while (true){
            try{
                System.out.println(label);
                prefixInputLabel();

                input = Double.parseDouble(scanner.nextLine());
                return input;
            }
            catch (Exception e){
                System.out.println("Please enter digit as a input for given field.");
            }
        }
    }

    /**
     * takes input from the user
     * @param label description of the user input
     * @return valid string
     */
    public static String getString(String label){
        String input;
        while (true){
            try{
                System.out.println(label);
                prefixInputLabel();
                input = scanner.nextLine().trim();
                return input;
            }
            catch (Exception e){
                System.out.println("Please enter digit as a input for given field.");
            }
        }
    }
    /**
     * takes input from the user
     * @param label description of the user input
     * @return valid date
     */
    public static Date getDate(String label){
        Date date;
        while (true){
            try{
                System.out.println(label);
                prefixInputLabel();
                date = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                formatter.parse(formatter.format(date));
                return date;
            }
            catch (Exception e){
                System.out.println("Please enter digit as a input for given field : " + e.getMessage());
            }
        }
    }
}
