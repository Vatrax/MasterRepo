/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 *
 * @author ciesiolk
 */
@Entity
public class TLoanData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private TUser user;
    @OneToOne
    private TBook book;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationTime;

    public TLoanData() {
        id = null;
    }
    
    @Override
    public String toString() {
        return "TLoan(" + "\n\tu={" + user.toString() + "}, \n\tb={" + book.toString() + "}, \n\texpirationTime={" + expirationTime.toString() + "}\n)";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(TUser u) {
        this.user = u;
    }

    public void setBook(TBook b) {
        this.book = b;
    }

    public TUser getUser() {
        return user;
    }

    public TBook getBook() {
        return book;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TLoanData other = (TLoanData) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        if (this.book != other.book && (this.book == null || !this.book.equals(other.book))) {
            return false;
        }
        return true;
    }

    
}
