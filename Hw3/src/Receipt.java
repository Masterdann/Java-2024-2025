public class Receipt extends Document {
    private int moneyAmount;

    public Receipt(String id, String documentType, int moneyAmount) {
        super(id, documentType);
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "receipt{id='" + id + "', moneyAmount=" + moneyAmount + "}";
    }
}