package cz.smartqa.findthebugapp.db.repository;

import cz.smartqa.findthebugapp.db.model.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

    List<MenuItemEntity> findAllByMenuId(Long menuId);

}