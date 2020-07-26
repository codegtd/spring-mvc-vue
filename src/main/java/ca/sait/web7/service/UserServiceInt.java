
package ca.sait.web7.service;

import ca.sait.web7.entity.Users;
import ca.sait.web7.exception.exceptionBasic;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserServiceInt{

    List<Users> authentic(@RequestBody Users user) throws exceptionBasic;

    void delete(@PathVariable(value = "id") Integer idMethod);

    Page<Users> finAllUsers(Pageable pageable);

    List<Users> findByName(@PathVariable(value = "name") String name);

    List<Users> findByNameAndPassword(@RequestBody Users us);

    List<Users> findByNamePassword(@RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass);

    List<Users> findByUserProfilesCQ(@RequestParam(value = "desc") String desc);

    List<Users> findByUserProfilesSD(@RequestParam(value = "desc") String desc);

    Users findUserById(@PathVariable(value = "id") Integer id);

    Users save(@RequestBody Users user);

    Users update(@RequestBody Users user);

}