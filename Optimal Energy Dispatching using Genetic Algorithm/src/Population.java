



public class Population  {
	//initialization
	
	private Chromosome[] chromosomes;
	

	public Population(int populationSize) {
		chromosomes = new Chromosome[populationSize];
		
	}

	public Population initializePopulation() {
		for (int x = 0; x < chromosomes.length; x++) {
			chromosomes[x] = new Chromosome(24).initializeChromosome();
		}
		sortChromosomeByFitness();
		return this;
	}

	public Chromosome[] getChromosome() {
		return chromosomes;

	}
	public void sortChromosomeByFitness() {
		Chromosome[] temp = new Chromosome[GA_Operator.POPULATION_SIZE];
		for (int i = 0; i < chromosomes.length - 1; i++) {

			for (int j = 0; j < chromosomes.length - 1; j++) {

				if (chromosomes[j + 1].choromosomeFitness() < chromosomes[j].choromosomeFitness()) {
					temp[j] = chromosomes[j + 1];
					chromosomes[j + 1] = chromosomes[j];
					chromosomes[j] = temp[j];
				}
			}
		}

	}
	public void	sortChromByFitness(int x) {
		Chromosome[] temp = new Chromosome[x];
		for (int i = 0; i < x - 1; i++) {

			for (int j = 0; j < x - 1; j++) {

				if (chromosomes[j + 1].choromosomeFitness()< chromosomes[j].choromosomeFitness()) {
					temp[j] = chromosomes[j + 1];
					chromosomes[j + 1] = chromosomes[j];
					chromosomes[j] = temp[j];
				}
			}
		}
		
	}
}
