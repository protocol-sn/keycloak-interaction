# keycloak-interaction

### To extport a realm
1. Open the keycloak container in docker
2. Navigate to the exec tag
3. Enter `./opt/keycloak/bin/kc.sh export --users realm_file --dir /tmp`
4. Navigate to the Files tab
5. Open the `tmp` folder. Docker desktop may resist this as it loads the new files.
	1. If this problem persists try clicking on the folder, holding down the right arrow key, and then right-clicking on the file.
6. Right-click the `social-network-ecosystem-realm.json` file and save it.
7. Save it to the keycloak-interaction repo at `/keycloak/ecosystem.json`, overwriting that file.
