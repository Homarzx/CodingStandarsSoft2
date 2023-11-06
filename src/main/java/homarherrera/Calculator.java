/**
 * Calculator package.
 */

package homarherrera;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the calculator class
 * to solve workshop #1.
 */
public final class Calculator {
    /**
     * This is an empty constructor.
     */
    private Calculator() { }
    /**
     * Initial value for the vacations.
     */
    static final int INITIAL_VALUE = 1000;
    /**
     * Extra value is the destiny is New York.
     */
    static final int EXTRA_NEWYORK = 600;
    /**
     * Extra value is the destiny is Paris.
     */
    static final int EXTRA_PARIS = 500;
    /**
     * Max days for apply to the discount.
     */
    static final int MAX_DAY_PROMO = 30;
    /**
     * Value of promo or charge if it doesn't meet the condition.
     */
    static final int PROMO = 200;
    /**
     * Minimum numbers of travels to apply a discount of 10%.
     */
    static final int MIN_DISC_TRAVS = 4;
    /**
     * Maximum travels to apply a discount of 10% or minimum to apply 20.
     */
    static final int LIMIT_DISC_TRAVS = 10;
    /**
     * 10% of discount.
     */
    static final double MIN_DISC = 0.9;
    /**
     * 20% of discount.
     */
    static final double MAX_DISC = 0.8;
    /**
     * Minimum number of days to apply for a promo.
     */
    static final int DAYS_PROMO = 7;
    /**
     * Cost of add-ons all inclusive cost.
     */
    static final int ALL_INCLUSIVE_COST = 200;
    /**
     * Cost of add-ons adventure activities.
     */
    static final int ADVENTURE_ACTIVITIES_COST = 150;
    /**
     * Cost of add-ons spa.
     */
    static final int SPA_AND_WELLNESS_COST = 100;
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

            int allInclusive = getAddOn("All-Inclusive Package");
            int adventureActivities = getAddOn("Adventure Activities Package");
            int spaAndWellness = getAddOn("Spa and Wellness Package");

            double addOnTotal = allInclusive
                    + adventureActivities + spaAndWellness;
            total += addOnTotal;

            System.out.println("Valor total: " + total);
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
                System.out.println("Solo ingresar valores numericos.");
            }
        }
        return days;
    }
    private static int getAddOn(final String addOnName) {
        int addOn = 0;
        while (true) {
            System.out.print("¿Desea agregar el paquete "
                    + addOnName + "? (1 para sí, 0 para no): ");
            try {
                int choice = input.nextInt();
                if (choice == 1) {
                    System.out.print("Ingrese el número de viajeros"
                            + "para el paquete " + addOnName + ": ");
                    addOn = input.nextInt() * getAddOnCost(addOnName);
                }
                break;
            } catch (InputMismatchException ime) {
                input.nextLine();
                System.out.println("Solo puede ingresar valores numericos.");
            }
        } return addOn;
    }
    private static int getAddOnCost(final String addOnName) {
        if ("All-Inclusive Package".equals(addOnName)) {
            return ALL_INCLUSIVE_COST;
        } else if ("Adventure Activities Package".equals(addOnName)) {
            return ADVENTURE_ACTIVITIES_COST;
        } else if ("Spa and Wellness Package".equals(addOnName)) {
            return SPA_AND_WELLNESS_COST;
        } else {
            return 0;
        }
    }
}
