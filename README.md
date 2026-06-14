# TechCorp Decision Game

**Final Project** | *Introduction to Java Programming*  
*SGH Warsaw School of Economics (Advanced Analytics - Big Data)*
 
You play as the CEO of a tech company. Manage employees, run projects, and keep the budget alive. Complete all projects before going bankrupt to win.
 
---
 
## How to Run
 
**Requirements:** Java 17+
 
```bash
mkdir -p out
find src -name "*.java" | xargs javac -d out
java -cp out com.university.techcorp.Main
```
 
---
 
## How to Play
 
| Option | Action |
|--------|--------|
| `1` | Show company status |
| `2` | Start planned projects |
| `3` | Work one turn (pays salaries + random event may occur) |
| `4` | Hire an employee |
| `5` | Fire an employee |
| `6` | Assign employee to a project |
| `0` | Exit |
 
- **Win** — all projects finished before running out of cash
- **Lose** — cash drops below 0 (bankruptcy)
---
 
## Project Structure
 
```
src/main/java/com/university/techcorp/
│
├── Main.java
│
├── engine/
│   └── GameEngine.java         # game loop and menu handling
│
├── ui/
│   └── ConsoleUI.java          # display and input
│
├── domain/
│   ├── Workable.java           # interface
│   ├── Employee.java           # abstract class
│   ├── Developer.java          # work = skill
│   ├── Tester.java             # work = skill / 2
│   ├── Manager.java            # work = skill / 3
│   ├── Intern.java             # work = skill / 4
│   ├── DevOps.java             # work = skill + 3
│   ├── ProjectStatus.java      # enum: PLANNED, IN_PROGRESS, FINISHED, CANCELLED
│   ├── Project.java
│   └── Company.java
│
└── events/
    ├── GameEvent.java              # interface
    ├── MarketSlowdownEvent.java    # -5 000 cash
    ├── TechBoomEvent.java          # +10 000 cash
    ├── StrikeEvent.java            # -8 000 cash
    ├── ClientLostEvent.java        # -12 000 cash
    └── RandomEventService.java     # triggers a random event ~every 3 turns
```
 
---
