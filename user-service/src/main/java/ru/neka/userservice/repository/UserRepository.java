package ru.neka.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neka.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
