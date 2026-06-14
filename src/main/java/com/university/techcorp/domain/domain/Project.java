package com.university.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int requiredWork;
    private int progress;
    private List<Workable> team;
    private ProjectStatus status;

    public Project(String name, int requiredWork) {
        validateName(name);
        validateRequiredWork(requiredWork);

        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
        this.team = new ArrayList<>();
        this.status = ProjectStatus.PLANNED;
    }

    public void addWorker(Workable worker) {
        if (worker == null) {
            throw new IllegalArgumentException("Worker cannot be null.");
        }
        team.add(worker);
    }

    public boolean removeWorker(Workable worker) {
        return team.remove(worker);
    }

    public void start() {
        if (status == ProjectStatus.PLANNED) {
            status = ProjectStatus.IN_PROGRESS;
        }
    }

    public void cancel() {
        if (status != ProjectStatus.FINISHED) {
            status = ProjectStatus.CANCELLED;
        }
    }

    public void workOneTurn() {
        if (status != ProjectStatus.IN_PROGRESS) {
            return;
        }

        for (Workable worker : team) {
            progress += worker.work();
        }

        if (progress >= requiredWork) {
            progress = requiredWork;
            status = ProjectStatus.FINISHED;
        }
    }

    public boolean isFinished() {
        return status == ProjectStatus.FINISHED;
    }

    public String getName() {
        return name;
    }

    public int getRequiredWork() {
        return requiredWork;
    }

    public int getProgress() {
        return progress;
    }

    public List<Workable> getTeam() {
        return team;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be null or blank.");
        }
    }

    private void validateRequiredWork(int requiredWork) {
        if (requiredWork <= 0) {
            throw new IllegalArgumentException("Required work must be greater than 0.");
        }
    }
}
