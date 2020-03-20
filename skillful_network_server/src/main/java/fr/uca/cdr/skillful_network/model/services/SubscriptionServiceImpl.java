package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.repositories.SubscriptionRepository;

@Service(value = "subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public List<Subscription> getAllSubscription() {
		return this.subscriptionRepository.findAll();
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
