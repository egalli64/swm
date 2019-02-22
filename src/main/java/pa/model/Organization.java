package pa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZATIONS")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORG_ID")
    private long id;

    @Column(name = "ORG_NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORG_COUNTRY", //
            joinColumns = @JoinColumn(name = "ORG_ID"), //
            inverseJoinColumns = @JoinColumn(name = "COUNTRY_ID"))
    Set<Country> countries;

    public Organization() {
    }

    public Organization(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    @Override
    public String toString() {
        return "Org [id=" + id + ", name=" + name + ", countries=" + countries + "]";
    }
}
