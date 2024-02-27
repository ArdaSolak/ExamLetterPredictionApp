package examLetterPredictionApp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class examLetterPredictionApp_Class 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in); 	
		System.out.println("Welcome to exam letter grade prediction application.\n");
		System.out.println("******************************************************************\n");
		System.out.println("This application calculates the average and standard deviation of the student grades entered into the application\n" +
        "in line with the number of students taking the exam (if all of them are entered, using the entered grades;\n" +
        "if there are not entered all grades, using the data set formed by randomly distributing the unentered grades\n" +
        "with the Chebyshev rule.)for Management Information and Industrial Engineering students in the 1st, 2nd, 3rd, and 4th grades.\n"+
        "Based on this information, it calculates the letter grade corresponding to the student's exam grade and shares the statistical information about the exam.\n");        
		
		System.out.println("Please enter your Name");
		String name = input.nextLine();
		
		System.out.println("Please enter your Surname");
		String surname = input.nextLine();					
		
		System.out.println("Please select your Department betweem these options - Management Information Systems : 1 , Industrial Engineering : 2" + " Please enter the number.");	
		int department = input.nextInt();
		for(int i = 0; i<100; i++) // For loop for checking valid number for department choosing.
		{						
			if (department != 1 && department != 2 ) 		
			{			
				System.out.println("Please re-enter the valid number from these options( 1 or 2) for the question.");
				department = input.nextInt();			
			}
			else 
				break;				
		}
		
		String departmentInString = "";		
		switch(department) 
		{
		case 1:
			departmentInString = "Management Information Systems";
			break;
		case 2:
		    departmentInString = "Industrial Engineering";
		break;
		}
		
		System.out.println("Please enter your Class year (Your class year can be between 1 up to 4).");
		int classYear = input.nextInt();
		for(int i = 0; i<100; i++) // For loop for checking valid number for class year.
		{									
			if (classYear!=1 && classYear!=2 && classYear!=3 && classYear!=4) 		
			{			
				System.out.println("Please re-enter the valid number from these options( 1,2,3 and 4) for the question.");
				classYear = input.nextInt();
			}
			else 
				break;				
		}		
		
		System.out.println("Please enter your course code from these options - CSE1143 , FNCE2003 , PYHS1415, IE1001, MIS2111, MIS2113, MRK2023, STAT2011");	
		String courseCode = input.next();
		int j = 0;
		while (j < 100) // While loop for checking valid enter for course code.
		{
		    if (!courseCode.equals("CSE1143") && !courseCode.equals("FNCE2003") && !courseCode.equals("PYHS1415") && !courseCode.equals("IE1001")&& !courseCode.equals("MIS2111")&& !courseCode.equals("MIS2113")&& !courseCode.equals("MRK2023")&& !courseCode.equals("STAT2011")) 
		    {
		        System.out.println("Please re-enter the valid number from these options (CSE1143 , FNCE2003 , PYHS1415, IE1001, MIS2111, MIS2113, MRK2023, STAT2011).");
		        courseCode = input.next();
		        j++;
		    } 
		    else 
		        j=100;
		}		
		
		System.out.println("Please enter the number of students taking exam.");
		int numStudTakingExam = input.nextInt();
		int[] studNumArr = new int[numStudTakingExam]; // it creates an array with as many elements as the number of students taking exam.
		
		System.out.println("Please enter the estimate grades by students taking from the exam, respectively.Don't forget that every student must get grade between (0-100). ");
		System.out.println("If you will not enter the estimate grades for all of the students taking exam, please enter 999 after the last grade entering.");		
		System.out.println("The mean of 999 is that at the end of the entering grades for students taking the exam and rest of grades will be set randomly with using Chebyshev rule.\n");
		System.out.println("!! It is recommended that at least half of the class size be entered into the system for more accurate results. !!\n");
		
		Random random = new Random();  // We created an object named random from the random class. Since we will randomly assign the grades that were not entered using Chebyshev's rule.
		
		int totalGradesEntered = 0;   
		int enterForGrade = 0;          // We defined all the variables we will use in the calculation outside the loop and gave them the value 0.
		double averageForEntered = 0;   // if we had defined them inside the loop, they would be redefined and assigned the value 0 each time.
		double entries = 0;
		double stdDeviationForEntered = 0;
		double difference = 0;
		double sumSquaredDifferences = 0;
		double k = 0;
		double meanSquaredDifference = 0;
		
		for(int r=0; r < studNumArr.length; r++)  // A for loop to write the number of students before each entry.
		{			
			System.out.println((r+1) +".Student: ");
			enterForGrade = input.nextInt();
			
			while(enterForGrade>100 || enterForGrade<0) // While loop for checking valid number for exam grade.
			{							
				if(enterForGrade == 999) // The if block ensures that the number 999 is kept out of control via exit from the loop because it is used to randomly assign numbers when entered.
				{
					break;
				}
				System.out.println("Please enter the valid grade.(YOUR EXAM GRADE MUST BE BETWEEN 0-100");
				System.out.println((r+1) +".Student: ");
				enterForGrade = input.nextInt();
			}			
			// Chebyshev's rule is a statistical rule that measures how dispersed a data set is. This rule is used to determine how far the values in any data set spread around the mean.
			if(enterForGrade == 999) // "The mean of 999 is that at the end of the entering grades for students taking the exam and rest of grades will be set randomly with using Chebyshev rule.
			{				         // so this if block consist of the calculations that using Chebyshev rule.
				totalGradesEntered = r;
				double percentageEntered = (double)totalGradesEntered/studNumArr.length;
				k = Math.sqrt(1.0/1.0-percentageEntered);		// formula of k is : 1-(1/k(square))= %x (percentage of entries in the student number taking exam)
				
				for(int y =0; y <= r; y++)
				{					
					entries += studNumArr[y];	
					averageForEntered=((entries / y+1))-1;				
				}
				for(int p =0; p < r; p++)
				{														        			        
			        difference += studNumArr[p] - averageForEntered;
			        sumSquaredDifferences += difference * difference;
			        meanSquaredDifference = sumSquaredDifferences / p+1;
			        stdDeviationForEntered = Math.sqrt(meanSquaredDifference);								
				}          
				
				double upperLimit = averageForEntered + k*stdDeviationForEntered;  // lower and upper limits determine the lower and upper limits of the numbers to be randomly assigned.
				double lowerLimit = averageForEntered - k*stdDeviationForEntered; 
				
				if(upperLimit > 100) 
				{
					upperLimit = 100;
				}
				if(lowerLimit < 0) 
				{
					lowerLimit = 0;
				}
				for(int h = r; h< studNumArr.length; h++) 
				{
					studNumArr[h] = random.nextInt((int) (upperLimit - lowerLimit + 1)) + (int) lowerLimit;
					
		        }					
				
				System.out.println("Because of the entering 999, remaining students' grades were randomly set as following below wtih using Chebyshev rule.");
				
				for(int i=0; i< studNumArr.length; i++) 
				{			

					System.out.println((i+1) +".Student: " + studNumArr[i]+"\n");					
				}	
			    
				System.out.println("Upper Limit to set random grades: "+upperLimit);
			    System.out.println("Lower Limit to set random grades: "+lowerLimit);
				System.out.println("Percentage of Enterings: "+"%"+percentageEntered*100);
				System.out.println("Total Enterings: "+entries);
				System.out.println("Average for entered grades: "+averageForEntered);
				System.out.println("Standart Deviation for entered grades: "+stdDeviationForEntered);
				System.out.println("k(multiple with standart deviation for entered grades):"+k);
				System.out.println("------------------------------------------------");		
				break;	
			}	
		    studNumArr[r]= enterForGrade;
		}						        
	    System.out.println("Please re-enter your exam's grade from entered grades.(DO NOT FORGET AGAIN, YOUR EXAM GRADE MUST BE BETWEEN 0-100");
	    System.out.println("Your exam grade: \n");
	    int yourGrade = input.nextInt();		
		for(int i = 0; i<100; i++) //  For loop for checking valid number for exam grade.
		{				
			if (yourGrade>100 || yourGrade<0) 		
			{			
				System.out.println("Please enter the valid grade for the first exam.(YOUR EXAM GRADE MUST BE BETWEEN 0-100");
				System.out.println("Your exam grade: ");
				yourGrade = input.nextInt();
			}
			else  
				break;					
		}
		
		Arrays.sort(studNumArr);  // Arrays.sort() is a method that allows us to sort the elements of the array from smallest to largest.
		
		for (int i = 0; i < studNumArr.length / 2; i++) // For Loop for to sort the elements of the array from largest to smallest with reverse the array.
		{
            int temp = studNumArr[i];
            studNumArr[i] = studNumArr[studNumArr.length - 1 - i];
            studNumArr[studNumArr.length - 1 - i] = temp;
        }
		
		int rank = 0;
		for(int i = 0; i<studNumArr.length;i++) // For Loop that finds the student's rank.
		{
			if(studNumArr[i] == yourGrade) 
			{
				rank = i+1; // i+1 becasuse highest grade is not 0.rank, it is first. 
			}
		}
		
		boolean isPassed= false;
		if(LetterGradeForCourses(yourGrade).equals("AA") || LetterGradeForCourses(yourGrade).equals("BA") || LetterGradeForCourses(yourGrade).equals("BB") || LetterGradeForCourses(yourGrade).equals("CB") || LetterGradeForCourses(yourGrade).equals("CC") || LetterGradeForCourses(yourGrade).equals("DC"))
		{	
			isPassed = true;
		}		
		Report(name, surname, departmentInString, classYear, courseCode, yourGrade, averageCalculation(studNumArr), standardDeviation(studNumArr, averageCalculation(studNumArr)), LetterGradeForCourses(yourGrade), rank, numStudTakingExam);
		System.out.println("Statistics about letter grades\n"); 
	    StatisticsOfLetterGrades(studNumArr,isPassed);
	    input.close();
	}    	

	// METHODS
    
    public static double averageCalculation(int[]arr) // Method for calculation of average.
	{
		int sum = 0;
        for (int value : arr) 
        {
            sum += value;
        }
        return (double)sum / arr.length;
	}	
	public static double standardDeviation(int[]arr,double average) // Method for calculation of standart deviation.
	{
		double sumSquaredDifferences = 0;
        for (int value : arr) 
        {
            double difference = value - average;
            sumSquaredDifferences += difference * difference;
        }
        double meanSquaredDifference = sumSquaredDifferences / arr.length;
        return Math.sqrt(meanSquaredDifference);
	}	
	public static String LetterGradeForCourses(int yourGrade) // Using catalog system to decieding to latter grade.
	{		
		String letterGrade ;
		if(yourGrade >= 90 && yourGrade <= 100)				
		{			
			letterGrade = "AA";	
			return letterGrade ;
		}
		else if (yourGrade >= 80 && yourGrade <= 89) 
		{
			letterGrade = "BA";	
			return letterGrade ;
		}
		else if (yourGrade >= 75 && yourGrade <= 79) 
		{
			letterGrade = "BB";	
			return letterGrade ;
		}
		else if (yourGrade >= 70 && yourGrade <= 74) 
		{
			letterGrade = "CB";	
			return letterGrade ;
		}	
		else if (yourGrade >= 60 && yourGrade <= 69) 
		{
			letterGrade = "CC";	
			return letterGrade ;
		}	
		else if (yourGrade >= 50 && yourGrade <= 59) 
		{
			letterGrade = "DC";	
			return letterGrade ;
		}	
		else if (yourGrade >= 40 && yourGrade <= 49) 
		{
			letterGrade = "DD";	
			return letterGrade ;
		}	
		else if (yourGrade >= 30 && yourGrade <= 39) 
		{
			letterGrade = "FD";	
			return letterGrade ;
		}	
		else 
		{
			letterGrade = "FF";	
			return letterGrade ;
		}
	}
	
	public static void StatisticsOfLetterGrades(int[]arr,boolean isPassed)  // Method that gives us statistics about course exam result.
	{
		double numberOfAA = 0;
		double numberOfBA = 0;
		double numberOfBB = 0;
		double numberOfCB = 0;
		double numberOfCC = 0;
		double numberOfDC = 0;
		double numberOfDD = 0;
		double numberOfFD = 0;
		double numberOfFF = 0;
		for( int i=0; i<arr.length; i++) 
		{
			if(arr[i]>= 90 && arr[i] <= 100) 
				numberOfAA += 1;
			if(arr[i]>= 80 && arr[i] <= 89) 
				numberOfBA += 1;
			if(arr[i]>= 75 && arr[i] <= 79) 
			    numberOfBB += 1;
			if(arr[i]>= 70 && arr[i] <= 74) 
				numberOfCB += 1;
			if(arr[i]>= 60 && arr[i] <= 69) 
				numberOfCC += 1;
			if(arr[i]>= 50 && arr[i] <= 59) 
				numberOfDC += 1;
			if(arr[i]>= 40 && arr[i] <= 49) 
				numberOfDD += 1;
			if(arr[i]>= 30 && arr[i] <= 39) 
				numberOfFD += 1;
			if(arr[i]>= 0 && arr[i] <= 29) 
				numberOfFF += 1;
		}
		double percentageAA = (numberOfAA/arr.length)*100;
		double percentageBA = (numberOfBA/arr.length)*100;
		double percentageBB = (numberOfBB/arr.length)*100;
		double percentageCB = (numberOfCB/arr.length)*100;
		double percentageCC = (numberOfCC/arr.length)*100;
		double percentageDC = (numberOfDC/arr.length)*100;
		double percentageDD = (numberOfDD/arr.length)*100;
		double percentageFD = (numberOfFD/arr.length)*100;
		double percentageFF = (numberOfFF/arr.length)*100;
		
		double totalPassed = numberOfAA +numberOfBA +numberOfBB +numberOfCB +numberOfCC +numberOfDC;
		double percentagePassed = ((numberOfAA +numberOfBA +numberOfBB +numberOfCB +numberOfCC +numberOfDC)/arr.length)*100;
		
		System.out.println("Number of Student who gets AA: "+ numberOfAA + " ,Percentage of students who gets AA: " + "%"+percentageAA);
		System.out.println("Number of Student who gets BA: "+ numberOfBA + " ,Percentage of students who gets AA: " + "%"+percentageBA);
		System.out.println("Number of Student who gets BB: "+ numberOfBB + " ,Percentage of students who gets AA: " + "%"+percentageBB);
		System.out.println("Number of Student who gets CB: "+ numberOfCB + " ,Percentage of students who gets AA: " + "%"+percentageCB);
		System.out.println("Number of Student who gets CC: "+ numberOfCC + " ,Percentage of students who gets AA: " + "%"+percentageCC);
		System.out.println("Number of Student who gets DC: "+ numberOfDC + " ,Percentage of students who gets AA: " + "%"+percentageDC);
		System.out.println("*********************************************************************************************************");
		System.out.println("Number of Student who gets DD: "+ numberOfDD + " ,Percentage of students who gets AA: " + "%"+percentageDD);
		System.out.println("Number of Student who gets FD: "+ numberOfFD + " ,Percentage of students who gets AA: " + "%"+percentageFD);
		System.out.println("Number of Student who gets FF: "+ numberOfFF + " ,Percentage of students who gets AA: " + "%"+percentageFF);	
		System.out.println("Number of student passed the course: " + totalPassed);
		System.out.println("Number of student can not passed the course: " + (arr.length- totalPassed));
		System.out.println("Percentage of students who passed the course: " +"%" + percentagePassed );
		System.out.println("Percentage of students who fail the course: " +"%" + (100-percentagePassed) );
		System.out.println("Is student passed the course: " + isPassed);
	}
	
	// Below Method report all the statistic and information about application.  
	public static void Report(String name, String surname,String departmentInString,int classYear, String courseCode,int yourGrade,double averageCalculation ,double standartDeviation, String latterGradeForCourses, int rank,int numStudTakingExam ) 
	{
		System.out.println("-----Statistic about Student and Course-----\n");
		System.out.println("Name:"+name);
		System.out.println("Surname:"+surname);
		System.out.println("Department: "+departmentInString);
		System.out.println("Class Year: "+classYear);
		System.out.println("Course Code: "+ courseCode);
		System.out.println("Grade: "+ yourGrade);
		System.out.println("Average of the grades: "+ averageCalculation);
		System.out.println("Standart Deviation of the grades: "+ standartDeviation);
		System.out.println("Letter Grade For Course: "+ latterGradeForCourses);
		System.out.println("Rank: " + rank+". student who has highest grade among "+numStudTakingExam+" students.\n");		
	}

}
