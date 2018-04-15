package domain.repository;

import data.OverDueLoanData;

import java.util.List;

/**
 * Created by sniper825 on 4/8/18.
 */
public interface OverDueLoanRepository {
    List <OverDueLoanData> getOverdueLoans();
}
