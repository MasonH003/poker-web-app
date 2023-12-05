import com.example.pokerwebapp.model.dao.GenericDAO;
import com.example.pokerwebapp.model.dao.TableDAO;
import com.example.pokerwebapp.model.entity.TableEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        TableEntity t2 = new TableEntity();
        t2.setName("test_table23");
        t2.setPlayers(2);
        dao.create(t2);
        assertNotNull(dao.read(t2));
        dao.deleteAll();
    }

    @Test public void TableUpdate(){
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        TableEntity t2 = new TableEntity();
        t2.setName("test_table23");
        t2.setPlayers(2);
        dao.create(t2);
        assertAll(()->assertNotNull(dao.updatePlayers(t2,3)),
                ()->assertEquals(3,t2.getPlayers()));
        dao.deleteAll();
    }

    @Test public void TableDelete(){
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        TableEntity t2 = new TableEntity();
        t2.setName("test_table24");
        dao.create(t2);
        System.out.println(t2.getID());
        dao.delete(t2.getID());
        assertNull(dao.read(t2));
    }

    @Test public void TableFind(){
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        TableEntity t2 = new TableEntity();
        TableEntity t3 = new TableEntity();
        t2.setName("test_table2");
        t3.setName("test_table3");
        dao.create(t2);
        dao.create(t3);
        List<TableEntity> t4;
        assertNotNull(dao.find("test_table2"));
        dao.deleteAll();
    }
}
