package fr.uca.cdr.skillful_network.controller;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.services.SubscriptionService;



@RestController
@CrossOrigin(origins = "*")
public class SubscriptionController {
	
	@Autowired 
	private SubscriptionService subscriptionService;
	


	@GetMapping(value = "/subscriptions")
	public Collection<Subscription> getSubscriptions() {
		Collection<Subscription> subscriptions = subscriptionService.getAllSubscription();
		return subscriptions;
	}
	
	
	@GetMapping("/subscriptions/{id}")
	@Transactional
	public Subscription findById(@PathVariable("id") long id) {
		return  subscriptionService.getSubscriptionById(id);
	}

	
	
	@PostMapping(value = "/subscriptions")
	@Transactional
	public Subscription save(@Valid @RequestBody Subscription subscription) {
		return subscriptionService.saveOrUpdateSubscription(subscription);

	}

	@DeleteMapping("/subscriptions/{id}")
	@Transactional
	public void deleteSubscription(@PathVariable(value = "id") Long id) {
		subscriptionService.deleteSubscription(id);
	}

}
