package ca.sait.web7.service;

import ca.sait.web7.entity.Profile;
import ca.sait.web7.exception.exceptionBasic;
import ca.sait.web7.entity.Users;
import ca.sait.web7.repository.ProfileRepository;
import ca.sait.web7.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserServiceImpl implements UserServiceInt {

    @Autowired
    private UserRepository userReposit;

    @Autowired
    private ProfileRepository profReposit;
    
    private Integer ADMINPROFILE = 1;
    private Integer PILOTPROFILE = 2;
    private Integer USERPROFILE = 3;

    @Override
    public Users save(@RequestBody Users user)
    {
        if (user.getProfile() == null) {
            Optional<Profile> profOptional = profReposit.findById(USERPROFILE);
            
            Profile profileDefault = new Profile();
            profileDefault.setIdprof(profOptional.get().getIdprof());
            profileDefault.setDescript(profOptional.get().getDescript());
            
            user.setProfile(profileDefault);
        }
        
        
        return userReposit.save(user);
    }

    @Override
    public Users update(@RequestBody Users user)
    {
        return this.save(user);
    }

    @Override
    public void delete(@PathVariable("id") Integer idMethod)
    {
        userReposit.deleteById(idMethod);
    }

    @Override
    public Users findUserById(@PathVariable("id") Integer id)
    {
        Optional<Users> user = userReposit.findById(id);

        Users userReturn = new Users();
        userReturn.setIduser(user.get().getIduser());
        userReturn.setName(user.get().getName());
        userReturn.setPassword(user.get().getPassword());
        userReturn.setProfile(user.get().getProfile());
        userReturn.add();

        return userReturn;
    }

    @Override
    public Page<Users> finAllUsers(Pageable pageable)
    {
        return userReposit.findAll(pageable);
    }

    @Override
    public List<Users> findByName(@PathVariable("name") String name)
    {
        return userReposit.findByNameOrderByIduserDesc(name);
    }

    @Override
    public List<Users> findByNameAndPassword(@RequestBody Users us)
    {
        return userReposit.findByNameAndPassword(us.getName(), us.getPassword());
    }

    @Override
    public List<Users> findByNamePassword(@RequestParam("name") String name, @RequestParam("pass") String pass)
    {
        return userReposit.findByNameAndPassword(name, pass);
    }

    @Override
    public List<Users> authentic(@RequestBody Users user) throws exceptionBasic
    {
        List<Users> li = userReposit.findByNameAndPassword(user.getName(), user.getPassword());

        if (li == null || li.isEmpty()) {
            throw new exceptionBasic();
        }
        return li;
    }

    @Override
    public List<Users> findByUserProfilesCQ(@RequestParam("desc") String desc)
    {
        return userReposit.findUserProfileCustomQuery(desc);
    }

    @Override
    public List<Users> findByUserProfilesSD(@RequestParam("desc") String desc)
    {
        return userReposit.findByProfileDescript(desc);
    }

}
