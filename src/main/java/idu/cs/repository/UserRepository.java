package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idu.cs.entity.UserEntity;

@Repository
public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {
	// findById, save, delete 선언없어도 구현 가능
	
	//아래 메소드들은 선언해야 JPA 규칙에 의해 구현됨
	//find-select문, By-where, OrderBy - order by, ASC와 DESC를 함꼐 사용
	public List<UserEntity> findByName(String name);
	
	//id 내림차순으로 정렬
	//public List<UserEntity> findByNameOrderByIdDESC(String name);
	public List<UserEntity> findByCompany(String company);
	public UserEntity findByUserId(String userId); // ByUserId== where userid=''
	//인터페이스는 퍼플릭 상관없다.
}
