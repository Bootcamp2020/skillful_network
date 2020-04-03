package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.ExerciseForm;

public interface QuestionSetService {
	
	float calculateGrade(ExerciseForm exerciseForm);
			
}
