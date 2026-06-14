package com.university.techcorp.engine;

import com.university.techcorp.domain.Company;
import com.university.techcorp.domain.Developer;
import com.university.techcorp.domain.DevOps;
import com.university.techcorp.domain.Employee;
import com.university.techcorp.domain.Intern;
import com.university.techcorp.domain.Manager;
import com.university.techcorp.domain.Project;
import com.university.techcorp.domain.ProjectStatus;
import com.university.techcorp.domain.Tester;
import com.university.techcorp.events.GameEvent;
import com.university.techcorp.events.RandomEventService;
import com.university.techcorp.ui.ConsoleUI;

import java.util.List;

public class GameEngine {

    private final Company company;
    private final ConsoleUI ui;
    private final RandomEventService eventService;
    private boolean running;
    private int turn;

    public GameEngine(Company company, ConsoleUI ui) {
        this.company = company;
        this.ui = ui;
        this.eventService = new RandomEventService();
        this.running = true;
        this.turn = 1;
    }

    public void run() {
        while (running) {
            ui.showTurnHeader(turn);
            ui.showCompanyStatus(company);
            ui.showMainMenu();

            int choice = ui.readMenuChoice();
            handleChoice(choice);

            if (running) {
                checkWinLoseCondition();
            }
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> ui.showCompanyStatus(company);
            case 2 -> startPlannedProjects();
            case 3 -> workOneTurn();
            case 4 -> hireEmployee();
            case 5 -> fireEmployee();
            case 6 -> assignEmployeeToProject();
            case 0 -> {
                running = false;
                ui.showMessage("Exiting game. Goodbye!");
            }
            default -> ui.showMessage("Invalid menu option. Please choose again.");
        }
    }

    private void startPlannedProjects() {
        boolean anyStarted = false;
        for (Project project : company.getProjects()) {
            if (project.getStatus() == ProjectStatus.PLANNED) {
                project.start();
                ui.showMessage("Project started: " + project.getName());
                anyStarted = true;
            }
        }
        if (!anyStarted) {
            ui.showMessage("No planned projects to start.");
        }
    }

    private void workOneTurn() {
        for (Project project : company.getProjects()) {
            project.workOneTurn();
        }
        ui.showMessage("Projects worked for one turn.");

        double salariesDue = company.calculateMonthlySalaries();
        company.paySalaries();
        ui.showMessage(String.format("Salaries paid: %.2f", salariesDue));

        if (eventService.shouldEventOccur()) {
            GameEvent event = eventService.triggerRandomEvent(company);
            ui.showMessage("[EVENT] " + event.getDescription());
        }

        turn++;
    }

    private void hireEmployee() {
        ui.showHireMenu();
        int choice = ui.readMenuChoice();
        if (choice == 0) {
            return;
        }

        String name = ui.readName();
        if (name.isBlank()) {
            ui.showMessage("Name cannot be blank. Hire cancelled.");
            return;
        }

        try {
            Employee newEmployee = createEmployee(choice, name);
            company.hire(newEmployee);
            ui.showMessage("Hired: " + newEmployee);
        } catch (IllegalArgumentException e) {
            ui.showMessage("Invalid choice. Hire cancelled.");
        }
    }

    private Employee createEmployee(int choice, String name) {
        return switch (choice) {
            case 1 -> new Developer(name, 8, 7_000);
            case 2 -> new Tester(name, 6, 6_000);
            case 3 -> new Manager(name, 7, 9_000);
            case 4 -> new Intern(name, 4, 2_500);
            case 5 -> new DevOps(name, 8, 8_000);
            default -> throw new IllegalArgumentException("Unknown employee type: " + choice);
        };
    }

    private void fireEmployee() {
        List<Employee> employees = company.getEmployees();
        ui.showFireMenu(employees);
        if (employees.isEmpty()) {
            return;
        }

        int choice = ui.readMenuChoice();
        if (choice == 0) {
            return;
        }

        if (choice < 1 || choice > employees.size()) {
            ui.showMessage("Invalid choice. Fire cancelled.");
            return;
        }

        Employee toFire = employees.get(choice - 1);
        company.fire(toFire);
        ui.showMessage("Fired: " + toFire.getName() + " (removed from all project teams).");
    }

    private void assignEmployeeToProject() {
        List<Employee> employees = company.getEmployees();
        List<Project> projects = company.getProjects();

        ui.showAssignMenu(employees, projects);
        if (employees.isEmpty() || projects.isEmpty()) {
            return;
        }

        int empChoice = ui.readMenuChoice();
        if (empChoice == 0) {
            return;
        }
        if (empChoice < 1 || empChoice > employees.size()) {
            ui.showMessage("Invalid employee choice. Assignment cancelled.");
            return;
        }

        ui.showProjectMenu(projects);
        int projChoice = ui.readMenuChoice();
        if (projChoice == 0) {
            return;
        }
        if (projChoice < 1 || projChoice > projects.size()) {
            ui.showMessage("Invalid project choice. Assignment cancelled.");
            return;
        }

        Employee employee = employees.get(empChoice - 1);
        Project project = projects.get(projChoice - 1);
        project.addWorker(employee);
        ui.showMessage(employee.getName() + " assigned to project: " + project.getName());
    }

    private void checkWinLoseCondition() {
        if (company.isBankrupt()) {
            ui.showLose();
            running = false;
        } else if (company.allProjectsFinished()) {
            ui.showWin();
            running = false;
        }
    }
}
