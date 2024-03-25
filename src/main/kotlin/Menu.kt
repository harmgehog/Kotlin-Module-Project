class Menu<T : Item>(private val manager: Manager<T>, private val isNote: Boolean) {
    fun showMenu() {
        println(
            "Вы находитесь в меню ${manager.itemName}:" +
                    "\nСоздать - Введите '1'" +
                    "\nУдалить - Введите '2'" +
                    "\nВыбрать - Введите '3'" +
                    "\nВыйти - Введите '4'"
        )
    }

    fun handleInput(input: String) {
        when (input) {
            "1" -> {
                manager.createItem(isNote)
            }

            "2" -> {
                val item = manager.select()
                item?.let { manager.delete(it) }
            }

            "3" -> {
                if (isNote) {
                    val item = manager.select() as? Note
                    item?.let { println("${it.name}: ${it.content}") }
                } else if (manager.items.isEmpty()) {
                    return
                } else {
                    val innerNotes = Manager<Note>("заметки")
                    innerNotes.items = (manager.select() as? Archive)?.notes ?: mutableListOf()

                    val noteMenu = Menu(innerNotes, true)
                    while (true) {
                        noteMenu.showMenu()
                        val noteInput = readLine()
                        if (noteInput == "4") break else noteInput?.let { noteMenu.handleInput(it) }
                    }
                }
            }

            "4" -> return
            else -> {
                println("Неверный ввод. Пожалуйста, выберите элемент из списка.")
            }
        }
    }
}
