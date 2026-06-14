package com.university.techcorp.domain;

public class DevOps extends Employee {

    public DevOps(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() + 3;
    }

    @Override
    public String getRoleName() {
        return "DevOps";
    }
}
