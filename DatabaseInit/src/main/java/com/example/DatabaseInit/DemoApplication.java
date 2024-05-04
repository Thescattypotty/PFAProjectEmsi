package com.example.DatabaseInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.DatabaseInit.Entity.Company;
import com.example.DatabaseInit.Entity.Contact;
import com.example.DatabaseInit.Entity.Interaction;
import com.example.DatabaseInit.Entity.Permission;
import com.example.DatabaseInit.Entity.Role;
import com.example.DatabaseInit.Entity.User;
import com.example.DatabaseInit.EntityRepository.CompanyRepository;
import com.example.DatabaseInit.EntityRepository.ContactRepository;
import com.example.DatabaseInit.EntityRepository.InteractionRepository;
import com.example.DatabaseInit.EntityRepository.PermissionRepository;
import com.example.DatabaseInit.EntityRepository.RoleRepository;
import com.example.DatabaseInit.EntityRepository.UserRepository;
import com.example.DatabaseInit.Enum.EBusinessType;
import com.example.DatabaseInit.Enum.ECompanySize;
import com.example.DatabaseInit.Enum.ECountry;
import com.example.DatabaseInit.Enum.EIndustry;
import com.example.DatabaseInit.Enum.EInteraction;
import com.example.DatabaseInit.Enum.EPermission;
import com.example.DatabaseInit.Enum.ERole;
import com.example.DatabaseInit.Enum.ETimeZone;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private InteractionRepository interactionRepository;

	// @Autowired
	private PasswordEncoder encoder;

	private static final Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		encoder = passwordEncoder();
		// clear Database
		clearDatabase();
		// init Database
		initDatabase();

	}

	public void clearDatabase() {
		companyRepository.deleteAll();
		contactRepository.deleteAll();
		interactionRepository.deleteAll();
		userRepository.deleteAll();
		roleRepository.deleteAll();
		permissionRepository.deleteAll();
	}

	public void initDatabase() {
		List<Permission> permissions = new ArrayList<>();
		Permission p = null;
		for (EPermission ePermission : EPermission.values()) {
			p = new Permission(ePermission);
			permissionRepository.save(p);
			permissions.add(p);
		}

		List<Role> roles = new ArrayList<>();
		Role r = null;
		for (ERole eRole : ERole.values()) {
			r = new Role(eRole);
			if (eRole.equals(ERole.ROLE_ADMIN)) {
				r.getPermissions().addAll(permissions);
			} else if (eRole.equals(ERole.ROLE_MODERATOR)) {
				r.getPermissions().add(permissions.get(0));
				r.getPermissions().add(permissions.get(1));
				r.getPermissions().add(permissions.get(2));
				r.getPermissions().add(permissions.get(4));
				r.getPermissions().add(permissions.get(5));
				r.getPermissions().add(permissions.get(6));
				r.getPermissions().add(permissions.get(8));
				r.getPermissions().add(permissions.get(9));
				r.getPermissions().add(permissions.get(10));

				r.getPermissions().add(permissions.get(12));
				r.getPermissions().add(permissions.get(13));
				r.getPermissions().add(permissions.get(14));
			} else {
				r.getPermissions().add(permissions.get(0));
				r.getPermissions().add(permissions.get(4));
				r.getPermissions().add(permissions.get(8));
			}
			roleRepository.save(r);
			roles.add(r);
		}
		Faker faker = new Faker();

		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setEmail(faker.name().username() + "@gmail.com");
			user.setPassword(encoder.encode("yahya123456"));
			Role randomRole = roles.get(random.nextInt(roles.size()));
			if (randomRole.getName().equals(ERole.ROLE_ADMIN)) {
				user.getPermissions().addAll(permissions);
				user.getRoles().add(roles.get(0));
				user.getRoles().add(roles.get(1));
				user.getRoles().add(roles.get(2));

			} else if (randomRole.getName().equals(ERole.ROLE_MODERATOR)) {
				user.getPermissions().add(permissions.get(0));
				user.getPermissions().add(permissions.get(2));
				user.getPermissions().add(permissions.get(1));
				user.getPermissions().add(permissions.get(4));
				user.getPermissions().add(permissions.get(6));
				user.getPermissions().add(permissions.get(5));
				user.getPermissions().add(permissions.get(8));
				user.getPermissions().add(permissions.get(9));
				user.getPermissions().add(permissions.get(10));

				user.getPermissions().add(permissions.get(12));
				user.getPermissions().add(permissions.get(13));
				user.getPermissions().add(permissions.get(14));

				user.getRoles().add(roles.get(0));
				user.getRoles().add(roles.get(1));
			} else {
				user.getPermissions().add(permissions.get(0));
				user.getPermissions().add(permissions.get(4));
				user.getPermissions().add(permissions.get(8));
				user.getPermissions().add(permissions.get(12));

				user.getRoles().add(roles.get(0));
			}
			userRepository.save(user);
			users.add(user);
		}
		List<Company> companys = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			Company company = new Company();
			company.setCompanyName(faker.company().name());
			company.setCompanyLogo(this.GenerateImage());
			company.setCompanySize(ECompanySize.values()[random.nextInt(ECompanySize.values().length)]);
			company.setIndustry(EIndustry.values()[random.nextInt(EIndustry.values().length)]);
			company.setBusinessType(EBusinessType.values()[random.nextInt(EBusinessType.values().length)]);
			company.setCountry(ECountry.values()[random.nextInt(ECountry.values().length)]);
			company.setWebsite(faker.company().url());
			company.setTotalIncome(faker.number().randomDouble(4, 120, 100000000));
			company.setCreatedBy(users.get(random.nextInt(users.size())));
			company.setCreatedAt(new Date());

			Company comp = companyRepository.save(company);

			comp.setSalesOwner(contactRepository.save(new Contact(
					faker.name().firstName(),
					faker.name().lastName(),
					faker.name().username() + "@gmail.com",
					faker.phoneNumber().cellPhone(),
					GenerateImage(),
					ETimeZone.values()[random.nextInt(ETimeZone.values().length)],
					users.get(random.nextInt(users.size())),
					new Date(),
					company)));
			comp.getSalesOwner().setCreatedAt(new Date());
			comp.getSalesOwner().setCreatedBy(users.get(random.nextInt(users.size())));
			contactRepository.save(comp.getSalesOwner());
			companyRepository.save(comp);

			companys.add(comp);
		}
		List<Contact> contacts = new ArrayList<>();

		for (int i = 0; i < 180; i++) {
			Contact contact = new Contact();
			contact.setFirstName(faker.name().firstName());
			contact.setLastName(faker.name().lastName());
			contact.setEmail(faker.name().username() + "@gmail.com");
			contact.setPhone(faker.phoneNumber().cellPhone());
			contact.setImage(GenerateImage());
			contact.setTimeZone(ETimeZone.values()[random.nextInt(ETimeZone.values().length)]);
			contact.setCreatedBy(users.get(random.nextInt(users.size())));
			contact.setCreatedAt(new Date());
			// contact.setCompany(companys.get(random.nextInt(companys.size())));
			contact.setCompany(companys.get(random.nextInt(companys.size() - 1)));
			contactRepository.save(contact);
			contacts.add(contact);
		}
		for (int i = 0; i < 90; i++) {
			Interaction interaction = new Interaction();
			interaction.setType(EInteraction.values()[random.nextInt(EInteraction.values().length)]);
			interaction.setNotes(faker.lorem().characters());
			interaction.setDate(faker.date().birthday());
			interaction.setContact(contacts.get(random.nextInt(contacts.size())));
			interaction.setCreatedBy(users.get(random.nextInt(users.size())));
			interaction.setCreatedAt(new Date());

			interactionRepository.save(interaction);
		}
	}

	public byte[] GenerateImage() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		graphics.dispose();
		byte[] companyLogo = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			companyLogo = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyLogo;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
