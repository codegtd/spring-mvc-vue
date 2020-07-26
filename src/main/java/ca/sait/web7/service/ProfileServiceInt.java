
package ca.sait.web7.service;

import ca.sait.web7.entity.Profile;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProfileServiceInt {

    Profile add(@RequestBody Profile profile);

    List<Profile> getAllProfiles();

}