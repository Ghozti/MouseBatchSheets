package Sheets;

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
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.GenericSignatureFormatError;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class SheetsMain {
    private static Sheets sheetsService;
    private static String name = "test";
    private static String id = "1XF_HoV6HJhxrd3yKWG0sMzIU5wMU2EBQ2pUbLkAg3sQ";

    private static Credential authorize() throws IOException, GenericSignatureFormatError, GeneralSecurityException {
        InputStream in = SheetsMain.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),secrets,scopes).setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline").build();

        Credential credential = new AuthorizationCodeInstalledApp(flow,new LocalServerReceiver()).authorize("user");
        return credential;
    }

    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),credential).setApplicationName(name).build();
    }


    public static List<List<Object>> getValues() throws GeneralSecurityException, IOException {
        sheetsService = getSheetsService();
        String range = "Sheet1!A:C";

        ValueRange response = sheetsService.spreadsheets().values().get(id,range).execute();

        List<List<Object>> values = response.getValues();
        return values;
    }

    public static void addToSheet(String problem, String sol) throws GeneralSecurityException, IOException {

        ValueRange appendToBody = new ValueRange().setValues(Arrays.asList(Arrays.asList(problem,sol)));

        AppendValuesResponse response = getSheetsService().spreadsheets().values()
                .append(id,"sheet1",appendToBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();
    }

    public static void main(String...args) throws GeneralSecurityException, IOException {

        if(getValues() == null || getValues().isEmpty()){
            System.out.println("no data");
        }else {
            for (List row : getValues()) {
                System.out.printf("%s, %s\n", row.get(0),row.get(1));
            }
        }
    }
}