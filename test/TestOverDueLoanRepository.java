import data.OverDueLoanData;
import domain.repository.OverDueLoanRepository;
import domain.repository.OverDueLoanRepositoryImpl;
import service.ServiceFactory;

import java.util.List;

/**
 * Created by sniper825 on 4/8/18.
 */
public class TestOverDueLoanRepository {

    public static void main(String[] args) {
        OverDueLoanRepository ODLR = ServiceFactory.getOverDueLoanRepository();
        List<OverDueLoanData> overdueLoans = ODLR.getOverdueLoans();
        for(OverDueLoanData x: overdueLoans){
            System.out.println(x);
        }
    }
}
