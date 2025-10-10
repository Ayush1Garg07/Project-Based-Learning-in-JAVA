import java.util.ArrayList;
import java.util.Scanner;

public class Auto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter how many numbers you want to add: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Accept numbers as string input and parse
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter number " + i + ": ");
            String input = sc.nextLine();        // String Input
            Integer num = Integer.parseInt(input); // Parsing + Autoboxing (String → int → Integer)
            numbers.add(num);                     // Autoboxing into ArrayList
        }

        // Sum using unboxing
        int sum = 0;
        for (Integer num : numbers) { // Enhanced for-loop with Unboxing
            sum += num;              // Unboxing (Integer → int)
        }

        System.out.println("Numbers Entered: " + numbers);
        System.out.println("Total Sum = " + sum);
        sc.close();
    }
}
