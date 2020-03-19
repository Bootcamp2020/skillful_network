package fr.uca.cdr.skillful_network.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;

public class AutoCompletion<T> {

	private Class<T> type;

	// const
	private static final long MAX_CANDIDATE_NUMBERS_WITHOUT_PREFIX = 5;
	private static final long MAX_CANDIDATE_NUMBERS = 5;

	private String candidateNameField;
	private String candidateSetField;



	public AutoCompletion(Class<T> classType, String candidateNameField, String candidateSetField) {
		super();
	
		//this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		//this.type = (Class<T>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];

		System.out.println(">>> START AutoCompletion(classType: " + classType.getSimpleName() + ", candidateNameField: " + candidateNameField + ", candidateSetField: " + candidateSetField + ")");
		this.type = classType;
		this.candidateNameField = candidateNameField;
		this.candidateSetField = candidateSetField;

		// unvalidate type if fields cannot be validated
		 if (! validateFields()) {
			 type = null;
		 }
	}
	
	public List<T> findCandidates(List<T> inputList, String pMatch) {

		// check that instance is validated
		if (type == null)
			return null;
		System.out.println(">>> START AutoCompletion.findCandidates<" + type.getSimpleName() + "> (match: " + pMatch + ")");
		
		// display all fields
//		for (T inputItem : inputList) {
//			Field[] inputClassFields = type.getDeclaredFields();
//			for (Field inputClassField : inputClassFields) {
//				System.out.println(">>> field : ");
//				System.out.println("- name \t" + inputClassField.getName());
//				System.out.println("- type \t" + inputClassField.getType().getSimpleName());
//				try {
//					inputClassField.setAccessible(true);
//					System.out.println("- value \t" + inputClassField.get(inputItem));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}

		// lowering match
		String match = pMatch != null ? pMatch.toLowerCase() : "";
		//System.out.println(">>> pMatch = " + pMatch + ", match = \"" + match + "\"");

		// looking for completion candidates : Java 8 code
		List<Candidate> candidates = inputList.stream()
			.map(item -> {
				Candidate candidate = null;
				String name = getCandidateName(item);
				//System.out.println(">>> candidate name = " + name);
				if ( match.isEmpty()	// load all candidates when match is not provided
						|| name.toLowerCase().contains(match) ) { 	// load candidate when matches
					//candidate = new Candidate(name, new Random().nextInt(3));
					candidate = new Candidate(name, getCandidateOccurence(item), inputList.indexOf(item));
					//System.out.println(">>> candidate = " + candidate);
				}
				return candidate;			
			})
			.filter(item -> item != null)
			.collect(Collectors.toList());

		// Sorting candidate list
		Collections.sort(candidates);
		System.out.println(">>>\tAutoCompletion sorted candidate list : " + candidates);

//		// set result List<String> base on found candidates
//		List<String> candidateNames = new ArrayList<>();
//		//candidates.forEach(item -> candidatesName.add(item.getName()));
//		candidateNames = candidates.stream()
//				.map(item -> item.getName())
//				.limit(match.isEmpty() ? MAX_CANDIDATE_NUMBERS_WITHOU_PREFIX : candidates.size())
//				.collect(Collectors.toList());
//		System.out.println(">>> sorted candidateNames list : " + candidateNames);
		
		// set result List<T> base on found candidates
		List<T> candidateItems = candidates.stream()
			.map(item -> {
				return inputList.get(item.getIndex());
			})
			.limit(match.isEmpty() ? MAX_CANDIDATE_NUMBERS_WITHOUT_PREFIX : MAX_CANDIDATE_NUMBERS) // or candidates.size() for full list
			.collect(Collectors.toList());
		//System.out.println(">>> sorted candidateItems list : " + candidateItems);

		return candidateItems;
	}
	
	public boolean validateFields() {
		
		// check fields type
		try {
			if (!type.getDeclaredField(candidateNameField).getType().getSimpleName().equals("String")) {
				System.out.println(">>> ERROR attribute " + candidateNameField + " is not a String");
				return false;
			}
			//System.out.println(">>> candidateNameField = " + candidateNameField + " is present and is a String");
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			System.out.println(">>> ERROR attribute " + candidateNameField + " is not present");
			return false;
		}

		try {
			if (!type.getDeclaredField(candidateSetField).getType().getSimpleName().equals("Set")) {
				System.out.println(">>> ERROR attribute " + candidateSetField + " is not a Set");
				return false;
			}
			//System.out.println(">>> candidateSetField = " + candidateSetField + " is present and is a Set");
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			System.out.println(">>> ERROR attribute " + candidateSetField + " is not present");
			return false;
		}
		
		System.out.println(">>>\tAutoCompletion is operational - fields are validated");
		return true;
	}
	
	public String getCandidateName(T inputItem) {
		String name = "";
		
		try {
			Field nameField = type.getDeclaredField(candidateNameField);
			nameField.setAccessible(true);
			name = (String) nameField.get(inputItem);
			//System.out.println(">>> field candidateNameField : " + candidateNameField + " = \t" + name);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(">>> ERROR attribute " + candidateNameField + " cannot be catch");
			return null;
		}

		return name;
	}

	public Integer getCandidateOccurence(T inputItem) {
		Integer occurrence;
		
		try {
			Field setField = type.getDeclaredField(candidateSetField);
			setField.setAccessible(true);
			occurrence = ((Set<?>) setField.get(inputItem)).size();
			//System.out.println(">>> field candidateSetField : " + candidateSetField + " = \t" + occurrence);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(">>> ERROR attribute " + candidateSetField + " cannot be catch");
			occurrence = null;
		}
		return occurrence;
	}

}
