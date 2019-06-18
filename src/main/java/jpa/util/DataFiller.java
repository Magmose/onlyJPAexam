package jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.entity.Member;
import jpa.entity.Organization;
import jpa.facade.DBFacade;

/**
 *
 * @author Magnus
 */
public class DataFiller {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        DBFacade df = new DBFacade(emf);

        addMember(df);
        addOrganization(df);
        assignMembersToOrganization(df);
    }

    public static void addOrganization(DBFacade df) {
        df.addOrganization(new Organization("FootballWinners", "Sports Club", "We win everytime", "www.wewin.com"));
        df.addOrganization(new Organization("UltimateProgramming", "Computer Seince", "We cde", "www.code.com"));
        df.addOrganization(new Organization("JumpNation", "Jumping lovers", "We just jump", "www.jump.com"));
    }

    public static void addMember(DBFacade df) {
        df.addMember(new Member("mad", "magnus@mail.com"));
        df.addMember(new Member("anders", "anders@mail.com"));
        df.addMember(new Member("kaj", "kaj@mail.com"));
        df.addMember(new Member("oskar", "oskar@mail.com"));
        df.addMember(new Member("mille", "mille@mail.com"));
    }

    public static void assignMembersToOrganization(DBFacade df) {
        df.assignMemberToOrganization("kaj", 3);
        df.assignMemberToOrganization("anders", 3);
        df.assignMemberToOrganization("mad", 3);
        df.assignMemberToOrganization("oskar", 2);
        df.assignMemberToOrganization("mille", 1);
    }
}
