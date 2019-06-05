package idu.cs.service;

import java.util.List;

import idu.cs.domain.Question;

public interface QuestionService {
	Question getUserById(long id); // primary key에 해당하는 id로  조회
	List<Question> getQuestion(); // 모든 사용자 조회
	
	List<Question> getUsersByUser(String user); // name으로 조회
	
	void saveUser(Question question); // 생성
	void updateUser(Question question); // 수정
	void deleteUser(Question question); // 삭제
}