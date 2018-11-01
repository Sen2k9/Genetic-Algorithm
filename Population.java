




public class Population  {
	//initialization
	public float[] convertion= new float[4];
	private Chromosome[] chromosomes;
	public float[] probability=new float[4];;
	public int[] Count=new int[4];

	public Population(int populationSize) {
		chromosomes = new Chromosome[populationSize];
		
	}

	public Population initializePopulation() {
		for (int x = 0; x < chromosomes.length; x++) {
			chromosomes[x] = new Chromosome(GA_Operator.TARGET_CHROMOSOME.length).initializeChromosome();
		}
		sortChromosomeByFitness();
		return this;
	}

	public Chromosome[] getChromosome() {
		return chromosomes;

	}
// Sorting individual by their fitness value
	public void sortChromosomeByFitness() {
		Chromosome[] temp = new Chromosome[GA_Operator.POPULATION_SIZE];
		for (int i = 0; i < chromosomes.length - 1; i++) {

			for (int j = 0; j < chromosomes.length - 1; j++) {

				if (chromosomes[j + 1].decimalValueByFitness() > chromosomes[j].decimalValueByFitness()) {
					temp[j] = chromosomes[j + 1];
					chromosomes[j + 1] = chromosomes[j];
					chromosomes[j] = temp[j];
				}
			}
		}

	}
	public float[] probabilityValue(Population population) {

		for (int x = 0; x < GA_Operator.POPULATION_SIZE; x++) {
			 probability[x] =  ((functionValue(population.getChromosome()[x]))
					/ (summationOfFunctionValue(population)));

			 
		}
		return probability;
	}
	//decimal value of an individual

	public int decimalValue(Chromosome chromosome) {
		int value = (1 * chromosome.elements_in_chromosome[0]) + (2 * chromosome.elements_in_chromosome[1]) + (4 * chromosome.elements_in_chromosome[2])
				+ (8 * chromosome.elements_in_chromosome[3]) + (16 * chromosome.elements_in_chromosome[4]) + (32 * chromosome.elements_in_chromosome[5]);
		return value;
	}

	public int functionValue(Chromosome chromosome) {
		return decimalValue(chromosome) * decimalValue(chromosome);
	}

	public float summationOfFunctionValue(Population population) {
		float sum = 0;
		for (int x = 0; x < GA_Operator.POPULATION_SIZE; x++) {
			sum = sum + functionValue(population.getChromosome()[x]);

		}
		
		return sum;
	}

	
	




}
