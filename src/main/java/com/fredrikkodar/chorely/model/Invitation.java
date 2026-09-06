package com.fredrikkodar.chorely.model;

import com.fredrikkodar.chorely.enums.InvitationStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "invitation_type")
@Table(name = "invitations")
public abstract class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String invitedEmail;
    private Integer invitedById;
    private InvitationStatus status;

    private String tokenHash;

    @CreationTimestamp
    private Date createdAt;
    private Date updatedAt;
    private Date expiresAt;

    public Invitation() {
    }

    public Invitation(String invitedEmail, Integer invitedById, String tokenHash) {
        this.invitedEmail = invitedEmail;
        this.invitedById = invitedById;
        this.status = InvitationStatus.PENDING;
        this.tokenHash = tokenHash;
        this.expiresAt = Date.from(new Date().toInstant().plusSeconds(60 * 15));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvitedEmail() {
        return invitedEmail;
    }

    public void setInvitedEmail(String invitedEmail) {
        this.invitedEmail = invitedEmail;
    }

    public Integer getInvitedById() {
        return invitedById;
    }

    public void setInvitedById(Integer invitedById) {
        this.invitedById = invitedById;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

}


