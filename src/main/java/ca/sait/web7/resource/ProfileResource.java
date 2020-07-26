/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.web7.resource;

import ca.sait.web7.entity.Profile;
import ca.sait.web7.service.ProfileServiceInt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1/usuarios/profile")
@Api(value = "API REST paulao - profiles area")
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
//@CrossOrigin(origins = {"*"})
public class ProfileResource {

    @Autowired
    private ProfileServiceInt service;

    @ApiOperation(value = "Save a new profile")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Profile add(@RequestBody Profile profile)
    {

        return service.add(profile);
    }

    @ApiOperation(value = "Get all profiles")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Profile> getAllProfiles()
    {
        return service.getAllProfiles();
    }

}
