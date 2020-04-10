package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exam;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Keyword;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Result;
import fr.uca.cdr.skillful_network.model.repositories.KeywordRepository;
import fr.uca.cdr.skillful_network.model.repositories.SimulationRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.request.ExerciseForm;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service(value = "SimulationService")
public class SimulationServiceImpl implements SimulationService {

	@Autowired
	private SimulationRepository simulationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	QuestionSetService questionSetService;
	
	@Autowired
	private KeywordRepository keywordRepository;

	@Autowired
	private JobOfferRepository jobOfferRepository;
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private JobOfferService jobOfferService;
	
	@Autowired
	private SimulationService simulationService;
	
	private final float JOBACCESSSCORE = 0.80f;

	private final float TRAININGSCORE = 0.4f;


	@Override
	public List<Simulation> getAllSimulations() {
		return simulationRepository.findAll();
	}

	@Override
	public Optional<Simulation> getSimulationById(Long id) {
		return simulationRepository.findById(id);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return simulationRepository.findById(id).map(Simulation::getUser);
	}

	@Override
	public Optional<List<Simulation>> getAllSimulationsByUserId(Long userId) {
		return simulationRepository.findByUserId(userId);
	}

	@Override
	public Optional<Simulation> saveOrUpdateSimulation(Simulation simulation) {
		return Optional.of(simulationRepository.save(simulation));
	}


	@Override
	public Optional<Exam> startSimulation(Long userId) {
		System.out.println(">>> SimulationServiceImpl.startSimulation(userID: " + userId  + ")");
		User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Aucun utilisateur trouvé avec l'id : " + userId));
		
		System.out.println(user.getCareerGoal());
		
		// recuperer tous les jobOffer
				List<JobOffer> jobOffer = jobOfferService.getAllJobOffer();
				System.out.println(jobOffer);
				ArrayList<String> wordMatchCareerGoal = new ArrayList<String>();
				wordMatchCareerGoal.addAll(this.simulationService.MatcherJobOfferJobGoal(user.getCareerGoal(), (ArrayList<JobOffer>) jobOffer));
				System.out.println("wordMatchCareerGoal:" +wordMatchCareerGoal);
				if(wordMatchCareerGoal.size()==0) {
					throw new ResponseStatusException(HttpStatus.OK, "Aucune offre d'emploi qui correspond avec votre objectif d'emploi");
				
					}else
				{ 
						Simulation simulation = new Simulation(user.getCareerGoal());
						simulation.setUser(user);
				ArrayList<JobOffer> listeJobOfferMatcheJobGoal = new ArrayList<JobOffer>();
				listeJobOfferMatcheJobGoal.addAll(simulationService.ListJobOfferByJobGoal(user.getCareerGoal(),(ArrayList<JobOffer>) jobOffer )); 
				System.out.println(listeJobOfferMatcheJobGoal);
				
				    Random rand = new Random();
				   
				    JobOffer JobOfferByJobGoal = listeJobOfferMatcheJobGoal.get(rand.nextInt(listeJobOfferMatcheJobGoal.size()));
				
			
				ArrayList<Keyword> listKeywordExo = (ArrayList<Keyword>) simulationService.findAllKeyWordExo();
				System.out.println("listKeywordExo :" + listKeywordExo);
				ArrayList<Keyword> listeKeyWordsEquals = new ArrayList<Keyword>();
				listeKeyWordsEquals.addAll(simulationService.exerciceMachJoboffer(listKeywordExo, wordMatchCareerGoal));
				System.out.println("listeKeyWordsEquals :" + listeKeyWordsEquals);
				// macher les mots clés (listeKeyWordsEquals) avec les exercices par id
				ArrayList<Exercise> listExerciseSimulation=new ArrayList<Exercise>();
		        for(int i=0; i < listeKeyWordsEquals.size(); i++) {
		        	
		        	listExerciseSimulation.addAll(listeKeyWordsEquals.get(i).getExercises());
		        }
		        Set<Exercise> mySet = new HashSet<Exercise>(listExerciseSimulation);

		        System.out.println(mySet);
		        Exam exam = new Exam();
		        exam.setExerciseSet(mySet);
		        simulation.setExam(exam);
		    	simulation.setJobOffer(JobOfferByJobGoal);      
		simulation.setSynthesis("Lorem Ipsum ....");
		simulation = simulationRepository.save(simulation);
		
		System.out.println(">>> saved simulation: " + simulation);
		return Optional.of(simulation.getExam());}
	}

	@Override
	public void deleteSimulation(Long id) {
		simulationRepository.findById(id).map(simulation -> {
			simulationRepository.deleteById(id);
			return simulation;
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + id));
	}

