package fr.uca.cdr.skillful_network.tools;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoCompletion<T> {

    private Class<T> type;
    private String candidateNameField;
    private String candidateSetField;

    // const
    private static final long MAX_CANDIDATE_NUMBERS_WITHOUT_PREFIX = 5;
    private static final long MAX_CANDIDATE_NUMBERS = 5;

    public AutoCompletion(Class<T> classType, String candidateNameField, String candidateSetField) {
        super();

         System.out.println(">>> START AutoCompletion(classType: " + classType.getSimpleName() + ", candidateNameField: " + candidateNameField + ", candidateSetField: " + candidateSetField + ")");
        this.type = classType;
        this.candidateNameField = candidateNameField;
        this.candidateSetField = candidateSetField;

        // unvalidate type if fields cannot be validated
        if (!validateFields()) {
            type = null;
        }
    }

    public List<T> findCandidates(List<T> inputList, String pMatch) {

        // check that instance is validated
        if (type == null)
            return null;
        System.out.println(">>> START AutoCompletion.findCandidates<" + type.getSimpleName() + "> (match: " + pMatch + ")");

        // lowering match
        String match = pMatch != null ? pMatch.toLowerCase() : "";

        // looking for completion candidates : Java 8 code
        List<Candidate> candidates = inputList.stream()
                .map(item -> {
                    Candidate candidate = null;
                    String name = getCandidateName(item);
                    if (match.isEmpty()    // load all candidates when match is not provided
                            || name.toLowerCase().contains(match)) {    // load candidate when matches
                        candidate = new Candidate(name, getCandidateOccurence(item), inputList.indexOf(item));
                    }
                    return candidate;
                })
                .filter(item -> item != null)
                .collect(Collectors.toList());

        // Sorting candidate list
        Collections.sort(candidates);
        System.out.println(">>>\tAutoCompletion sorted candidate list : " + candidates);

        // set result List<T> base on found candidates
        List<T> candidateItems = candidates.stream()
                .map(item -> {
                    return inputList.get(item.getIndex());
                })
                .limit(match.isEmpty() ? MAX_CANDIDATE_NUMBERS_WITHOUT_PREFIX : MAX_CANDIDATE_NUMBERS) // or candidates.size() for full list
                .collect(Collectors.toList());

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
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            System.out.println(">>> ERROR attribute " + candidateNameField + " cannot be catch");
            return null;
        }
        return name;
    }

    public Integer getCandidateOccurence(T inputItem) {
        Integer occurrence = null;

        try {
            Field setField = type.getDeclaredField(candidateSetField);
            setField.setAccessible(true);
            occurrence = ((Set<?>) setField.get(inputItem)).size();
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            System.out.println(">>> ERROR attribute " + candidateSetField + " cannot be catch");
        }
        return occurrence;
    }

}
