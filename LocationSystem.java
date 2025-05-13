import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocationSystem{

    private List<Item> items;

    public LocationSystem(List<Item> itemNames) {
        this.items = new ArrayList<>();
        for (Item name : itemNames) {
            items.add(name);
        }
    }

    public void showAvailableItems() {
        System.out.println("Articles disponibles :");
        items.stream()
            .filter(item -> item.getStatus().equals(StatusEnum.DISPONIBLE))
            .forEach(item -> System.out.println("- " + item.getName()));
    }

    public void showRentedItems() {
        System.out.println("Articles loués :");
        items.stream()
            .filter(item -> item.getStatus().equals(StatusEnum.LOUÉ))
            .forEach(item -> System.out.println("- " + item.getName()));
    }

    public void rentItem(String itemName, int durationInDays) {
        Item item = items.stream()
            .filter(i -> i.getName().equalsIgnoreCase(itemName) && i.getStatus().equals(StatusEnum.DISPONIBLE))
            .findFirst()
            .orElse(null);

        if (item == null) {
            System.out.println("L'article \"" + itemName + "\" n'est pas disponible pour le moment.");
            return;
        }

        String returnDate = calculateReturnDate(durationInDays);
        item.rent(returnDate);
        System.out.println("L'article \"" + itemName + "\" a été loué pour " + durationInDays + " jour(s). Retour prévu le " + returnDate + ".");
    }

    public void returnItem(String itemName) {
        Item item = items.stream()
            .filter(i -> i.getName().equalsIgnoreCase(itemName) && i.getStatus().equals(StatusEnum.LOUÉ))
            .findFirst()
            .orElse(null);

        if (item == null) {
            System.out.println("L'article \"" + itemName + "\" n'a pas été trouvé parmi les articles loués.");
            return;
        }

        item.returnItem();
        System.out.println("L'article \"" + itemName + "\" a été retourné et est maintenant disponible.");
    }

    private String calculateReturnDate(int durationInDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Date returnDate = new Date(today.getTime() + (long) durationInDays * 24 * 60 * 60 * 1000);
        return sdf.format(returnDate);
    }

}