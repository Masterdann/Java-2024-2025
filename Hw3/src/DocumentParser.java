import java.io.*;
import java.util.HashMap;
import java.util.Map;
import exceptions.*;
public class DocumentParser {

    public static Document parseDocument(String fileName) throws DocumentParseException, InvalidDocumentTypeException, MissingFieldException, FileDoesNotExistException, UnsupportedValueTypeException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileDoesNotExistException("file doesn't not exist: " + fileName);
        }

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line.trim());
            }
        } catch (IOException e) {
            throw new DocumentParseException("error while reading file: " + fileName);
        }

        Map<String, Object> jsonMap = parseJson(jsonContent.toString());

        // Check for the required fields
        if (!jsonMap.containsKey("id")) {
            throw new MissingFieldException("a mising file(??): id");
        }
        if (!jsonMap.containsKey("document_type")) {
            throw new MissingFieldException("missing field: document_type");
        }

        String id = (String) jsonMap.get("id");
        String documentType = (String) jsonMap.get("document_type");

        switch (documentType.toUpperCase()) {
            case "CONTRACT":
                if (!jsonMap.containsKey("cost") || !jsonMap.containsKey("date")) {
                    throw new MissingFieldException("missing fields for Contract");
                }
                return new Contract(id, documentType, (int) jsonMap.get("cost"), (String) jsonMap.get("date"));

            case "RECEIPT":
                if (!jsonMap.containsKey("money_amount")) {
                    throw new MissingFieldException("missing fields for Receipt");
                }
                return new Receipt(id, documentType, (int) jsonMap.get("money_amount"));

            case "RESUME":
                if (!jsonMap.containsKey("name")) {
                    throw new MissingFieldException("missin fields for Resume");
                }
                return new Resume(id, documentType, (String) jsonMap.get("name"));

            default:
                throw new InvalidDocumentTypeException("unssuported document type: " + documentType);
        }
    }

    // A simple method to parse a JSON-like string manually
    private static Map<String, Object> parseJson(String jsonString) throws UnsupportedValueTypeException {
        Map<String, Object> map = new HashMap<>();
        jsonString = jsonString.replaceAll("[{}\"]", "").trim();
        String[] pairs = jsonString.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length != 2) {
                continue;
            }
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if (value.matches("\\d+")) {
                map.put(key, Integer.parseInt(value));
            } else if (value.matches(".*\\d{1,2}-\\d{1,2}-\\d{4}.*") || value.matches("[a-zA-Z]+")) {
                map.put(key, value);
            } else {
                throw new UnsupportedValueTypeException("unsupportec value type for key: " + key);
            }
        }

        return map;
    }
}