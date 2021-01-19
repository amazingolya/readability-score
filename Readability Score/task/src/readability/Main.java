package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(args[0])));

            TextInfo info = TextInfo.createInfo(text);
            System.out.println(info.toString());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
            String method = scanner.next();
            ReadabilityIndexCalculator calculator = new ReadabilityIndexCalculator();
            switch (method) {
                case "ARI": {
                    double score = calculator.getAutomatedIndex(info);
                    int age = GradeLevel.getAge((int) Math.round(score));
                    System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", score, age);
                    System.out.printf("This text should be understood in average by %s year olds.", age);
                    break;
                }
                case "FK": {
                    double score = calculator.getFleschKincaidIndex(info);
                    int age = GradeLevel.getAge((int) Math.round(score));
                    System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).\n", score, age);
                    System.out.printf("This text should be understood in average by %s year olds.", age);
                    break;
                }
                case "SMOG": {
                    double score = calculator.getSMOGIndex(info);
                    int age = GradeLevel.getAge((int) Math.round(score));
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", score, age);
                    System.out.printf("This text should be understood in average by %s year olds.", age);
                    break;
                }
                case "CL": {
                    double score = calculator.getColemanLiauIndex(info);
                    int age = GradeLevel.getAge((int) Math.round(score));
                    System.out.printf("Coleman–Liau index: %.2f (about %d year olds).\n", score, age);
                    System.out.printf("This text should be understood in average by %s year olds.", age);
                    break;
                }
                case "all": {
                    double score1 = calculator.getAutomatedIndex(info);
                    int age1 = GradeLevel.getAge((int) Math.round(score1));
                    double score2 = calculator.getFleschKincaidIndex(info);
                    int age2 = GradeLevel.getAge((int) Math.round(score2));
                    double score3 = calculator.getSMOGIndex(info);
                    int age3 = GradeLevel.getAge((int) Math.round(score3));
                    double score = calculator.getColemanLiauIndex(info);
                    int age = GradeLevel.getAge((int) Math.round(score));
                    System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", score1, age1);
                    System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).\n", score2, age2);
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", score3, age3);
                    System.out.printf("Coleman–Liau index: %.2f (about %d year olds).\n", score, age);
                    System.out.printf("This text should be understood in average by %.2f year olds.", (double) (age1 + age2 + age3 + age) / 4);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read file.");
        }
    }

}
