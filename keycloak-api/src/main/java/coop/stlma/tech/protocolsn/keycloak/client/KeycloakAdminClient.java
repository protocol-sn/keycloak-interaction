package coop.stlma.tech.protocolsn.keycloak.client;

import coop.stlma.tech.protocolsn.keycloak.domain.GroupRepresentation;
import coop.stlma.tech.protocolsn.keycloak.domain.UserRepresentation;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Declarative client for Keycloak Admin API
 * See: <a href="https://www.keycloak.org/docs-api/25.0.0/rest-api/index.html">...</a>
 *
 * NB: the query param does not appear to work when in the client annotation. It has been moved to all the method annotations.
 */
@Client(id="keycloak-admin", path="/admin/realms/")
public interface KeycloakAdminClient {

    /**
     * Returns the number of users that match the given criteria.
     * @param realm realm name (not id!)
     * @param email A String contained in email, or the complete email, if param &quot;exact&quot; is true
     * @param emailVerified whether the email has been verified
     * @param enabled Boolean representing if user is enabled or not
     * @param lastName A String contained in lastName, or the complete lastName, if param &quot;exact&quot; is true
     * @param q A query to search for custom attributes, in the format 'key1:value2 key2:value2'
     * @param search A String contained in username, first or last name, or email. Default search behavior is prefix-based (e.g., foo or foo*). Use foo for infix search and &quot;foo&quot; for exact search.
     * @param username A String contained in username, or the complete username, if param &quot;exact&quot; is true
     * @return Collection of users that meet query criteria
     */
    @Get("{realm}/users/count")
    Mono<HttpResponse<List<Integer>>> countUsers(@PathVariable("realm") String realm,
                                                            @Nullable @QueryValue("email") String email,
                                                            @Nullable @QueryValue("emailVerified") Boolean emailVerified,
                                                            @Nullable @QueryValue("enabled") Boolean enabled,
                                                            @Nullable @QueryValue("firstName") String firstNaId,
                                                            @Nullable @QueryValue("lastName") String lastName,
                                                            @Nullable @QueryValue("q") String q,
                                                            @Nullable @QueryValue("search") String search,
                                                            @Nullable @QueryValue("username") String username);


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
    @Get("{realm}/users")
    Mono<HttpResponse<List<UserRepresentation>>> queryUsers(@PathVariable("realm") String realm,
                                                            @Nullable @QueryValue("briefRepresentation") Boolean briefRepresentation,
                                                            @Nullable @QueryValue("email") String email,
                                                            @Nullable @QueryValue("emailVerified") Boolean emailVerified,
                                                            @Nullable @QueryValue("enabled") Boolean enabled,
                                                            @Nullable @QueryValue("exact") Boolean exact,
                                                            @Nullable @QueryValue("first") Integer first,
                                                            @Nullable @QueryValue("firstName") String firstName,
                                                            @Nullable @QueryValue("idpAlias") Boolean idpAlias,
                                                            @Nullable @QueryValue("idpUserId") String idpUserId,
                                                            @Nullable @QueryValue("lastName") String lastName,
                                                            @Nullable @QueryValue("max") Integer max,
                                                            @Nullable @QueryValue("q") String q,
                                                            @Nullable @QueryValue("search") String search,
                                                            @Nullable @QueryValue("username") String username);

    /**
     * Get representation of the user
     * @param realm realm name (not id!)
     * @param id user id
     * @return The requested user
     */
    @Get("{realm}/users/{id}")
    Mono<HttpResponse<UserRepresentation>> getUser(@PathVariable("realm") String realm,
                                                   @PathVariable("id") String id);

    /**
     * Update the user
     * @param realm realm name (not id!)
     * @param id user id
     * @param user UserRepresentation (optional)
     * @return 200 OK if success, otherwise 400
     */
    @Put("{realm}/users/{id}")
    Mono<HttpResponse<Void>> updateUser(@PathVariable("realm") String realm,
                                        @PathVariable("id") String id,
                                        @Body UserRepresentation user);

    /**
     * Create a new user Username must be unique.
     * @param realm realm name (not id!)
     * @param user UserRepresentation (optional)
     * @return 200 OK if success, otherwise 400
     */
    @Post("{realm}/users")
    Mono<HttpResponse<Void>> createUser(@PathVariable("realm") String realm,
                                        @Body UserRepresentation user);

