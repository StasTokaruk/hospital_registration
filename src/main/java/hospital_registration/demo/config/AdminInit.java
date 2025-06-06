package hospital_registration.demo.config;

import hospital_registration.demo.Models.PersonalModel;
import hospital_registration.demo.repo.PersonalRepo;
import hospital_registration.demo.service.PasswordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфігураційний клас, який ініціалізує адміністратора при старті застосунку.
 */
@Configuration
public class AdminInit {

    /**
     * Створює обліковий запис адміністратора, якщо його ще не існує.
     *
     * @param personalRepo репозиторій для доступу до персоналу
     * @return CommandLineRunner, який виконується при запуску застосунку
     */
    @Bean
    public CommandLineRunner createAdminIfNotExists(PersonalRepo personalRepo, PasswordService passwordService) {
        return args -> {
            String adminLogin = "admin"; // Логін адміністратора
            String adminPassword = "admin"; // Початковий пароль (потрібно змінити вручну після першого входу)

            if (!personalRepo.existsByPosition("Головний лікар")) {
                String hashedPassword = passwordService.encodePassword(adminPassword);
                PersonalModel admin = new PersonalModel(
                        "Рибак Михайло Класович",
                        adminLogin,
                        "0960741514",
                        "Головний лікар",
                        "Хірург",
                        hashedPassword,
                        "tokariuk.stanislav@lll.kpi.ua"
                );
                personalRepo.save(admin);
                System.out.println("Адміністратор створений: Логін - " + adminLogin);
            }
        };
    }
}
