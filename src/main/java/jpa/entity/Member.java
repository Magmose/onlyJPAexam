/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Magnus
 */
@Entity
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String userName;
    private String email;
    
    @ManyToMany
    private List<Organization> organizations;
    
    @ManyToMany
    private List<Activity> activitys;
    
    public Member() {
    }
    
    public Member(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(Organization organizations) {
        this.organizations.add(organizations);
    }

    public List<Activity> getActivitys() {
        return activitys;
    }

    public void addActivitys(Activity activity) {
        this.activitys.add(activity);
    }

    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
