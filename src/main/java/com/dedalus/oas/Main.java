package com.dedalus.oas;

import org.apache.commons.cli.*;
import org.apache.commons.cli.help.HelpFormatter;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException {
        Options options = new Options();
        Option oasHostOption = Option.builder("o")
                .longOpt("oas-host")
                .hasArg()
                .required()
                .desc("The OAS's host. Make sure that there is a route from your testing machine to this host.")
                .get();
        options.addOption(oasHostOption);

        try {
            OasChecker oasChecker = buildOasChecker();
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            String oasHost = cmd.getOptionValue(oasHostOption);
            if (oasChecker.acceptsTokenWithInvalidSignature(oasHost)) {
                System.out.println(oasHost + " DOES ACCEPT tokens with signature.");
            } else {
                System.out.println(oasHost + " does NOT accept tokens with invalid signature.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            HelpFormatter formatter = HelpFormatter.builder().get();
            formatter.printHelp("java -jar oas-checker", "", options, "", true);
        }
    }

    private static OasChecker buildOasChecker() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new OasChecker(new OasMonitorClient(HttpUtils.trustEveryoneClient()));
    }
}

