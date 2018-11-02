




import java.util.ArrayList;
import java.util.Arrays;

public class MainProgram {

	public static void main(String[] args) {
		// Initializing population for a Generation
		// Creating object of Population and GA_Operator
		
		Population population= new Population(GA_Operator.POPULATION_SIZE).initializePopulation();
		GA_Operator geneticAlgorithm = new GA_Operator();
		//For generation 0 printing the values in the print method
		int generationNumber=0;
		print(population, generationNumber);
		// Creating three Array list to store values of maximum, minimum and average
		ArrayList<Integer> max_value = new ArrayList<Integer>();
		ArrayList<Integer> min_value = new ArrayList<Integer>();
		ArrayList<Float> avg_value = new ArrayList<Float>();
		 // Running the loop for the given generation number
		for(int i=0; i<GA_Operator.GENERATION_NUMBER; i++){
			max_value.add(generationNumber, decimalValueIndividualChromosome(population.getChromosome()[0]));
			
			min_value.add(generationNumber, decimalValueIndividualChromosome(population.getChromosome()[GA_Operator.POPULATION_SIZE-1]));
			
			float a=0;
			// Creating summation for average value
			for(int j=0; j<GA_Operator.POPULATION_SIZE; j++) {
			 
			a= a+decimalValueIndividualChromosome(population.getChromosome()[j]);
			}
			a=a/(GA_Operator.POPULATION_SIZE);
			avg_value.add(generationNumber,a);
			// Looping through the selection process, crossover and mutation
			population= geneticAlgorithm.evolution_Operator(population);
			population.sortChromosomeByFitness();
			// Increasing generation number by one as zero number generation is outside of the loop
			generationNumber++;
			// Printing the generation number, chromosome_number, string and fitness value 
			print(population, generationNumber);
			
		}
		//Loop for printing maximum value, minimum value and average value
		System.out.print("Maximum Values"+"\t"+"Minimum Values"+"\t"+"Average Values\n");
		for (int i=0; i<generationNumber; i++)
		{ System.out.print(max_value.get(i)+"\t\t"+min_value.get(i)+"\t\t"+avg_value.get(i)+"\n");}
		

	}
	// Method for print operation of each generation
		public static void print(Population population, int generationNumber) {
			System.out.println("Target Chromosome "+ Arrays.toString(GA_Operator.TARGET_CHROMOSOME));
			System.out.println("Generation # " + generationNumber + " |Fittest Chromosome :"+ Arrays.toString(population.getChromosome()[0].getGenes())+
					" | fitness: "
					+ population.getChromosome()[0].decimalValueByFitness());

			for(int i=0; i<GA_Operator.POPULATION_SIZE; i++) {
				System.out.println("Generation # "+generationNumber+": "+" Chromosome # "+i+" "+
						Arrays.toString(population.getChromosome()[i].getGenes())+" | Fitness "+ population.getChromosome()[i].decimalValueByFitness());
			}
		}
		// Method for creating decimal value 
		 public static int decimalValueIndividualChromosome (Chromosome chromosome) {
				int value = (1 * chromosome.elements_in_chromosome[0]) + (2 * chromosome.elements_in_chromosome[1]) + (4 * chromosome.elements_in_chromosome[2])
						+ (8 * chromosome.elements_in_chromosome[3]) + (16 * chromosome.elements_in_chromosome[4]) + (32 * chromosome.elements_in_chromosome[5]);
				return value;
			}

}

