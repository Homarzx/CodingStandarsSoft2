/**
 * Calculator package.
*/

package homarherrera;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Calculator {
    /**
     *This is a empty constructor.
     */
    private Calculator() { }
    /**
     *Initial value for the vacations.
     */
    static final int INITIAL_VALUE = 1000;
    /**
     *Extra value is the destiny is New York.
     */
    static final int EXTRA_NEWYORK = 600;
    /**
     *Extra value is the destiny is Paris.
     */
    static final int EXTRA_PARIS = 500;
    /**
     *Max days for apply to the discount.
     */
    static final int MAX_DAY_PROMO = 30;
    /**
     *Value of promo or charge if it doesn't meet the condition.
     */
    static final int PROMO = 200;
    /**
     * Minimun numbers of travels to apply a discount of 10%.
     */
    static final int MIN_DISC_TRAVS = 4;
    /**
     *Maximun travels to apply a discount of 10% or minimun to apply 20.
     */
    static final int LIMIT_DISC_TRAVS = 10;
    /**
     *10% of discount.
     */
    static final double MIN_DISC = 0.9;
    /**
     *20% of discount.
     */
    static final double MAX_DISC = 0.8;
    /**
     *Minimun number of days to apply for a promo.
     */
    static final int DAYS_PROMO = 7;
    /**
     * Scanner to read the user inputs.
     */
    private static Scanner input;
    /**
     * Main function.
     * @param args
     */
    public static void main(final String[] args) {
        try {
            System.out.println("Empezamos el programa");
            input = new Scanner(System.in);
            String destination = "";
            while (true) {
                System.out.print("Escogamos un destino: ");
                destination = input.nextLine();
                if (destination.length() > 2) {
                        break;
                }
                System.out.print("Ingrese un destino correcto: ");
            }
            int travelers = getTravelers();
            int days = getDays();
            double total = INITIAL_VALUE;
            if (destination.compareToIgnoreCase("Paris") == 0) {
                total += EXTRA_PARIS;
            } else if (destination.compareToIgnoreCase("New York") == 0) {
                total += EXTRA_NEWYORK;
            }
            if (travelers > MIN_DISC_TRAVS && travelers < LIMIT_DISC_TRAVS) {
                total = total * MIN_DISC;
            } else if (travelers >  LIMIT_DISC_TRAVS) {
                total = total * MAX_DISC;
            }
            if (days < DAYS_PROMO) {
                total += PROMO;
            }
            if (days > MAX_DAY_PROMO || travelers == 2) {
                total -= PROMO;
            }
            System.out.println("Valor total: ".concat(String.valueOf(total)));
         } catch (Exception e) {
             System.err.println("-1");
         }
    }
    private static int getTravelers() {
        int travelers = 0;
        while (true) {
            System.out.print("Ingrese el numero de viajeros: ");
            try {
                travelers = input.nextInt();
                final int minTravelers = 0;
                final int maxTravelers = 80;
                if (travelers > minTravelers && travelers <= maxTravelers) {
                    break;
                }
                System.err.println("Capacidad debe estar entre 1 y 80.");
            } catch (InputMismatchException ime) {
                input.nextLine();
                System.err.print("Solo puede ingresar valores numericos. ");
            }
        }
        return travelers;
    }
    private static int getDays() {
        int days = 0;
        while (true) {
            System.out.print("Ingrese el numero de dias: ");
            try {
                days = input.nextInt();
                break;
            } catch (InputMismatchException ime) {
                input.nextLine();
                System.err.println("Solo ingresar valores numericos.");
            }
        }
        return days;
    }
}