package fr.uca.cdr.skillful_network.model.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.IteratorUtils;


import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.repositories.SubscriptionRepository;

@Service(value = "subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService{
	
	@Autowired 
	private SubscriptionRepository subscriptionRepository;
	
	@Override
	public Collection<Subscription> getAllSubscription() {
		return IteratorUtils.toList(subscriptionRepository.findAll().iterator());
	}

	@Override
	public Subscription getSubscriptionById(Long id) {
		return  subscriptionRepository.getOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Subscription saveOrUpdateSubscription(Subscription subscription) {	
		return  subscriptionRepository.save(subscription);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteById(id);
		
	}

	

}
