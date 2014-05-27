package sub_business_tier.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sub_business_tier.entities.TLoanData;
import sub_business_tier.entities.TTitle_book;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-27T07:15:14")
@StaticMetamodel(TBook.class)
public class TBook_ { 

    public static volatile SingularAttribute<TBook, Long> id;
    public static volatile SingularAttribute<TBook, TLoanData> mLoan_data;
    public static volatile SingularAttribute<TBook, Integer> number;
    public static volatile SingularAttribute<TBook, TTitle_book> mTitle_book;

}