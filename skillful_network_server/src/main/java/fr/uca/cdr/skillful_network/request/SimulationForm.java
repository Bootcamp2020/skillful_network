	package fr.uca.cdr.skillful_network.request;
    import java.util.HashSet;
	import java.util.Set;

	public class SimulationForm {
		
		private Set<ExerciseForm> exerciseSet = new HashSet<ExerciseForm>();
		// représenté par un tableau côté front

		public Set<ExerciseForm> getExerciseSet() {
			return exerciseSet;
		}

		public void setExerciseSet(Set<ExerciseForm> exerciseSet) {
			this.exerciseSet = exerciseSet;
		}
		public SimulationForm(Set<ExerciseForm> exerciseSet) {
			super();
			this.exerciseSet = exerciseSet;
		}

		public SimulationForm() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "SimulationForm [exerciseSet=" + exerciseSet + "]";
		}		

	}


