package com.donghd.notend.service;

import com.donghd.notend.domain.Authority;
import com.donghd.notend.domain.User;
import com.donghd.notend.repository.AuthorityRepository;
import com.donghd.notend.repository.FriendRepository;
import com.donghd.notend.repository.MessageRepository;
import com.donghd.notend.repository.TransactionHistoryRepository;
import com.donghd.notend.config.Constants;
import com.donghd.notend.repository.UserRepository;
import com.donghd.notend.security.AuthoritiesConstants;
import com.donghd.notend.security.SecurityUtils;
import com.donghd.notend.service.util.RandomUtil;
import com.donghd.notend.service.dto.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.donghd.notend.web.rest.errors.InvalidPasswordException;
import com.donghd.notend.web.rest.vm.UserStatistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final FriendRepository friendRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final MessageRepository messageRepository;

    private final CacheManager cacheManager;

    public UserService(
            MessageRepository messageRepository,
            TransactionHistoryRepository transactionHistoryRepository,
            FriendRepository friendRepository,
            UserRepository userRepository, 
            PasswordEncoder passwordEncoder, 
            AuthorityRepository authorityRepository, 
            CacheManager cacheManager
        ) {
        this.messageRepository = messageRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.cacheManager = cacheManager;
    }

    public UserStatistics userStatistic() {
        UserStatistics userStatistics = new UserStatistics();

        userStatistics.setTotalUser(userRepository.count());
        userStatistics.setActivedUser(userRepository.countByActivatedIsTrue());
        userStatistics.setPaidUser(userRepository.countByPaidUserIsTrue());

        userStatistics.setTotalFriends(friendRepository.count()/2);
        userStatistics.setPendingFriends(friendRepository.countByStatus(Constants.FRIEND_STATUS_PENDDING));

        userStatistics.setTotalMessages(messageRepository.count());
        userStatistics.setUnreadMessages(messageRepository.countByStatus(Constants.SENT));

        userStatistics.setTotalTrangsactions(transactionHistoryRepository.count());

        return userStatistics;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                this.clearUserCaches(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
           .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                this.clearUserCaches(user);
                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                this.clearUserCaches(user);
                return user;
            });
    }

    public User registerUser(UserDTO userDTO, String password) {
        // fake avatar
        if (userDTO.getGender() != null) {
            if (userDTO.getGender().equals(Constants.MALE_INTEGER)) {
                userDTO.setImageUrl("avatar/boy/"+ RandomUtil.getRandomNumberInRange(1, 10) +".jpg");
            } else {
                userDTO.setImageUrl("avatar/girl/"+ RandomUtil.getRandomNumberInRange(1, 10) +".jpg");
            }
        } else {
            userDTO.setImageUrl("avatar/no-avatar.jpg");
        }

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());
        // fake gender
        if (null == userDTO.getGender()) {
            userDTO.setGender(Constants.UNKNOW_INTEGER);
        }
        newUser.setGender(userDTO.getGender());
        newUser.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() != null) {
            newUser.setLangKey(userDTO.getLangKey());
        } else {
            newUser.setLangKey(Constants.DEFAULT_LANGUAGE);
        }
        // new user is not active
        newUser.setActivated(false);
        // new user is not paid
        newUser.setPaidUser(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        userRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setLangKey(langKey);
                user.setImageUrl(imageUrl);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    public void updateUserWithDTO(UserDTO userDTO) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                // "firstName": "string",
                user.setFirstName(userDTO.getFirstName());
                // "lastName": "string",
                user.setLastName(userDTO.getLastName());
                // "middleName": "string",
                user.setMiddleName(userDTO.getMiddleName());
                // "gender": 0,
                user.setGender(userDTO.getGender());
                // "imageUrl": "string",
                user.setImageUrl(userDTO.getImageUrl());
                // "heightCm": 0,
                user.setHeightCm(userDTO.getHeightCm());
                // "marriedStatus": 0,
                user.setMarriedStatus(userDTO.getMarriedStatus());
                // "aboutSelf": "string",
                user.setAboutSelf(userDTO.getAboutSelf());
                // "familyDetails": "string",
                user.setFamilyDetails(userDTO.getFamilyDetails());
                // "city": "string",
                user.setCity(userDTO.getCity());
                // "contactNumber": "string",
                user.setContactNumber(userDTO.getContactNumber());
                // "countryLiving": "string",
                user.setCountryLiving(userDTO.getCountryLiving());
                // "dateOfBirth": "string",
                user.setDateOfBirth(userDTO.getDateOfBirth());
                // "hobbies": "string",
                user.setHobbies(userDTO.getHobbies());
                // "motherTongue": "string",
                user.setMotherTongue(userDTO.getMotherTongue());
                // "qualification": "string",
                user.setQualification(userDTO.getQualification());
                // "religion": "string",
                user.setReligion(userDTO.getReligion());
                // "workingAt": "string"
                user.setWorkingAt(userDTO.getWorkingAt());
                
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
            .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setLogin(userDTO.getLogin());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());

                user.setExpirationDate(userDTO.getExpirationDate());
                user.setContactNumber(userDTO.getContactNumber());
                user.setCountryLiving(userDTO.getCountryLiving());
                user.setDateOfBirth(userDTO.getDateOfBirth());
                user.setFamilyDetails(userDTO.getFamilyDetails());
                user.setGender(userDTO.getGender());
                user.setHeightCm(userDTO.getHeightCm());
                user.setHobbies(userDTO.getHobbies());
                user.setMarriedStatus(userDTO.getMarriedStatus());
                user.setMiddleName(userDTO.getMiddleName());
                user.setMotherTongue(userDTO.getMotherTongue());
                user.setQualification(userDTO.getQualification());
                user.setReligion(userDTO.getReligion());
                user.setWorkingAt(userDTO.getWorkingAt());
                user.setPaidUser(userDTO.getPaidUser());

                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            this.clearUserCaches(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                this.clearUserCaches(user);
                log.debug("Changed password for User: {}", user);
            });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        Optional<User> optUsr = getUserWithAuthorities();
        if (optUsr.isPresent()) {
            User u = optUsr.get();
            Authority authAdmin = authorityRepository.findByName(Constants.ROLE_ADMIN);
            if (u.getAuthorities().contains(authAdmin)) {
                return userRepository.findAll(pageable).map(UserDTO::new);
            } else {
                log.debug("u.getGender() =============================================== {}", u.getGender());
                return userRepository.findByGenderNotAndCreatedByAndActivatedIsTrue(pageable, u.getGender(), "anonymousUser").map(UserDTO::new);
            }
        } else {
            throw new InvalidPasswordException();
        }
        // return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
            this.clearUserCaches(user);
        }
    }

    /**
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkPaidUsers() {
        List<User> users = userRepository.findAllByPaidUserIsTrueAndExpirationDateBefore(Instant.now());
        for (User user : users) {
            log.debug("Paid User is Expiration {}", user.getLogin());
            user.setPaidUser(false);
            userRepository.save(user);
        }
    }
    
    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }
}
