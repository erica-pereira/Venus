package org.academiadecodigo.tailormoons.placeholder.persistence.dao.jpa;

import org.academiadecodigo.tailormoons.placeholder.persistence.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class JpaCustomerDao extends GenericJpaDao<Customer>  {

    public JpaCustomerDao() {
        super(Customer.class);
    }

    public Customer findByEmail(String email){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        query.select(root).where(builder.like(root.get("email"), email));

        return em.createQuery(query).getSingleResult();
    }
}
