package am.lavshuka.lad.dao.product;

import am.lavshuka.lad.model.product.ProductBrand;
import am.lavshuka.lad.model.product.ProductCategory;
import am.lavshuka.lad.model.product.ProductModel;
import am.lavshuka.lad.model.product.ProductType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */

public class ProductDao {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    public ProductDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addProduct(ProductModel productModel) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(productModel);
        tx.commit();
        session.close();
    }

    public ProductModel findByVendorCode(String vendorCode) throws SQLException {

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from ProductModel where vendorCode = :code");
        query.setParameter("code", vendorCode);

        ProductModel productModel = (ProductModel) query.uniqueResult();

        tx.commit();
        session.close();

        return productModel;
    }

    public List<ProductModel> findAllProducts() throws SQLException {

        List<ProductModel> list;
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from ProductModel ");
        list = query.list();

        tx.commit();
        session.close();

        return list;
    }

    public List<ProductModel> findProductsByCategory(ProductCategory productCategory) throws SQLException {

        List<ProductModel> list = new ArrayList<>();

        list.addAll(productCategory.getProductModelSet());

        return list;
    }

    public List<ProductModel> findProductsByType(ProductType productType) throws SQLException {

        List<ProductModel> list = new ArrayList<>();

        list.addAll(productType.getProductModelSet());

        return list;
    }

    public List<ProductModel> findProductsByBrand(ProductBrand productBrand) throws SQLException {

        List<ProductModel> list = new ArrayList<>();

        list.addAll(productBrand.getProductModelSet());

        return list;
    }

    public void changeProductData(ProductModel productModel, Double price, String description) throws SQLException {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        if (productModel == null || (price == null && description == null)) {
            throw new IllegalArgumentException();
        }



        if (price != null) {
            productModel.setPrice(price);
        }

        if (description != null) {
            productModel.setDescription(description);
        }

        session.merge(productModel);

        tx.commit();
        session.close();
    }

    public void removeProduct(String vendorCode) throws SQLException {
        ProductModel model = findByVendorCode(vendorCode);
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.delete(model);
        tx.commit();
        session.close();
    }
}