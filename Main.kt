fun main() {
    val items = mutableListOf(
        Item("Vélo"),
        Item("Trottinette électrique"),
        Item("Kayak")
    )

    val locationSystem = LocationSystem(items)

    println("=== Bienvenue dans le système de location ===\n")

    locationSystem.showAvailableItems()
    println()

    locationSystem.rentItem("Vélo", 3)
    println()

    locationSystem.showAvailableItems()
    println()

    locationSystem.showRentedItems()
    println()

    locationSystem.returnItem("Vélo")
    println()

    locationSystem.showAvailableItems()
}
