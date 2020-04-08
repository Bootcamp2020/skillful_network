package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.*;
import fr.uca.cdr.skillful_network.model.repositories.SimulationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service(value = "SimulationFormService")
public class SimulationFormServiceImpl implements SimulationFormService {

    @Autowired
    private SimulationFormRepository simulationFormRepository;

    @Override
    public List<SimulationForm> getAllSimulationForms() {
        return simulationFormRepository.findAll();
    }

    @Override
    public Optional<SimulationForm> getSimulationFormById(Long id) {
        return simulationFormRepository.findById(id);
    }

    @Override
    public SimulationForm saveOrUpdateSimulationForm(SimulationForm simulationForm) {
        return simulationFormRepository.save(simulationForm);
    }

}
