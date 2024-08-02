import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DistributeApple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> apples = new ArrayList<>();

        System.out.println("Enter apple weight in gram (-1 to stop): ");
        while (true) {
            int weight = sc.nextInt();
            if (weight == -1) break;
            apples.add(weight);
        }

        distributeApples(apples);
        sc.close();
    }

    private static void distributeApples(List<Integer> apples) {
        // Amounts paid
        int ramPaid = 50;
        int shamPaid = 30;
        int rahimPaid = 20;
        int totalPaid = ramPaid + shamPaid + rahimPaid;

        // Initialize result lists
        List<Integer> ramApples = new ArrayList<>();
        List<Integer> shamApples = new ArrayList<>();
        List<Integer> rahimApples = new ArrayList<>();

        // Sort apples in descending order
        Collections.sort(apples, Collections.reverseOrder());

        // Distribute apples based on the proportion of the amount paid
        int ramWeight = 0;
        int shamWeight = 0;
        int rahimWeight = 0;

        for (int apple : apples) {
            if (ramWeight * totalPaid < ramPaid * (ramWeight + shamWeight + rahimWeight + apple)) {
                ramApples.add(apple);
                ramWeight += apple;
            } else if (shamWeight * totalPaid < shamPaid * (ramWeight + shamWeight + rahimWeight + apple)) {
                shamApples.add(apple);
                shamWeight += apple;
            } else {
                rahimApples.add(apple);
                rahimWeight += apple;
            }
        }

        // Print the result
        System.out.println("Distribution Result: ");
        System.out.print("Ram: ");
        
        for (int apple : ramApples) {
            System.out.print(apple + " ");
        }
        System.out.println();

        System.out.print("Sham: ");
        for (int apple : shamApples) {
            System.out.print(apple + " ");
        }
        System.out.println();

        System.out.print("Rahim: ");
        for (int apple : rahimApples) {
            System.out.print(apple + " ");
        }
        System.out.println();
    }
}
