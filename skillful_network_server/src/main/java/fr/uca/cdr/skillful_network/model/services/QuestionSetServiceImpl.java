package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Question;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.QuestionSet;
import fr.uca.cdr.skillful_network.model.repositories.QuestionSetRepository;
import fr.uca.cdr.skillful_network.request.AnswerForm;
import fr.uca.cdr.skillful_network.request.ExerciseForm;
import fr.uca.cdr.skillful_network.tools.NumberTool;

@Service(value = "questionSetService")
public class QuestionSetServiceImpl implements QuestionSetService {

	@Autowired
	QuestionSetRepository questionSetRepository;

	@Autowired
	QuestionSetService questionSetService;

	@Override
	public float calculateGrade(ExerciseForm exerciseForm) {
		float questionGrade = 0;
		float exerciseGrade = 0;		
		Optional<QuestionSet> questionSet = questionSetRepository.findById(exerciseForm.getId());
		Set<AnswerForm> answers = exerciseForm.getAnswerSet();
		Set<Question> questions = questionSet.get().getQuestions();
		for (AnswerForm answerForm : answers) {
			Question question = findQuestion(answerForm.getQuestionId(), questions);
			if (question.getIndexAnswer() == answerForm.getAnswer()) {
				questionGrade ++;
			} 	
		}
		exerciseGrade = NumberTool.round(questionGrade/answers.size(),2);
		return exerciseGrade;
	}

	private Question findQuestion(Long questionId, Set<Question> questions) {
		Question questionResult = null;
		for (Question question : questions) {
			if (question.getId() == questionId) {
				questionResult = question;
				break;
			}
		}
		return questionResult;
	}
	
	@Override
	public List<QuestionSet> getAllQuestionSets() {
		return questionSetRepository.findAll();
	}

	@Override
	public Optional<QuestionSet> getQuestionSetById(Long id) {
		return questionSetRepository.findById(id);
	}

	@Override
	public QuestionSet saveOrUpdateQuestionSet(QuestionSet questionSet) {
		return questionSetRepository.save(questionSet);
	}
}
