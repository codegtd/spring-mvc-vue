package ca.sait.web7.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user")
    private Integer iduser;
    
    @NotEmpty
    @Size(min = 3, max = 30)
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    
    @NotNull(message = "{NotNull.Users.password}")
    @Size(min = 3, max = 30)
    @Column(name = "pass", nullable = false, length = 30)
    private String password;
    
    @Valid
    @JoinColumn
    @ManyToOne
    private Profile profile;

}
