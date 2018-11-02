

public class GeneticAlgorithm {
	public static final int POP_SIZE=4;
	public static final int TARGET_CHROMOSOME= 14;
	public static final int NUMBER_OF_ELITE_CHROMOSOME=1;
	private static final double MUTATION_RATE=0.05;
	public static final int TOURNAMENT_SELECTION_SIZE=4;
	public Pop_2 evolvePopulation(Pop_2 population) {
		return mutatePopulation(crossoverPopulation(population));
	}
	private Pop_2 crossoverPopulation(Pop_2 population) {
		
		Pop_2 crossoverPopulation= new Pop_2(population.getChorm2().length);
		for(int x=0; x< NUMBER_OF_ELITE_CHROMOSOME; x++)
			crossoverPopulation.getChorm2()[x]= population.getChorm2()[x];
		for(int x=NUMBER_OF_ELITE_CHROMOSOME; x<population.getChorm2().length; x++) {
			chorm_2 chromosome1= selectTournamentPopulation(population).getChorm2()[0];
			
			chorm_2 chromosome2= selectTournamentPopulation(population).getChorm2()[1];
			crossoverPopulation.getChorm2()[x]= crossoverChromosome(chromosome1, chromosome2);
		}
		return crossoverPopulation;
		
		
	}
	private Pop_2 mutatePopulation(Pop_2 population) {
		Pop_2 mutatePopulation= new Pop_2 (population.getChorm2().length);
		for(int x=0; x< NUMBER_OF_ELITE_CHROMOSOME; x++) {
			mutatePopulation.getChorm2()[x]= population.getChorm2()[x];
		}
		for(int x=NUMBER_OF_ELITE_CHROMOSOME; x<population.getChorm2().length; x++) {
			mutatePopulation.getChorm2()[x]= mutateChromosome(population.getChorm2()[x]);
		}
		return mutatePopulation;
	}
	private chorm_2 crossoverChromosome(chorm_2 chromosome1, chorm_2 chromosome2) {
		chorm_2 crossoverChromosome= new chorm_2(TARGET_CHROMOSOME);
		for(int x=0; x< chromosome1.getGenes2().length; x++) {
			if(x<7) crossoverChromosome.getGenes2()[x]= chromosome1.getGenes2()[x];
			else crossoverChromosome.getGenes2()[x]= chromosome2.getGenes2()[x];
			
		}
		return crossoverChromosome;
	}
	private chorm_2 mutateChromosome(chorm_2 chromosome) {
		chorm_2  mutateChromosome= new chorm_2 (TARGET_CHROMOSOME);
		for(int x=0; x<chromosome.getGenes2().length; x++) {
		
			mutateChromosome.getGenes2()[x]= chromosome.getGenes2()[x];
		}
		return mutateChromosome;
	}
	private Pop_2 selectTournamentPopulation(Pop_2 population) {

	Pop_2 tournamentPopulation= new Pop_2 (TOURNAMENT_SELECTION_SIZE);
	for(int x=0; x<TOURNAMENT_SELECTION_SIZE; x++) {
		tournamentPopulation.getChorm2()[x]= 
				population.getChorm2()[(int)(Math.random()*population.getChorm2().length)];
	}

	tournamentPopulation.sortChromByFitness2(TOURNAMENT_SELECTION_SIZE);
	return tournamentPopulation;

}
}
