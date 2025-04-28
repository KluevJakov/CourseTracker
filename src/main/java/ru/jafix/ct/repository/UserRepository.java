package ru.jafix.ct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.jafix.ct.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByActivateCode(UUID activateCode);

    List<User> findByAgeGreaterThan(int age);

    @Query(value = "SELECT u FROM User u WHERE u.age < :maxAge AND u.email like :liter")
    List<User> findByEmailStartsWithLitterAndAgeBefore(@Param("maxAge") int maxAge, @Param("liter") String liter);
}