	@Override
	public Optional<Simulation> evaluateSimulation(Set<ExerciseForm> exercises, Long examId) {
		Simulation simulation = simulationRepository.findByExamId(examId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune simulation trouvée avec l'id : " + examId));
		simulation.setTraining(null);
		float simulationGrade = calculateSimulationGrade(exercises, simulation);
		accessToJobOffer(simulationGrade, simulation);
		if (!simulation.isJobAccess()) {
			Training training = accessToJobOfferViaTraining(simulationGrade);
			if(training != null) {
				simulation.setTraining(training);
				simulation.setJobAccess(true);
			}
		}
		simulationRepository.save(simulation);
		return Optional.of(simulation);
	}
    
    @Override
    public Optional<Simulation> getSimulationByExamId(Long examId){
    	return simulationRepository.findByExamId(examId);
    }
    private float calculateSimulationGrade(Set<ExerciseForm> exercises, Simulation simulation) {
		float simulationGrade = 0;
		Set<Result> results = new HashSet<Result>();
		float weightByExercice = 0.8f / exercises.size();
		for (ExerciseForm exerciseForm : exercises) {
			float exerciceResult = questionSetService.calculateGrade(exerciseForm, weightByExercice);

			Result result = new Result(exerciseForm.getId(), exerciceResult);
			results.add(result);
			simulationGrade += exerciceResult;
		}
		simulation.setResults(results);
		return simulationGrade;
	}

	@Override
	public ArrayList<String> MatcherJobOfferJobGoal(String careerGoal, ArrayList<JobOffer> jobOffer) {
		ArrayList<String> listeKeyWords = new ArrayList<String>();

		for (int i = 0; i < jobOffer.size(); i++) {
			Set<Keyword> key = jobOffer.get(i).getKeywords();
			for (Iterator<Keyword> it = key.iterator(); it.hasNext(); ) {
				Keyword k = it.next();
				if (searchTheWord(careerGoal, k.getName())!= "")
					listeKeyWords.add(searchTheWord(careerGoal, k.getName()));
			}
		}
	
		Set<String> mySet = new HashSet<String>(listeKeyWords);
		List<String> listeKeyWords2 = new ArrayList<String>(mySet);

		return (ArrayList<String>) listeKeyWords2;
	}

	@Override
	public ArrayList<JobOffer> ListJobOfferByJobGoal(String careerGoal, ArrayList<JobOffer> jobOffer) {
		ArrayList<JobOffer> listeJobOfferMatcheJobGoal = new ArrayList<JobOffer>();
		for (int i = 0; i < jobOffer.size(); i++) {
			Set<Keyword> key = jobOffer.get(i).getKeywords();
			for (Iterator<Keyword> it = key.iterator(); it.hasNext(); ) {
				Keyword k = it.next();
				if (searchTheWord(careerGoal, k.getName())!= "")
					listeJobOfferMatcheJobGoal.add(jobOffer.get(i));
			}
		}

		Set<JobOffer> mySet = new HashSet<JobOffer>(listeJobOfferMatcheJobGoal);
		List<JobOffer> listeJobOfferMatcheJobGoal2 = new ArrayList<JobOffer>(mySet);
		return (ArrayList<JobOffer>) listeJobOfferMatcheJobGoal2;
	}

	@Override
	public Optional<Keyword> getKeyWordExoById(Long id) {
		return keywordRepository.findById(id);
	}

	@Override
	public List<Keyword> findAllKeyWordExo() {

		return keywordRepository.findAll();
	}

	///////////////////////////////
	public static int computeEditDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0) {
					costs[j] = j;
					continue;
				} else if (j <= 0) {
					continue;
				}

				int newValue = costs[j - 1];
				if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
					newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
				}
				costs[j - 1] = lastValue;
				lastValue = newValue;
			}
			if (i > 0) {
				costs[s2.length()] = lastValue;
			}
		}
		return costs[s2.length()];
	}

	public static double calculsimilarityOfStrings(String s1, String s2) {
		double similarityOfStrings = 0.0;
		int editDistance = 0;
		if (s1.length() < s2.length()) { // s1 should always be bigger
			String swap = s1;
			s1 = s2;
			s2 = swap;
		}
		int bigLen = s1.length();
		editDistance = computeEditDistance(s1, s2);
		if (bigLen == 0) {
			similarityOfStrings = 1.0; /* both strings are zero length */
		} else {
			similarityOfStrings = (bigLen - editDistance) / (double) bigLen;
		}
		return similarityOfStrings;
	}

	public static String searchTheWord(String str, String s2) {
		String[] splitArray;
		String motChercher = "";
		//
		splitArray = str.split(" ");
		for (int i = 0; i < splitArray.length; i++) {
			if (calculsimilarityOfStrings(splitArray[i], s2) >= 0.7) {
				motChercher = s2;
			}
		}
		return motChercher;
	}

	@Override
	public ArrayList<Keyword> exerciceMachJoboffer(ArrayList<Keyword> keyExo, ArrayList<String> keyJob) {
		ArrayList<Keyword> listeKeyWordsEquals = new ArrayList<Keyword>();
		for (int i = 0; i < keyJob.size(); i++) {
			for (int j = 0; j < keyExo.size(); j++) {
				if (calculsimilarityOfStrings(keyJob.get(i), keyExo.get(j).getName()) >= 0.8) {
					System.out.println("Similarity calcul : ");
					System.out.println(calculsimilarityOfStrings(keyJob.get(i), keyExo.get(j).getName()));
					listeKeyWordsEquals.add(keyExo.get(j));
				}
			}
		}
		return listeKeyWordsEquals;
	}


	private void accessToJobOffer(float simulationGrade, Simulation simulation) {
		if (simulationGrade >= JOBACCESSSCORE) {
			simulation.setJobAccess(true);
		} else {
			simulation.setJobAccess(false);
		}
	}

	private Training accessToJobOfferViaTraining(float simulationGrade) {
		Training training=null;
		if ((simulationGrade + TRAININGSCORE) >= JOBACCESSSCORE) {
			training = trainingService.getTrainingById(2L).orElseThrow(//Ici on passe l'Id du training(l'exemple de la demo: formation developpeur full stack) en dur en attendant future optimisation 
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun training trouvé avec l'id: " + 2L));
		}
		return training;
	}	
}
