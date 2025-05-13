import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocationSystem{

    private List<Item> items;

    public LocationSystem(List<String> itemNames) {
        this.items = new ArrayList<>();
        for (String name : itemNames) {
            items.add(new Item(name));
        }
    }

    public void showAvailableItems() {
        System.out.println("Articles disponibles :");
        items.stream()
            .filter(item -> "disponible".equals(item.getStatus()))
            .forEach(item -> System.out.println("- " + item.getName()));
    }

    public void showRentedItems() {
        System.out.println("Articles loués :");
        items.stream()
            .filter(item -> "loué".equals(item.getStatus()))
            .forEach(item -> System.out.println("- " + item));
    }

    public void rentItem(String itemName, int durationInDays) {
        Item item = items.stream()
            .filter(i -> i.getName().equalsIgnoreCase(itemName) && "disponible".equals(i.getStatus()))
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
            .filter(i -> i.getName().equalsIgnoreCase(itemName) && "loué".equals(i.getStatus()))
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