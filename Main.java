import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> predefinedItems = Arrays.asList("Vélo", "Trottinette électrique", "Kayak");
        LocationSystem system = new LocationSystem(predefinedItems);

        Scanner scanner = new Scanner(System.in);
        String option;

        System.out.println("Bienvenue dans le système de location !");
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Louer un article");
            System.out.println("2. Retourner un article");
            System.out.println("3. Afficher les articles disponibles");
            System.out.println("4. Afficher les articles loués");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Entrez le nom de l'article à louer : ");
                    String rentName = scanner.nextLine();
                    System.out.print("Entrez la durée de location (en jours) : ");
                    int duration = Integer.parseInt(scanner.nextLine());
                    system.rentItem(rentName, duration);
                    break;
                case "2":
                    System.out.print("Entrez le nom de l'article à retourner : ");
                    String returnName = scanner.nextLine();
                    system.returnItem(returnName);
                    break;
                case "3":
                    system.showAvailableItems();
                    break;
                case "4":
                    system.showRentedItems();
                    break;
                case "5":
                    System.out.println("Merci d'avoir utilisé le système de location. Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (!"5".equals(option));

        scanner.close();
    }
}
