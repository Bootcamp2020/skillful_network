package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.tools.PageTool;

public interface TrainingService {
	List<Training> getAllTraining();

	Optional<Training> getTrainingById(Long id);

	Page<Training> getPageOfEntities(PageTool pageTool);

	Page<Training> searchTrainingByKeyword(Pageable pageable, String keyword);

}
