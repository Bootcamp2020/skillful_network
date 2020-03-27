package fr.uca.cdr.skillful_network.model.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.CodeTemporaire;
import fr.uca.cdr.skillful_network.model.repositories.CodeTemporaireRepository;


@Service(value = "motDePasseService")
public class CodeTemporaireServiceImpl implements CodeTemporaireService {
	
	@Autowired
	private  CodeTemporaireRepository codeTemporaireRepository;
	
	@Autowired
	private CodeTemporaireService codeTemporaireService;
		

	@Override
	public Boolean isExpired(LocalDateTime dateExpiration, LocalDateTime currentDate) {
		Boolean codeExpire = currentDate.isAfter(dateExpiration);
		return codeExpire;
	}

	@Override
	public CodeTemporaire getMotDePasseByPwd(String pwd) {
		return  codeTemporaireRepository.findByCodeTempo(pwd);

}
	
	@Override
	public ResponseEntity<String> testValidite(String token) {
		
		CodeTemporaire codeTempo = codeTemporaireRepository.findByCodeTempo(token);
		
		Boolean isAfter = codeTemporaireService.isExpired(codeTempo.getDateExpiration(), LocalDateTime.now());
		Long idToken = codeTempo.getId();
		// test de la date réelle par rapport à la date d'expiration
		if (isAfter == true) {
			// le mot de passe est supprimé de la table si isAfter est true
			codeTemporaireRepository.deleteById(idToken);
			System.out.println("mot de passe supprimé en Bdd");
			System.out.println("utilisateur provisoire supprimé de la Bdd");
		} else {
			// cas du mot de passe en cours de validité
			System.out.println("mot de passe toujours valable : ");
			System.out.println(codeTempo.getCodeTempo());
		}
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		
	}
}
