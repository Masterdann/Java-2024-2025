public class Cat implements Animal {
    private final String id;
    private final double height;
    private Supervisor supervisor;

    public Cat(String id, double height) {
        this.id = id;
        this.height = height;
    }


    public String getId() {
        return id;
    }


    public double getHeight() {
        return height;
    }


    public String getSound() {
        return "Meow";
    }


    public boolean canMakeSound() {
        return true;
    }


    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }


    public Supervisor getSupervisor() {
        return supervisor;
    }
}
