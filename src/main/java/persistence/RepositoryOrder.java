package persistence;

import model.Customer;
import model.Order;
import model.OrderLine;
import util.DbUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositoryOrder {
    private EntityManager entityManager;
    public RepositoryOrder(){
        entityManager = DbUtil.getEntityManager();
    }

    public void createOrder(Order order){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public Order addCustomer(Long id, Customer customer){
        entityManager.getTransaction().begin();
        Order order = findID(id);
        order.setCustomer(customer);
        entityManager.getTransaction().commit();
        return order;
    }

    public void addOrderLine(Long id, OrderLine orderLine){
        entityManager.getTransaction().begin();
        Order order = findID(id);
        if(order != null){
            order.getOrderLine().add(orderLine);
        }
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    public Order findID(Long id) {
        return entityManager.find(Order.class,id);
    }

    public List<Order> listAllOrders(){
        return entityManager
                .createQuery("FROM Order", Order.class)
                .getResultList();
    }

    public Order searchOrderByDate(String date){
        Query query = entityManager.createQuery("SELECT o from Order o WHERE o.orderDate = :date");
        query.setParameter("date",date);
        return (Order) query.getSingleResult();
    }

    public long countOrders(){
        Query query = entityManager.createQuery("SELECT count(*) FROM Order o");
        return (long) query.getSingleResult();
    }

    public Order searchOrderByCustomer(long id) {
        Query query = entityManager.createQuery("SELECT o FROM Order o JOIN Customer c Where c.id = :id ");
        query.setParameter("id", id);

        return (Order) query.getSingleResult();

    }
}
