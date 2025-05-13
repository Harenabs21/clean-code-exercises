enum class Status {
    LOUE, DISPONIBLE
}

class Item(private val name: String) {
    private var status: Status = Status.DISPONIBLE
    private var rentedUntil: String = ""

    fun getName(): String = name

    fun getStatus(): Status = status

    fun getRentedUntil(): String = rentedUntil

    fun rent(returnDate: String) {
        status = Status.LOUE
        rentedUntil = returnDate
    }

    fun returnItem() {
        status = Status.DISPONIBLE
        rentedUntil = ""
    }

    override fun toString(): String {
        return if (status == Status.LOUE) {
            "$name (loué jusqu'au $rentedUntil)"
        } else {
            "$name (disponible)"
        }
    }
}
