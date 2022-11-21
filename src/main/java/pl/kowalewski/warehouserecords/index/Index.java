package pl.kowalewski.warehouserecords.index;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pl.kowalewski.warehouserecords.index.category.Category;

@Entity
@Table(name="Indexes")
public class Index {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "index_generator")
    @SequenceGenerator(name="index_generator", sequenceName = "index_seq", initialValue = 14, allocationSize = 1)
    @Basic(optional = false)
    Long index;
    String code;
    String name;
    String description;
    @ManyToOne
    @JoinColumn(name="category_id")
    Category category;
    Double quantity;

    public Long getIndex() {
        return index;
    }
    public void setIndex(Long index) {
        this.index = index;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
