package com.chernyak.authservice.config.mongodb.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.chernyak.authservice.entity.AuthClientDetails;
import com.chernyak.authservice.entity.User;
import com.chernyak.authservice.entity.Role;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashSet;
import java.util.Set;

@ChangeLog
public class InitialValuesChangeLog {
/*    @ChangeSet(order = "001", id = "insertBrowserClientDetails", author = "Chernyak")
    public void insertBrowserClientDetails(MongoTemplate mongoTemplate) {
        AuthClientDetails browserClientDetails = new AuthClientDetails();
        browserClientDetails.setClientId("browser");
        browserClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        browserClientDetails.setScopes("ui");
        browserClientDetails.setGrantTypes("refresh_token,password");

        mongoTemplate.save(browserClientDetails);
    }

    @ChangeSet(order = "002", id = "insertUserToTestAuthentication", author = "Chernyak")
    public void insertUserToTestAuthentication(MongoTemplate mongoTemplate) {
        Set<Role> authorities = new HashSet<>();
        authorities.add(new Role("ROLE_USER"));

        User user = new User();
        user.setActivated(true);
        user.setAuthorities(authorities);
        user.setPassword("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        user.setUsername("vlad");

        mongoTemplate.save(user);
    }*/

    @ChangeSet(order = "003", id = "insertExerciseClientDetails", author = "Chernyak")
    public void insertExerciseClientDetails(MongoTemplate mongoTemplate) {
        AuthClientDetails browserClientDetails = new AuthClientDetails();
        browserClientDetails.setClientId("exercise-service");
        browserClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        browserClientDetails.setScopes("server");
        browserClientDetails.setGrantTypes("refresh_token,client_credentials");

        mongoTemplate.save(browserClientDetails);
    }

    @ChangeSet(order = "004", id = "insertUserClientDetails", author = "Chernyak")
    public void insertUserClientDetails(MongoTemplate mongoTemplate) {
        AuthClientDetails browserClientDetails = new AuthClientDetails();
        browserClientDetails.setClientId("user-service");
        browserClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        browserClientDetails.setScopes("server");
        browserClientDetails.setGrantTypes("refresh_token,client_credentials");

        mongoTemplate.save(browserClientDetails);
    }
}
