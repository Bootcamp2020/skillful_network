package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.*;
import java.util.List;
import java.util.Optional;

public interface SimulationFormService {

    List<SimulationForm> getAllSimulationForms();
    Optional<SimulationForm> getSimulationFormById(Long id);
    SimulationForm saveOrUpdateSimulationForm(SimulationForm simulationForm);

}
