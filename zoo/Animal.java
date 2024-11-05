public interface Animal {
    String getId();
    double getHeight();
    String getSound();
    boolean canMakeSound();
    void setSupervisor(Supervisor supervisor);
    Supervisor getSupervisor();
}
