import java.util.HashMap;
import java.util.Map;
import exceptions.*;
public class Library<T extends Document> {
    private Map<String, T> documents = new HashMap<>();

    public void addDocument(T document) throws DocumentParseException {
        if (documents.containsKey(document.getId())) {
            throw new DocumentParseException("document with id " + document.getId() + " already exists");
        }
        documents.put(document.getId(), document);
    }

    public T getDocument(String id) throws DocumentParseException {
        if (!documents.containsKey(id)) {
            throw new DocumentParseException("no document found with id: " + id);
        }
        return documents.get(id);
    }

    public void removeDocument(String id) throws DocumentParseException {
        if (!documents.containsKey(id)) {
            throw new DocumentParseException("no document found with id: " + id);
        }
        documents.remove(id);
    }
}