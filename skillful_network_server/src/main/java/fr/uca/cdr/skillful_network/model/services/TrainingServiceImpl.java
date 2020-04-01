package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.tools.PageTool;

@Service(value = "TrainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	TrainingRepository trainingRepository;

	@Override
	public List<Training> getAllTraining() {
		return trainingRepository.findAll();
	}

	@Override
	public Page<Training> getPageOfEntities(PageTool pageTool) {
		return trainingRepository.findAll(pageTool.requestPage());

	}

	@Override
	public Optional<Training> getTrainingById(Long id) {
		return trainingRepository.findById(id);
	}

	@Override
	public Page<Training> searchTrainingByKeyword(Pageable pageable, String keyword) {
		return trainingRepository.findByNameContainsOrOrganisationContainsAllIgnoreCase(pageable, keyword, keyword);
	}

}
