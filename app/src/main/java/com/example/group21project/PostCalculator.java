package com.example.group21project;

public class PostCalculator {

    // Define constants for grade thresholds
    private static final double GRADE_A_MINUS = 3.7; // Assuming a 4.0 scale
    private static final double GRADE_B = 3.0;
    private static final double GRADE_C_MINUS = 1.7;
    private static final double GPA_THRESHOLD = 2.5;
    private static final int CREDITS_REQUIRED = 4;
    private static final double GPA_PASS = 0.7;


    public boolean isEligible(double mata37, double mata31, double mata22, double mata67, double cscA48, double cscA08, int creditsCompleted, boolean isInAdmissionCategory, String programType) {
        // Check for eligibility for minor
        if (programType.equalsIgnoreCase("minor")) {
            return isEligibleForMinor(creditsCompleted, cscA48, mata22, mata67, cscA08, mata31);
        }
        // Check for eligibility for major/specialist
        else if (programType.equalsIgnoreCase("specialist/major")) {
            return isEligibleForMajorSpecialist(cscA08, mata37, mata31, mata22, mata67, cscA48, isInAdmissionCategory);
        }

        return false;
    }

    private boolean isEligibleForMinor(int creditsCompleted, double cscA48, double mata22, double mata67, double csca08, double mata31) {
        // Criteria for minor
        return creditsCompleted >= CREDITS_REQUIRED && cscA48 >= GPA_PASS && csca08 >= GPA_PASS && (mata67 >= GPA_PASS || mata22 >= GPA_PASS || mata31 >= GPA_PASS);
    }

    private boolean isEligibleForMajorSpecialist(double csca08, double mata37, double mata31, double mata22, double mata67, double cscA48, boolean isInAdmissionCategory) {
        boolean aboveThreshold;
        if(isInAdmissionCategory){
            aboveThreshold = calculateGPA(mata37, mata31, mata22, mata67, cscA48) >= GPA_THRESHOLD;
            boolean req31 = (mata67 >= GRADE_C_MINUS && mata22 >= GRADE_C_MINUS);
            boolean req32 = (mata67 >= GRADE_C_MINUS && mata37 >= GRADE_C_MINUS);
            boolean req33 = (mata22 >= GRADE_C_MINUS && mata37 >= GRADE_C_MINUS);
            return aboveThreshold && (cscA48 >= GRADE_B) &&(req31 || req32 || req33);
        }else {
            return (mata67 >= GRADE_A_MINUS) && (mata31 >= GRADE_A_MINUS) && (mata31 >= GPA_PASS) && (mata22 >= GPA_PASS) && (cscA48 >= GPA_PASS) && (csca08 >= GPA_PASS);
        }

    }

    private double calculateGPA(double mata37, double mata31, double mata22, double mata67, double csca48) {
        return (mata67 + mata22 + mata31 + mata37 + csca48) / 5;
    }
}
