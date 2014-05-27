package sub_business_tier.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TUser;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-27T07:15:14")
@StaticMetamodel(TLoanData.class)
public class TLoanData_ { 

    public static volatile SingularAttribute<TLoanData, Long> id;
    public static volatile SingularAttribute<TLoanData, Date> expirationTime;
    public static volatile SingularAttribute<TLoanData, TBook> book;
    public static volatile SingularAttribute<TLoanData, TUser> user;

}