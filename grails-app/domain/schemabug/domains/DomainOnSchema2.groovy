package schemabug.domains

class DomainOnSchema2 {

    String description

    static mapping = {
        table schema:'schema2'
        version false
    }

    static constraints = {
        description unique: true, blank:false
    }
}

