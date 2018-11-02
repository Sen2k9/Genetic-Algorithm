

import java.util.Arrays;

public class chorm_2 {
	private boolean isFitnessChanged= true;
	private double fitness=0;
	public  int[] genes;
	
	
	public chorm_2(int targetChromosome) {
		genes= new int[targetChromosome];// length of genes in a chromosome
	}
	public chorm_2 initializeChromosome2() {
		
		DAGwithConnectedComponen adjacentList= new DAGwithConnectedComponen(16);
		adjacentList.initializeDag();
		while(DAGwithConnectedComponen.count2!=14) {
			//System.out.println("Inside while loop count: "+DAGwithConnectedComponen.count2);
			DAGwithConnectedComponen.count2=0;
			
			adjacentList.initializeDag();
		}
		DAGwithConnectedComponen.count2=0;
		for(int x=0;x<genes.length;x++) {
			genes[x]=DAGwithConnectedComponen.active_lines[x];
		}
		
		
		
		return this;
	}
	public  int[] getGenes2() {
		
	
		return genes;
	}
	public double getFitness2() {
		
		if(isFitnessChanged) {
			fitness= recalculateFitness2();
			isFitnessChanged= false;
		}
	
		return fitness;
	}
	private double recalculateFitness2() {
		
		 double total_loss=0;
		for(int i=0;i<DAGwithConnectedComponen.active_lines.length;i++) {
			total_loss+= (Math.pow(Driver.current, 2)*DAGwithConnectedComponen.active_lines[i]);
			
			}// Line loss calculation
		
		return total_loss;
	}
	public String toString() {
		return Arrays.toString(this.genes);
	}

}
