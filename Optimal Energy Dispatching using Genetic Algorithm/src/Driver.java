

import java.util.Arrays;

public class Driver {
	public static double current;
	public static double fittest;
	public static String fittestFromDriver;

	public Driver(double current2) {
		Driver.current=current2;
	}

	public void initialize_driver () {
		
		Pop_2 population =  new Pop_2 (GeneticAlgorithm.POP_SIZE).initializePopulation2();
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
		
		fittest= population.getChorm2()[0].getFitness2();
		fittestFromDriver= Arrays.toString(population.getChorm2()[0].getGenes2());
		for(int i=1;i<2;i++) {

			population =  new Pop_2 (GeneticAlgorithm.POP_SIZE).initializePopulation2();
			population.sortChromosomeByFitness2();
			
			fittestFromDriver= fittestNetwork(population);
			fittest=fittestChromosome(population.getChorm2()[0].getFitness2());
			
		
			
		} 
	
		
		
	}

	private String fittestNetwork(Pop_2 population) {
		if(population.getChorm2()[0].getFitness2()<fittest) {
			
			
			fittestFromDriver= Arrays.toString(population.getChorm2()[0].getGenes2());
			
		}
	
		return fittestFromDriver;
	}

	private double fittestChromosome(double fitness) {
		if(fitness<fittest) {
			fittest=fitness;
			
		}
	
		return fittest;
		
		
	}


}
