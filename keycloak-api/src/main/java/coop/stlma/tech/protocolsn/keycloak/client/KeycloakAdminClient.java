package coop.stlma.tech.protocolsn.keycloak.client;

import coop.stlma.tech.protocolsn.keycloak.domain.UserRepresentation;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Declarative client for Keycloak Admin API
 * See: <a href="https://www.keycloak.org/docs-api/25.0.0/rest-api/index.html">...</a>
 */
@Client(id="keycloak-admin", path="/admin/realms/{realm}")
public interface KeycloakAdminClient {

    /**
     * Get users Returns a stream of users, filtered according to query parameters.
     * @param realm realm name (not id!)
     * @param briefRepresentation Boolean which defines whether brief representations are returned (default: false)
     * @param email A String contained in email, or the complete email, if param &quot;exact&quot; is true
     * @param emailVerified whether the email has been verified
     * @param enabled Boolean representing if user is enabled or not
     * @param exact Boolean which defines whether the params &quot;last&quot;, &quot;first&quot;, &quot;email&quot; and &quot;username&quot; must match exactly
     * @param first Pagination offset
     * @param firstName A String contained in firstName, or the complete firstName, if param &quot;exact&quot; is true
     * @param idpAlias The alias of an Identity Provider linked to the user
     * @param idpUserId The userId at an Identity Provider linked to the user
     * @param lastName A String contained in lastName, or the complete lastName, if param &quot;exact&quot; is true
     * @param max Maximum results size (defaults to 100)
     * @param q A query to search for custom attributes, in the format 'key1:value2 key2:value2'
     * @param search A String contained in username, first or last name, or email. Default search behavior is prefix-based (e.g., foo or foo*). Use foo for infix search and &quot;foo&quot; for exact search.
     * @param username A String contained in username, or the complete username, if param &quot;exact&quot; is true
     * @return Collection of users that meet query criteria
     */
    @Get("/users")
    Mono<HttpResponse<List<UserRepresentation>>> queryUsers(@PathVariable("realm") String realm,
                                                            @Nullable @QueryValue("briefRepresentation") Boolean briefRepresentation,
                                                            @Nullable @QueryValue("email") String email,
                                                            @Nullable @QueryValue("emailVerified") Boolean emailVerified,
                                                            @Nullable @QueryValue("enabled") Boolean enabled,
                                                            @Nullable @QueryValue("exact") Boolean exact,
                                                            @QueryValue("first") Integer first,
                                                            @Nullable @QueryValue("firstName") String firstName,
                                                            @Nullable @QueryValue("idpAlias") Boolean idpAlias,
                                                            @Nullable @QueryValue("idpUserId") String idpUserId,
                                                            @Nullable @QueryValue("lastName") String lastName,
                                                            @QueryValue("max") Integer max,
                                                            @Nullable @QueryValue("q") String q,
                                                            @Nullable @QueryValue("search") String search,
                                                            @Nullable @QueryValue("username") String username);

    /**
     * Get representation of the user
     * @param realm realm name (not id!)
     * @param id user id
     * @return The requested user
     */
    @Get("/users/{id}")
    Mono<HttpResponse<UserRepresentation>> getUser(@PathVariable("realm") String realm,
                                                   @PathVariable("id") String id);

    /**
     * Update the user
     * @param realm realm name (not id!)
     * @param id user id
     * @param user UserRepresentation (optional)
     * @return 200 OK if success, otherwise 400
     */
    @Put("/users/{id}")
    Mono<HttpResponse<Void>> updateUser(@PathVariable("realm") String realm,
                                        @PathVariable("id") String id,
                                        @Body UserRepresentation user);

    /**
     * Create a new user Username must be unique.
     * @param realm realm name (not id!)
     * @param user UserRepresentation (optional)
     * @return 200 OK if success, otherwise 400
     */
    @Post("/users")
    Mono<HttpResponse<Void>> createUser(@PathVariable("realm") String realm,
                                        @Body UserRepresentation user);
}
