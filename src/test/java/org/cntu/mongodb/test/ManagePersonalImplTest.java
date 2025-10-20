package org.cntu.mongodb.test;

import org.cntu.code.ManagePersonalImpl;
import org.cntu.model.SalesMan;
import org.cntu.model.SocialPerformanceRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagePersonalImplTest {
    private ManagePersonalImpl manager;

    @BeforeEach
    void setUp() {
        manager = new ManagePersonalImpl();
    }

    @Test
    void insertSalesMan() {
        SalesMan s1 = new SalesMan("Max", "Black", 0);
        manager.createSalesMan(s1);

        assertEquals("Max" , manager.readSalesMan(0).getFirstname());

        manager.deleteSalesMan(0);
    }

    @Test
    void insertSocialPerformanceRecord() {
        SalesMan s1 = new SalesMan("Max", "Black", 0);
        manager.createSalesMan(s1);
        SocialPerformanceRecord r1 = new SocialPerformanceRecord(0, "Exceeded sales target", 9.5, 8.8, 2024);
        manager.addSocialPerformanceRecord(r1, s1);

        assertEquals("Exceeded sales target" , manager.readSocialPerformanceRecord(s1).getFirst().getGoalDescription());

        manager.deleteSalesMan(0);
    }

    @Test
    void updateSocialPerformanceRecord() {
        SalesMan s1 = new SalesMan("Max", "Black", 0);
        manager.createSalesMan(s1);
        SocialPerformanceRecord r1 = new SocialPerformanceRecord(0, "Exceeded sales target", 9.5, 8.8, 2024);
        manager.addSocialPerformanceRecord(r1, s1);

        manager.updateSalesmanSocialPerformanceRecord(0, "Exceeded sales target", 8.5, 7.8);

        assertEquals(8.5, manager.readSocialPerformanceRecord(s1).getFirst().getValueSupervisor());

        manager.deleteSalesMan(0);
    }
}
