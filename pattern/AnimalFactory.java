public class AnimalFactory {
    public static Animal create(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        if ("cat".equalsIgnoreCase(type)) return new Cat();
        throw new IllegalArgumentException("Unknown animal type: " + type);
    }
}