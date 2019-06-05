package idu.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import idu.cs.domain.Question;

@Service("QuestionService") // 중요
public class QuestionServiceImpl implements QuestionService {

	@Override
	public Question getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getUsersByUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Question question) {
		// TODO Auto-generated method stub

	}

}
