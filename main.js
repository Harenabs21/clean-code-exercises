import { LocationSystem } from './locationable.js';
import readline from 'readline'
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const predefinedItems = ["Vélo", "Trottinette électrique", "Kayak"];
const system = new LocationSystem(predefinedItems);

const showMenu = () => {
  console.log("\n=== Menu ===");
  console.log("1. Louer un article");
  console.log("2. Retourner un article");
  console.log("3. Afficher les articles disponibles");
  console.log("4. Afficher les articles loués");
  console.log("5. Quitter");
  console.log("==============");
};

// Gérer les interactions utilisateur
const handleInput = (input) => {
  switch (input.trim()) {
    case "1":
      rl.question("Entrez le nom de l'article à louer : ", (itemName) => {
        rl.question("Entrez la durée de location (en jours) : ", (duration) => {
          system.rentItem(itemName, parseInt(duration, 10));
          showMenu();
        });
      });
      break;
    case "2":
      rl.question("Entrez le nom de l'article à retourner : ", (itemName) => {
        system.returnItem(itemName);
        showMenu();
      });
      break;
    case "3":
      system.showAvailableItems();
      showMenu();
      break;
    case "4":
      system.showRentedItems();
      showMenu();
      break;
    case "5":
      console.log("Merci d'avoir utilisé le système de location. Au revoir !");
      rl.close();
      break;
    default:
      console.log("Option invalide. Veuillez réessayer.");
      showMenu();
      break;
  }
};
console.log("Bienvenue dans le système de location !");
showMenu();
rl.on("line", handleInput);