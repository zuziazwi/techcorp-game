package com.university.techcorp.ui;

import com.university.techcorp.domain.Company;
import com.university.techcorp.domain.Employee;
import com.university.techcorp.domain.Project;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    public void showTurnHeader(int turn) {
        System.out.println();
        System.out.println("=== TURN " + turn + " ===");
    }

    public void showMainMenu() {
        System.out.println("Choose an action:");
        System.out.println("  1. Show company status");
        System.out.println("  2. Start planned projects");
        System.out.println("  3. Work on projects (advance one turn)");
        System.out.println("  4. Hire employee");
        System.out.println("  5. Fire employee");
        System.out.println("  6. Assign employee to project");
        System.out.println("  0. Exit game");
    }

    public int readMenuChoice() {
        System.out.print("Enter choice: ");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void showCompanyStatus(Company company) {
        System.out.println();
        System.out.println("=== COMPANY STATUS ===");
        System.out.println("Name:  " + company.getName());
        System.out.printf("Cash:  %.2f%n", company.getCash());
        System.out.printf("Monthly salaries: %.2f%n", company.calculateMonthlySalaries());
        System.out.println("Employees: " + company.getEmployees().size());

        if (company.getEmployees().isEmpty()) {
            System.out.println("  No employees hired yet.");
        } else {
            for (Employee employee : company.getEmployees()) {
                System.out.println("  - " + employee);
            }
        }

        System.out.println("Projects: " + company.getProjects().size());
        if (company.getProjects().isEmpty()) {
            System.out.println("  No active projects.");
        } else {
            for (Project project : company.getProjects()) {
                System.out.println("  - " + project.getName()
                        + " | status: " + project.getStatus()
                        + " | progress: " + project.getProgress()
                        + " / " + project.getRequiredWork()
                        + " | team size: " + project.getTeam().size());
            }
        }
        System.out.println("======================");
    }

    public void showHireMenu() {
        System.out.println("Choose role to hire:");
        System.out.println("  1. Developer  (skill 8, salary 7000)");
        System.out.println("  2. Tester     (skill 6, salary 6000)");
        System.out.println("  3. Manager    (skill 7, salary 9000)");
        System.out.println("  4. Intern     (skill 4, salary 2500)");
        System.out.println("  5. DevOps     (skill 8, salary 8000)");
        System.out.println("  0. Cancel");
    }

    public void showFireMenu(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to fire.");
            return;
        }
        System.out.println("Choose employee to fire:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + employees.get(i));
        }
        System.out.println("  0. Cancel");
    }

    public void showAssignMenu(List<Employee> employees, List<Project> projects) {
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return;
        }
        System.out.println("Choose employee:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + employees.get(i));
        }
        System.out.println("  0. Cancel");
    }

    public void showProjectMenu(List<Project> projects) {
        System.out.println("Choose project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + projects.get(i).getName()
                    + " [" + projects.get(i).getStatus() + "]");
        }
        System.out.println("  0. Cancel");
    }

    public String readName() {
        System.out.print("Enter employee name: ");
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showWin() {
        System.out.println();
        System.out.println("*** CONGRATULATIONS! All projects are finished. You win! ***");
    }

    public void showLose() {
        System.out.println();
        System.out.println("*** GAME OVER! The company is bankrupt. Cash fell below zero. ***");
    }
}
