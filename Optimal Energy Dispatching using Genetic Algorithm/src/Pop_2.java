


import java.util.Arrays;

public class Pop_2 {
	private chorm_2 [] chromosomes;
	
	public Pop_2(int length) {
		
		chromosomes= new chorm_2 [length];// how many chromosomes/individual will be in a population
	}
	public Pop_2 initializePopulation2() {
		
		
		
		for(int x=0;x<chromosomes.length; x++) {
			chromosomes[x]= new chorm_2 (GeneticAlgorithm.TARGET_CHROMOSOME).initializeChromosome2();
		}
		sortChromosomeByFitness2();
		return this;
	}
	
	public void sortChromosomeByFitness2() {
		
		chorm_2 [] temp = new chorm_2 [GeneticAlgorithm.POP_SIZE];
		for (int i = 0; i < chromosomes.length - 1; i++) {

			for (int j = 0; j < chromosomes.length - 1; j++) {

				if (chromosomes[j + 1].getFitness2()< chromosomes[j].getFitness2()) {
					temp[j] = chromosomes[j + 1];
					chromosomes[j + 1] = chromosomes[j];
					chromosomes[j] = temp[j];
				}
			}
		}

		
	}
	
	
	public chorm_2 [] getChorm2() {
		return chromosomes;
	}
	public void sortChromByFitness2 (int x) {
		chorm_2 [] temp = new chorm_2 [x];
		for (int i = 0; i < x - 1; i++) {

			for (int j = 0; j < x - 1; j++) {

				if (chromosomes[j + 1].getFitness2()< chromosomes[j].getFitness2()) {
					temp[j] = chromosomes[j + 1];
					chromosomes[j + 1] = chromosomes[j];
					chromosomes[j] = temp[j];
				}
			}
		}
		
	}
	
	

}
