package fr.uca.cdr.skillful_network.model.services;


import java.util.Collection;


import fr.uca.cdr.skillful_network.model.entities.Subscription;


public interface SubscriptionService {
	
	Collection<Subscription> getAllSubscription();
	
	Subscription getSubscriptionById(Long id);

	Subscription saveOrUpdateSubscription(Subscription subscription);

	void deleteSubscription(Long id);


}
