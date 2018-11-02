
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Chromosome {
	public String[] storedNetwork = new String[24];
	public static double paraControl = 1;

	public int[] elements_in_chromosome;
	public Scanner x = null;
	public Scanner d = null;
	private boolean is_fitness_Changed = true;
	private int fitness_value = 0;
	public Genes[] Genes;

	// Constructor
	public Chromosome(int chromosomeSize) {
		Genes = new Genes[chromosomeSize];

	}

	// creating individual
	public Chromosome initializeChromosome() {
		elements_in_chromosome = new int[Genes.length];

		for (int x = 0; x < Genes.length; x++) {
			
			Genes[x] = new Genes(3).initializeGenes();
			elements_in_chromosome[x] = Genes[x].geneEquivalentDecimal();

		}
		return this;
	}

	// get method
	public Genes[] getGenes() {
		is_fitness_Changed = true;
		return Genes;
	}

	private int[] getIndividualGenesPower() {
		int[] total_power = new int[24];

		try {
			x = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\hourly_breakdown_sources.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}

		for (int i = 0; i < Genes.length; i++) {
			

			int exist_source = this.Genes[i].calculatePower(x);
			total_power[i] = exist_source;

		}

		x.close();
		return total_power;
	}

	private int[] getIndividualHydroFitness() {
		int[] hydro_power = new int[24];

		try {
			x = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\hourly_breakdown_sources.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}

		for (int i = 0; i < Genes.length; i++) {
			

			int exist_source = this.Genes[i].hydrofitness(x);
			
			hydro_power[i] = exist_source;

		}

		x.close();
		return hydro_power;
	}

	private int[] getIndividualSolarFitness() {
		int[] solar_power = new int[24];

		try {
			x = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\hourly_breakdown_sources.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}

		for (int i = 0; i < Genes.length; i++) {
			

			int exist_source = this.Genes[i].solarfitness(x);
		
			solar_power[i] = exist_source;

		}

		x.close();
		return solar_power;
	}

	private int[] getIndividualWindFitness() {
		int[] wind_power = new int[24];

		try {
			x = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\hourly_breakdown_sources.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}

		for (int i = 0; i < Genes.length; i++) {
			

			int exist_source = this.Genes[i].windfitness(x);
			// System.out.println("Hour "+i+" Hydro power "+exist_source);
			wind_power[i] = exist_source;

		}

		x.close();
		return wind_power;
	}

	public double choromosomeFitness() {
		double[] total_fitness = new double[24];
		double count = 0;
		total_fitness = allIndividualGeneFitness();
		for (int i = 0; i < total_fitness.length; i++) {
			count = count + total_fitness[i];
		}
		return count;
	}

	private double[] allIndividualGeneFitness() {

		double[] individualGeneFitness = new double[24];

		try {
			d = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\Total_daily_load_demand.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}
		double[] fitness2 = new double[24];
		double[] fitness1 = new double[24];
		double[] fitnessHydro = new double[24];
		double[] fitnessSolar = new double[24];
		

		int[] individual_power = new int[24];
		double[] individual_lineLoss = new double[24];
		int[] individual_hydroPower = new int[24];
		int[] individual_SolarPower = new int[24];
		int[] individual_WindPower = new int[24];

		individual_lineLoss = getIndividualLineLoss();
		individual_power = getIndividualGenesPower();
		individual_hydroPower = getIndividualHydroFitness();
		individual_SolarPower = getIndividualSolarFitness();
		individual_WindPower = getIndividualWindFitness();

		for (int i = 0; i < 24; i++) {
			int hourly_demand = d.nextInt();
			
			if (this.Genes[i].elements_in_genes[0] == 0 && this.Genes[i].elements_in_genes[1] == 0
					&& this.Genes[i].elements_in_genes[2] == 0) {
				
				fitness2[i] = 1000;// penalty
				fitnessHydro[i] = 1;

				fitnessSolar[i] = 1;
				fitness1[i] = 100*individual_lineLoss[i]; // line loss
			}

			

			else {
				
				if ((individual_power[i] < hourly_demand)) {
					fitness2[i] = 1000;
					fitnessHydro[i] = 1;

					fitnessSolar[i] = 1;
					fitness1[i] = 100*individual_lineLoss[i]; // line loss
				} else {
					fitness1[i] = individual_lineLoss[i]; // line loss
					fitness2[i] = 1;
					fitnessHydro[i] = 1;

					fitnessSolar[i] = 1;
					// individual_hydroPower[i]< hourly_demand &&
					if ((individual_hydroPower[i] >= hourly_demand) && (this.Genes[i].elements_in_genes[0] == 1)
							&& (this.Genes[i].elements_in_genes[2] == 0) && (this.Genes[i].elements_in_genes[1] == 0)) {
						fitnessHydro[i] = 0.01;// reward

						fitnessSolar[i] = 1;
					}

					else if (((individual_hydroPower[i] + individual_WindPower[i] >= hourly_demand )
							&& (this.Genes[i].elements_in_genes[0] == 1)&& (this.Genes[i].elements_in_genes[2] == 0))
							) {
						
						fitnessHydro[i] = 0.01;// reward

						fitnessSolar[i] = 1;

					}
					else if((individual_SolarPower[i] >= hourly_demand
							&& individual_hydroPower[i] <= hourly_demand)) {
						fitnessHydro[i] = 0.01;// reward

						fitnessSolar[i] = 1;
					}
					
					else {
						fitnessHydro[i] = 1;// reward

						fitnessSolar[i] = 1;// reward
						
					}
					
				}
			}
			
			// System.out.println("fitness1: "+i+" "+fitness1[i]);
			individualGeneFitness[i] = fitness1[i] * fitness2[i] * fitnessHydro[i] * fitnessSolar[i];
		}

		d.close();

		return individualGeneFitness;

	}

	public double getTotalLineLoss() {
		double total_lineloss = 0;
		double[] total_loss = new double[24];
		total_loss = getIndividualLineLoss();
		for (int i = 0; i < 24; i++) {
			total_lineloss += total_loss[i];
		}
		return total_lineloss;
	}

	private double[] getIndividualLineLoss() {// we have to minimize using re-configuration method
		double[] total_loss = new double[24];
		int[] individual_power = new int[24];
		individual_power = getIndividualGenesPower();
		try {
			x = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\hourly_breakdown_sources.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}
//		
		for (int i = 0; i < Genes.length; i++) {
			

			double exist_source = this.Genes[i].calculateLineLoss(x); // need to apply re-configuration method
			//if (individual_power[i] >= hourly_demand) {
				total_loss[i] = exist_source;
			//} else {
				//total_loss[i] = 1000 * exist_source; // penalty
			//}

			storedNetwork[i] = this.Genes[i].fittestNetwork;
			

		}
	

		x.close();
		return total_loss;
	}

	// toString method
	public String toString() {
		return Arrays.toString(this.Genes);
	}

}