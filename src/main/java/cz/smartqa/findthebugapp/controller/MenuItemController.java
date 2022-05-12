package cz.smartqa.findthebugapp.controller;

import cz.smartqa.findthebugapp.db.model.MenuEntity;
import cz.smartqa.findthebugapp.db.model.MenuItemEntity;
import cz.smartqa.findthebugapp.db.repository.MenuItemRepository;
import cz.smartqa.findthebugapp.db.repository.MenuRepository;
import cz.smartqa.findthebugapp.dto.MenuItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MenuItemController {

  @Autowired
  private MenuItemRepository menuItemRepository;

  @Autowired
  private MenuRepository menuRepository;

  @GetMapping("/menu-item")
  List<MenuItemDTO> getAll() {
    return menuItemRepository.findAll().stream().map(this::toMenuItemDTO).collect(Collectors.toList());
  }

  @GetMapping("/menu/{id}/menu-item")
  List<MenuItemDTO> getAllByMenuId(@PathVariable Long id) {
    return menuItemRepository.findAllByMenuId(id).stream().map(this::toMenuItemDTO).collect(Collectors.toList());
  }

  @PostMapping("/menu-item")
  MenuItemDTO create(@RequestBody MenuItemDTO newMenu) {
    MenuEntity menuEntity = menuRepository.findById(newMenu.getMenuId())
            .orElseThrow(() -> new RuntimeException("ID not found"));

    MenuItemEntity menuItemEntity = menuItemRepository.save(toMenuItemEntity(newMenu, menuEntity));
    return toMenuItemDTO(menuItemEntity);
  }

  @GetMapping("/menu-item/{id}")
  MenuItemDTO getOne(@PathVariable Long id) {
    return toMenuItemDTO(menuItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ID not found")));
  }

  @DeleteMapping("/menu-item/{id}")
  void delete(@PathVariable Long id) {
    menuItemRepository.deleteById(id);
  }

  private MenuItemDTO toMenuItemDTO(MenuItemEntity entity) {
    MenuItemDTO dto = new MenuItemDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setMenuId(entity.getMenu().getId());
    return dto;
  }

  private MenuItemEntity toMenuItemEntity(MenuItemDTO dto, MenuEntity menuEntity) {
    MenuItemEntity entity = new MenuItemEntity();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setMenu(menuEntity);
    return entity;
  }
}

