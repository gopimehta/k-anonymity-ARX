
package org.deidentifier.arx.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.deidentifier.arx.ARXAnonymizer;
import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataSource;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.AttributeType.Hierarchy;
import org.deidentifier.arx.criteria.KAnonymity;


public class AdultDataAnonymization extends BaseClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        adultData();
    }

    
    private static void adultData() throws IOException {
        
        // Define configuration for CSV file
    	DataSource source = DataSource.createCSVSource("data/adult.csv", StandardCharsets.UTF_8, ';', true);
        
    	// Adding columns with their data types
        source.addColumn("sex", DataType.STRING); //column name based referencing
        source.addColumn(1, DataType.INTEGER); //column index based referencing
        source.addColumn("race", DataType.STRING);
        source.addColumn(3, DataType.STRING);
        source.addColumn("education", DataType.STRING);
        source.addColumn(5, DataType.STRING);
        source.addColumn("workclass", DataType.STRING);
        source.addColumn(7, DataType.STRING);
        source.addColumn("salary-class", "salaryClass", DataType.STRING); //renaming a particular column
        
        // Create data object
        Data data = Data.create(source);
        
        // Print to console the unanonymized data
        print(data.getHandle());
        System.out.println("\n");
        
        // Loading already defined hierarchies from CSV file for attributes
        data.getDefinition().setAttributeType("sex", Hierarchy.create("data/adult_hierarchy_sex.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("age", Hierarchy.create("data/adult_hierarchy_age.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("race", Hierarchy.create("data/adult_hierarchy_race.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("marital-status", Hierarchy.create("data/adult_hierarchy_marital-status.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("education", Hierarchy.create("data/adult_hierarchy_education.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("native-country", Hierarchy.create("data/adult_hierarchy_native-country.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("workclass", Hierarchy.create("data/adult_hierarchy_workclass.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("occupation", Hierarchy.create("data/adult_hierarchy_occupation.csv", StandardCharsets.UTF_8, ';'));
        data.getDefinition().setAttributeType("salaryClass", Hierarchy.create("data/adult_hierarchy_salary-class.csv", StandardCharsets.UTF_8, ';'));
        
        // Create an instance of the anonymizer
        ARXAnonymizer anonymizer = new ARXAnonymizer();
        
        // Execute the privacy algorithm
        ARXConfiguration config = ARXConfiguration.create();
        config.addPrivacyModel(new KAnonymity(5));  //setting k = 5
        config.setSuppressionLimit(0d); //setting suppression limit as 0d
        ARXResult result = anonymizer.anonymize(data, config);
        
        // Print info
        printResult(result, data);
        
        
        // Write results to external file
        System.out.print(" - Writing data...");
        result.getOutput(false).save("data/adult_anonymized_data.csv", ';');
        
        //Accessing the newly created csv file inorder to show in the terminal
        DataSource finalOutput = DataSource.createCSVSource("data/adult_anonymized_data.csv", StandardCharsets.UTF_8, ';', true);
        
        //Adding columns
        finalOutput.addColumn("sex"); //column name based referencing
        finalOutput.addColumn(1); //column index based referencing
        finalOutput.addColumn("race"); 
        finalOutput.addColumn(3);
        finalOutput.addColumn("education");
        finalOutput.addColumn(5);
        finalOutput.addColumn("workclass");
        finalOutput.addColumn(7);
        finalOutput.addColumn("salaryClass"); //renamed
        
        Data output = Data.create(finalOutput);
        
        //Print to console the anonymized data
        print(output.getHandle());
        System.out.println("\n");
        
        System.out.println("Done!");

    }

    }
