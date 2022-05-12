package cz.smartqa.findthebugapp.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Boolean enabled;

    @OneToMany(mappedBy = "menu")
    private final List<MenuItemEntity> menuItems = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private RestaurantEntity restaurant;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuEntity that = (MenuEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(enabled, that.enabled);
    }
}
