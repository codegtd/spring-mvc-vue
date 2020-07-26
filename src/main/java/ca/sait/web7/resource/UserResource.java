package ca.sait.web7.resource;

import ca.sait.web7.exception.exceptionBasic;
import ca.sait.web7.entity.Users;
import ca.sait.web7.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.sait.web7.service.UserServiceInt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("v1/usuarios")
@Api(value = "API REST paulao - users area")
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
//@CrossOrigin(origins = {"*"})
public class UserResource {

    @Autowired
    private UserServiceInt userService;
    

    @ApiOperation(value = "Save a new user")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Users save(@Valid @RequestBody Users user)
    {
        return userService.save(user);
    }

    @ApiOperation(value = "Update a new user")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Users update(@Valid @RequestBody Users user)
    {
        return userService.save(user);
    }

    @ApiOperation(value = "Delete a new user")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id)
    {
        verifyIfIdUserExists(id);
        userService.delete(id);
    }

    @ApiOperation(value = "Get all users")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> finAllUsers(Pageable pageable) throws NoSuchFieldException
    {
        Page<Users> list = userService.finAllUsers(pageable);

        list.forEach(itemList -> {

            try {
                itemList.add(linkTo(methodOn(UserResource.class).findUserById(itemList.getIduser(), pageable)).withSelfRel());
            } catch (NoSuchFieldException exception) {
                Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, exception);
            }
        });

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Users> findUserById(@PathVariable("id") Integer id, Pageable pageable) throws NoSuchFieldException
    {
        verifyIfIdUserExists(id);
        Users user = userService.findUserById(id);
        user.add(
                linkTo(methodOn(UserResource.class)
                        .finAllUsers(pageable))
                        .withSelfRel());
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by name")
    @GetMapping(path = "/name/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> findByName(@PathVariable("name") String name)
    {
        return userService.findByName(name);
    }

    @ApiOperation(value = "Get user by authentication by BodyJsonObject")
    @GetMapping(path = "/authenticationBody", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> findByNameAndPassword(@RequestBody Users us)
    {

        return userService.findByNameAndPassword(us);
    }

    @ApiOperation(value = "Get user by authentication By parameter")
    @GetMapping(path = "/authenticationParam", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> findByNamePassword(@RequestParam("name") String name, @RequestParam("pass") String pass)
    {
        return userService.findByNamePassword(name, pass);
    }

    @ApiOperation(value = "Get user by authentication with username and password")
    @PostMapping(path = "/authentic", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> authentic(@RequestBody Users userBody) throws exceptionBasic
    {
        List<Users> list = userService.findByNameAndPassword(userBody);

        if (list == null || list.isEmpty()) {
            throw new exceptionBasic();
        }
        return list;
    }

    @ApiOperation(value = "Get user by profile with username using custom queries")
    @GetMapping(path = "/userProfilesCQ", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> findByUserProfilesCQ(@RequestParam("desc") String desc)
    {
        return userService.findByUserProfilesCQ(desc);
    }

    @ApiOperation(value = "Get user by profile with username using Spring Data")
    @GetMapping(path = "/userProfilesSD", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> findByUserProfilesSD(@RequestParam("desc") String desc)
    {
        return userService.findByUserProfilesSD(desc);
    }

    public void verifyIfIdUserExists(Integer id)
    {
        Users userTest = new Users();
        try {
            userTest = userService.findUserById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User ID not found in this table!");
        }
    }

}
