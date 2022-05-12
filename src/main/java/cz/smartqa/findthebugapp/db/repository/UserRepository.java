package cz.smartqa.findthebugapp.db.repository;

import cz.smartqa.findthebugapp.db.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
