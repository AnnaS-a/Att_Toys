package Metod;

public class Toy {
    private String id = "";
    private String name;
    private String count;
    private String dropping;
    
    public Toy(String name, String count, String dropping) {
        this.name = name;
        this.count = count;
        this.dropping = dropping;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCount() {
        return count;
    }
    
    public void setCount(String count) {
        this.count = count;
    }
    
    public String getDropping() {
        return dropping;
    }
    
    public void setDropping(String dropping) {
        this.dropping = dropping;
    }
    
    public Toy(String id, String name, String count, String dropping) {
        this(name, count, dropping);
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("id: %s  Наименование: %s; количество: %s; частота выпадения: %s", id, name, count, dropping);
    }
}
