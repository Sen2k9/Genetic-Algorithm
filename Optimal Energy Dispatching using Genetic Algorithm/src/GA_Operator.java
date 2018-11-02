




public class GA_Operator {
	//initialization
	public static final int POPULATION_SIZE = 8;
	public static final int TARGET_CHROMOSOME = 24;
	public static final int GENE_SIZE=3;
	private static final double MUTATION_RATE=0.05;
	public static final int NUMBER_OF_ELITE_CHROMOSOME=1;
	public static final int TOURNAMENT_SELECTION_SIZE=4;
	public static final int GENERATION_NUMBER=50;
	// Evaluation operator
	public Population evolve_Population(Population population) {
		return mutate_Population(crossover_Population(population));
	}
	private Population mutate_Population(Population population) {
        Population mutatePopulation= new Population(population.getChromosome().length);
		
		for(int x=0; x<population.getChromosome().length; x++) {
			mutatePopulation.getChromosome()[x]= mutation(population.getChromosome()[x]);
		}
		return mutatePopulation;
	}
	private Chromosome mutation(Chromosome chromosome) {
		Chromosome temp_mutation= new Chromosome(TARGET_CHROMOSOME);
		temp_mutation.initializeChromosome();
		for(int x=0; x<chromosome.getGenes().length; x++) {
			if(Math.random()<MUTATION_RATE) {
				temp_mutation.getGenes()[x].elements_in_genes[0]=1;
				for(int i=1;i<temp_mutation.getGenes()[x].elements_in_genes.length;i++) {
				temp_mutation.getGenes()[x].elements_in_genes[i]=(int)(Math.random()*2);
				}
				
			}
			else temp_mutation.getGenes()[x]= chromosome.getGenes()[x];
		}
		return temp_mutation;
	}
	private Population crossover_Population(Population population) {
		Population crossoverPopulation= new Population (population.getChromosome().length);
		for(int x=0; x< NUMBER_OF_ELITE_CHROMOSOME; x++)
			crossoverPopulation.getChromosome()[x]= population.getChromosome()[x];
		for(int x=NUMBER_OF_ELITE_CHROMOSOME; x<population.getChromosome().length; x++) {
			Chromosome chromosome1= selectTournamentPopulation(population).getChromosome()[0];
			
			Chromosome chromosome2= selectTournamentPopulation(population).getChromosome()[1];
			crossoverPopulation.getChromosome()[x]= crossoverChromosome(chromosome1, chromosome2);
		}
		return crossoverPopulation;
	}
	private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossoverChromosome= new Chromosome(TARGET_CHROMOSOME);
		crossoverChromosome.initializeChromosome();
		for(int x=0; x< chromosome1.getGenes().length; x++) {
			int random=(int)(1+Math.random()*TARGET_CHROMOSOME);
			for(int i=0; i<random;i++)
				crossoverChromosome.getGenes()[i]= chromosome1.getGenes()[i];
			for(int j=random; j<TARGET_CHROMOSOME;j++)
			 crossoverChromosome.getGenes()[j]= chromosome2.getGenes()[j];
			
		}
		return crossoverChromosome;
		
	}
	private Population selectTournamentPopulation(Population population) {
//		Population tournamentPopulation= new Population(TOURNAMENT_SELECTION_SIZE);
//		for(int x=0; x<TOURNAMENT_SELECTION_SIZE; x++) {
//		tournamentPopulation.getChromosome()[x]= 
//				population.getChromosome()[x];
//		}
//	
//		//tournamentPopulation.sortChromByFitness(TOURNAMENT_SELECTION_SIZE);
//		return tournamentPopulation;
		Population tournamentPopulation= new Population(TOURNAMENT_SELECTION_SIZE);
		for(int x=0; x<TOURNAMENT_SELECTION_SIZE; x++) {
		tournamentPopulation.getChromosome()[x]= 
				population.getChromosome()[(int)(Math.random()*population.getChromosome().length)];
		}
	
		tournamentPopulation.sortChromByFitness(TOURNAMENT_SELECTION_SIZE);
		return tournamentPopulation;

	}
}