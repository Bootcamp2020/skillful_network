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


@Service(value = "questionSetService")
public class QuestionSetServiceImpl implements QuestionSetService {

	@Autowired
	QuestionSetRepository questionSetRepository;

	@Autowired
	QuestionSetService questionSetService;

	@Override
	public double calculateGrade(ExerciseForm exerciseForm, double weightByExercice) {
		double questionGrade = 0;
		double exerciseGrade = 0;		
		QuestionSet questionSet = questionSetRepository.findById(exerciseForm.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Aucun questionSet trouvée pour l'exercice : " + exerciseForm.getId()));
		Set<AnswerForm> answers = exerciseForm.getAnswerSet();
		Set<Question> questions = questionSet.getQuestions();
		for (AnswerForm answerForm : answers) {
			Question question = findQuestion(answerForm.getQuestionId(), questions).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
					"La question " + answerForm.getQuestionId() + " de l'exercice " + exerciseForm.getId() + " n'existe pas"));
			if (question.getIndexAnswer() == answerForm.getAnswer()) {
				questionGrade ++;
			} 	
		}

		exerciseGrade = (double)Math.round((questionGrade/answers.size())* weightByExercice*100)/100;
  	return exerciseGrade;
	}

	private Optional<Question> findQuestion(Long questionId, Set<Question> questions) {
		Question questionResult = null;
		for (Question question : questions) {
			if (question.getId() == questionId) {
				questionResult = question;
				break;
			}
		}
		return Optional.ofNullable(questionResult);
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
