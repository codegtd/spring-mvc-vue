package ca.sait.web7.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "seq_prof")
    @SequenceGenerator(name = "seq_prof", sequenceName = "seq_prof")
    private Integer idprof;

    @NotNull(message = "{NotNull.Profile.descript}")
    @Column(name = "descr", nullable = true, length = 20)
    private String descript;

    public Profile(Integer idprof)
    {
        this.idprof = idprof;
    }
    
    

}
