/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_tier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sub_business_tier.entities.TTitle_book;

public class TTitle_bookController {

    private EntityManagerFactory emf = null;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Library1PU2");
        }
        return emf.createEntityManager();
    }

    public boolean addTTitle_book(TTitle_book title_book) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(title_book);
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }

    public boolean addTTitle_books(ArrayList<TTitle_book> titles) {
        EntityManager em = getEntityManager();
        TTitle_book newTTitle_book = null;
        try {
            Iterator it = titles.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                newTTitle_book = (TTitle_book) it.next();
                if (newTTitle_book.getId() == null) {
                    em.persist(newTTitle_book);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }

    public TTitle_book[] getTTitle_books_() {
        return (TTitle_book[]) getTTitle_books().toArray(new TTitle_book[0]);
    }

    public List<TTitle_book> getTTitle_books() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from TTitle_book as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
