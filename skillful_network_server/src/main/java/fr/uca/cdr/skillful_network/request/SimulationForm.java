	package fr.uca.cdr.skillful_network.request;
    import java.util.HashSet;
	import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

	@Entity
	public class SimulationForm {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private Set<ExerciseForm> exerciseSet = new HashSet<ExerciseForm>();
		// représenté par un tableau côté front

		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
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
			return "SimulationForm [id=" + id + ", exerciseSet=" + exerciseSet + "]";
		}
			

	}


