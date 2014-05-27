package sub_business_tier.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sub_business_tier.entities.TBook;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-27T07:15:14")
@StaticMetamodel(TTitle_book.class)
public class TTitle_book_ { 

    public static volatile SingularAttribute<TTitle_book, Long> id;
    public static volatile SingularAttribute<TTitle_book, String> author;
    public static volatile CollectionAttribute<TTitle_book, TBook> books;
    public static volatile SingularAttribute<TTitle_book, String> title;
    public static volatile SingularAttribute<TTitle_book, String> ISBN;
    public static volatile SingularAttribute<TTitle_book, String> publisher;

}