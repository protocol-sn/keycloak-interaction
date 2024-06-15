package coop.stlma.tech.protocolsn.keycloak.domain;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class UserProfileMetadata {
    private List<UserProfileAttributeMetadata> attributes;
    private List<UserProfileAttributeGroupMetadata> groups;

    public UserProfileMetadata() {

    }

    public UserProfileMetadata(List<UserProfileAttributeMetadata> attributes, List<UserProfileAttributeGroupMetadata> groups) {
        super();
        this.attributes = attributes;
        this.groups = groups;
    }

    public List<UserProfileAttributeMetadata> getAttributes() {
        return attributes;
    }

    public List<UserProfileAttributeGroupMetadata> getGroups() {
        return groups;
    }

    public void setAttributes(List<UserProfileAttributeMetadata> attributes) {
        this.attributes = attributes;
    }

    public UserProfileAttributeMetadata getAttributeMetadata(String name) {
        for (UserProfileAttributeMetadata m : Optional.ofNullable(getAttributes()).orElse(emptyList())) {
            if (m.getName().equals(name)) {
                return m;
            }
        }

        return null;
    }

    public UserProfileAttributeGroupMetadata getAttributeGroupMetadata(String name) {
        for (UserProfileAttributeGroupMetadata m : Optional.ofNullable(getGroups()).orElse(emptyList())) {
            if (m.getName().equals(name)) {
                return m;
            }
        }

        return null;
    }
}
