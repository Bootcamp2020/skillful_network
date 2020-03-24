package fr.uca.cdr.skillful_network.tools.json;

//import com.google.gson.*;
//import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
//
//import java.lang.reflect.Type;

public class ExerciseAdapter { //implements JsonDeserializer<Exercise> {

    private final static String PACKAGE_EXERCISES_IMPL = "fr.uca.cdr.skillful_network.model.entities.simulation.exercise.";
//
//    @Override
//    public Exercise deserialize(JsonElement jsonElement,
//                                Type type,
//                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        try {
//            return jsonDeserializationContext.deserialize(
//                    jsonElement,
//                    Class.forName(PACKAGE_EXERCISES_IMPL + jsonObject.get("type").getAsString())
//            );
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(
//                    PACKAGE_EXERCISES_IMPL + jsonObject.get("type").getAsString() + " could not be found." +
//                                        "The implementation of an Exercise must be in the package " +
//                                        "fr.uca.cdr.skillful_network.model.entities.simulation.exercises.", e);
//        }
//    }

}
