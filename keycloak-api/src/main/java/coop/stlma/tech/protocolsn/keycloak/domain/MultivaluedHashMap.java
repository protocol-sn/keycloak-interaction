package coop.stlma.tech.protocolsn.keycloak.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("serial")
public class MultivaluedHashMap<K, V> extends HashMap<K, List<V>> implements MultivaluedMap<K, V>
{
    public MultivaluedHashMap() {
    }

    public MultivaluedHashMap(Map<K, List<V>> map) {
        if (map == null) {
            throw new IllegalArgumentException("Map can not be null");
        }
        putAll(map);
    }


    public MultivaluedHashMap(MultivaluedHashMap<K, V> config) {
        addAll(config);
    }
}
