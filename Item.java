public class Item {
    private String name;
    private StatusEnum status;
    private String rentedUntil;

    public Item(String name, StatusEnum status, String rentedUntil) {
        this.name = name;
        this.status = status;
        this.rentedUntil = rentedUntil;
    }

    public Item(String name) {
        this.name = name;
        this.status = StatusEnum.DISPONIBLE;
    }

    public String getName() {
        return name;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getRentedUntil() {
        return rentedUntil;
    }

    public void rent(String returnDate) {
        this.status = StatusEnum.LOUÉ;
        this.rentedUntil = returnDate;
    }

    public void returnItem() {
        this.status = StatusEnum.DISPONIBLE;
        this.rentedUntil = null;
    }

    @Override
    public String toString() {
        if (this.status.equals(StatusEnum.LOUÉ)) {
            return name + " (loué jusqu'au " + rentedUntil + ")";
        }
        return name + " (disponible)";
    }
    
    
}
