package com.sneakpick.config;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sneakpick.dto.SocialProvider;
import com.sneakpick.entities.Brand;
import com.sneakpick.entities.Role;
import com.sneakpick.entities.Size;
import com.sneakpick.entities.User;
import com.sneakpick.repositories.RoleRepository;
import com.sneakpick.repositories.UserRepository;
import com.sneakpick.services.BrandService;
import com.sneakpick.services.SizeService;

/**
 * Database loader to set initial data
 *
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SizeService sizeService;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		// Create initial roles
		Role userRole = createRoleIfNotFound(Role.ROLE_USER);
		createUserIfNotFound("user@admin.com", Set.of(userRole));
		Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
		createUserIfNotFound("admin@admin.com", Set.of(adminRole));
		// Create initial brands
		createBrandIfNotFound("NIKE");
		createBrandIfNotFound("ADIDAS");
		createBrandIfNotFound("PUMA");
		createBrandIfNotFound("New Balance");
		createBrandIfNotFound("Reebok");
		createBrandIfNotFound("Converse");
		createBrandIfNotFound("Balenciaga");
		createBrandIfNotFound("Y-3");
		// Create initial sizes for sneakers
		createSizeIfNotFound("4");
		createSizeIfNotFound("4.5");
		createSizeIfNotFound("5");
		createSizeIfNotFound("5.5");
		createSizeIfNotFound("6");
		createSizeIfNotFound("6.5");
		createSizeIfNotFound("7");
		createSizeIfNotFound("7.5");
		createSizeIfNotFound("8");
		createSizeIfNotFound("8.5");
		createSizeIfNotFound("9");
		createSizeIfNotFound("9.5");
		createSizeIfNotFound("10");
		createSizeIfNotFound("10.5");
		createSizeIfNotFound("11");
		createSizeIfNotFound("11.5");
		createSizeIfNotFound("12");
		createSizeIfNotFound("12.5");
		createSizeIfNotFound("13");
		createSizeIfNotFound("13.5");
		createSizeIfNotFound("14");

		alreadySetup = true;
	}

	@Transactional
	private final User createUserIfNotFound(final String email, Set<Role> roles) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode("123456"));
			user.setRoles(roles);
			user.setProvider(SocialProvider.LOCAL.getProviderType());
			user.setEnabled(true);
			Date now = Calendar.getInstance().getTime();
			user.setCreatedDate(now);
			user.setModifiedDate(now);
			user = userRepository.save(user);
		}
		return user;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = roleRepository.save(new Role(name));
		}
		return role;
	}

	@Transactional
	private final Brand createBrandIfNotFound(final String name) {
		Brand brand = brandService.findByName(name);
		if (brand == null) {
			brand = brandService.save(new Brand(name));
		}
		return brand;
	}

	@Transactional
	private final Size createSizeIfNotFound(final String name) {
		Size size = sizeService.findByValue(name);
		if (size == null) {
			size = sizeService.save(new Size(name));
		}
		return size;
	}
}