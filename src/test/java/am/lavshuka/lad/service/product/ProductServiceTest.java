package am.lavshuka.lad.service.product;

import am.lavshuka.lad.service.InitDBunit;
import org.dbunit.operation.DatabaseOperation;

/**
 * Created by David on 5/9/2018.
 */
public class ProductServiceTest extends InitDBunit {

    public ProductServiceTest(String name) {
        super(name);
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    private void addProductCategory() {

    }
}