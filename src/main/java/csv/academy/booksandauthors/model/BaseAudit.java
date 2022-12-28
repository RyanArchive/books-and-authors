package csv.academy.booksandauthors.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseAudit {

    @CreatedBy
    @Getter(AccessLevel.NONE)
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    @Getter(AccessLevel.NONE)
    private String lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;

}