    /**
     * Add a user to a group
     * @param realm realm name (not id!)
     * @param userId user id
     * @param groupId group id
     * @return 204 if success
     */
    @Put("{realm}/users/{userId}/groups/{groupId}")
    Mono<HttpResponse<Void>> addUserToGroup(@PathVariable("realm") String realm,
                                            @PathVariable("userId") String userId,
                                            @PathVariable("groupId") String groupId);

    /**
     * Remove a user from a group
     * @param realm realm name (not id!)
     * @param userId user id
     * @param groupId group id
     * @return 204 if success
     */
    @Delete("{realm}/users/{userId}/groups/{groupId}")
    Mono<HttpResponse<Void>> removeUserFromGroup(@PathVariable("realm") String realm,
                                                 @PathVariable("userId") String userId,
                                                 @PathVariable("groupId") String groupId);

    /**
     * Get group hierarchy. Only name and id are returned. subGroups are only returned when using the search or q parameter. If none of these parameters is provided, the top-level groups are returned without subGroups being filled.
     *
     * @param realm realm name (not id!)
     * @param briefRepresentation Boolean which defines whether brief representations are returned (default: false)
     * @param exact Boolean which defines whether the params &quot;search&quot; must match exactly or not
     * @param first The position of the first result to be returned (pagination offset).
     * @param max The maximum number of results that are to be returned. Defaults to 10
     * @param populateHierarchy Will subgroups be included in response
     * @param q A query to search for custom attributes, in the format 'key1:value2 key2:value2'
     * @param search A String representing either an exact group name or a partial name
     * @return Collection of groups that meet query criteria
     */
    @Get("{realm}/groups")
    Mono<HttpResponse<List<GroupRepresentation>>> queryGroups(@PathVariable("realm") String realm,
                                                              @Nullable @QueryValue("briefRepresentation") Boolean briefRepresentation,
                                                              @Nullable @QueryValue("exact") Boolean exact,
                                                              @Nullable @QueryValue("first") Integer first,
                                                              @Nullable @QueryValue("max") Integer max,
                                                              @Nullable @QueryValue("populateHierarchy") Boolean populateHierarchy,
                                                              @Nullable @QueryValue("q") String q,
                                                              @Nullable @QueryValue("search") String search);

    /**
     * Update group, ignores subgroups.
     *
     * @param realm realm name (not id!)
     * @param id group id
     * @param groupRepresentation GroupRepresentation(optional)
     * @return 200 OK if success
     */
    @Put("{realm}/groups/{id}")
    Mono<HttpResponse<Void>> updateGroup(@PathVariable("realm") String realm,
                                         @PathVariable("id") String id,
                                         @Body GroupRepresentation groupRepresentation);

    /**
     *
     * @param realm realm name (not id!)
     * @param id group id
     * @return the requested group
     */
    @Get("{realm}/groups/{id}")
    Mono<HttpResponse<GroupRepresentation>> getGroup(@PathVariable("realm") String realm,
                                                     @PathVariable("id") String id);

    /**
     * create or add a top level realm groupSet or create child.
     * This will update the group and set the parent if it exists. Create it and set the parent if the group doesn’t exist.
     * @param realm realm name (not id!)
     * @param groupRepresentation GroupRepresentation(optional)
     * @return 200 OK if success
     */
    @Post("{realm}/groups")
    Mono<HttpResponse<Void>> createGroup(@PathVariable("realm") String realm,
                                         @Body GroupRepresentation groupRepresentation);

    /**
     * Get groups for a user
     * @param realm realm name (not id!)
     * @param id user id
     * @param briefRepresentation Boolean which defines whether brief representations are returned (default: false)
     * @param first The position of the first result to be returned (pagination offset).
     * @param max The maximum number of results that are to be returned. Defaults to 10
     * @param search A String representing either an exact group name or a partial name
     * @return Collection of groups that meet query criteria and of which this user is a member
     */
    @Get("{realm}/users/{id}/groups")
    Mono<HttpResponse<List<GroupRepresentation>>> getUserGroups(@PathVariable("realm") String realm,
                                                                @PathVariable("id") String id,
                                                                @Nullable @QueryValue("briefRepresentation") Boolean briefRepresentation,
                                                                @Nullable @QueryValue("first") Integer first,
                                                                @Nullable @QueryValue("max") Integer max,
                                                                @Nullable @QueryValue("search") String search);
}
