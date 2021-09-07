package amorallife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import amorallife.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

}
