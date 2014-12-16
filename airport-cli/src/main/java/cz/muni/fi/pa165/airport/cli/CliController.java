package cz.muni.fi.pa165.airport.cli;

import cz.muni.fi.pa165.airport.api.dto.BaseDTO;
import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.cli.rest.RestClient;
import cz.muni.fi.pa165.airport.cli.utils.JsonUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.util.ArrayList;

/**
 * @author Mariia Shevchenko
 */
public class CliController {

    /**
     * Server URL to connect to
     */
    private static final String SERVER_URL = "http://localhost:8080/pa165/rest";

    //
    // OPTIONS
    //
    private static final String OPT_ENTITY = "entity";
    private static final String OPT_GET = "get";
    private static final String OPT_LIST = "list";
    private static final String OPT_DELETE = "delete";
    private static final String OPT_CREATE = "create";
    private static final String OPT_UPDATE = "update";
    private static final String OPT_HELP = "help";

    //
    // Output controllers
    //
    private static void err(String message) {
        System.err.println(message);
    }

    private static void out(String message) {
        System.out.println(message);
    }

    private static void out(Object message) {
        System.out.println(message);
    }

    private static void help(final Options options) {
        new HelpFormatter().printHelp("airport --entity=<entity> [other_opts]", options);
    }

    public static void main(String[] args) {

        RestClient restClient = new RestClient(SERVER_URL);

        CommandLineParser parser = new GnuParser();

        Options options = new Options();
        options.addOption(OPT_ENTITY, true, "Entity name on which all other operations will be done. MANDATORY!");
        options.addOption(OPT_LIST, false, "List all items");
        options.addOption(OPT_GET, true, "Fetch one item by ID");
        options.addOption(OPT_DELETE, true, "Delete item identified by ID");
        options.addOption(OPT_CREATE, true, "Create new item by providing JSON data");
        options.addOption(OPT_UPDATE, true, "Update item by providing JSON data");
        options.addOption(OPT_HELP, false, "Show this help");


//        String[] args2 = new String[]{ "--entity=plane", "--delete=208" };
//        String[] args2 = new String[]{ "--entity=plane", "--create={\"name\":\"ABC\",\"type\":\"AK\",\"capacity\":4}" };
//        String[] args2 = new String[]{ "--entity=plane", "--update={\"id\":212,\"name\":\"DEF\",\"type\":\"AK47\",\"capacity\":12}" };
//        String[] args2 = new String[]{ "--entity=plane", "--update={\"id12,\"name\":\"Andrejek\",\"type\":\"AK47\",\"capacity\":12}" };
//        String[] args2 = new String[]{ "--entity=plane", "--list" };
        String[] args2 = new String[]{ "--entity=planes", "--get=s" };

        try {
            CommandLine line = parser.parse( options, args2 );

            if (!line.hasOption(OPT_ENTITY)) {
                err("Entity is not specified");
                help(options);
                System.exit(1);
            }

            Class clazz = determineClass(line.getOptionValue(OPT_ENTITY));

            // GET ALL
            if (line.hasOption(OPT_LIST)) {
                ArrayList all = (ArrayList) restClient.getAll(clazz);
                if (all.isEmpty()) {
                    out("No values found. Try to create some.");
                } else {
                    for (Object object : all) {
                        out(object);
                    }
                }
            }
            // GET BY ID
            else if (line.hasOption(OPT_GET)) {
                BaseDTO baseDTO = restClient.get(getLongValue(line.getOptionValue(OPT_GET)), clazz);

                if (hasErrors(baseDTO)) {
                    printErrors(baseDTO);
                } else {
                    out(baseDTO);
                }
            }
            // DELETE BY ID
            else if (line.hasOption(OPT_DELETE)) {
                BaseDTO baseDTO = restClient.delete(getLongValue(line.getOptionValue(OPT_GET)), clazz);

                if (hasErrors(baseDTO)) {
                    printErrors(baseDTO);
                } else {
                    out("Success!");
                }
            }
            // CREATE
            else if (line.hasOption(OPT_CREATE)) {
                String json = line.getOptionValue(OPT_CREATE);
                BaseDTO baseDTO = restClient.create((BaseDTO) JsonUtils.fromJson(json, clazz), clazz);

                if (hasErrors(baseDTO)) {
                    printErrors(baseDTO);
                } else {
                    out("Success!");
                }
            }
            // UPDATE
            else if (line.hasOption(OPT_UPDATE)) {
                String json = line.getOptionValue(OPT_UPDATE);
                BaseDTO baseDTO = restClient.update((BaseDTO) JsonUtils.fromJson(json, clazz), clazz);

                if (hasErrors(baseDTO)) {
                    printErrors(baseDTO);
                } else {
                    out("Success!");
                }
            }
        } catch (Exception e) {
            err("Failed to execute: " + e.getMessage());
            help(options);
            System.exit(1);
        } finally {
            restClient.shutdown();
        }
    }

    private static boolean hasErrors(BaseDTO baseDTO) {
        return !baseDTO.getErrorCodes().isEmpty();
    }

    /**
     * Print errors from baseDTO
     */
    private static void printErrors(BaseDTO baseDTO) {
        if (hasErrors(baseDTO)) {
            err("One or more errors occurred:");
            for (String error : baseDTO.getErrorCodes()) {
                err("\t - " + error);
            }
            System.exit(1);
        }
    }

    /**
     * Get long value from string, throw RuntimeException if has wrong format
     */
    private static Long getLongValue(String optionValue) throws NumberFormatException{
        try {
            return new Long(optionValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException("ID has to be Long number. Input was " + optionValue);
        }
    }

    /**
     * Determine class by entity name
     */
    private static Class determineClass(String entity) {
        if ("plane".equals(entity)) {
            return PlaneDTO.class;
        } else if ("steward".equals(entity)) {
            return StewardDTO.class;
        } else {
            throw new IllegalStateException("Unexpected entity type = " + entity);
        }
    }
}
