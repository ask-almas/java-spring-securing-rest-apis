package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
	private final ResolutionRepository resolutions;
	private final UserRepository users;

	public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users) {
		this.resolutions = resolutions;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.resolutions.save(new Resolution("Read War and Peace user", "user"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower user", "user"));
		this.resolutions.save(new Resolution("Hang Christmas Lights user", "user"));
		this.resolutions.save(new Resolution("Read War and Peace hasread", "hasread"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower hasread", "hasread"));
		this.resolutions.save(new Resolution("Hang Christmas Lights hasread", "hasread"));
		this.resolutions.save(new Resolution("Read War and Peace haswrite", "haswrite"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower haswrite", "haswrite"));
		this.resolutions.save(new Resolution("Hang Christmas Lights haswrite", "haswrite"));

		User user = new User("user", "{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		user.grantAuthority("resolution:read");
		user.grantAuthority("resolution:write");
		this.users.save(user);

		User hasread = new User("hasread", "{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		hasread.grantAuthority("resolution:read");
		this.users.save(hasread);

		User haswrite = new User("haswrite", "{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		haswrite.grantAuthority("resolution:write");
		this.users.save(haswrite);

		User admin = new User("admin","{bcrypt}$2a$10$bTu5ilpT4YILX8dOWM/05efJnoSlX4ElNnjhNopL9aPoRyUgvXAYa");
		admin.grantAuthority("ROLE_ADMIN");
		this.users.save(admin);
	}
}
