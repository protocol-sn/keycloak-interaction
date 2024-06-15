package coop.stlma.tech.protocolsn.keycloak.domain;

import java.util.Map;

public class UserProfileAttributeMetadata {
    private String name;
    private String displayName;
    private boolean required;
    private boolean readOnly;
    private Map<String, Object> annotations;
    private Map<String, Map<String, Object>> validators;
    private String group;
    private boolean multivalued;

    public UserProfileAttributeMetadata() {

    }

    public UserProfileAttributeMetadata(String name, String displayName, boolean required, boolean readOnly, String group, Map<String, Object> annotations,
                                        Map<String, Map<String, Object>> validators, boolean multivalued) {
        this.name = name;
        this.displayName = displayName;
        this.required = required;
        this.readOnly = readOnly;
        this.annotations = annotations;
        this.validators = validators;
        this.group = group;
        this.multivalued = multivalued;
    }

    public String getName() {
        return name;
    }

    /**
     * @return display name, either direct string to display, or construct for i18n like <code>${i18nkey}</code>
     */
    public String getDisplayName() {
        return displayName;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public String getGroup() {
        return group;
    }

    /**
     * Get info about attribute annotations loaded from UserProfile configuration.
     */
    public Map<String, Object> getAnnotations() {
        return annotations;
    }

    /**
     * Get info about validators applied to attribute.
     *
     * @return map where key is validatorId and value is map with configuration for given validator (loaded from UserProfile configuration)
     */
    public Map<String, Map<String, Object>> getValidators() {
        return validators;
    }

    public void setMultivalued(boolean multivalued) {
        this.multivalued = multivalued;
    }

    public boolean isMultivalued() {
        return multivalued;
    }
}
