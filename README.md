
Hi, I'm having a little problem when using `HibernateTestMixin` in unit tests, here is the full story:

When I test Domain classes that has
```groovy
static mapping = {
        table schema:'schema_name'
    }
```
the h2 mem datasource doesn't create the schema needed and throws `org.h2.jdbc.JdbcSQLException: Schema "SCHEMA_NAME" not found` when any query is called, but to be clear I don't think is grails fault, just the default behavior.
But to overcome this issue I added `INIT=create schema if not exists schema_name\;` to the h2 connection URL, the this only works with Integration Tests, when I run unit tests with `HibernateTestMixin` I still got the same errors than before, this is because the mixin override the `application.yml` settings.

### Task List
- [X] Steps to reproduce provided
- [X] Stacktrace (if present) provided
- [X] Example that reproduces the problem uploaded to Github
- [X] Full description of the issue provided (see below)

### Steps to Reproduce

Create a simple domain class with `table schema:corp_name`
Create a test using `HibernateTestMixin`
Run test and call `validate()`

### Expected Behaviour

Test Success

### Actual Behaviour

Test fails with org.h2.jdbc.JdbcSQLException
Stacktrace:
```
org.springframework.jdbc.UncategorizedSQLException: Hibernate operation: could not prepare statement; uncategorized SQLException for SQL [select this_.id as id1_0_0_, this_.description as descript2_0_0_ from schema1.domain_on_schema1 this_ where this_.description=?]; SQL state [90079]; error code [90079]; Schema "SCHEMA1" not found; SQL statement:
select this_.id as id1_0_0_, this_.description as descript2_0_0_ from schema1.domain_on_schema1 this_ where this_.description=? [90079-191]; nested exception is org.h2.jdbc.JdbcSQLException: Schema "SCHEMA1" not found; SQL statement:
select this_.id as id1_0_0_, this_.description as descript2_0_0_ from schema1.domain_on_schema1 this_ where this_.description=? [90079-191]
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:84)
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)
	at org.grails.orm.hibernate.GrailsHibernateTemplate.convertJdbcAccessException(GrailsHibernateTemplate.java:621)
	at org.grails.orm.hibernate.GrailsHibernateTemplate.convertHibernateAccessException(GrailsHibernateTemplate.java:609)
	at org.grails.orm.hibernate.GrailsHibernateTemplate.doExecute(GrailsHibernateTemplate.java:200)
	at org.grails.orm.hibernate.GrailsHibernateTemplate.execute(GrailsHibernateTemplate.java:140)
	at org.grails.orm.hibernate.GrailsHibernateTemplate.execute(GrailsHibernateTemplate.java:110)
	at org.grails.orm.hibernate.validation.UniqueConstraint.processValidate(UniqueConstraint.java:148)
	at grails.validation.AbstractConstraint.validate(AbstractConstraint.java:107)
	at grails.validation.ConstrainedProperty.validate(ConstrainedProperty.java:979)
	at org.grails.validation.GrailsDomainClassValidator.validatePropertyWithConstraint(GrailsDomainClassValidator.java:211)
	at org.grails.validation.GrailsDomainClassValidator.validate(GrailsDomainClassValidator.java:81)
	at org.grails.orm.hibernate.AbstractHibernateGormInstanceApi.save(AbstractHibernateGormInstanceApi.groovy:122)
	at org.grails.datastore.gorm.GormInstanceApi.save(GormInstanceApi.groovy:115)
	at org.grails.datastore.gorm.GormEntity$Trait$Helper.save(GormEntity.groovy:98)
	at schemabug.domains.DomainOnSchema1Spec.test domain on non default schema(DomainOnSchema1Spec.groovy:22)
Caused by: org.h2.jdbc.JdbcSQLException: Schema "SCHEMA1" not found; SQL statement:
select this_.id as id1_0_0_, this_.description as descript2_0_0_ from schema1.domain_on_schema1 this_ where this_.description=? [90079-191]...
```

### Environment Information

- **Operating System**: Windows 10 x64
- **Grails Version:** 3.1.9
- **JDK Version:** 1.8.0_92

### Example Application
