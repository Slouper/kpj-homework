package cz.smartqa.findthebugapp.dto;

import java.util.Objects;

public class MenuDTO {

  private Long id;
  private String name;
  private Boolean enabled;

  public MenuDTO() {
  }

  public MenuDTO(Long id, String name, Boolean enabled) {
    this.id = id;
    this.name = name;
    this.enabled = enabled;
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

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuDTO menuDTO = (MenuDTO) o;
    return Objects.equals(id, menuDTO.id) && Objects.equals(name, menuDTO.name)
           && Objects.equals(enabled, menuDTO.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, enabled);
  }

  @Override
  public String toString() {
    return "MenuDTO{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", enabled=" + enabled +
           '}';
  }
}
