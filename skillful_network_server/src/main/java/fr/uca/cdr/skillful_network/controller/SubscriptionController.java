package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.SubscriptionService;
import fr.uca.cdr.skillful_network.tools.AutoCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@Transactional
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	// Autocompletion init
	AutoCompletion<Subscription> completor = new AutoCompletion<>(Subscription.class, "name", "userList");
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping("/subscriptions")
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		List<Subscription> listSubscription = this.subscriptionService.getAllSubscription();
		return new ResponseEntity<List<Subscription>>(listSubscription, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping("/subscriptions/{id}")
	public Optional<Subscription> findById(@PathVariable("id") long id) {
		return subscriptionService.getSubscriptionById(id);
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/subscriptions/{id}/users")
	public ResponseEntity<Set<User>> getAllUserBySubscription(@PathVariable(value = "id") Long id) throws Throwable {
		Set<User> listUser = this.subscriptionService.getSubscriptionById(id).map((subscription) -> {
			return subscription.getUserList();
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun abonnement trouvé avec l'id : " + id));

		return new ResponseEntity<Set<User>>(listUser, HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER)")
	@GetMapping(value = "/subscriptions/{name}")
	public ResponseEntity<Subscription> getSubscriptionByName(@PathVariable(value = "name") String name) {
		Subscription subscriptionFromDb = this.subscriptionService.getSubscriptionByName(name).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun abonnement trouvé avec le nom " + name));

		return new ResponseEntity<Subscription>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@PostMapping(value = "/subscriptions")
	public Subscription save(@Valid @RequestBody Subscription subscription) {
		return subscriptionService.saveOrUpdateSubscription(subscription);

	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@DeleteMapping("/subscriptions/{id}")
	public void deleteSubscription(@PathVariable(value = "id") Long id) {
		subscriptionService.deleteSubscription(id);
	}
	// Le changement de RequestBody par RequestParam est par rapport à une limite angular 
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/subscriptions/candidates")
	public List<Subscription>  getAutoCompletionByMatch(@RequestParam(required=false , name="contain") String pMatch) {
		// Get subscriptions list
		List<Subscription> subscriptions = subscriptionService.getAllSubscription();

		// looking for completion candidates
		List<Subscription> candidates = completor.findCandidates(subscriptions, pMatch);
		return candidates;
	}
}
