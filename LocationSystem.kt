import java.text.SimpleDateFormat
import java.util.Date

class LocationSystem(private val items: MutableList<Item>) {

    fun showAvailableItems() {
        println("Articles disponibles :")
        items.filter { it.getStatus() == Status.DISPONIBLE }
            .forEach { println("- ${it.getName()}") }
    }

    fun showRentedItems() {
        println("Articles loués :")
        items.filter { it.getStatus() == Status.LOUE }
            .forEach { println("- $it") }
    }

    fun rentItem(itemName: String, durationInDays: Int) {
        val item = items.find { it.getName().equals(itemName, ignoreCase = true) && it.getStatus() == Status.DISPONIBLE }

        if (item == null) {
            println("L'article \"$itemName\" n'est pas disponible pour le moment.")
            return
        }

        val returnDate = calculateReturnDate(durationInDays)
        item.rent(returnDate)
        println("L'article \"$itemName\" a été loué pour $durationInDays jour(s). Retour prévu le $returnDate.")
    }

    fun returnItem(itemName: String) {
        val item = items.find { it.getName().equals(itemName, ignoreCase = true) && it.getStatus() == Status.LOUE }

        if (item == null) {
            println("L'article \"$itemName\" n'a pas été trouvé parmi les articles loués.")
            return
        }

        item.returnItem()
        println("L'article \"$itemName\" a été retourné et est maintenant disponible.")
    }

    private fun calculateReturnDate(durationInDays: Int): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val today = Date()
        val returnDate = Date(today.time + durationInDays * 24 * 60 * 60 * 1000L)
        return dateFormat.format(returnDate)
    }
}
