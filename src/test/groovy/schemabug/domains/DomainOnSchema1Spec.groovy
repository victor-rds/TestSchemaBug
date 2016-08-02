package schemabug.domains

import grails.test.mixin.*
import grails.test.mixin.gorm.Domain
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(DomainOnSchema1)
@Domain(DomainOnSchema1)
@TestMixin(HibernateTestMixin)
class DomainOnSchema1Spec extends Specification {

    void "test domain on non default schema"() {

        when:'New object is correctly created'
        def d = new DomainOnSchema1(description: 'Teste Schema1')

        then:'It should save without problem'
        d.save()
    }
}
