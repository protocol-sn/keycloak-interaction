## Keycloak custom event listener: 

This is adapted with only slight changes from https://github.com/cevheri/keycloak-custom-event-listener

## To use
Ensure `src/main/resources/EndpointsList.txt` has the endpoints that will be listening for events.
`mvn clean package` to make the plugin jar
Docker compose for the keycloak server will look in the target directory here.

## TODO
Some sort of management tool that will allow runtime updating of target endpoints.
Convert to gradle?
Code cleanup. This is a tech demo and it shows.
