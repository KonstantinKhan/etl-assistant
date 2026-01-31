package khan366kos.excel.models.simple

@JvmInline
value class Title(private val value: String) {

    fun asString() = value

    companion object {
        val NONE = Title("")
    }
}