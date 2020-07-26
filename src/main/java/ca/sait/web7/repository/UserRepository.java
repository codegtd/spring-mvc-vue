package ca.sait.web7.repository;

import ca.sait.web7.entity.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer>{
    
    public List<Users> findByNameOrderByIduserDesc(String name);
    //findByNameIgnorecaseOrderByNameAsc
    
    public List<Users> findByNameAndPassword(String name, String password);

    @Query("select u from Users u where u.profile.descript= :d")
    public List<Users> findUserProfileCustomQuery(@Param("d") String descprof);
    
    public List<Users> findByProfileDescript(String desc);
}
