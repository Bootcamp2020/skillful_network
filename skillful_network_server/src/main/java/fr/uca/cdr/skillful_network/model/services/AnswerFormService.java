package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.*;
import java.util.List;
import java.util.Optional;

public interface AnswerFormService {

    List<AnswerForm> getAllAnswerForms();
    Optional<AnswerForm> getAnswerFormById(Long id);
    AnswerForm saveOrUpdateAnswerForm(AnswerForm answerForm);

}
