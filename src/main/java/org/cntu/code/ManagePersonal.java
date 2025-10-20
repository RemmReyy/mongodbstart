package org.cntu.code;

import org.cntu.model.SalesMan;
import org.cntu.model.SocialPerformanceRecord;

import java.util.List;

/**
 * Code lines are commented for suppressing compile errors.
 * Are there any CRUD-operations missing?
 * Answer: update and delete
 */

public interface ManagePersonal {
    public void createSalesMan( SalesMan record );

    public void addSocialPerformanceRecord(SocialPerformanceRecord record , SalesMan salesMan );
    // Remark: an SocialPerformanceRecord corresponds to part B of a bonus sheet

    public SalesMan readSalesMan( int sid );

    public List<SalesMan> readAllSalesMen();

    public List<SocialPerformanceRecord> readSocialPerformanceRecord( SalesMan salesMan );
    // Remark: How do you integrate the year?
    // Answer:
    public List<SocialPerformanceRecord> readSocialPerformanceRecord( SalesMan salesMan, int year );

    public void updateSalesmanSocialPerformanceRecord(int sid, String description, double newValueSupervisor, double newValuePeerGroup);

    public void deleteSalesMan(int sid);

    public void deleteSalesManSocialPerformanceRecord(int sid, String description);

}
