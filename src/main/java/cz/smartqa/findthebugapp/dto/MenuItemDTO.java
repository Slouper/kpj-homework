package cz.smartqa.findthebugapp.dto;

import java.util.Objects;

public class MenuItemDTO {

  private Long id;
  private String name;
  private Long menuId;

  public MenuItemDTO() {
  }

  public MenuItemDTO(Long id, String name, Long menuId) {
    this.id = id;
    this.name = name;
    this.menuId = menuId;
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

  public Long getMenuId() {
    return menuId;
  }

  public void setMenuId(Long menuId) {
    this.menuId = menuId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuItemDTO that = (MenuItemDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(
            menuId, that.menuId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, menuId);
  }

  @Override
  public String toString() {
    return "MenuItemDTO{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", menuId=" + menuId +
           '}';
  }
}
