package hospital_registration.demo.config;

import hospital_registration.demo.Models.PersonalModel;
import hospital_registration.demo.repo.PersonalRepo;
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
    public CommandLineRunner createAdminIfNotExists(PersonalRepo personalRepo) {
        return args -> {
            String adminLogin = "admin"; // Логін адміністратора
            String adminPassword = "admin"; // Початковий пароль (потрібно змінити вручну після першого входу)

            if (!personalRepo.existsByPosition("Головний лікар")) {
                PersonalModel admin = new PersonalModel(
                        "Рибак Михайло",
                        adminLogin,
                        960741514,
                        "Головний лікар",
                        "Хірург",
                        adminPassword,
                        "tokariuk.stanislav@lll.kpi.ua"
                );
                personalRepo.save(admin);
                System.out.println("Адміністратор створений: Логін - " + adminLogin);
            }
        };
    }
}
