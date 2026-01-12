package com.dedalus.oas;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class OasChecker {

    private static final String TOKEN_WITH_INVALID_SIGNATURE =
            "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOblN2WTlwR05WUUoyS2s3RkM2Q1Nna0M5eXVnQVBvLUZnNzRQbEUyMktZIn0" +
                    "." +
                    "eyJleHAiOjE3Njc5NjIyNTIsImlhdCI6MTc2Nzk2MDQ1MiwianRpIjoiZTM2NWE3NzItMTY4Mi00YWYwLWE3YTMtMWNjZGVkM2M0OTc5IiwiaXNzIjoiaHR0cHM6Ly9lb2Y1MjYwYS1wcm94eS5vcmJpcy5kZWRhbHVzLmNvbS9hdXRoL3JlYWxtcy9vcmJpcyIsImF1ZCI6Imh5ZG1lZGlhIiwic3ViIjoiZjoxNDRmZTE5ZS1lZTM1LTRiOWMtYjE1OC0wZTM1NjUxZTQxM2U6MTAwMDAiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJvcmJpcy11LXdlYmNsaWVudCIsInNlc3Npb25fc3RhdGUiOiI0YmMwN2ZjNy02NDkwLTQ1ZDMtOGE5MS02OTY1ZjQ4NTZkYzEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiT1JCSVNVU0VSIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiI0YmMwN2ZjNy02NDkwLTQ1ZDMtOGE5MS02OTY1ZjQ4NTZkYzEiLCJvcmJpc19lbXBsb3llZV9pZCI6MCwib3JiaXNfcHJhY3RpdGlvbmVyX2lkIjoiMTAwMDEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm9yYmlzX3VzZXJfaWQiOjEwMDAwLCJuYW1lIjoiT1JCSVMtaW50ZXJuIE9SQklTLWludGVybiIsIm9yYmlzX2ZhY2lsaXR5X2lkIjoxLCJvcmJpc19zaWduaW5nX2xldmVsIjoiMTAiLCJsYW5ndWFnZSI6ImZyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2NodWx1bmciLCJnaXZlbl9uYW1lIjoiT1JCSVMtaW50ZXJuIiwiZmFtaWx5X25hbWUiOiJPUkJJUy1pbnRlcm4ifQ" +
                    "." +
                    "IF_THIS_IS_A_VALID_SIGNATURE_THEN_SOMETHING_IS_HORRIBLY_WRONG";

    private final OasMonitorClient oasMonitorClient;

    public OasChecker(OasMonitorClient oasMonitorClient) {
        this.oasMonitorClient = oasMonitorClient;
    }

    public boolean acceptsTokenWithInvalidSignature(String oasHost) throws IOException {
        HttpResponse httpResponse = oasMonitorClient.getResourcesInfo(oasHost, "Bearer " + TOKEN_WITH_INVALID_SIGNATURE);
        EntityUtils.consume(httpResponse.getEntity());
        return httpResponse.getStatusLine().getStatusCode() == 200;
    }
}
