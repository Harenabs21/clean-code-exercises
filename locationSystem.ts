enum statusEnum {
    LOUÉ = "loué",
    DISPONIBLE = "disponible"
}


export class Item {
    private name: string;
    private status: statusEnum;
    private rentedUntil: string;

    constructor(name: string) {
        this.name = name;
        this.status = statusEnum.DISPONIBLE;
        this.rentedUntil = "";
    }

    public getName(): string {
        return this.name;
    }

    public getStatus(): statusEnum {
        return this.status;
    }

    public getRentedUntil(): string {
        return this.rentedUntil;
    }

    public rent(returnDate: string): void {
        this.status = statusEnum.LOUÉ;
        this.rentedUntil = returnDate;
    }

    public returnItem(): void {
        this.status = statusEnum.DISPONIBLE;
        this.rentedUntil = "";
    }

    public toString(): string {
        if (this.status === statusEnum.LOUÉ) {
            return `${this.name} (loué jusqu'au ${this.rentedUntil})`;
        }
        return `${this.name} (disponible)`;
    }
}


export class LocationSystem {
    private items: Item[];

    constructor(items: Item[]) {
        this.items = items;
    }

    public showAvailableItems(): void {
        console.log("Articles disponibles :");
        this.items
            .filter(item => item.getStatus() === statusEnum.DISPONIBLE)
            .forEach(item => console.log(`- ${item.getName()}`));
    }

    public showRentedItems(): void {
        console.log("Articles loués :");
        this.items
            .filter(item => item.getStatus() === statusEnum.LOUÉ)
            .forEach(item => console.log(`- ${item.toString()}`));
    }

    public rentItem(itemName: string, durationInDays: number): void {
        const item = this.items.find(
            i => i.getName().toLowerCase() === itemName.toLowerCase() && i.getStatus() === statusEnum.DISPONIBLE
        );

        if (!item) {
            console.log(`L'article "${itemName}" n'est pas disponible pour le moment.`);
            return;
        }

        const returnDate = this.calculateReturnDate(durationInDays);
        item.rent(returnDate);
        console.log(
            `L'article "${itemName}" a été loué pour ${durationInDays} jour(s). Retour prévu le ${returnDate}.`
        );
    }

    public returnItem(itemName: string): void {
        const item = this.items.find(
            i => i.getName().toLowerCase() === itemName.toLowerCase() && i.getStatus() === statusEnum.LOUÉ
        );

        if (!item) {
            console.log(`L'article "${itemName}" n'a pas été trouvé parmi les articles loués.`);
            return;
        }

        item.returnItem();
        console.log(`L'article "${itemName}" a été retourné et est maintenant disponible.`);
    }

    private calculateReturnDate(durationInDays: number): string {
        const today = new Date();
        const returnDate = new Date(today.getTime() + durationInDays * 24 * 60 * 60 * 1000);
        return returnDate.toLocaleDateString("fr-FR");
    }
}
