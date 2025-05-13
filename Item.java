public class Item {
    private String name;
    private String status;
    private String rentedUntil;

    public Item(String name, String status, String rentedUntil) {
        this.name = name;
        this.status = status;
        this.rentedUntil = rentedUntil;
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getRentedUntil() {
        return rentedUntil;
    }

    public void rent(String returnDate) {
        this.status = "loué";
        this.rentedUntil = returnDate;
    }

    public void returnItem() {
        this.status = "disponible";
        this.rentedUntil = null;
    }

    @Override
    public String toString() {
        if ("loué".equals(status)) {
            return name + " (loué jusqu'au " + rentedUntil + ")";
        }
        return name + " (disponible)";
    }
    
    
}
