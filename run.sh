mkdir -p out find src -name "*.java" | xargs javac -d out java -cp out com.university.techcorp.Main chmod +x run.sh
