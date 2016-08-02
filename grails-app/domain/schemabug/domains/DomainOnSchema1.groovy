package schemabug.domains

class DomainOnSchema1 {

    String description

    static mapping = {
        table schema:'schema1'
        version false
    }

    static constraints = {
        description unique: true, blank:false
    }
}
