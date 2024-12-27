public class Contract extends Document {
    private int cost;
    private String date;

    public Contract(String id, String documentType, int cost, String date) {
        super(id, documentType);
        this.cost = cost;
        this.date = date;
    }

    @Override
    public String toString() {
        return "contract{id='" + id + "', cost=" + cost + ", date='" + date + "'}";
    }
}