package pl.kowalewski.warehouserecords.index.category;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    Long id;
    String name;

    Category(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
