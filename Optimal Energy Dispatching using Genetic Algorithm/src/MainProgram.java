
//Name: Sajib Sen
//UUID: 00658162

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainProgram  extends Application {
	
	Stage stage = new Stage();
	
	public static double[] loss_print = new double[GA_Operator.GENERATION_NUMBER];
	//array to store total loss for every generation
	public static int [] similarity = new int [24];
	//array to store similarity with the ideal source configuration for every 24 hours
	public static double similarity_per_generation[]=new double [GA_Operator.GENERATION_NUMBER];
	// array to store the percentage of similarity for every generation
	public static void main(String[] args) {
		//method to show the outputs with optimization
		networkWithoutGA();
		// Initializing population for a Generation
		// Creating object of Population and GA_Operator
		
		Population population = new Population(GA_Operator.POPULATION_SIZE).initializePopulation();
		GA_Operator geneticAlgorithm = new GA_Operator();
		
		int generationNumber = 0;
		loss_print[0]=population.getChromosome()[0].getTotalLineLoss()/1000;
		//storing loss for initial generation
		print(population, generationNumber);
		// method to output the optimized for every generation
		for (int i = 1; i < GA_Operator.GENERATION_NUMBER; i++) {
			
			population = geneticAlgorithm.evolve_Population(population);
			// evolution of population
			population.sortChromosomeByFitness();
			loss_print[i]=population.getChromosome()[0].getTotalLineLoss()/1000;
			print(population, i);
		}
		
		launch(args);
		// launching application for ploting
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		init2(stage);
		
	}

	public void init2(Stage primaryStage) {
		HBox root = new HBox();
		//one ploting
		Scene scene = new Scene(root, 1000,1000);
	// creating frame with the given size
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Generation Number");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel(" Similarity percentage (%)");
		
		LineChart<Number, Number> linechart = new LineChart<Number, Number>(xAxis, yAxis);
	// initializing linechart object
		linechart.setTitle("Similarity with Ideal switching of energy sources in per generation");
	
		XYChart.Series<Number, Number> data = new XYChart.Series<>();
	
		for(int i=0;i<GA_Operator.GENERATION_NUMBER;i++) {
	    data.getData().add(new XYChart.Data<Number, Number>(i, similarity_per_generation[i]));
		}
		//loop for getting data for every generation
		
		linechart.getData().add(data);
	
		root.getChildren().add(linechart);
		
		primaryStage.setTitle("Comparison with ideal stage in per generation");
		primaryStage.setScene(scene);
	
	primaryStage.show();
		
	}


	public void init(Stage primaryStage) {
		HBox root = new HBox();
		
		Scene scene = new Scene(root, 1000,1000);
	
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Generation Number");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Line loss(kW)");
		
		LineChart<Number, Number> linechart = new LineChart<Number, Number>(xAxis, yAxis);
		
		linechart.setTitle("Line loss for 24 hours per generation");
		
		XYChart.Series<Number, Number> data = new XYChart.Series<>();
	
		for(int i=0;i<GA_Operator.GENERATION_NUMBER;i++) {
	    data.getData().add(new XYChart.Data<Number, Number>(i, loss_print[i]));
		}
	
		
		linechart.getData().add(data);
	
		root.getChildren().add(linechart);
	
		primaryStage.setTitle("Line loss for fittest chromosome for 24 hours per generation");
		primaryStage.setScene(scene);
		
	primaryStage.show();
		
	}

	
	public static void print(Population population, int generationNumber) {
		
		double loss_value = population.getChromosome()[0].getTotalLineLoss();


		if(generationNumber==GA_Operator.GENERATION_NUMBER-1) {
			
		System.out.println("Generation # " + (generationNumber+1) + " |Fittest Chromosome :"
				/*+ Arrays.toString(population.getChromosome()[0].getGenes()) */
				+ " | Total Loss: " + loss_value / 1000+" kW");
			//" |Chromosome fitness :"+population.getChromosome()[0].choromosomeFitness()/1000000);
		System.out.println();
		System.out.println("Hours\t\tEnergy Sources Configuration\t\t\tOptimized Network");
		System.out.println("      \t\t    Hydro Wind Solar");
		}
		Scanner c= null;
		try {
			c = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\Compare_with_ideal.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}
		for (int k = 0; k < 24; k++) {
			if(generationNumber==GA_Operator.GENERATION_NUMBER-1) {
			System.out.printf("%-8d\t\t%-4s\t\t%s", k + 1, population.getChromosome()[0].getGenes()[k],
					population.getChromosome()[0].storedNetwork[k]);
			System.out.println();
			}
			String s=Arrays.toString(population.getChromosome()[0].getGenes()[k].elements_in_genes);
			
			int p= s.indexOf(",", 0);
			int q= s.indexOf(",", p+1);
			String s1=s.substring(1, p).trim();
			String s2=s.substring(p+1, q).trim();
			String s3=s.substring(q+1, s.length()-1).trim();
			
			int m= Integer.parseInt(s1);
			int n= Integer.parseInt(s2);
			int r= Integer.parseInt(s3);
			int f=c.nextInt();
			int g=c.nextInt();
			int h=c.nextInt();
	
			if(k<=6) {
				if(m==f && n==g) 
					similarity[k]=1;
				else
					similarity[k]=0;
					
				
				
			}
			else if(k>=18) {
				if(m==f && n==g) 
					similarity[k]=1;
				else
					similarity[k]=0;
					
			}
			else {
				if(m==f && n==g && r==h)
					similarity[k]=1;
				else
					similarity[k]=0;
			}
			if(generationNumber==GA_Operator.GENERATION_NUMBER-1) {
			singleLineDiagram d = new singleLineDiagram (population.getChromosome()[0].storedNetwork[k],s,k);
			}
		}
		int total=0;
		for(int j=0;j<24;j++) {
			total+=similarity[j];
		}
		similarity_per_generation[generationNumber]= (total/24.0)*100;
		

		//
	}

	public static void networkWithoutGA() {
		int[][] chromosome = new int[24][3];
		Scanner p = null;
		try {
			p = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\hourly_breakdown_sources.txt")); // text
																														// file
																														// location
		} catch (Exception e) {
			System.out.println("Could not find file");
		}

		int active_source;
		int total_power = 0;
		double total_loss = 0;
		double current = 0;
		double full_day_loss = 0;
		int resistance;
	
		
		for (int i = 0; i < 24; i++) {
			total_loss = 0;
			for (int j = 0; j < 3; j++) {
				active_source = p.nextInt();
				total_power += active_source;
				if (active_source != 0)
					chromosome[i][j] = 1;
				else
					chromosome[i][j] = 0;
			}
			current = total_power / 220.0;
			Scanner q = null;
			try {
				q = new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\Resistance_without_line.txt")); // text
																															// file
																															// location
			} catch (Exception e) {
				System.out.println("Could not find file");
			}
			for (int k = 0; k < 16; k++) {

				
				resistance = q.nextInt();
				total_loss += (Math.pow(current, 2) * resistance);
				
			} // Line loss calculation
			q.close();
			full_day_loss += total_loss;

		
		}
		p.close();
		String s= "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";
		System.out.println("Initial Energy Sources Configuration with Power Network (without optimization):  \n");
		System.out.println("Hours\t\tEnergy Sources Configuration\t\t\tNetwork without optimization");
		System.out.println("      \t\t    Hydro Wind Solar");
		for (int i = 0; i < 24; i++) {
			System.out.printf("%-8d\t\t%-4s\t\t%s", i + 1, Arrays.toString(chromosome[i]),
					s);
			System.out.println();
			
		}

		System.out.println();
		System.out.println("Total Loss(kW) without any optimization(Energy Source & Network Configuration both): " + full_day_loss / 1000+" kW");
		System.out.println();

	}


	

	

}
