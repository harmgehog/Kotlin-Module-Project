fun main() {
    val menu = Menu(Manager<Archive>("архив"), false)
    while (true) {
        menu.showMenu()
        val archiveInput = readLine()
        if (archiveInput == "4") break else archiveInput?.let { menu.handleInput(it) }
    }
}
