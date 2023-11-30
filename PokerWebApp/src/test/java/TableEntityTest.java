import com.example.pokerwebapp.model.dao.GenericDAO;
import com.example.pokerwebapp.model.dao.TableDAO;
import com.example.pokerwebapp.model.entity.TableEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TableEntityTest {
    TableDAO dao = new TableDAO();

    @Test public void TableTesting(){
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        TableEntity t = new TableEntity();
        t.setName("test_table");
        dao.create(t);
        assertNotNull(t.getID());
        dao.deleteAll();
    }

    @Test public void TableRead(){
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        TableEntity t = new TableEntity();
        t.setName("test_table");
        dao.create(t);
        assertNotNull(dao.read(t));
        dao.deleteAll();
    }
}
