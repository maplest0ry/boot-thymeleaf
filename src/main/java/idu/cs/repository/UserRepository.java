package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.domain.User;

@Repository
public interface UserRepository 
	extends JpaRepository<User, Long> {
	public List<User> findByName(String name);
	public List<User> findByCompany(String company);
	public User findByUserId(String userId); // ByUserId== where userid=''
	//인터페이스는 퍼플릭 상관없다.
}
