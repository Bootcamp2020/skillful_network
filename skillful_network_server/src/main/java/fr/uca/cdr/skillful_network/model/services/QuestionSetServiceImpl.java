package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	public float calculateGrade(ExerciseForm exerciseForm, float weightByExercice) {
		float questionGrade = 0;
		float exerciseGrade = 0;		
		QuestionSet questionSet = questionSetRepository.findById(exerciseForm.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Aucune question trouvée pour l'exercice : " + exerciseForm.getId()));
		Set<AnswerForm> answers = exerciseForm.getAnswerSet();
		Set<Question> questions = questionSet.getQuestions();
		for (AnswerForm answerForm : answers) {
			Question question = findQuestion(answerForm.getQuestionId(), questions);
			if (question.getIndexAnswer() == answerForm.getAnswer()) {
				questionGrade ++;
			} 	
		}
		exerciseGrade = NumberTool.round((questionGrade/answers.size())* weightByExercice,2);
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
	public QuestionSet saveOrUpdateJobApplication(QuestionSet questionSet) {
		return questionSetRepository.save(questionSet);
	}
}
