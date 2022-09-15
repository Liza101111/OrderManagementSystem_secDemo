package persistence;

import model.Customer;
import util.DbUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositoryCustomer {
    private EntityManager entityManager;

    public RepositoryCustomer() {
        entityManager = DbUtil.getEntityManager();
    }

    public Customer createCustomer(Customer customer){
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return customer;
    }

    public Customer findID(long id){
        return entityManager.find(Customer.class, id);
    }


    public List<Customer> listAllCustomers(){
        return entityManager
                .createQuery("FROM Customer",Customer.class)
                .getResultList();
    }

    public Customer findCustomerById(long id) {
        Query query = entityManager.createQuery("SELECT c from Customer c WHERE c.id = :id");
        query.setParameter("id",id);
        return (Customer) query.getSingleResult();

    }

    public Customer updateCustomer(Customer customer) {
        Customer customerToUpdate = findID(customer.getId());
            entityManager.getTransaction().begin();
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setPhoneNum(customer.getPhoneNum());
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setLastName(customer.getLastName());
            entityManager.getTransaction().commit();
            return customerToUpdate;
    }

    public void deleteCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(customer));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }


}
