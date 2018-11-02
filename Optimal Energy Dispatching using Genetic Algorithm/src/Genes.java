import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Genes {
	public String fittestNetwork = " ";
	
	public int[] elements_in_genes;

	// Constructor
	public Genes(int gene_size) {
		elements_in_genes = new int[gene_size];

	}

	public Driver driver;

	// creating individual
	public Genes initializeGenes() {

		for (int x = 0; x < elements_in_genes.length; x++) {
			int random = (int) (Math.random() * 2);
			// if (random==1)// for random value greater than 0.5 take 1 otherwise 0
			elements_in_genes[x] = random;
			
		}
		return this;
	}

	public int geneEquivalentDecimal() {
		int decimal;
		decimal = this.elements_in_genes[0] * 4 + this.elements_in_genes[1] * 2 + this.elements_in_genes[2] * 1;
		return decimal;

	}

	// get method
	public int[] getElements() {
		// is_fitness_Changed=true;
		return elements_in_genes;
	}

	// toString method
	public String toString() {
		return Arrays.toString(this.elements_in_genes);
	}

	public int calculatePower(Scanner x) {

		int total_power = 0;

		for (int i = 0; i < elements_in_genes.length; i++) {
			int source = x.nextInt();
			if (elements_in_genes[i] == 1)
				total_power += source;

		}
		return total_power;
	}

	public int hydrofitness(Scanner x) {
		int hydro_power = 0;
		int source = x.nextInt();
		if (elements_in_genes[0] == 1)
			hydro_power  = source;
	
		for (int i = 1; i < elements_in_genes.length; i++) {
			 source = x.nextInt();
			

		}
		return hydro_power ;
	}
	public int solarfitness(Scanner x) {
		int solar_power = 0;
		int source;
		for (int i = 1; i < elements_in_genes.length; i++) {
			 source = x.nextInt();
			

		}
		 source = x.nextInt();
		if (elements_in_genes[2] == 1)
			solar_power  = source;
		
		return solar_power ;
	}
	public int windfitness(Scanner x) {
		int wind_power = 0;
		
		int source;
		
			 source = x.nextInt();
			 source = x.nextInt();

			 if (elements_in_genes[1] == 1)
					wind_power  = source;
		
		 source = x.nextInt();
		
		
		return wind_power ;
	}

	public double calculateLineLoss(Scanner x) {
		double total_loss = 0;
		int total_power = 0;

		for (int i = 0; i < elements_in_genes.length; i++) {
			int source = x.nextInt();
			if (elements_in_genes[i] == 1)
				total_power += source;
		}
		double current = total_power / 220.0;
		driver = new Driver(current);
		driver.initialize_driver();
		
		total_loss = Driver.fittest;
		fittestNetwork = Driver.fittestFromDriver;
		
		return total_loss;
	}

}
