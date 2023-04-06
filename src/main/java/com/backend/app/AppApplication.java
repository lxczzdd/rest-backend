package com.backend.app;

import com.backend.app.roles.entity.Role;
import com.backend.app.roles.repository.RoleRepository;
import com.backend.app.users.entity.User;
import com.backend.app.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@SpringBootApplication
public class AppApplication {
	private static boolean alreadySetup = true;
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


	@Component
	@RequiredArgsConstructor
	public static class SetupDataLoader implements
			ApplicationListener<ContextRefreshedEvent> {



		private final UserRepository userRepository;

		private final RoleRepository roleRepository;

		private final PasswordEncoder passwordEncoder;

		@Override
		@Transactional
		public void onApplicationEvent(ContextRefreshedEvent event) {
			if(!alreadySetup) {
				createRoleIfNotFound("ADMIN");
				Set<Role> adminRole = roleRepository.findByName("ADMIN");
				User user = new User();
				user.setUsername("Admino");
				user.setPassword(passwordEncoder.encode("gigachad"));
				user.setEmail("admin@adminovich.com");
				user.setRoles(adminRole);
				userRepository.save(user);
			}

			alreadySetup = true;
		}

		@Transactional
		Role createRoleIfNotFound(String name) {
			Set<Role> roles = roleRepository.findByName(name);
			Role adminRole = new Role("ADMIN");
			if(roles.isEmpty())
				roles.add(adminRole);

			return adminRole;
		}
	}
}
