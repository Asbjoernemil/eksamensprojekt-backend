package dat3.eksamensprojekt.config;

import dat3.eksamensprojekt.discipline.Discipline;
import dat3.eksamensprojekt.discipline.DisciplineRepository;
import dat3.eksamensprojekt.participant.Participant;
import dat3.eksamensprojekt.participant.ParticipantRepository;
import dat3.eksamensprojekt.result.Result;
import dat3.eksamensprojekt.result.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitData implements CommandLineRunner {

    private final DisciplineRepository disciplineRepository;
    private final ParticipantRepository participantRepository;
    private final ResultRepository resultRepository;

    public InitData(DisciplineRepository disciplineRepository, ParticipantRepository participantRepository, ResultRepository resultRepository) {
        this.disciplineRepository = disciplineRepository;
        this.participantRepository = participantRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello from InitData");
        createDisciplinesAndParticipants();
    }

    public void createDisciplinesAndParticipants() {
        // Adding some athletics disciplines
        Discipline discipline1 = new Discipline("100m Sprint", Discipline.ResultType.TIME);
        Discipline discipline2 = new Discipline("Long Jump", Discipline.ResultType.DISTANCE);
        Discipline discipline3 = new Discipline("Shot Put", Discipline.ResultType.POINTS);
        Discipline discipline4 = new Discipline("High Jump", Discipline.ResultType.DISTANCE);

        disciplineRepository.save(discipline1);
        disciplineRepository.save(discipline2);
        disciplineRepository.save(discipline3);
        disciplineRepository.save(discipline4);

        // Adding some participants
        LocalDate johnBirthDate = LocalDate.of(1999, 5, 15);
        LocalDate janeBirthDate = LocalDate.of(1995, 9, 22);
        LocalDate mikeBirthDate = LocalDate.of(1990, 7, 10);

        Participant participant1 = new Participant("John Doe", Participant.Gender.MALE, johnBirthDate, "Athletics Club A");
        Participant participant2 = new Participant("Jane Doe", Participant.Gender.FEMALE, janeBirthDate, "Athletics Club B");
        Participant participant3 = new Participant("Mike Smith", Participant.Gender.MALE, mikeBirthDate, "Athletics Club C");

        Set<Discipline> johnDisciplines = new HashSet<>();
        johnDisciplines.add(discipline1);
        johnDisciplines.add(discipline2);

        Set<Discipline> janeDisciplines = new HashSet<>();
        janeDisciplines.add(discipline2);
        janeDisciplines.add(discipline3);

        Set<Discipline> mikeDisciplines = new HashSet<>();
        mikeDisciplines.add(discipline3);
        mikeDisciplines.add(discipline4);

        participant1.setDisciplines(johnDisciplines);
        participant2.setDisciplines(janeDisciplines);
        participant3.setDisciplines(mikeDisciplines);

        participantRepository.save(participant1);
        participantRepository.save(participant2);
        participantRepository.save(participant3);

        // Opret resultater og tilføj dem til repository
        createAndSaveResults(participant1, discipline1, LocalDate.now(), "10.55"); // Eksempel på tid
        createAndSaveResults(participant2, discipline2, LocalDate.now(), "6.75");  // Eksempel på distance
        createAndSaveResults(participant3, discipline3, LocalDate.now(), "450");   // Eksempel på points
    }

    public void createAndSaveResults(Participant participant, Discipline discipline, LocalDate date, String resultValue) {
        Result result = new Result(participant, discipline, date, resultValue);
        resultRepository.save(result);
    }
}
