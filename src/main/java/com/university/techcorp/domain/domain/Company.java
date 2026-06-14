package com.university.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private double cash;
    private List<Employee> employees;
    private List<Project> projects;

    public Company(String name, double cash) {
        validateName(name);
        validateCash(cash);

        this.name = name;
        this.cash = cash;
        this.employees = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void hire(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        employees.add(employee);
    }

    public boolean fire(Employee employee) {
        for (Project project : projects) {
            project.removeWorker(employee);
        }
        return employees.remove(employee);
    }

    public void addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }
        projects.add(project);
    }

    public void startProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }
        if (!projects.contains(project)) {
            projects.add(project);
        }
        project.start();
    }

    public void reduceCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to reduce cannot be negative.");
        }
        cash -= amount;
    }

    public void addCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative.");
        }
        cash += amount;
    }

    public void paySalaries() {
        double totalSalaries = calculateMonthlySalaries();
        cash -= totalSalaries;
    }

    public double calculateMonthlySalaries() {
        double total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }

    public boolean allProjectsFinished() {
        if (projects.isEmpty()) {
            return false;
        }
        for (Project project : projects) {
            if (!project.isFinished()) {
                return false;
            }
        }
        return true;
    }

    public boolean isBankrupt() {
        return cash < 0;
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be null or blank.");
        }
    }

    private void validateCash(double cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("Initial cash cannot be negative.");
        }
    }
}
