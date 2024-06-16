package coop.stlma.tech.protocolsn.keycloak.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserConsentRepresentation {

    protected String clientId;

    protected List<String> grantedClientScopes;

    private Long createdDate;

    private Long lastUpdatedDate;

    @Deprecated
    protected List<String> grantedRealmRoles;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getGrantedClientScopes() {
        return grantedClientScopes;
    }

    public void setGrantedClientScopes(List<String> grantedClientScopes) {
        this.grantedClientScopes = grantedClientScopes;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Deprecated
    public List<String> getGrantedRealmRoles() {
        return grantedRealmRoles;
    }
}
