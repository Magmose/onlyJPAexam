package jpa.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.entity.Member;
import jpa.entity.Organization;

/**
 *
 * @author Magnus
 */
public class DBFacade {

    EntityManagerFactory emf;

    public DBFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Member addMember(Member member) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
            return member;
        } finally {
            em.close();
        }
    }

    public void deleteMember(String userName) {
        EntityManager em = emf.createEntityManager();
        try {
            Member toRemove = (Member) em.createQuery("SELECT m FROM Member m WHERE m.userName=:userName").setParameter("userName", userName).getSingleResult();
            em.getTransaction().begin();
            em.remove(toRemove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Organization addOrganization(Organization organization) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(organization);
            em.getTransaction().commit();
            return organization;
        } finally {
            em.close();
        }
    }

    public void deleteOrganization(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Organization toRemove = em.find(Organization.class, id);
            em.getTransaction().begin();
            em.remove(toRemove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Organization updateOrganization(int id, Organization newOrganization) {
        EntityManager em = emf.createEntityManager();
        try {
            Organization oldOrganization = em.find(Organization.class, id);
            em.getTransaction().begin();
            oldOrganization.setDescription(newOrganization.getDescription());
            oldOrganization.setName(newOrganization.getName());
            oldOrganization.setType(newOrganization.getType());
            oldOrganization.setUrl(newOrganization.getUrl());
            em.getTransaction().commit();
            return newOrganization;
        } finally {
            em.close();
        }
    }

    public void assignMemberToOrganization(String userName, int id) {
        EntityManager em = emf.createEntityManager();
        Organization organization = em.find(Organization.class, id);
        Member member = em.find(Member.class, userName);
        
        member.addOrganization(organization);
        organization.addMember(member);
        try {
            em.getTransaction().begin();
            em.merge(member);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Organization getOrganizationById(int id) {
        EntityManager em = emf.createEntityManager();
        //Method one
        return (Organization) em.find(Organization.class, id);

        //Method two
//        return (Organization) em.createQuery("SELECT o FROM Organization o WHERE o.id=:id").setParameter("id", id).getSingleResult();
    }

    public List<Organization> getAllOrganizations() {
        EntityManager em = emf.createEntityManager();
        return (List<Organization>) em.createQuery("SELECT o FROM Organization o").getResultList();
    }

    public List<Organization> getAllOrganizationsByName(String name) {
        EntityManager em = emf.createEntityManager();
        return (List<Organization>) em.createQuery("SELECT o FROM Organization o WHERE o.name=:name").setParameter("name", name).getResultList();
    }

    public Organization getOrganizationsByName(String name) {
        EntityManager em = emf.createEntityManager();
        return (Organization) em.createQuery("SELECT o FROM Organization o WHERE o.name=:name").setParameter("name", name).getSingleResult();
    }

    public List<Member> getAllMembersInOrganization(String name) {
        EntityManager em = emf.createEntityManager();
        return (List<Member>) em.createQuery("SELECT m FROM Member m JOIN m.organizations o WHERE o.name=:name")
                .setParameter("name", name).getResultList();
    }

    public Organization getOrganizationWithMostMembers(){
        EntityManager em = emf.createEntityManager();
//        return (Organization) em.createQuery("SELECT o FROM Organization o WHERE o.id=(SELECT MAX(m.userName) FROM Organization oo JOIN oo.members m)").getSingleResult();
//        return (Organization) em.createQuery("SELECT MAX(o) FROM Member m JOIN m.organizations o").getSingleResult();
        return (Organization) em.createQuery("SELECT MAX(o.id) FROM Organization o, Member m JOIN m.organizations om WHERE o.id = om.id++ ").getSingleResult();
//    return null;
    }
}
