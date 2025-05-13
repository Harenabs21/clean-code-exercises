export class LocationSystem {
  constructor(items) {
    this.items = items.map(itemName => ({
      name: itemName,
      status: "disponible",
      rentedUntil: null,
    }));
  }

  rentItem(itemName, durationInDays) {
    const item = this.items.find(i => i.name === itemName && i.status === "disponible");
    if (!item) {
      console.log(`L'article "${itemName}" n'est pas disponible pour le moment.`);
      return;
    }

    const returnDate = new Date();
    returnDate.setDate(returnDate.getDate() + durationInDays);

    item.status = "loué";
    item.rentedUntil = returnDate;

    console.log(`L'article "${itemName}" a été loué pour ${durationInDays} jour(s). Retour prévu le ${returnDate.toLocaleDateString()}.`);
  }

  returnItem(itemName) {
    const item = this.items.find(i => i.name === itemName && i.status === "loué");
    if (!item) {
      console.log(`L'article "${itemName}" n'a pas été trouvé parmi les articles loués.`);
      return;
    }

    item.status = "disponible";
    item.rentedUntil = null;

    console.log(`L'article "${itemName}" a été retourné et est maintenant disponible.`);
  }

  showAvailableItems() {
    const availableItems = this.items.filter(i => i.status === "disponible");
    console.log("Articles disponibles :", availableItems.length
      ? availableItems.map(i => i.name).join(", ")
      : "Aucun article disponible.");
  }

  showRentedItems() {
    const rentedItems = this.items.filter(i => i.status === "loué");
    if (rentedItems.length === 0) {
      console.log("Aucun article loué.");
      return;
    }

    console.log("Articles loués :");
    rentedItems.forEach(item => {
      console.log(`- ${item.name}, retour prévu le ${item.rentedUntil.toLocaleDateString()}`);
    });
  }
}