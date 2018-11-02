



import java.util.Arrays;

public class Chromosome {
	 private boolean is_fitness_Changed=true;
	 private int fitness_value=0;
	public int[] elements_in_chromosome;
//Constructor 
	public Chromosome(int chromosomeSize) {
		elements_in_chromosome = new int[chromosomeSize];
		
	}
//creating individual
	public Chromosome initializeChromosome() {

	
		for (int x = 0; x < elements_in_chromosome.length; x++) {
			int random=(int)(Math.random()*2);
			if (random==1)// for random value greater than 0.5 take 1 otherwise 0
				elements_in_chromosome[x] = 1;
			else
				elements_in_chromosome[x] = 0;
		}
		return this;
	}
// get method 
	public int[] getGenes() {
		is_fitness_Changed=true;
		return elements_in_chromosome;
	}
//toString method
	public String toString() {
		return Arrays.toString(this.elements_in_chromosome);
	}
	//Method for getting fitness value
	public int getFitness() {
		if(is_fitness_Changed) {
			fitness_value= calculateFitness();
			is_fitness_Changed=false;
		}
		return fitness_value;
		
	}
	// Creating fitness by using decimal value
	public int decimalValueByFitness() {
		int value = 0;
		for (int x = 0; x < elements_in_chromosome.length; x++) {
		 value = (1 * elements_in_chromosome[0]) + (2 * elements_in_chromosome[1]) + (4 * elements_in_chromosome[2])
				+ (8 * elements_in_chromosome[3]) + (16 * elements_in_chromosome[4]) + (32 * elements_in_chromosome[5]);
		}
		return value;
	}
//fitness calculation
	public int calculateFitness() {
		int chromosomeFitness = 0;
		for (int x = 0; x < elements_in_chromosome.length; x++) {
			if (elements_in_chromosome[x] == GA_Operator.TARGET_CHROMOSOME[x])
				chromosomeFitness++;
		}
		return chromosomeFitness;
	}
	// decimal value of an individual
	public int decimalValue(Chromosome chromosome) {
		int value = (1 * chromosome.elements_in_chromosome[0]) + (2 * chromosome.elements_in_chromosome[1]) + (4 * chromosome.elements_in_chromosome[2])
				+ (8 * chromosome.elements_in_chromosome[3]) + (16 * chromosome.elements_in_chromosome[4]) + (32 * chromosome.elements_in_chromosome[5]);
		return value;
	}
// objective function
	public int functionValue(Chromosome chromosome) {
		return decimalValue(chromosome) * decimalValue(chromosome);
	}

}
