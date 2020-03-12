package fr.uca.cdr.skillful_network.model.services;

public interface UserService {
      Boolean alreadyExists(String mail);
      Boolean existingMailIsValidated(String mail);
}
