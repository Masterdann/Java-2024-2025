public class Fish implements Animal {
    private final String id;
    private final double height;
    private Supervisor supervisor;
    public Fish(String id, double height) {
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
        return ""; // Fish does not make sound
    }


    public boolean canMakeSound() {
        return false;
    }


    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }


    public Supervisor getSupervisor() {
        return supervisor;
    }
}
