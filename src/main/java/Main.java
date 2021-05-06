import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.GenericSignatureFormatError;
import java.nio.file.FileStore;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static Sheets sheetsService;
    private static String name = "google sheets ex";
    private static String id = "1Ij02Y_GKUHW8gLqDluOg44nPk3D9-BJd8NiL9mPC8vc";

    private static Credential authorize() throws IOException, GenericSignatureFormatError, GeneralSecurityException {
        InputStream in = Main.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),secrets,scopes).setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline").build();

        Credential credential = new AuthorizationCodeInstalledApp(flow,new LocalServerReceiver()).authorize("user");
        return credential;
    }


}
