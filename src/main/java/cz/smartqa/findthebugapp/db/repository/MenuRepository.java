package cz.smartqa.findthebugapp.db.repository;

import cz.smartqa.findthebugapp.db.model.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

}