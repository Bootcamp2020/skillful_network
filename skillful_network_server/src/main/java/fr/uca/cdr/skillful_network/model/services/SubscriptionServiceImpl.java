package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.tools.AutoCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.repositories.SubscriptionRepository;

@Service(value = "subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	// Autocompletion init
	AutoCompletion<Subscription> completor = new AutoCompletion<>(Subscription.class, "name", "userList");

	@Override
	public List<Subscription> getAllSubscription() {
		return this.subscriptionRepository.findAll();
	}

	@Override
	public List<Subscription> getSubscriptionsByMatch(String match) {
		return completor.findCandidates(subscriptionRepository.findAll(), match);
	}

	@Override
	public Optional<Subscription> getSubscriptionById(Long id) {
		return this.subscriptionRepository.findById(id);
	}

	@Override
	public Optional<Subscription> getSubscriptionByName(String name) {
		return this.subscriptionRepository.findByName(name);
	}

	@Override
	public Subscription saveOrUpdateSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	@Override
	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteById(id);

	}

}
