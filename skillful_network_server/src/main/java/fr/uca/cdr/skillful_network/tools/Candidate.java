package fr.uca.cdr.skillful_network.tools;

public class Candidate implements Comparable<Object> {

	private String name;
	private Integer occurrence;
	private Integer index;

	public Candidate(String name, Integer occurrence, Integer index) {
		this.name = name;
		this.occurrence = occurrence;
		this.index = index;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass().equals(Candidate.class)) {

			Candidate candidateItem = (Candidate) o;

			// reversed order comparator flag for occurrence
			int compareOccurence = -this.occurrence.compareTo(candidateItem.getOccurrence());

			// if both items have same occurrence value, we sort on item name
			if (compareOccurence == 0)
				return this.name.compareTo(candidateItem.getName());
			return compareOccurence;
		}
		return -1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Integer occurence) {
		this.occurrence = occurence;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String toString() {
		return "Candidate [name=" + name + ", occurrence=" + occurrence + ", index=" + index + "]";
	}
}
