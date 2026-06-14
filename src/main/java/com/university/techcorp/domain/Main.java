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
        // FIX #1 (critical): starting cash raised to 350 000 so the game is winnable
        // Calculation: website takes ~13 turns with only Ewa (2 work/turn),
        // total salary cost = 13 * 22 000 = 286 000; 350 000 leaves a comfortable buffer.
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

        // FIX #5: startProject() is now actually used — mobileApp begins immediately
        company.startProject(mobileApp);
        // website stays PLANNED — the player must start it via the menu
        company.addProject(website);

        ConsoleUI ui = new ConsoleUI();
        GameEngine engine = new GameEngine(company, ui);
        engine.run();
    }
}
