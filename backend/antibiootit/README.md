# Backend - Antibiootit

## Vaatimukset

- Java 17
- Maven 4.0
- Spring 3.0.2
- MongoDB 4.8.2
- springdoc-openapi v2.0.2


## Ympäristöasetukset
Palvelin vaatii toimiakseen ympäristömuuttujaksi `apikey` muuttujan. Se voi olla määritettynä joko ympäristömuuttujana tai määritetty tiedostossa `secrets.properties` projektin juurikansiossa.
Tätä tiedostoa **ei tule** lisätä versionhallintaan.
Kysy tarvittaessa `apikey` arvoa ylläpitäjältä.

### Hostauspalvelu
Hostauspalvelussa `apikey` muuttuja määritetään ympäristömuuttujana.

### Paikallinen debuggaus
Paikallisesti muuttujan arvo kannattaa määrittää `secrets.properties` tiedostossa:
```
apikey=API-KEY-VALUE
```

Toinen vaihtoehto on määrittää arvo ympäristömuuttujana (ei suositeltavaa).