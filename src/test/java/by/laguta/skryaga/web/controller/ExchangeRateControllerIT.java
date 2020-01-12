package by.laguta.skryaga.web.controller;

import by.laguta.skryaga.utils.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeRateControllerIT extends BaseIntegrationTest {

/*    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRestTemplate restTemplate;

    User existingUser, newUser, updateUser;

    @Before
    public void setUp() {
        newUser = TestHelper.buildUser();

        existingUser = TestHelper.buildUser();
        existingUser = userRepository.save(existingUser);

        updateUser = TestHelper.buildUser();
        updateUser = userRepository.save(updateUser);
    }

    @After
    public void tearDown() {
        if(newUser.getId() != null) {
            userRepository.deleteById(newUser.getId());
        }
        userRepository.deleteAll(userRepository.findAllById(asList(existingUser.getId(), updateUser.getId())));
    }

    @Test
    public void should_get_all_users() {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("/api/users", User[].class);
        List<User> users = asList(responseEntity.getBody());
        assertThat(users).isNotEmpty();
    }

    @Test
    public void should_get_user_by_id() {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("/api/users/"+existingUser.getId(), User.class);
        User user = responseEntity.getBody();
        assertThat(user).isNotNull();
    }

    @Test
    public void should_create_user() {
        HttpEntity<User> request = new HttpEntity<>(newUser);
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("/api/users", request, User.class);
        User savedUser = responseEntity.getBody();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void should_update_user() {
        HttpEntity<User> request = new HttpEntity<>(updateUser);
        restTemplate.put("/api/users/"+updateUser.getId(), request, User.class);
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("/api/users/"+updateUser.getId(), User.class);
        User updatedUser = responseEntity.getBody();
        assertThat(updatedUser.getId()).isEqualTo(updateUser.getId());
        assertThat(updatedUser.getEmail()).isEqualTo(updateUser.getEmail());
    }

    @Test
    public void should_delete_user() {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("/api/users/"+existingUser.getId(), User.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        restTemplate.delete("/api/users/"+existingUser.getId());
        responseEntity = restTemplate.getForEntity("/api/users/"+existingUser.getId(), User.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(NOT_FOUND);
    }*/
}