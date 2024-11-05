import java.util.*;

public class Zoo implements Observer {
    Map<String, Animal> animals;

    public Zoo() {
        animals = new HashMap<>();
    }

    public void addAnimal(Animal animal) {
        animals.put(animal.getId(), animal);
        animal.setSupervisor(null);
    }

    public Animal findAnimalById(String id) {
        return animals.get(id);
    }

    public void removeAnimalById(String id) {
        animals.remove(id);
    }

    public void assignSupervisorToAnimal(String animalId, Supervisor supervisor) {
        Animal animal = findAnimalById(animalId);
        if (animal != null) {
            animal.setSupervisor(supervisor);
            update(animalId, supervisor);
        }
    }

    public List<Animal> getAnimalsBySupervisorId(String supervisorId) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals.values()) {
            if (animal.getSupervisor() != null && animal.getSupervisor().getId().equals(supervisorId)) {
                result.add(animal);
            }
        }
        return result;
    }


    public void update(String animalId, Supervisor supervisor) {
        System.out.println("Supervisor " + supervisor.getName() + " is supervising " + animalId);
    }
}
