package console;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productClasses.ProductEntity;
import utils.HashCreator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String username;
    private String password;
    @OneToMany(mappedBy = "owner",orphanRemoval = true)
    private List<ProductEntity> products;

    public User(String username, String password) {
        this.username = username;
        this.password = HashCreator.hash384(password);
    }

}
