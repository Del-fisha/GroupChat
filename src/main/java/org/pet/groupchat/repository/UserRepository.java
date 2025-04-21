package org.pet.groupchat.repository;

import org.pet.groupchat.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
