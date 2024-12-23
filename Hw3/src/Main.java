import exceptions.*;
public class Main {
    public static void main(String[] args) {
        try {
            Document contract = DocumentParser.parseDocument("contract.json");
            System.out.println(contract);
            Document receipt = DocumentParser.parseDocument("receipt.json");
            System.out.println(receipt);
            Document resume = DocumentParser.parseDocument("resume.json");
            System.out.println(resume);
            Library<Contract> contractLibrary = new Library<>();
            contractLibrary.addDocument((Contract) contract);
            Contract retrievedContract = contractLibrary.getDocument("A1");
            System.out.println("retrieved contract: " + retrievedContract);
            contractLibrary.removeDocument("A1");
            System.out.println("removed contract from library");

        } catch (DocumentParseException | InvalidDocumentTypeException | MissingFieldException |
                 FileDoesNotExistException | UnsupportedValueTypeException e) {
            System.err.println("errrror occurred: " + e.getMessage());
        }
    }
}