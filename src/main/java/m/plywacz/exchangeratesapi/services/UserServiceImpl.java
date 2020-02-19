package m.plywacz.exchangeratesapi.services;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.dto.UserDto;
import m.plywacz.exchangeratesapi.exceptions.EntityDuplicateException;
import m.plywacz.exchangeratesapi.exceptions.ResourceNotFoundException;
import m.plywacz.exchangeratesapi.model.Credentials;
import m.plywacz.exchangeratesapi.model.User;
import m.plywacz.exchangeratesapi.repo.CredentialsRepo;
import m.plywacz.exchangeratesapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";

    @Value("${security.admin_key}")
    private String adminKey;

    private final BCryptPasswordEncoder encoder; //

    private final UserRepo userRepo;
    private final CredentialsRepo credentialsRepo;

    public UserServiceImpl(BCryptPasswordEncoder encoder, UserRepo userRepo, CredentialsRepo credentialsRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.credentialsRepo = credentialsRepo;
    }

    @Override public User saveUser(UserDto userDto) {
        if (credentialsRepo.existsByUsername(userDto.getUsername())) {
            throw new EntityDuplicateException("Username must be unique, given: " + userDto.getUsername() + " already exists");
        }

        var user = convertDto(userDto);
        return userRepo.save(user);
    }

    private User convertDto(UserDto userDto) {
        var credentials = new Credentials();
        credentials.setUsername(userDto.getUsername());
        credentials.setPassword(encoder.encode(userDto.getPassword()));
        determineUserRole(credentials,userDto);

        var user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        credentials.setUser(user);
        user.setCredentials(credentials);

        var savedUser = userRepo.save(user);
        credentialsRepo.save(credentials);

        return savedUser;
    }

    private void determineUserRole(Credentials credentials, UserDto userDto) {
        if (userDto.getAdminKey() != null && userDto.getAdminKey().equals(this.adminKey))
            credentials.setRole(ADMIN_ROLE);
        else
            credentials.setRole(USER_ROLE);
    }

    @Override public User listUser(Long userId) {
        return userRepo.findById(userId).orElseThrow( //todo add exception handling
                () -> new ResourceNotFoundException("user with id: " + userId));
    }

    //== Security stuff below
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var credentials = credentialsRepo.findByUsername(username);
        if (credentials == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(credentials.getUsername(), credentials.getPassword(), getAuthority(credentials));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Credentials credentials) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + credentials.getRole()));
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Autowired
    void insertSampleUsersOnStartUp() { //loads admin and basic user on startup
        if (userRepo.findAll().size() != 0) //in case ddl-auto set on update, prevents default user duplication
            return;

        //admin
        var aCredentials = new Credentials();
        aCredentials.setRole("ADMIN");
        aCredentials.setUsername("admin");
        aCredentials.setPassword(encoder.encode("admin"));

        var aUser = new User();
        aUser.setFirstName("sample admin");
        aUser.setLastName("sample admin");
        aUser.setEmail("sample admin");
        aUser.setCredentials(aCredentials);

        aUser.setCredentials(aCredentials);
        aCredentials.setUser(aUser);

        userRepo.save(aUser);

        credentialsRepo.save(aCredentials);

        //user
        var uCredentials = new Credentials();
        uCredentials.setRole("USER");
        uCredentials.setUsername("user");
        uCredentials.setPassword(encoder.encode("user"));

        var uUser = new User();
        uUser.setFirstName("sample user");
        uUser.setLastName("sample user");
        uUser.setEmail("sample user");
        uUser.setCredentials(uCredentials);

        uUser.setCredentials(uCredentials);
        uCredentials.setUser(uUser);

        userRepo.save(uUser);

        credentialsRepo.save(uCredentials);
    }

}
