package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repositories.*;
import com.example.demo.services.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.criteria.CriteriaBuilder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private InstrumentalRepository instrumentalRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("admin");
        Role teacher = new Role("teacher");
        Role student = new Role("student");
        roleRepository.save(admin);
        roleRepository.save(teacher);
        roleRepository.save(student);

        Instrumental piano = new Instrumental("piano");
        Instrumental saxophone = new Instrumental("saxophone");
        Instrumental violin = new Instrumental("violin");
        Instrumental guitar = new Instrumental("guitar");
        instrumentalRepository.save(piano);
        instrumentalRepository.save(saxophone);
        instrumentalRepository.save(violin);
        instrumentalRepository.save(guitar);

        Role AdminById = roleRepository.findById(1L).get();
        Role TeacherId = roleRepository.findById(2L).get();
        Instrumental piano_id = instrumentalRepository.findById(1L).get();
        Instrumental saxophone_id = instrumentalRepository.findById(2L).get();
        Instrumental violin_id = instrumentalRepository.findById(3L).get();
        Instrumental guitar_id = instrumentalRepository.findById(4L).get();
        User alisher = new User("admin", passwordEncoder.encode("admin"), "Alisher", AdminById);
        userRepository.save(alisher);
        userRepository.save(new User("NN", passwordEncoder.encode("123"), "Наталья Николаевна", TeacherId,saxophone_id));
        userRepository.save(new User("AD", passwordEncoder.encode("123"), "Акан Даурен", TeacherId,guitar_id));
        userRepository.save(new User("UT", passwordEncoder.encode("123"), "Умурзаков Тимур", TeacherId,saxophone_id));
        userRepository.save(new User("KA", passwordEncoder.encode("123"), "Кочнева Арина", TeacherId,piano_id));
        userRepository.save(new User("AA", passwordEncoder.encode("123"), "Абдрахманова Ангелина", TeacherId,violin_id));
        userRepository.save(new User("ED", passwordEncoder.encode("123"),"Ермагомбет Дарахан", TeacherId,guitar_id));
        userRepository.save(new User("АБ", passwordEncoder.encode("123"), "Аханов Батырхан", TeacherId,piano_id));
        userRepository.save(new User("EE", passwordEncoder.encode("123"), "Есимова Эльнура", TeacherId,violin_id));

        Subject musicInstrument = new Subject("Музыкальный инструмент", "Игра на выбраном музыкальном инструменте");
        Subject solfeggio = new Subject("Сольфеджио", "Изучение дирежерной части");
        Subject choralSinging = new Subject("Хоровое пение", "Обучение хоровому пению");
        subjectRepository.save(musicInstrument);
        subjectRepository.save(solfeggio);
        subjectRepository.save(choralSinging);

        timeSlotRepository.save(new TimeSlot(14, 15));
        timeSlotRepository.save(new TimeSlot(15, 16));
        timeSlotRepository.save(new TimeSlot(16, 17));
        timeSlotRepository.save(new TimeSlot(17, 18));
        timeSlotRepository.save(new TimeSlot(18, 19));
        timeSlotRepository.save(new TimeSlot(19, 20));

    }
}