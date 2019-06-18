package jpa.util;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.entity.Member;
import jpa.entity.Organization;
import jpa.facade.DBFacade;

/**
 *
 * @author Magnus
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        DBFacade df = new DBFacade(emf);

//        df.addMember(new Member("test", "exam"));
//        df.deleteMember("test");
//        df.addOrganization(new Organization("kage", "mad", "vi er sultene", "www.kage.com"));
//        df.deleteOrganization(4);
//        df.updateOrganization(3, new Organization("FootballWinners", "Sports Club", "We win everytime", "www.wewin.com"));
//        df.assignMemberToOrganization("kaj", 2);
//        System.out.println(df.getOrganizationById(3).getName());
//        getAllOrganizations(df);
        getAllOrganizationsByName(df);
//        getAllMembersInOrganization(df);
//        System.out.println(df.getOrganizationWithMostMembers().getName());

    }

    public static void getAllMembersInOrganization(DBFacade df) {
        List<Member> members = df.getAllMembersInOrganization("UltimateProgramming");
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).getUserName());
        }
    }

    public static void getAllOrganizationsByName(DBFacade df) {
        List<Organization> organizations = df.getAllOrganizationsByName("UltimateProgramming");
        for (int i = 0; i < organizations.size(); i++) {
            System.out.println(organizations.get(i).getName());
        }
    }

    public static void getAllOrganizations(DBFacade df) {
        List<Organization> organizations = df.getAllOrganizations();
        for (int i = 0; i < organizations.size(); i++) {
            System.out.println(organizations.get(i).getName());
        }
    }

}
