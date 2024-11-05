import java.util.List;

public class Main {

    public static void main(String[] args) {

        Animal cat1 = new Cat("C1", 0.5);
        Animal dog1 = new Dog("D1", 0.6);
        Animal fish1 = new Fish("F1", 0.2);
        Animal hippo1 = new Hippos("H1", 1.5);
        Animal horse1 = new Horse("HO1", 2.4);
        Zoo zoo = new Zoo();
        zoo.addAnimal(cat1);
        zoo.addAnimal(dog1);
        zoo.addAnimal(fish1);
        zoo.addAnimal((horse1));
        zoo.addAnimal(hippo1);

        Supervisor supervisor1 = new Supervisor("Roger", "S1");


        zoo.assignSupervisorToAnimal("C1", supervisor1);
        zoo.assignSupervisorToAnimal("D1", supervisor1);
        zoo.assignSupervisorToAnimal("HO1", supervisor1);
        zoo.assignSupervisorToAnimal("H1", supervisor1);

        List<Animal> rogersAnimals = zoo.getAnimalsBySupervisorId("S1");
        System.out.println("Animals supervised by Roger:");
        for (Animal animal : rogersAnimals) {
            System.out.println(animal.getId());
        }

        System.out.println("Animals that can make sounds:");
        for (Animal animal : zoo.getAnimalsBySupervisorId("S1")) {
            if (animal.canMakeSound()) {
                System.out.println(animal.getId() + ": " + animal.getSound());
            }
        }


        System.out.println("Animals higher than 1.2m:");
        for (Animal animal : zoo.getAnimalsBySupervisorId("S1")) {
            if (animal.getHeight() > 1.2) {
                System.out.println(animal.getId());
            }
        }


        zoo.removeAnimalById("F1");
        System.out.println("Animals that remain after deletion");
        for (String id : zoo.animals.keySet()) {
            System.out.println(zoo.findAnimalById(id).getId());
        }

    }
}
