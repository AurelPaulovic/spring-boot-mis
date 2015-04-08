package sk.anext.msi.ui.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;

import sk.anext.msi.bo.UserType;
import sk.anext.msi.bo.neo.domain.NeoType;
import sk.anext.msi.bo.neo.domain.NeoUser;
import sk.anext.msi.repository.neo.NeoTypeRepository;
import sk.anext.msi.repository.neo.NeoUserRepository;
import sk.anext.msi.ui.form.UserForm;

@Service("NeoUserService")
public class NeoUserServiceImpl implements NeoUserService {
    private static final Log log = LogFactory.getLog(NeoUserServiceImpl.class);

    @Autowired
    private NeoUserRepository userRepository;

    @Autowired
    private NeoTypeRepository typeRepository;

    @Override
    public UserForm createNewUser(String name, Integer age, Boolean isEnabled, UserType type) {
        log.info("Create new user with name - " + name + " and age - " + age + " and type - " + type);

        NeoUser create = new NeoUser(name, age, isEnabled);
        NeoType neoType = null;

        if (UserType.ADMIN.equals(type)) {
            neoType = typeRepository.findByCode(NeoType.Type.ADMIN.getCode());
        } else {
            neoType = typeRepository.findByCode(NeoType.Type.USER.getCode());
        }

        create.setType(neoType);

        NeoUser user = userRepository.save(create);
        log.info("Created new user " + user);

        return new UserForm(user.getName(), user.getAge(), user.getEnabled());
    }

    @Override
    public List<UserForm> getUsers() {
        log.info("Getting list of all users");

        Result<NeoUser> users = userRepository.findAll();
        List<UserForm> userForms = new ArrayList<UserForm>();

        for (NeoUser user : users) {
            userForms.add(new UserForm(user.getName(), user.getAge(), user.getEnabled()));
        }

        return userForms;
    }

    @Override
    public Long getCountOfUsers() {
        return userRepository.count();
    }
}
