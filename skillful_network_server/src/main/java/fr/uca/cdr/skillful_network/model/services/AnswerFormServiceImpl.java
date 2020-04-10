package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.*;
import fr.uca.cdr.skillful_network.model.repositories.AnswerFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service(value = "AnswerFormService")
public class AnswerFormServiceImpl implements AnswerFormService {

    @Autowired
    private AnswerFormRepository answerFormRepository;

    @Override
    public List<AnswerForm> getAllAnswerForms() {
        return answerFormRepository.findAll();
    }

    @Override
    public Optional<AnswerForm> getAnswerFormById(Long id) {
        return answerFormRepository.findById(id);
    }

    @Override
    public AnswerForm saveOrUpdateAnswerForm(AnswerForm answerForm) {
        return answerFormRepository.save(answerForm);
    }

}
