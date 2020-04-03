package fr.uca.cdr.skillful_network.model.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Question;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.QuestionSet;
import fr.uca.cdr.skillful_network.model.repositories.QuestionSetRepository;
import fr.uca.cdr.skillful_network.request.AnswerForm;
import fr.uca.cdr.skillful_network.request.ExerciseForm;

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
		exerciseGrade = questionGrade/answers.size();
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
}
