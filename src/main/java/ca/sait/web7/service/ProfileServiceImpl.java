package ca.sait.web7.service;

import ca.sait.web7.entity.Profile;
import ca.sait.web7.repository.ProfileRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProfileServiceImpl implements ProfileServiceInt {

    @Autowired
    private ProfileRepository profRep;

    @Override
    public Profile add(@RequestBody Profile profile)
    {
        return profRep.save(profile);
    }

    @Override
    public List<Profile> getAllProfiles()
    {
        return profRep.findAll();
    }
}
