import { Item, LocationSystem } from "./locationSystem";

const items = [
    new Item("Vélo"),
    new Item("Trottinette électrique"),
    new Item("Kayak")
];

const locationSystem = new LocationSystem(items);

console.log("=== Bienvenue dans le système de location ===\n");

locationSystem.showAvailableItems();
console.log("\n");

locationSystem.rentItem("Vélo", 3);
console.log("\n");

locationSystem.showAvailableItems();
console.log("\n");

locationSystem.showRentedItems();
console.log("\n");

locationSystem.returnItem("Vélo");
console.log("\n");

locationSystem.showAvailableItems();
