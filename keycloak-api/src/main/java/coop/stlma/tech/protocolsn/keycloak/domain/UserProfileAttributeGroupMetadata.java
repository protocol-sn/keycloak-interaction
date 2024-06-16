package coop.stlma.tech.protocolsn.keycloak.domain;

import java.util.Map;

public class UserProfileAttributeGroupMetadata {

    private String name;
    private String displayHeader;
    private String displayDescription;
    private Map<String, Object> annotations;

    public UserProfileAttributeGroupMetadata() {
    }

    public UserProfileAttributeGroupMetadata(String name, String displayHeader, String displayDescription, Map<String, Object> annotations) {
        this.name = name;
        this.displayHeader = displayHeader;
        this.displayDescription = displayDescription;
        this.annotations = annotations;
    }

    public String getName() {
        return name;
    }

    public String getDisplayHeader() {
        return displayHeader;
    }


    public String getDisplayDescription() {
        return displayDescription;
    }

    public Map<String, Object> getAnnotations() {
        return annotations;
    }
}
