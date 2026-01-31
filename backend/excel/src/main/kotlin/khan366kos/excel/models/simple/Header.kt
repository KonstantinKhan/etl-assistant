package khan366kos.excel.models.simple

@JvmInline
value class Header(private val value: String) {

    fun asString() = value

    companion object {
        val NONE = Header("")
    }
}