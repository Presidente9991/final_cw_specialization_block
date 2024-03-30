import java.util.Scanner;

// Основной класс программы
public class AnimalRegistryProgram {
    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        Scanner scanner = new Scanner(System.in);

        try (Counter counter = new Counter()) {
            boolean exit = false;
            while (!exit) {
                System.out.println("Всего зарегистрировано животных: " + registry.getCounter());
                System.out.println("Добро пожаловать в питомник! Выберите действие:");
                System.out.println("1. Просмотреть зарегистрированных животных");
                System.out.println("2. Зарегистрировать новое животное");
                System.out.println("3. Просмотреть список команд, известных животному");
                System.out.println("4. Обучить животное новой команде");
                System.out.println("5. Выйти");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Выберите вид просматриваемых животных:");
                        System.out.println("1. Просмотреть всех");
                        System.out.println("2. Только все домашние животные");
                        System.out.println("3. Только собаки");
                        System.out.println("4. Только коты");
                        System.out.println("5. Только хомяки");
                        System.out.println("6. Только все вьючные животные");
                        System.out.println("7. Только лошади");
                        System.out.println("8. Только верблюды");
                        System.out.println("9. Только ослы");
                        int viewType = scanner.nextInt();
                        scanner.nextLine();

                        switch (viewType) {
                            case 1:
                                registry.displayAllAnimals();
                                break;
                            case 2:
                                registry.displayByType("Домашние животные");
                                break;
                            case 3:
                                registry.displayByType("Собака");
                                break;
                            case 4:
                                registry.displayByType("Кот");
                                break;
                            case 5:
                                registry.displayByType("Хомяк");
                                break;
                            case 6:
                                registry.displayByType("Вьючные животные");
                                break;
                            case 7:
                                registry.displayByType("Лошадь");
                                break;
                            case 8:
                                registry.displayByType("Верблюд");
                                break;
                            case 9:
                                registry.displayByType("Осёл");
                                break;
                            default:
                                System.out.println("Некорректно указан вид животных для просмотра.");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Введите имя животного:");
                        String name = scanner.nextLine();
                        System.out.println("Выберите вид животного (1 - Собака, 2 - Кот, 3 - Хомяк, 4 - Лошадь, 5 - Верблюд, 6 - Осёл):");
                        int animalType = scanner.nextInt();
                        scanner.nextLine();
                        Animal animal;
                        switch (animalType) {
                            case 1:
                                animal = new Dog(name);
                                break;
                            case 2:
                                animal = new Cat(name);
                                break;
                            case 3:
                                animal = new Hamster(name);
                                break;
                            case 4:
                                animal = new Horse(name);
                                break;
                            case 5:
                                animal = new Camel(name);
                                break;
                            case 6:
                                animal = new Donkey(name);
                                break;
                            default:
                                System.out.println("Некорректно указан вид животного для регистрации.");
                                continue;
                        }
                        registry.addAnimal(animal);
                        System.out.println("Животное было успешно зарегистрировано.");
                        counter.add();
                        break;
                    case 3:
                        System.out.println("Выберите животное для просмотра известных ему команд:");
                        for (int i = 0; i < registry.getCounter(); i++) {
                            System.out.println((i + 1) + ". " + registry.getAnimal(i).getName());
                        }
                        int animalIndex = scanner.nextInt();
                        scanner.nextLine();
                        Animal selectedAnimal = registry.getAnimal(animalIndex - 1);
                        System.out.println("Известные команды для " + selectedAnimal.getName() + ": " + selectedAnimal.getCommands());
                        break;
                    case 4:
                        System.out.println("Выберите животное для обучения команде:");
                        for (int i = 0; i < registry.getCounter(); i++) {
                            System.out.println((i + 1) + ". " + registry.getAnimal(i).getName());
                        }
                        int trainingAnimalIndex = scanner.nextInt();
                        scanner.nextLine();
                        Animal trainingAnimal = registry.getAnimal(trainingAnimalIndex - 1);
                        System.out.println("Известные команды для " + trainingAnimal.getName() + ": " + trainingAnimal.getCommands());
                        System.out.println("Введите новую команду для обучения:");
                        String newCommand = scanner.nextLine();
                        registry.trainAnimal(trainingAnimal, newCommand);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Некорректное действие.");
                }
            }
        } catch (Exception e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }
    }
}
