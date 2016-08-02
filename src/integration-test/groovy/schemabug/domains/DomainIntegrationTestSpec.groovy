package schemabug.domains


import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.hibernate.SessionFactory
import spock.lang.*

@Integration
@Rollback
class DomainIntegrationTestSpec extends Specification {

    void "Test Schemas using Integration Test"() {
        when:'New object1 is correctly created'
        def d1 = new DomainOnSchema1(description: 'Teste Schema1')

        then:'It should save without problem'
        d1.save()

        when:'New object is correctly created'
        def d2 = new DomainOnSchema2(description: 'Teste Schema2')

        then:'It should save without problem'
        d2.save()
    }
}
