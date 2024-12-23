public class Resume extends Document {
    private String name;

    public Resume(String id, String documentType, String name) {
        super(id, documentType);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resume{id='" + id + "', name='" + name + "'}";
    }
}