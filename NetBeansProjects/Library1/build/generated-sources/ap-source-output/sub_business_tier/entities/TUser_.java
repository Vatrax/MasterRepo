package sub_business_tier.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sub_business_tier.entities.TLoanData;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-27T07:15:14")
@StaticMetamodel(TUser.class)
public class TUser_ { 

    public static volatile SingularAttribute<TUser, Long> id;
    public static volatile CollectionAttribute<TUser, TLoanData> loans;
    public static volatile SingularAttribute<TUser, String> name;

}