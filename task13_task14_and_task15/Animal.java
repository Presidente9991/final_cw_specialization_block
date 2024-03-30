import java.util.ArrayList;
import java.util.List;

// Базовый класс Животные
class Animal {
    private final String name;
    private final String type;
    private final List<String> commands;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
        this.commands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void displayInfo() {
        System.out.println("Имя животного: " + name);

        System.out.println("Класс (debug): " + getClass().getSimpleName());
        System.out.println("Вид животного: " + type);
        System.out.println("Известные команды: " + commands);
        System.out.println();
    }
}
