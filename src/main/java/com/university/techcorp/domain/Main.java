package com.university.techcorp;

import com.university.techcorp.domain.Company;
import com.university.techcorp.domain.Developer;
import com.university.techcorp.domain.Manager;
import com.university.techcorp.domain.Project;
import com.university.techcorp.domain.Tester;
import com.university.techcorp.engine.GameEngine;
import com.university.techcorp.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        Company company = new Company("TechCorp", 350_000);

        Developer anna = new Developer("Anna", 8, 7_000);
        Tester piotr = new Tester("Piotr", 6, 6_000);
        Manager ewa = new Manager("Ewa", 7, 9_000);

        company.hire(anna);
        company.hire(piotr);
        company.hire(ewa);

        Project mobileApp = new Project("Mobile App", 40);
        mobileApp.addWorker(anna);
        mobileApp.addWorker(piotr);

        Project website = new Project("Website", 25);
        website.addWorker(ewa);

        company.startProject(mobileApp);
        company.addProject(website);

        ConsoleUI ui = new ConsoleUI();
        GameEngine engine = new GameEngine(company, ui);
        engine.run();
    }
}
