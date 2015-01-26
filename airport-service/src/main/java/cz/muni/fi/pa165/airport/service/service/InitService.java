package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.dao.dao.IUserDAO;
import cz.muni.fi.pa165.airport.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

/**
 * Service for initializing app after start
 *
 * @author Mariia Schevchenko
 */
@Service
public class InitService {

    private static final String ADMIN_EMAIL = "admin@airport.com";
    private static final String REST_EMAIL = "rest@airport.com";


    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private JpaTransactionManager tm;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminUser() {

        // Transaction manager may be not loaded yet due to
        // @PostContruct. Run transaction manually
        TransactionTemplate tmpl = new TransactionTemplate(tm);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User userByEmail = userDAO.getUserByEmail(ADMIN_EMAIL);
                if (userByEmail == null) {
                    User user = new User();
                    user.setName("Admin");
                    user.setEmail(ADMIN_EMAIL);
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setPrivileged(true);

                    userDAO.create(user);
                }

                User restUser = userDAO.getUserByEmail(REST_EMAIL);
                if (restUser == null) {
                    User user = new User();
                    user.setName("REST app");
                    user.setEmail(REST_EMAIL);
                    user.setPassword(passwordEncoder.encode("rest"));
                    user.setPrivileged(true);

                    userDAO.create(user);
                }
            }
        });


    }
}
