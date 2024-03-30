import java.util.ArrayList;
import java.util.List;

// Класс реестра домашних животных
class AnimalRegistry {
    private final List<Animal> animals;

    public AnimalRegistry() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void displayAllAnimals() {
        System.out.println("Список зарегистрированных животных:");
        for (Animal animal : animals) {
            animal.displayInfo();
        }
    }

    public void displayByType(String type) {
        System.out.println("Список " + type.toLowerCase() + ":");
        for (Animal animal : animals) {
            if (type.equals("Домашние животные")) {
                if (animal instanceof DomesticAnimal) {
                    animal.displayInfo();
                }
            } else if (type.equals("Вьючные животные")) {
                if (animal instanceof WorkingAnimal) {
                    animal.displayInfo();
                }
            } else if (animal.getType().equals(type)) {
                animal.displayInfo();
            }
        }
    }


    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public int getCounter() {
        return animals.size();
    }

    public void trainAnimal(Animal animal, String newCommand) {
        animal.addCommand(newCommand);
        System.out.println("Животное " + animal.getName() + " обучено новой команде: " + newCommand);
    }
}
