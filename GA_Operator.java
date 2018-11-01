

public class GA_Operator {
	//initialization
	public static final int POPULATION_SIZE = 20;
	public static final int[] TARGET_CHROMOSOME = { 1, 1, 1, 1, 1, 1 };
	private static final double MUTATION_RATE=0.05;
	public static final int GENERATION_NUMBER=50;
	// Evaluation operator
	public  Population evolution_Operator(Population population) {
		return mutate_Operator(crossover_Operator(population));
	}
	

	public int decimalValue(Chromosome chromosome) {
		int value = (1 * chromosome.elements_in_chromosome[0]) + (2 * chromosome.elements_in_chromosome[1]) + (4 * chromosome.elements_in_chromosome[2])
				+ (8 * chromosome.elements_in_chromosome[3]) + (16 * chromosome.elements_in_chromosome[4]) + (32 * chromosome.elements_in_chromosome[5]);
		return value;
	}
	// Roulette_wheel selection method
	public Chromosome selection_operator(Population population) {
		//population.initializePopulation();
		float rand = (float) (Math.random()*population.summationOfFunctionValue(population));
		float c_sum=0;
		for(int i=0;i<POPULATION_SIZE; i++) 
		{
			
			float fi= decimalValue(population.getChromosome()[i])*decimalValue(population.getChromosome()[i]);
			
			while(c_sum<=rand) {
				
				if((c_sum+fi)>=rand)
					{ return population.getChromosome()[i];}
				else
					{c_sum+=fi;}
			}
			
		}
		return null;
		
		
	}
	
//crossover method
	private Population crossover_Operator(Population population) {
		Population crossoverPopulation= new Population(GA_Operator.POPULATION_SIZE).initializePopulation();
		for(int i=0; i<GA_Operator.POPULATION_SIZE; i++)
		{crossoverPopulation.getChromosome()[i].initializeChromosome();}
		Chromosome chromosome= new Chromosome(GA_Operator.TARGET_CHROMOSOME.length).initializeChromosome();
		Chromosome chromosome2= new Chromosome(GA_Operator.TARGET_CHROMOSOME.length).initializeChromosome();
		for(int x=0; x<GA_Operator.POPULATION_SIZE; x++) {
			 chromosome= selection_operator(population);
			 chromosome2= selection_operator(population);
			crossoverPopulation.getChromosome()[x]= crossoverChromosome(chromosome, chromosome2);
		}
		return crossoverPopulation;
	}
	private Chromosome crossoverChromosome(Chromosome chromosome, Chromosome chromosome2) {
		Chromosome crossoverChromosome= new Chromosome(TARGET_CHROMOSOME.length);
		crossoverChromosome.initializeChromosome();
		for(int x=0; x< chromosome.getGenes().length; x++) {
			int random=(int)(Math.random()*5+1);
			for(int i=0; i<random;i++)
				crossoverChromosome.getGenes()[i]= chromosome.getGenes()[i];
			for(int j=random; j<6;j++)
			 crossoverChromosome.getGenes()[j]= chromosome2.getGenes()[j];
			
		}
		return crossoverChromosome;
	}
	//Mutation method
	private Population mutate_Operator(Population population) {
		Population mutatePopulation= new Population(population.getChromosome().length);
		
		for(int x=0; x<population.getChromosome().length; x++) {
			mutatePopulation.getChromosome()[x]= mutation(population.getChromosome()[x]);
		}
		return mutatePopulation;
	}
	private Chromosome mutation(Chromosome chromosome) {
		Chromosome temp_mutation= new Chromosome(TARGET_CHROMOSOME.length);
		for(int x=0; x<chromosome.getGenes().length; x++) {
			if(Math.random()<MUTATION_RATE) {
				if(Math.random()<0.5) temp_mutation.getGenes()[x]=1;
				else temp_mutation.getGenes()[x]=0;
			}
			else temp_mutation.getGenes()[x]= chromosome.getGenes()[x];
		}
		return temp_mutation;
	}

}
